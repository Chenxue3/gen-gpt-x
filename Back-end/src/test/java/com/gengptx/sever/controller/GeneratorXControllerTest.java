package com.gengptx.sever.controller;

import com.gengptx.sever.entity.GeneratorX;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author: xueshanChen
 * @ClassName: GeneratorXControllerTest
 * @description：test
 * @version: v1.0
 */

public class GeneratorXControllerTest {

    //create unit test for GeneratorXController
    @Test
    public void testGeneratorXController(){
        GeneratorXController generatorXController = new GeneratorXController();
        //test api getTree
        GeneratorX generator = new GeneratorX();
        generator.setSy_depth(3);
        generator.setSy_num_tree(1);
        generator.setSy_num_goal(1);
        generator.setSy_num_plan(1);
        generator.setSy_num_action(1);
        generator.setSy_num_var(5);
        generator.setSy_num_selected(2);
        generator.setSy_prob_leaf(0.5);
        generator.setSeed(1);
        generator.setSy_num_group(2);
        MockHttpServletRequest request = new MockHttpServletRequest();
        generatorXController.getTree(generator,request);


    }
}
