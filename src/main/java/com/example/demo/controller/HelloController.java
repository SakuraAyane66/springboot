package com.example.demo.controller;

import com.example.demo.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Date;

/**
 * @author CTL
 * <p>测试hello </p>
 * 创建日期：2020-10-14 00:32
 */
@RestController
public class HelloController {
    //测试Druid
    @Autowired
    private DataSource dataSource;
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request,@RequestParam("token") String token){
        //从请求头中获取jwt
        //String jwt = request.getHeader("Authorization");
        //改为了ajax中data携带token
        String jwt = token;
        //解析jwt
        if (JwtUtil.parse(jwt) == null) {
            return "请先登录";
        }
        //执行业务逻辑，解析完成之后
        Date nowtime = new Date(); //返回的是当前时间
        System.out.println("nowtime is "+nowtime);
        return "Hello sakura!";
    }
    //测试druid
    @RequestMapping("/test1")
    public String test1(){
        System.out.println(dataSource);
        return "test1";
    }
    //测试pathVaribale注解，解析路径中的{}
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") int userId){
        System.out.println("映射的userID为"+userId);
        return "sakura";
    }

}
