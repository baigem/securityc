package com.cmc.securityc.service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis 工具类
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
@SuppressWarnings(value = {"rawtypes"})
public interface SecuritycCacheService {
    

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value);

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
     * 设置位
     *
     * @param key    钥匙
     * @param offset 偏移
     * @param value  值
     */
    public boolean setBit(final String key, long offset, final boolean value);

    public boolean getBit(final String key, long offset);

    public <T> List<T> range(String key, int start, int end);

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout);

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit);

    /**
     * 获取有效时间
     *
     * @param key Redis键
     * @return 有效时间
     */
    public long getExpire(final String key);

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key);

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key);

    public <T> T getCacheObject(final String key, Class<T> clazz);

    /**
     * 删除单个对象
     */
    public boolean deleteObject(final String key);

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     */
    public long deleteObject(final Collection collection);

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList);

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(final String key);

    /**
     * 获得缓存的set
     */
    public <T> Set<T> getCacheSet(final String key);

    /**
     * 缓存Map
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap);

    /**
     * 获得缓存的Map
     */
    public <T> Map<String, T> getCacheMap(final String key);

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value);

    /**
     * 往Hash中删除
     *
     * @param key  Redis键
     * @param hKey Hash键
     */
    public void deleteCacheMapKey(final String key, final String hKey);

    /**
     * 往Hash中删除
     *
     * @param key  Redis键
     * @param hKey Hash键
     */
    public void deleteCacheMapKey(final String key, final String[] hKey);
    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey);

    /**
     * 获取多个Hash中的数据, 注意对应索引位置空指针判断
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys);

    public <T> boolean putIfAbsentMap(final String key, final String hKey, final T value);

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern);

    /**
     * 如果没有，则设置
     * 不存在则设置值
     *
     * @param key      钥匙
     * @param value    值
     * @param timeout  超时
     * @param timeUnit 时间单位
     * @return boolean
     */
    public <T> boolean setIfAbsent(final String key, final T value, final Long timeout, final TimeUnit timeUnit);

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return long
     */
    public long incr(String key, long delta);

    /**
     * 搜索hashmap中的key
     *
     * @param key     键
     * @param pattern 搜索map中key的正则表达式
     * @param size    搜索的条数
     * @return {@link List}<{@link T}>
     */
    public <T> List<T> searchMap(String key, String pattern, Long size);
}
