package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert(value = "INSERT INTO tbl_note(title, description, user_id) VALUES (#{title}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertOne(Note note);

    @Select(value = "SELECT * FROM tbl_note WHERE user_id = #{userId} AND id = #{id}")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    Note selectOneByUserIdAndId(Integer userId, Integer id);

    @Update(value = "UPDATE tbl_note SET title = #{title}, description = #{description} WHERE user_id = #{userId} AND id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer updateOne(Note note);

    @Delete(value = "DELETE FROM tbl_note WHERE user_id = #{userId} AND id = #{id}")
    void deleteOneByUserIdAndId(Integer userId, Integer id);

    @Select(value = "SELECT * FROM tbl_note WHERE user_id = #{userId} ORDER BY id DESC")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    List<Note> selectAllByUserIdOrderByIdDesc(Integer userId);

    @Select(value = "SELECT * FROM tbl_note WHERE username = #{username} ORDER BY id DESC")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    List<Note> selectAllByUsernameOrderByIdDesc(String username);
}
