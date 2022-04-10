package cn.my.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Controller
@RequestMapping("/jsp")
public class FDController {
    @RequestMapping("showDownFiles")
    public String show(HttpServletRequest request, Model model) {
        String realpath = request.getSession().getServletContext().getRealPath("/files");
        System.out.println("realpath" + realpath);
        File dir = new File(realpath);
        File files[] = dir.listFiles();
        ArrayList<String> fileName = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            fileName.add(files[i].getName());
        }
        model.addAttribute("files", fileName);
        return "ShowDownFiles";
    }

    //执行下载
    @RequestMapping("/fileDown")
    public String down(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) {
        String filePath = null;
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
            filePath = request.getSession().getServletContext().getRealPath("/files");
            response.setHeader("Content-Type", "application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            in = new FileInputStream(filePath + "\\" + fileName);
            out = response.getOutputStream();
            out.flush();
            int aRead = 0;
            byte b[] = new byte[1024];
            while ((aRead = in.read(b)) != -1 && in != null) {
                out.write(b, 0, aRead);
            }
            out.flush();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Success");
        return null;
    }

    public String toUTF8Sting(String str) {
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c); //Unicode码值为 0~255时不做处理
            } else {
                byte b[];
                b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                //转换为%HH的字符串形式
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        if (k < 0) {
                            k &= 255;
                            sb.append("%" + Integer.toHexString(k).toUpperCase());
                        }
                    }
                }
            }
            return sb.toString();
        }
    }
    private static final Log logger = LogFactory.getLog(FUController.class);
}
