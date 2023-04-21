package com.gengptx.sever.controller;

import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.entity.Generator;
import com.gengptx.sever.service.GeneratorService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xueshanChen
 * @ClassName : GeneratorController
 * @description：
 * @version: v1.0
 */

@RestController
public class GeneratorController {
    /**
     *
     * @param generator require a set of parameters
     * @return the generated GPT or error message
     */
    @CrossOrigin
    @RequestMapping(value = "/setPara", method = RequestMethod.POST)
    public JSONObject getTree(@RequestBody Generator generator, HttpServletRequest request){

        /**
         * the generator
         */
        GeneratorService generatorService = new GeneratorService();
        JSONObject jsonObject = generatorService.generateGpt(request,generator.getSeed(), generator.getSy_depth(), generator.getSy_num_tree(), generator.getSy_num_goal(), generator.getSy_num_plan(), generator.getSy_num_action(), generator.getSy_num_var(), generator.getSy_num_selected(), generator.getSy_prob_leaf());

        return jsonObject;
    }




}
