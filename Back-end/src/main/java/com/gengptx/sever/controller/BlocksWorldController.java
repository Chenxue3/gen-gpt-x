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
    @CrossOrigin
    @GetMapping("/getBlocksWorld")
    public JSONObject getBlocksWorld(HttpServletRequest request){

        BlocksWorldService blocksWorldService = new BlocksWorldService();
        JSONObject jsonObject = blocksWorldService.returnBlocksWorld(request);
        return jsonObject;

    }
}
