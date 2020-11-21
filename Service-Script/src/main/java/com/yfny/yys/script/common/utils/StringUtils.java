package com.yfny.yys.script.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 字符串工具类
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public class StringUtils {

    /**
     * 分隔字符串
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return
     */
    public static String[] spilt(String str, String separator) {
        //去除前后空格
        str = trim(str);
        return str.split(separator);
    }

    /**
     * 去除前后空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串是否为空
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 连接字符串
     *
     * @param separator
     * @param strs
     * @return
     */
    public static String join(String separator, String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
            sb.append(separator);
        }
        return sb.toString();
    }

    /**
     * 格式化
     *
     * @param str  需要格式化的字符串
     * @param strs 参数
     * @return 格式化好的字符串
     */
    public static String format(String str, String... strs) {
        if (strs == null || strs.length <= 0 || isBlank(str)) {
            return str;
        }
        for (int i = 0; i < strs.length; i++) {
            str = str.replaceAll("\\{" + (i + 1) + "\\}", strs[i]);
        }
        return str;
    }


    public static String print(Map<String, String> params) {
        StringBuffer  sb = new StringBuffer();
        if (params != null && params.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static String print(List<String[]> params) {
        StringBuffer  sb = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (String[] param : params) {
                sb.append(StringUtils.join(" ", param));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static String print(ArrayList<String> params) {
        StringBuffer sb = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (String param : params) {
                sb.append(param);
                sb.append(System.lineSeparator());
            }

        }
        return sb.toString();
    }
}
