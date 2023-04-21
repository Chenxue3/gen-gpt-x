package com.gengptx.sever.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: xueshanChen
 * @ClassName : ResourceControllerTest
 * @description: unit test for ResourceController
 * @version: v1.0
 */

public class ResourceControllerTest {
    /**
     * test resource controller
     */
    @Test
    public void testFileDownLoadBlocksWorldXML() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadBlocksWorldXML(httpServletResponse);
    }

    @Test
    public void testFileDownLoadBlocksWorldJSON() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadBlocksWorldJSON(httpServletResponse);
    }

    @Test
    public void testFileDownLoadCraftWorldXML() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadCraftWorldXML(httpServletResponse);
    }

    @Test
    public void testFileDownLoadCraftWorldJSON() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadCraftWorldJSON(httpServletResponse);
    }

    @Test
    public void testFileDownLoadJSON() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadJSON(httpServletResponse);
    }

    @Test
    public void testFileDownLoadXML() {
        ResourceController resourceController = new ResourceController();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        resourceController.fileDownLoadXML(httpServletResponse);
    }


}
