package com.doubles.bootdemo1;

import com.doubles.bootdemo1.controller.SampleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest 사용X
@WebMvcTest(SampleController.class) // @Controller, @Component, @ControllerAdvice 인식
public class SampleControllerTest {

    @Autowired
    MockMvc mock;   // 컨트롤러 테스트를 위한 객체 주입

    @Test
    public void testSayHello() throws Exception {

        mock.perform(get("/hello"))                                 // 서버 URL 호출
                .andExpect(content().string("Hello World"));    // 결과를 확인

        MvcResult result = mock.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World")).andReturn();

        // 응답 내용 출력
        System.out.println(result.getResponse().getContentAsString());

    }


}
