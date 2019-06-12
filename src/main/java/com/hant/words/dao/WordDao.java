package com.hant.words.dao;

import com.hant.words.bean.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


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
 * @date ：Created in 2019/5/30 11:07
 * @description：
 */
@Mapper
public interface WordDao{
    @Select("SELECT * from wordlist LIMIT 0,2000")
    List<Word> findAll();
    @Select("select * from wordlist where id = #{id}")
    Word findById(Integer id);
    @Select("SELECT * from wordlist LIMIT #{page},10000")
    List<Word> findByLimit(Integer page);
}
