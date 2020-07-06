package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class hiController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }


    //添加用户
    @RequestMapping("/adduser")
    public String adduser(HttpServletRequest request, Map<String,Object> map)
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //判断重复用户名,先查询
        User user = userMapper.getuser(username);
        if(user!=null)     //查询到该用户，不能注册
        {
            map.put("msg","repeat");   //不能注册，在login页面输出repeat
        }
        else
        {
            userMapper.adduser(username,password);
            map.put("msg1","register");  //注册成功，在login页面输出register
        }
        return "login";
    }

    //删除用户
    @RequestMapping("/deleteuser")
    public String deleteuser(HttpServletRequest request,Map<String,Object> map)
    {
        String username = request.getParameter("username");

        //判断有误该用户
        User user = userMapper.getuser(username);
        if(user!=null)     //查询到该用户，删除
        {
            userMapper.deleteuser(username);   //删除
            map.put("msg2","delete");
        }
        else      //查不到，输出null
        {
            map.put("msg3","null");
        }
        return "login";
    }


    //修改用户信息
    @RequestMapping("/updateuser")
    public String updateuser(HttpServletRequest request,Map<String,Object> map)
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //判断有误该用户
        User user = userMapper.getuser(username);
        if(user!=null)     //查询到该用户，修改
        {
            userMapper.updateuser(username,password);//修改
            map.put("msg4","update");
        }
        else             //查不到，输出null
        {
            map.put("msg5","null");
        }

        return "login";
    }

    //查询用户（得到一个用户）
    @RequestMapping("/getuser")
    public String getuser(HttpServletRequest request,Map<String,Object> map)
    {
        String username = request.getParameter("username");
        User user = userMapper.getuser(username);

        //判断有误该用户
        if(user!=null)     //查询到该用户，输出
        {
            System.out.println(user); //输出
            map.put("msg6","get");
        }
        else             //查不到，输出null
        {
            map.put("msg7","null");
        }
        return "login";
    }



}
