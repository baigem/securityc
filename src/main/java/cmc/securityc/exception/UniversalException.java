package cmc.securityc.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 通用异常工具
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/07/07
 */
@SuppressWarnings("unused")
public class UniversalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UniversalException(String message) {
        super(message, null, false, false);
    }

    /**
     * 不为空
     *
     * @param obj     obj
     * @param message 消息
     */
    @SuppressWarnings("unused")
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new UniversalException(message);
        }
    }

    /**
     * 不为空
     *
     * @param collection 集合
     * @param message    消息
     */
    @SuppressWarnings("unused")
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new UniversalException(message);
        }
    }

    @SuppressWarnings("unused")
    public static void notEmpty(String str, String message) {
        if (!StringUtils.hasText(str)) {
            throw new UniversalException(message);
        }
    }

    /**
     * 状态
     *
     * @param obj     obj
     * @param message 消息
     */
    @SuppressWarnings("unused")
    public static void state(boolean obj, String message) {
        if (!obj) {
            throw new UniversalException(message);
        }
    }

    /**
     * @param code
     * @param message
     */
    @SuppressWarnings("unused")
    public static void state(int code, String message) {
        throw new UniversalException(message);
    }
}
