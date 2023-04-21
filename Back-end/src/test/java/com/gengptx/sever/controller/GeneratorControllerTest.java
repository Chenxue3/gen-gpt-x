package com.gengptx.sever.controller;

import com.gengptx.sever.entity.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xueshanChen
 * @ClassName : GeneratorControllerTest
 * @description: unit test for GeneratorController
 * @version: v1.0
 */

public class GeneratorControllerTest {
    @Test
    public void testGeneratorController() {
        GeneratorController generatorController = new GeneratorController();
        //generate a Generator
        MockHttpServletRequest request = new MockHttpServletRequest();
        Generator generator = new Generator();
        /**    private int seed;
         //    max depth of the GPT
         private int sy_depth;
         //    number of trees
         private int sy_num_tree;
         //    number of goals in each plan
         private int sy_num_goal;
         //    number of plans for each goal
         private int sy_num_plan;
         //    number of actions
         private int sy_num_action;
         //    number of variables
         private int sy_num_var;
         //    number of selected variables
         private int sy_num_selected;
         //    probability of a node becomes a leaf node
         private double sy_prob_leaf;

         set this values to generator
         * */
        generator.setSy_depth(5);
        generator.setSy_num_tree(1);
        generator.setSy_num_goal(1);
        generator.setSy_num_plan(1);
        generator.setSy_num_action(3);
        generator.setSy_num_var(1);
        generator.setSy_num_selected(1);
        generator.setSy_prob_leaf(0.5);
        generator.setSeed(1);
        generatorController.getTree(generator,request);
    }
}
