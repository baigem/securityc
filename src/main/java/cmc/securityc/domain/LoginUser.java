package cmc.securityc.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户，此模型不可直接使用，必须进行继承使用
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
@Data
public abstract class LoginUser<K,T>  implements Serializable {
    /**
     * 用户id
     */
    protected K userId;

    /**
     * 用户名
     */
    protected String userName;

    /**
     * 令牌
     */
    protected String token;
    /**
     * 权限组
     */
    protected Set<String> permissions;

    /**
     * 角色组
     */
    protected Set<String> roles;

    /**
     * 原始用户数据
     */
    protected T user;
    /**
     * ip地址
     */
    protected String ipaddr;
    /**
     * 登录时间
     */
    protected long loginTime;
    /**
     * 过期时间
     */
    protected long expireTime;

}
