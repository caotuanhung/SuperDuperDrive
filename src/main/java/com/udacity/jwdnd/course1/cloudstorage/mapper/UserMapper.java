package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {

    @Insert(value = "INSERT INTO tbl_user (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insertOne(User user);

    @Select(value = "SELECT * FROM tbl_user WHERE id = #{id}")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    public User selectOneById(Integer id);

    @Delete(value = "DELETE FROM tbl_user WHERE id = #{id}")
    public void deleteOneById(Integer id);

    @Select(value = "SELECT * FROM tbl_user WHERE username = #{username}")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    User selectOneByUsername(String username);
}
