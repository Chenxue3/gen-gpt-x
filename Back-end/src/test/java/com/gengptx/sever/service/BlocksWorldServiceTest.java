package com.gengptx.sever.service;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: BlocksWorldServiceTest
 * @author: xueshanChen
 * @description: test of blocks world
 * @version: v1.0
 */

public class BlocksWorldServiceTest {
    /**
     * test blocks world service
//     */

    @Test
    public void testBlocksWorldService() {
        BlocksWorldService blocksWorldService = new BlocksWorldService();
        blocksWorldService.returnBlocksWorld();
    }

}
