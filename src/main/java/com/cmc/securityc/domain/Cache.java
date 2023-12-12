package com.cmc.securityc.domain;

import com.cmc.securityc.constant.Constants;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author cmc
 */
@Data
public class Cache<T> implements Serializable {
    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;
    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;
    private static final long serialVersionUID = 1L;
    private int code;

    private String msg;

    private T data;

    public static <T> Cache<T> ok() {
        return restResult(null, SUCCESS, null);
    }

    public static <T> Cache<T> ok(T data) {
        return restResult(data, SUCCESS, null);
    }

    public static <T> Cache<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> Cache<T> fail() {
        return restResult(null, FAIL, null);
    }

    public static <T> Cache<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> Cache<T> fail(T data) {
        return restResult(data, FAIL, null);
    }

    public static <T> Cache<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> Cache<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> Cache<T> restResult(T data, int code, String msg) {
        Cache<T> apiResult = new Cache<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> Boolean isError(Cache<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(Cache<T> ret) {
        return Cache.SUCCESS == ret.getCode();
    }
}
