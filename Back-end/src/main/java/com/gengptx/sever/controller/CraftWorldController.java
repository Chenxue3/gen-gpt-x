package com.gengptx.sever.controller;

import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.service.CraftWorldService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xueshanChen
 * @ClassName : CraftWorldController
 * @description：
 * @version: v1.0
 */

@RestController
@CrossOrigin
public class CraftWorldController {

    /**
     * get the GPT of craft world
     * @return generated GPT in json format of Blocks world
     */
    @GetMapping("/getCraftWorld")
    public JSONObject getCraftWorld(){
        CraftWorldService craftWorldService = new CraftWorldService();
        JSONObject jsonObject = craftWorldService.createCraftWorld();
        return  jsonObject;
    }
}
