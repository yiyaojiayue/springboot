package com.example.springbootfile07.web;

/*
  实现文件上传
 */

import com.example.springbootfile07.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {

    @Value("${uploadFolder}")
    private String  fileDir;



    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){  return "picUpload";}

    @ResponseBody
    @PostMapping("/upload")
    public Object upload(MultipartFile fileUpload){
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        try {
            System.out.println("------->>"+fileDir);
            File dir = new File(fileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File upload_file = new File(fileDir + fileName);
            fileUpload.transferTo(upload_file);
            return new Message(0,"success to upload");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(-1,"fail to upload");
        }
    }
}
