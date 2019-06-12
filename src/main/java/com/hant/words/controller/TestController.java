package com.hant.words.controller;

import com.hant.words.service.UserService;
import com.hant.words.service.impl.UserServiceImpl;
import com.hant.words.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
 * @date ：Created in 2019/6/4 16:31
 * @description：
 */
@Controller
@ResponseBody
public class TestController {
    @Autowired
    UserDetailsService myUserService;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public JsonResult auth(HttpSession session){
        SecurityContext context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return new JsonResult(200,"获取成功",context.getAuthentication());
    }
    @Cacheable(value = "result",key = "#id")
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public JsonResult findUser(@PathVariable Integer id){
        return  new JsonResult(200,"获取成功",userService.findUserById(id));
    }


    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public JsonResult testUser(){
        return  new JsonResult(200,"有USER权限");
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public JsonResult testAdmin(){
        return  new JsonResult(200,"有ADMIN权限");
    }

    @GetMapping("/tourist")
    @PreAuthorize("hasAuthority('TOURIST')")
    public JsonResult testTourist(){
        return  new JsonResult(200,"有TOURIST权限");
    }
}
