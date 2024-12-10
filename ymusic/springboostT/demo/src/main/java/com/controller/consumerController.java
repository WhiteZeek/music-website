package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.domain.consumer;
import com.service.consumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//前端用户控制类
@RestController
@RequestMapping("/consumer")
public class consumerController 
{
    @Autowired
    private consumerService cm;

    // 添加前端用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addconsumer(HttpServletRequest request) 
    {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phone = request.getParameter("phone").trim(); 
        String mail = request.getParameter("mail").trim(); 
        String birth = request.getParameter("birth").trim();
        String intro = request.getParameter("intro").trim();
        String location = request.getParameter("location").trim();
        String pic = request.getParameter("pic").trim(); 
        
        if (username == null || username.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "用户名不能为空");
            return jsonObject;
        }

        consumer consumer1 = cm.getByUsername(username);
        if (consumer1 != null) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "用户名已存在");
            return jsonObject;
        }

        if (password == null || password.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "密码不能为空");
            return jsonObject;
        }

        //转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 保存到前端用户的对象中
        consumer c = new consumer();
        c.setUsername(username);
        c.setName(name);
        c.setPassword(password);
        c.setSex(new Integer(sex));
        c.setPhone(phone);
        c.setMail(mail);
        c.setBirth(birthDate);
        c.setIntro(intro);
        c.setLocation(location);
        c.setPic(pic);

        boolean flag = cm.insert(c);//调用consumerService类
        if (flag) 
        { 
            jsonObject.put("code", 1);
            jsonObject.put("message", "添加成功！");
            return jsonObject;
        }
        jsonObject.put("code", 0);
        jsonObject.put("message", "添加失败！");
        return jsonObject;
    }

    //修改前端用户
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateconsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim(); //id
        String username = request.getParameter("username").trim();
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim(); 
        String phone = request.getParameter("phone").trim();
        String mail = request.getParameter("mail").trim();
        String birth = request.getParameter("birth").trim();
        String intro = request.getParameter("intro").trim();
        String location = request.getParameter("location").trim();

        if (username.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "用户名不能为空！");
            return jsonObject;
        }
        if (password.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "密码不能为空！");
            return jsonObject;
        }

        // 转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 保存到前端用户的对象中
        consumer c = new consumer();
        c.setId(new Integer(id));
        c.setUsername(username);
        c.setName(name);
        c.setPassword(password);
        c.setSex(new Integer(sex));
        c.setPhone(phone);
        c.setMail(mail);
        c.setBirth(birthDate);
        c.setIntro(intro);
        c.setLocation(location);

        boolean flag = cm.update(c);
        if (flag) {
            jsonObject.put("code", 1);
            jsonObject.put("message", "修改成功！");
            return jsonObject;
        }
        jsonObject.put("code", 0);
        jsonObject.put("message", "修改失败！");
        return jsonObject;
    }

    // 删除前端用户
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteconsumer(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = cm.delete(Integer.parseInt(id));
        return flag;
    }

    // 根据id查询
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return cm.selectByPrimaryKey(Integer.parseInt(id));
    }

    // 所有用户
    @RequestMapping(value = "/allConsumer", method = RequestMethod.GET)
    public Object allconsumer(HttpServletRequest request) {
        //System.out.println(cm.allconsumer().get(0).getUsername());
        return cm.allconsumer();
    }

    // 更新头像
    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile picFile, @RequestParam("id") int id) 
    {
        JSONObject jsonObject = new JSONObject();
        if (picFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "文件上传失败！");
            return jsonObject;
        }
        // 文件名=用户id_原文件名
        String fileName = Integer.toString(id)+"_"+picFile.getOriginalFilename();
        // 文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"/src/main/resources/static/picImages";
        //若路径不存在则创建
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        // System.out.println(fileName);
        
        // System.out.println("file://"+System.getProperty("user.dir").replaceAll("\\\\", "/"));
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        // 存储到数据库里的相对文件地址
        String PicPath = "/picImages/" + fileName;
        try {
            picFile.transferTo(dest);
            consumer c = new consumer();
            c.setId(id);
            c.setPic(PicPath);
            boolean flag = cm.update(c);
            if (flag) {
                jsonObject.put("code", 1);
                jsonObject.put("message", "上传成功！");
                jsonObject.put("pic", PicPath);
                return jsonObject;
            }
            jsonObject.put("code", 0);
            jsonObject.put("message", "上传失败！");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "上传失败！" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim(); // 账号
        String password = request.getParameter("password").trim(); // 密码
        if (username.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "用户名不能为空！");
            return jsonObject;
        }
        if (password.equals("")) {
            jsonObject.put("code", 0);
            jsonObject.put("message", "密码不能为空！");
            return jsonObject;
        }

        // 保存到前端用户的对象中
        consumer c = new consumer();
        c.setUsername(username);
        c.setPassword(password);
        boolean flag = cm.verifyPassword(username, password)>0?true:false;
        if (flag) { // 验证成功
            jsonObject.put("code", 1);
            jsonObject.put("message", "登录成功！");
            jsonObject.put("userMsg", cm.getByUsername(username));
            return jsonObject;
        }
        jsonObject.put("code", 0);
        jsonObject.put("message", "用户名或密码错误！");
        return jsonObject;
    }
}
