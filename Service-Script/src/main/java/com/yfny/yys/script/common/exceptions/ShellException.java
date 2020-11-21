package com.yfny.yys.script.common.exceptions;

import com.yfny.yys.script.common.utils.StringUtils;

/**
 * Shell异常
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public class ShellException extends Exception {
    /**
     * 异常实体
     */
    Exception ex;


    public ShellException(String message) {
        super(message);
    }

    public static ShellException valueOf(Exception e, String message, String... params) {
        message = StringUtils.format(message, params);
        ShellException ex = new ShellException(message);
        ex.setEx(e);
        return ex;
    }

    public static ShellException valueOf(String message, String... params) {
        return valueOf(null, message, params);
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}
