package cmc.securityc.aspect;

import cmc.securityc.annotation.RequiresLogin;
import cmc.securityc.annotation.RequiresPermissions;
import cmc.securityc.auth.AuthUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequiresLoginAspect implements Ordered {
    /**
     * 必须登录
     */
    @Around("@annotation(ignore)")
    public Object innerAround(ProceedingJoinPoint point, RequiresLogin ignore) throws Throwable {
        AuthUtil.checkLogin();
        return point.proceed();
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
