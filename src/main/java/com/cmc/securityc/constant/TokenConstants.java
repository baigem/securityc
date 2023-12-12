package com.cmc.securityc.constant;

/**
 * Token的Key常量
 *
 * @author cmc
 */
public class TokenConstants {
    /**
     * 令牌自定义标识
     */
    public static final String AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    public static final String PREFIX = "Bearer ";

    /**
     * 令牌秘钥
     */
    public final static String SECRET = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 签名者
     */
    public final static String SIGNER = "HS512";

}
