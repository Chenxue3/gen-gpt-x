package com.gengptx.sever.controller;

import com.alibaba.fastjson.JSONObject;
import com.gengptx.sever.entity.Resource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.http.HttpHeaders;
import java.util.logging.ErrorManager;

/**
 * @author ：xueshanChen
 * @ClassName : ResourceController
 * @description：
 * @version: v1.0
 */
@RestController
@CrossOrigin(origins = "*")
public class ResourceController {

    @RequestMapping(value = "/downloadxml", method = RequestMethod.GET)
    public String fileDownLoadXML(HttpServletResponse response){
        File file = new File("D:/FYP/Development/out" +'/'+ "xmlGPT.xml");
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


    @RequestMapping(value = "/downloadjson", method = RequestMethod.GET)
    public String fileDownLoadJSON(HttpServletResponse response){
        File file = new File("D:/FYP/Development/out" +'/'+ "jsonGPT.json");
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


    @RequestMapping(value = "/downloadBlocksWorldJSON", method = RequestMethod.GET)
    public String fileDownLoadBlocksWorldJSON(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("D:/FYP/Development/out" +'/'+ "blocksworld.json");
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


    @RequestMapping(value = "/downloadBlocksWorldXML", method = RequestMethod.GET)
    public String fileDownLoadBlocksWorldXML(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("D:/FYP/Development/out" +'/'+ "blocksworld.xml");
        if(!file.exists()){
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


    @RequestMapping(value = "/downloadCraftWorldXML", method = RequestMethod.GET)
    public String fileDownLoadCraftWorldXML(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("D:/FYP/Development/out" +'/'+ "craftworld.xml");
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


    @RequestMapping(value = "/downloadCraftWorldJSON", method = RequestMethod.GET)
    public String fileDownLoadCraftWorldJSON(HttpServletResponse response){
        System.out.println("start download the json file of blocks world");
        File file = new File("D:/FYP/Development/out" +'/'+ "craftworld.json");
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
