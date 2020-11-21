package com.device.materials.enums;

import java.awt.event.InputEvent;

/**
 * 点击动作类型
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public enum ClickTypeEnum {

    /**
     * 左
     */
    LEFT("-l",InputEvent.BUTTON1_MASK),
    /**
     * 右
     */
    RIGHT("-r", InputEvent.BUTTON3_MASK),
    /**
     * 双击
     */
    DOUBLE("-d", InputEvent.BUTTON1_MASK),
    /**
     * 拖拽
     */
    DRAG_DROP("-dd", InputEvent.BUTTON1_MASK),
    /**
     * 滚动滚轮
     */
    ROLLING_WHEEL("-rw", InputEvent.BUTTON3_DOWN_MASK);

    private int code;
    private String value;

    ClickTypeEnum(String value, int code) {
        this.code = code;
        this.value = value;
    }

    public static ClickTypeEnum getTypeEnum(String value) {
        ClickTypeEnum[] values = ClickTypeEnum.values();
        for (ClickTypeEnum typeEnum : values) {
            if (value.equalsIgnoreCase(typeEnum.getValue())) {
                return typeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
