package com.hant.words.dao;

import com.hant.words.bean.domain.Role;
import com.hant.words.bean.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　＞　　　＜　┃
 * ┃　　　　　　　┃
 * ┃...　⌒　...　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃
 * ┃　　　┃  神兽保佑
 * ┃　　　┃  代码无bug
 * ┃　　　┃
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author ：Hant
 * @date ：Created in 2019/6/6 9:29
 * @description：
 */
@Mapper
@Component
public interface UserDao {
    @Select("select * from user where username = #{name}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "roles",many = @Many(
                    select = "com.hant.words.dao.UserDao.listByUserId"
            ))
    })
    User findUserByName(String name);

    @Select("select r.* from role r\n" +
            "join user_roles ur on  r.id = ur.roles_id \n" +
            "join user u on u.id = ur.user_id \n" +
            "where u.id = #{id}")
    List<Role> listByUserId(Integer id);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "id", property = "roles",many = @Many(
                    select = "com.hant.words.dao.UserDao.listByUserId"
            ))
    })
    User findUserById(Integer id);
}
