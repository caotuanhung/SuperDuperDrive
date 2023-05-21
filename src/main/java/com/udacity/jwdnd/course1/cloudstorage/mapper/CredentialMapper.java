package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert(value = "INSERT INTO tbl_credential (url, username, password, key, user_id) VALUES (#{url}, #{username}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertOne(Credential credential);

    @Select(value = "SELECT * FROM tbl_credential WHERE user_id = #{userId} AND id = #{id}")
    @Results(value = {@Result(property = "userId", column = "user_id")})
    Credential selectOneByUserIdAndId(Integer userId, Integer id);

    @Update(value = "UPDATE tbl_credential SET url = #{url}, username = #{username}, password = #{password} WHERE user_id = #{userId} AND id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer updateOne(Credential credential);

    @Delete(value = "DELETE FROM tbl_credential WHERE user_id = #{userId} AND id = #{id}")
    void deleteOneByUserIdAndId(Integer userId, Integer id);

    @Select(value = "SELECT * FROM tbl_credential WHERE user_id = #{userId} ORDER BY id DESC")
    List<Credential> selectAllByUserIdOrderByIdDesc(Integer userId);

    @Select(value = "SELECT * FROM tbl_credential WHERE username = #{userId} ORDER BY id DESC")
    List<Credential> selectAllByUsernameOrderByIdDesc(String username);
}
