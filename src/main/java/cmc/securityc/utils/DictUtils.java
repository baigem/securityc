package cmc.securityc.utils;

import cmc.securityc.constant.CacheConstants;

/**
 * 字典工具类
 *
 * @author cmc
 */
public class DictUtils {
    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }
}
