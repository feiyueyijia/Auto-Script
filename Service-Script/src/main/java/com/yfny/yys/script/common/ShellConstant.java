package com.yfny.yys.script.common;

/**
 * Shell文件系统常量定义
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public class ShellConstant {

    /**
     * robot执行休眠时间
     */
    public static final int ROBOT_DELAY = 100;

    /**
     * 标记常量定义
     */
    public enum MARK {
        /**
         * 类型
         */
        TYPE("TYPE"),
        /**
         * 名称
         */
        NAME("NAME"),
        /**
         * 描述
         */
        DESC("DESC"),
        /**
         * 版本
         */
        VERSION("VERSION"),
        /**
         * 参数
         */
        PARAMS("PARAMS"),
        /**
         * 注释
         */
        ANNOTATE("*"),
        /**
         * 输入占位符
         */
        PLACEHOLDER_INPUT("$"),
        /**
         * 输出占位符
         */
        PLACEHOLDER_OUTPUT("&");

        String value;

        MARK(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 标记类型字段
     */
    public interface MARK_TYPE {
        /**
         * 脚本
         */
        String SHELL = "SHELL";
        /**
         * 模板
         */
        String TEMPLATE = "TEMPLATE";
    }

    /**
     * 逻辑常量定义
     */
    public interface LOGIC {
        /**
         * 逻辑标记符
         */
        String FLAG = "#";
        /**
         * 块代码
         */
        String BLOCK = "#BLOCK";
        /**
         * 字段
         */
        String FIELD = "#FIELD";
    }

    /**
     * cmd常量定义
     */
    public interface CMD {
        /**
         * 点击
         */
        String CLICK = "CLICK";
        /**
         * 移动
         */
        String MOVE = "MOVE";
        /**
         * 输入
         */
        String INPUT = "INPUT";

        /**
         * 等待
         */
        String WAIT = "WAIT";

        /**
         * 清空
         */
        String CLEAR = "CLEAR";
        /**
         * 拷贝
         */
        String COPY = "COPY";
        /**
         * 键盘
         */
        String KV = "KV";
    }


}
