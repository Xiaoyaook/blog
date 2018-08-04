package com.ziliang.blog.controller;

import com.ziliang.blog.entity.User;
import com.ziliang.blog.result.CodeMsg;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录Controller
 * 由于用户只有我一个人，所以一些地方目前先写死
 *
 */
@Api(value = "登录controller", tags = {"登录操作"})
@RestController
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RequestMapping("/login")
public class LoginController {

    private static final String username = "xiaoyaook";
    private static final String password = "123456";

    @ApiOperation(value = "执行登录操作", notes = "接收username和password进行登录操作")
    @PostMapping("/do_login")
    public Result<CodeMsg> doLogin(HttpServletRequest request, HttpServletResponse response) {
        //登录
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        User user =new User(username,password);
        if(username.equals(LoginController.username) && password.equals(LoginController.password)){
            // 下面这种使用HttpSession，一次登录仅在当前Session有效
            // request.getSession().setAttribute("user",user);

            // 生成Cookie，这里强行嵌入业务逻辑了，先这么写，后边登录验证麻烦了，单独写一个service
            String token = UUIDUtil.uuid();
            Cookie cookie = new Cookie("user", token);
            cookie.setMaxAge(60 * 60 * 24 * 2); // 两天
            cookie.setPath("/");
            response.addCookie(cookie);
        }else{
            return Result.error(CodeMsg.BIND_ERROR);
        }
        return Result.success(CodeMsg.SUCCESS);
    }
}
