package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.service.AdminService;

@RestController//返回json格式
public class adminController {
    @Autowired
    private AdminService adminService;

    //判断登录是否成功
    @RequestMapping(value="/admin/login/status",method=RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request,HttpSession session)
    {
        JSONObject jsonObject=new JSONObject();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        boolean flag=adminService.verify(name, password);
        if(flag)
        {
            jsonObject.put("code",1);
            jsonObject.put("message","登陆成功");
            session.setAttribute("name", name);
            return jsonObject;
        }

        jsonObject.put("code",0);
        jsonObject.put("message","登陆失败，请检查用户名或密码是否正确");
        return jsonObject;
    }
}
