package com.gengptx.sever.controller;

import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.service.BlocksWorldService;
import com.gengptx.sever.service.GeneratorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xueshanChen
 * @ClassName : BlocksWorldController
 * @description：
 * @version: v1.0
 */
@RestController
public class BlocksWorldController {
    /**
     * return the blocks world GPT
     * @return generated GPT in json format of Blocks world
     */
    @CrossOrigin
    @GetMapping("/getBlocksWorld")
    public JSONObject getBlocksWorld(){

        BlocksWorldService blocksWorldService = new BlocksWorldService();
        JSONObject jsonObject = blocksWorldService.returnBlocksWorld();
        return jsonObject;

    }
}
