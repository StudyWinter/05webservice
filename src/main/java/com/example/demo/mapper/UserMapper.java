package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //增删改查

    //添加用户
    @Insert("insert into user (username,password) values (#{username},#{password})")
    void adduser(String username,String password);

    //删除用户
    @Delete("delete from user where username=#{username}")
    void deleteuser(String username);

    //修改用户信息
    @Update("update user set password=#{password} where username=#{username}")
    void updateuser(String username,String password);

    //查询用户信息
    @Select("select * from user where username=#{username}")
    User getuser(String username);

    //查询所有用户
    @Select("select * from user")
    List<User> getalluser();



}
