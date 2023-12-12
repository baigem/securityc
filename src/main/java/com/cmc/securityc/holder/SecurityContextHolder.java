package com.cmc.securityc.holder;

import cn.hutool.core.convert.Convert;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.cmc.securityc.constant.SecurityConstants;
import com.cmc.securityc.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全上下文持有人
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author cmc
 * &#064;date  2023/06/06
 */
@SuppressWarnings(value = {"unchecked"})
public class SecurityContextHolder {
    /**
     * 线程本地
     */
    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StringUtils.EMPTY : value);
    }

    /**
     * 获取
     *
     * @param key 钥匙
     * @return {@link T}
     */
    public static <T> T get(String key) {
        return StringUtils.cast(getLocalMap().getOrDefault(key, StringUtils.EMPTY));
    }

    /**
     * 获取
     *
     * @param key          钥匙
     * @param defaultValue 默认值
     * @return {@link T}
     */
    public static <T> T get(String key, Object defaultValue) {
        Map<String, Object> map = getLocalMap();
        return StringUtils.cast(map.getOrDefault(key, defaultValue));
    }

    /**
     * 获取本地地图
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    /**
     * 设置本地地图
     *
     * @param threadLocalMap 线程局部映射
     */
    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static <T> T getLoginUser() {
        return (T) Convert.toLong(get(SecurityConstants.LOGIN_USER), null);
    }

    /**
     * 获取用户标识
     *
     * @return {@link Long}
     */
    public static Long getUserId() {
        return Convert.toLong(get(SecurityConstants.USER_ID), 0L);
    }

    /**
     * 设置用户id
     *
     * @param account 账户
     */
    public static void setUserId(String account) {
        set(SecurityConstants.USER_ID, account);
    }

    /**
     * 获取企业id
     */
    public static Long getEnterpriseId() {
        return Convert.toLong(get(SecurityConstants.ENTERPRISE_id), null);
    }

    /**
     * 获取职员id
     *
     * @return {@link Long}
     */
    public static Long getStafferId() {
        return Convert.toLong(get(SecurityConstants.STAFFER_ID), null);
    }

    /**
     * 获取打开id
     */
    public static String getOpenId() {
        return get(SecurityConstants.OPEN_ID);
    }

    /**
     * 设置打开id
     *
     * @param openId 打开id
     */
    public static void setOpenId(String openId) {
        set(SecurityConstants.OPEN_ID, openId);
    }

    /**
     * 移动
     *
     * @return {@link String}
     */
    public static String getPhone() {
        return get(SecurityConstants.MOBILE);
    }

    /**
     * 设置移动
     *
     * @param phone 移动
     */
    public static void setPhone(String phone) {
        set(SecurityConstants.MOBILE, phone);
    }

    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public static String getUserName() {
        return get(SecurityConstants.DETAILS_USERNAME);
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public static void setUserName(String username) {
        set(SecurityConstants.DETAILS_USERNAME, username);
    }

    /**
     * 获取用户标识
     *
     * @return {@link String}
     */
    public static String getUserKey() {
        return get(SecurityConstants.USER_KEY);
    }

    /**
     * 设置用户标识
     *
     * @param userKey 用户密钥
     */
    public static void setUserKey(String userKey) {
        set(SecurityConstants.USER_KEY, userKey);
    }

    /**
     * 获得许可
     *
     * @return {@link String}
     */
    public static String getPermission() {
        return get(SecurityConstants.ROLE_PERMISSION);
    }

    /**
     * 设置权限
     *
     * @param permissions 权限
     */
    public static void setPermission(String permissions) {
        set(SecurityConstants.ROLE_PERMISSION, permissions);
    }

    /**
     * 获取请求
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return get(SecurityConstants.REQUEST, null);
    }

    /**
     * 获得响应
     *
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return get(SecurityConstants.RESPONSE, null);
    }

    /**
     * 移除此次线程中的信息
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }

    /**
     * 获取ruo钥匙
     *
     * @return {@link Object}
     */
    public static String getRuoKey() {
        Long o = get(SecurityConstants.ENTERPRISE_id, null);
        if (o == null) {
            return get(SecurityConstants.USER_ID);
        }
        return o + ":" + get(SecurityConstants.USER_ID);
    }
}
