package com.gengptx.sever.service;

import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.Test;

/**
 * @ClassName: GeneratorXServiceTest
 * @author: xueshanChen
 * @description: test of generatorX
 * @version: v1.0
 */

public class GeneratorXServiceTest {

        /**
        * test generatorX service
        */
        @Test
        public void testGeneratorXService() {
            GeneratorXService generatorXService = new GeneratorXService();
            //mock a request
            MockHttpServletRequest request = new MockHttpServletRequest();

            generatorXService.generateGpt(request,50,10,10,3,3,5,6,5,0.5, 10);

        }
}
