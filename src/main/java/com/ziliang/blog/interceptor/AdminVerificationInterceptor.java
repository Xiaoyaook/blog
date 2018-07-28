package com.ziliang.blog.interceptor;

import com.ziliang.blog.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * admin验证拦截器
 * 说明：对/admin开头的地址进行拦截，必须经过验证之后才能够访问
 *
 */
@Service
public class AdminVerificationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag;

        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            // 如果用户为空则跳转到login页面(我们不再在后端处理这部分，转由前端处理)
            // 修改sendRedirect方法为getRequestDispatcher，进行服务器端跳转
            // 目的是保证地址栏不改变，这样用户就不知道后台页面的地址
            // request.getRequestDispatcher(request.getContextPath() + "/error.html").forward(request, response);
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

}
