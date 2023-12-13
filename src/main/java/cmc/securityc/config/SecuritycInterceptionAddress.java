package cmc.securityc.config;

/**
 * securityc拦截地址
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
public interface SecuritycInterceptionAddress {
    /**
     * 获取排除url
     *
     * @return {@link String[]}
     */
    String[] getExcludeUrls();
}
