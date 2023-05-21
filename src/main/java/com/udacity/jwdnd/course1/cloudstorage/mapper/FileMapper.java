package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert(value = "INSERT INTO tbl_file(name, content_type, size, data, user_id) VALUES (#{name}, #{contentType}, #{size}, #{data}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertOne(File file);

    @Select(value = "SELECT * FROM tbl_file WHERE id = #{id}")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    File selectOneById(Integer id);

    @Delete(value = "DELETE FROM tbl_file WHERE id = #{id}")
    void deleteOneById(Integer id);

    @Select("SELECT * FROM tbl_file WHERE user_id = #{userId}")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    List<File> selectAllByUserId(Integer userId);

    @Select("SELECT * FROM tbl_file WHERE user_id = #{userId} ORDER BY id DESC")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    List<File> selectAllByUserIdOrderById(Integer userId);

    @Select(value = "SELECT * FROM tbl_file WHERE user_id = #{userId} AND id = #{id}")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    File selectOneByUserIdAndId(Integer userId, Integer id);

    @Select(value = "SELECT * FROM tbl_file WHERE user_id = #{userId} AND name = #{name}")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    File selectOneByUserIdAndName(Integer userId, String name);

    @Delete(value = "DELETE FROM tbl_file WHERE user_id = #{userId} AND id = #{id}")
    void deleteOneByUserIdAndId(Integer userId, Integer id);

    @Select("SELECT * FROM tbl_file WHERE username = #{username} ORDER BY id DESC")
    @Results(value = {@Result(property = "contentType", column = "content_type"), @Result(property = "userId", column = "user_id")})
    List<File> selectAllByUsernameOrderById(String username);
}
