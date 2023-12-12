package com.cmc.securityc.service;

import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
public interface SecuritycCacheService {

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit);

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key);

    /**
     * 删除单个对象
     */
    public boolean deleteObject(final String key);

}
