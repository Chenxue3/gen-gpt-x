package com.gengptx.sever.service;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: CraftWorldServiceTest
 * @author: xueshanChen
 * @description: test of craft world
 * @version: v1.0
 */

public class CraftWorldServiceTest {

    /**
     * test craft world service
     */
    @Test
    public void testCraftWorldService() {
        CraftWorldService craftWorldService = new CraftWorldService();
        craftWorldService.createCraftWorld();
    }
}
