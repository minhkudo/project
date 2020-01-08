/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.interceptor;

import com.dev.vin.demo.config.Auth;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author MinhKudo
 */
public class AuthInterceptor implements HandlerInterceptor {

    // preHandle sẽ được gọi khi có request (config request url ở dưới)
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        // trích xuất method tương ứng với request mapping trong controller
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // tìm trong method có khai báo anotation Auth?
        Auth roleAnnotation = AnnotationUtils
                .findAnnotation(method, Auth.class);
        // nếu có lấy ra thuộc tính role, không trả về null
        Auth.Role role = roleAnnotation != null ? roleAnnotation.role() : null;

        // lấy các thông tin đăng nhập từ session
        HttpSession session = request.getSession();
        boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session
                .getAttribute("isLogin") : false;
        Auth.Role loginRole = session.getAttribute("role") != null ? (Auth.Role) session
                .getAttribute("role") : null;

        // - trường hợp role yêu cầu của method = null bỏ qua interceptor này và
        // chạy bình thường
        // - khác null tức request này chỉ được thực hiên khi đã đăng nhập
        if (role != null) {
            // chưa đăng nhập chuyển hướng sang trang login để đăng nhập
            if (!isLogined) {
                response.sendRedirect("login");
                return false;
            } else {
                // - trường hợp đã login tiến hành kiểm tra role
                // - những trường hợp chỉ yêu cầu login mà không yêu cầu cụ thể
                // role nào thì tất cả các role đều có quyền truy cập
                // - trường hợp yêu cầu cụ thể loại role sau khi đăng nhập thì
                // phải kiểm tra
                // - không thoả mãn điều kiện dưới chuyển hướng sang trang
                // denied
                if (role != Auth.Role.LOGIN && role != loginRole) {
                    response.sendRedirect("deny?url=\""
                            + request.getRequestURL().toString() + "?"
                            + request.getQueryString() + "\"&role=" + role);
                    return false;
                }
            }
        }

        return true;
    }
}
