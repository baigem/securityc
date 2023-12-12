package com.cmc.securityc.aspect;

import com.cmc.securityc.annotation.RequiresPermissions;
import com.cmc.securityc.exception.NotPermissionException;
import com.cmc.securityc.utils.SecurityUtils;
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
        // 企业主 和 允许个人的 不进行拦截
        if(Optional.ofNullable(SecurityUtils.getLoginUser().getPermissions()).map(e->e.contains(permissions.value())).orElse(false)){
            return point.proceed();
        }
        throw new NotPermissionException("无权限访问");
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
