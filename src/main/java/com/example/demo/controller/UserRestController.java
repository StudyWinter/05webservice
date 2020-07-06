package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserMapper userMapper;

    //增删改查

    //添加用户
    @RequestMapping("/restuser/adduser")
    public String adduser(String username,String password)
    {
        //先查找，判断有无该用户
        User user = userMapper.getuser(username);
        if(user!=null)    //即数据库中有该用户，不能添加
        {
            return "fault";
            //return "the user "+username+" has been registered";
        }
        else             //没有该用户，可以添加
        {
            userMapper.adduser(username,password);
            return "ok";
            //return "the user "+username+" added";
        }
    }


    //删除用户
    @RequestMapping("/restuser/deleteuser")
    public String deleteuser(String username)
    {
        //先查找，判断有无该用户
        User user = userMapper.getuser(username);
        if(user!=null)    //即数据库中有该用户，可以删除
        {
            userMapper.deleteuser(username);
            return "delete";
            //return "the user "+username+" has been deleted";
        }
        else
        {
            return "not search user";
        }
    }


    //修改用户信息
    @RequestMapping("/restuser/updateuser")
    public String updateuser(String username,String password)
    {
        //先查找，判断有无该用户
        User user = userMapper.getuser(username);
        if(user!=null)    //即数据库中有该用户，可以修改
        {
            userMapper.updateuser(username,password);
            return "update";
        }
        else
        {
            return "not search user";
        }
    }


    //查找一个用户
    @RequestMapping("/restuser/getuser")
    public User getuser(String username)
    {
        User user = userMapper.getuser(username);
        return user;
    }

    //返回所有用户
    @RequestMapping("/restuser/getalluser")
    public List<User> getalluser()
    {
        List<User> userList = userMapper.getalluser();
        return userList;
    }

}
