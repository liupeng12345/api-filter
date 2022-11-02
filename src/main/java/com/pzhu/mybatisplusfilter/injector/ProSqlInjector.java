package com.pzhu.mybatisplusfilter.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.pzhu.mybatisplusfilter.annotation.SearchMapper;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfo;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfoHelper;
import com.pzhu.mybatisplusfilter.method.SearchMethod;
import org.apache.ibatis.builder.MapperBuilderAssistant;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 在原有注入逻辑上增强,  增强自定义sql
 *
 * @author 75073
 */
public class ProSqlInjector extends DefaultSqlInjector {
    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {
        Class<?> modelClass = ReflectionKit.getSuperClassGenericType(mapperClass, Mapper.class, 0);
        if (modelClass != null) {
            String className = mapperClass.toString();
            Set<String> mapperRegistryCache = GlobalConfigUtils.getMapperRegistryCache(builderAssistant.getConfiguration());
            if (!mapperRegistryCache.contains(className)) {
                TableInfo tableInfo = TableInfoHelper.initTableInfo(builderAssistant, modelClass);
                // 注入普通方法
                List<AbstractMethod> methodList = this.getMethodList(mapperClass, tableInfo);
                if (CollectionUtils.isNotEmpty(methodList)) {
                    // 循环注入自定义方法
                    methodList.forEach(m -> m.inject(builderAssistant, mapperClass, modelClass, tableInfo));
                } else {
                    logger.debug(mapperClass + ", No effective injection method was found.");
                }
                final Method[] declaredMethods = mapperClass.getDeclaredMethods();
                Arrays.stream(declaredMethods).filter(method -> Objects.nonNull(method.getAnnotation(SearchMapper.class))).forEach(method -> {
                    final String methodName = method.getName();
                    final SearchMapper annotation = method.getAnnotation(SearchMapper.class);
                    final Class<?> searchBeanClass = annotation.value();
                    final boolean isCount = methodName.startsWith("count");
                    final SearchBeanInfo searchBeanInfo = SearchBeanInfoHelper.initTableInfo(searchBeanClass);
                    final SearchMethod searchMethod = new SearchMethod(methodName);
                    searchMethod.inject(builderAssistant, searchBeanClass, isCount, modelClass, searchBeanInfo);
                });
                mapperRegistryCache.add(className);
            }
        }
    }

}
