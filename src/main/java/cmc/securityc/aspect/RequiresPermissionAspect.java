package cmc.securityc.aspect;

import cmc.securityc.auth.AuthUtil;
import cmc.securityc.exception.NotPermissionException;
import cmc.securityc.annotation.RequiresPermissions;
import cmc.securityc.utils.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class RequiresPermissionAspect  implements Ordered {
    @Around("@annotation(permissions)")
    public Object innerAround(ProceedingJoinPoint point, RequiresPermissions permissions) throws Throwable {
        AuthUtil.checkPermi(permissions);
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
