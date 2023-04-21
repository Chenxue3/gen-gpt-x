package com.gengptx.sever.controller;

import org.junit.jupiter.api.Test;

/**
 * @author : xueshanChen
 * @ClassName : CraftWorldControllerTest
 * @description: unit test for CraftWorldController
 * @version: v1.0
 */

public class CraftWorldControllerTest {
    @Test
    public void testCraftWorldController() {
        CraftWorldController craftWorldController = new CraftWorldController();
        craftWorldController.getCraftWorld();
    }
}
