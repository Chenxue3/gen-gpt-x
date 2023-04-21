package com.gengptx.sever.controller;

import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.entity.Generator;
import com.gengptx.sever.entity.GeneratorX;
import com.gengptx.sever.service.GeneratorService;
import com.gengptx.sever.service.GeneratorXService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xueshanChen
 * @ClassName : GeneratorController
 * @description：the controller of GenGPT-X generator
 * @version: v1.0
 */

@RestController
public class GeneratorXController {
    /**
     *
     * @param generator require a set of parameters
     * @return the generated GPT or error message
     */
    @CrossOrigin
    @RequestMapping(value = "/setXPara", method = RequestMethod.POST)
    public JSONObject getTree(@RequestBody GeneratorX generator, HttpServletRequest request){

        /**
         * the generator
         */
        GeneratorXService generatorServiceX = new GeneratorXService();
        JSONObject jsonObject = generatorServiceX.generateGpt(request,generator.getSeed(), generator.getSy_depth(), generator.getSy_num_tree(), generator.getSy_num_goal(), generator.getSy_num_plan(), generator.getSy_num_action(), generator.getSy_num_var(), generator.getSy_num_selected(), generator.getSy_prob_leaf(),generator.getSy_num_group());

        return jsonObject;
    }




}
