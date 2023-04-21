package com.gengptx.sever.service;

import org.springframework.mock.web.MockHttpServletRequest;
import org.testng.annotations.Test;

/**
 * @ClassName: GeneratorServiceTest
 * @author: xueshanChen
 * @description: test of generator
 * @version: v1.0
 */

public class GeneratorServiceTest {

        /**
        * test generator service
        */
        @Test
        public void testGeneratorService() {
            GeneratorService generatorService = new GeneratorService();
            MockHttpServletRequest request = new MockHttpServletRequest();
            generatorService.generateGpt(request, 50,10,10,5,5,5,60,20,0.5);

        }
}
