package cmc.securityc.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cmc.securityc.constant.SecurityConstants;
import cmc.securityc.constant.TokenConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Jwt工具类
 *
 * @author cmc
 */
@Slf4j
public class JwtUtils {
    /**
     * 秘密
     */
    public static String secret = TokenConstants.SECRET;
    /**
     * 签名者
     */
    public static JWTSigner signer = JWTSignerUtil.createSigner(TokenConstants.SIGNER, secret.getBytes());

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims) {
        return JWTUtil.createToken(claims, signer);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public static JSONObject parseToken(String token) {
//        try {
        return JWTUtil.parseToken(token).setSigner(signer).getPayloads();
//        } catch (SignatureException e) {
//            GlobalContextHolder.setError("令牌解析失败，令牌已被篡改", e);
//            log.error("令牌解析失败，令牌已被篡改");
//            return null;
//        } catch (MalformedJwtException e) {
//            GlobalContextHolder.setError("令牌格式错误，解析失败", e);
//            log.error("令牌格式错误，解析失败");
//            return null;
//        }
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param token 令牌
     * @return 用户ID
     */
    public static String getUserKey(String token) {
        JSONObject claims = parseToken(token);
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    /**
     * 根据令牌获取用户标识
     *
     * @param claims 身份信息
     * @return 用户ID
     */
    public static String getUserKey(JSONObject claims) {
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    /**
     * 根据令牌获取用户ID
     *
     * @param token 令牌
     * @return 用户ID
     */
    public static String getUserId(String token) {
        JSONObject claims = parseToken(token);
        return getValue(claims, SecurityConstants.USER_ID);
    }

    /**
     * 根据身份信息获取用户ID
     *
     * @param claims 身份信息
     * @return 用户ID
     */
    public static String getUserId(JSONObject claims) {
        return getValue(claims, SecurityConstants.USER_ID);
    }

    /**
     * 根据令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUserName(String token) {
        JSONObject claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    /**
     * 根据身份信息获取用户名
     *
     * @param claims 身份信息
     * @return 用户名
     */
    public static String getUserName(JSONObject claims) {
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }


    /**
     * 获取打开id
     *
     * @param claims 索赔
     * @return {@link String}
     */
    public static String getOpenId(JSONObject claims) {
        return getValue(claims, SecurityConstants.OPEN_ID);
    }

    /**
     * 获取手机号
     *
     * @param claims 索赔
     * @return {@link String}
     */
    public static String getPhone(JSONObject claims) {
        return getValue(claims, SecurityConstants.MOBILE);
    }

    /**
     * 根据身份信息获取键值
     *
     * @param claims 身份信息
     * @param key    键
     * @return 值
     */
    public static String getValue(JSONObject claims, String key) {
        if (claims == null) {
            return null;
        }
        return Convert.toStr(claims.get(key), "");
    }

}
