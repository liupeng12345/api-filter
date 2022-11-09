package com.pzhu.mybatisplusfilter;

import org.apache.catalina.util.URLEncoder;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@MapperScan("com.pzhu.**.mapper")
@AutoConfigureMockMvc
public class MvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void echo() throws Exception {
        final String result = mockMvc.perform(MockMvcRequestBuilders.get("/test")
                        .param("filter", URLEncoder.QUERY.encode("name $sw '名字'", StandardCharsets.UTF_8)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
    }
}
