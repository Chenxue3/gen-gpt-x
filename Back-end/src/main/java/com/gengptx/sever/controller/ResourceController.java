package com.gengptx.sever.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author ：xueshanChen
 * @ClassName : ResourceController
 * @description：
 * @version: v1.0
 */
@RestController
@CrossOrigin(origins = "*")
public class ResourceController {

    /**
     * download XML
     * @param response HttpServletRequest
     * @return the XML file of generated GPT
     */
    @RequestMapping(value = "/downloadxml", method = RequestMethod.GET)
    public String fileDownLoadXML(HttpServletResponse response){
        File file = new File( "xmlGPT.xml");
        if(!file.exists()){
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "xmlGPT.xml" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }


    /**
     * download JSON
     * @param response HttpServletRequest
     * @return the JSON file of generated GPT
     */
    @RequestMapping(value = "/downloadjson", method = RequestMethod.GET)
    public String fileDownLoadJSON(HttpServletResponse response){
        File file = new File("jsonGPT.json");
        if(!file.exists()){
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "jsonGPT.json" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }

    /**
     * download Json file of blocks world
     * @param response HttpServletRequest
     * @return the Json file of generated GPT (Blocks world)
     */
    @RequestMapping(value = "/downloadBlocksWorldJSON", method = RequestMethod.GET)
    public String fileDownLoadBlocksWorldJSON(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("BlocksWorldJSON.json");
        if(!file.exists()){
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "blocksworld.json" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }

    /**
     * download XML file of blocks world
     * @param response HttpServletRequest
     * @return the XML file of generated GPT (Blocks world)
     */
    @RequestMapping(value = "/downloadBlocksWorldXML", method = RequestMethod.GET)
    public String fileDownLoadBlocksWorldXML(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("../../../src/main/resources/static/blocksworld.xml");
        if(!file.exists()){
            System.out.println("not found");
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "blocksworld.xml" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }

    /**
     * download XML file of craft world
     * @param response HttpServletRequest
     * @return the XML file of generated GPT (craft world)
     */
    @RequestMapping(value = "/downloadCraftWorldXML", method = RequestMethod.GET)
    public String fileDownLoadCraftWorldXML(HttpServletResponse response){
        System.out.println("start download the json file of  world");
        File file = new File("craftworld.xml");
        if(!file.exists()){
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "craftworld.xml" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }

    /**
     * download Json file of craft world
     * @param response HttpServletRequest
     * @return the Json file of generated GPT (craft world)
     */
    @RequestMapping(value = "/downloadCraftWorldJSON", method = RequestMethod.GET)
    public String fileDownLoadCraftWorldJSON(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("craftworld.json");
        if(!file.exists()){
            return "the file is not exists" ;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "craftworld.json" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "Fail to download";
        }
        return "success";
    }
}
