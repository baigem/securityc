package cmc.securityc.aspect;

import cmc.securityc.annotation.RequiresRoles;
import cmc.securityc.auth.AuthUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequiresRolesAspect implements Ordered {
    @Around("@annotation(requiresRoles)")
    public Object innerAround(ProceedingJoinPoint point, RequiresRoles requiresRoles) throws Throwable {
        AuthUtil.checkRole(requiresRoles);
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
