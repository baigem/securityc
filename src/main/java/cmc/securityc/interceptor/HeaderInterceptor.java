package cmc.securityc.interceptor;

import cmc.securityc.utils.JwtUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cmc.securityc.auth.AuthUtil;
import cmc.securityc.constant.SecurityConstants;
import cmc.securityc.constant.TokenConstants;
import cmc.securityc.domain.LoginUser;
import cmc.securityc.exception.UniversalCodeException;
import cmc.securityc.holder.SecurityContextHolder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 *
 * @author cmc
 */
@Slf4j
public class HeaderInterceptor implements AsyncHandlerInterceptor {
    @SneakyThrows
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 设置请求体和响应体
        SecurityContextHolder.set(SecurityConstants.REQUEST, request);
        SecurityContextHolder.set(SecurityConstants.RESPONSE, response);
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        if (token == null) {
            log.error(request.getRequestURI());
            throw new UniversalCodeException(HttpStatus.UNAUTHORIZED, "请登录后访问");
        }
        // 解析出数据
        JSONObject claims = JwtUtils.parseToken(token);
        if (claims == null) {
            log.error(request.getRequestURI());
            throw new UniversalCodeException(HttpStatus.UNAUTHORIZED, "请登录后访问");
        }
        // 设置用户id
        SecurityContextHolder.setUserId(JwtUtils.getUserId(claims));
        // 设置用户名称
        SecurityContextHolder.setUserName(JwtUtils.getUserName(claims));
        // 设置开放id
        SecurityContextHolder.setOpenId(JwtUtils.getOpenId(claims));
        // 设置手机号
        SecurityContextHolder.setPhone(JwtUtils.getPhone(claims));
        Thread.currentThread().getContextClassLoader().loadClass(AuthUtil.class.getName());
        LoginUser<?,?> loginUser = AuthUtil.getLoginUser(token);
        if (ObjectUtil.isNotNull(loginUser)) {
            // 校验是否过期
            AuthUtil.verifyLoginUserExpire(loginUser);
            // 设置登录用户信息
            SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
        }
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        // 清空数据
        SecurityContextHolder.remove();
    }

}
