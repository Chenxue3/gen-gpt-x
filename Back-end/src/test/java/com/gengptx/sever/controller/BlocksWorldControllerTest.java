package com.gengptx.sever.controller;

import org.junit.jupiter.api.Test;

/**
 * @author ：xueshanChen
 * @ClassName : BlocksWorldControllerTest
 * @description：
 * @version: v1.0
 */

public class BlocksWorldControllerTest {
    //unit test for BlocksWorldController
    @Test
    public void testBlocksWorldController() {
        BlocksWorldController blocksWorldController = new BlocksWorldController();
        blocksWorldController.getBlocksWorld();

    }
}
