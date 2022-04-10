package cn.my.controller;

import cn.my.pojo.FileDomain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/jsp")
public class FUController {
    @RequestMapping("/getFileUpload")
    public String getFileUpload() {
        return "fileUpload";
    }
    //单文件上传
    @RequestMapping("/fileUpload")
    public String onFileUpload(@ModelAttribute FileDomain fileDomain, HttpServletRequest request) {
        System.out.println(fileDomain);
        System.out.println("进入fileDomain");
        String realpath = request.getSession().getServletContext().getRealPath("/files");
        System.out.println("服务器存放的realpath："+realpath);
        String filename = fileDomain.getMyFile().getOriginalFilename();
        System.out.println("文件名为："+filename);
        File targetFile = new File(realpath,filename);
        if (!targetFile.exists()) {
            System.out.println("开始创建文件夹");
            targetFile.mkdirs();
        } else {
            System.out.println("文件夹已存在");
        }
        try {
            fileDomain.getMyFile().transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "showFile";
    }

    private static final Log logger = LogFactory.getLog(FUController.class);
}
