package com.hant.words.service;

import com.hant.words.bean.domain.Role;
import com.hant.words.bean.domain.User;
import com.hant.words.dao.UserDao;
import com.hant.words.excpetion.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
 * @date ：Created in 2019/6/6 9:18
 * @description：
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(userDao==null){
            try {
                throw new MyException("Dao is empty");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        User user = userDao.findUserByName(s);
        if (user == null){
            throw new UsernameNotFoundException(s + "User Not Find");
        }
        System.out.println(s+"登陆成功");
        Collection<GrantedAuthority> authList = getAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authList);
    }

    private Collection<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        for (Role role:
             roles) {
            authList.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authList;
    }
}
