package com.yfny.yys.script.instruction;


import com.device.materials.enums.ClickTypeEnum;
import com.device.materials.pojo.ClickPojo;
import com.yfny.yys.script.common.ShellConstant;
import com.yfny.yys.script.common.exceptions.ShellException;

import java.awt.*;
import java.util.Map;

/**
 * 点击命令
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public class ClickCmd implements ICmd<ClickPojo> {

    @Override
    public Map<String, String> execute(Robot robot, ClickPojo clickPojo) throws ShellException {
        ClickTypeEnum type = clickPojo.getType();
        switch (type) {
            //单击
            case LEFT:
                //右键
            case RIGHT:
                click(robot, type, 1);
                break;
            //双击
            case DOUBLE:
                click(robot, type, 2);
                break;
            //拖拽
            case DRAG_DROP:
                clickDragDrop(robot, type, clickPojo.getX(), clickPojo.getY());
                break;
            //滚动滚轮
            case ROLLING_WHEEL:
                clickRollingWheel(robot, type, clickPojo.getX());
                break;
            default:
                throw ShellException.valueOf("{1} Instruction not support,type:{2}", ShellConstant.CMD.CLICK, type.getValue());
        }
        return null;
    }

    /**
     * 滚动滚轮
     *
     * @param robot
     * @param type
     * @param wheelAmt 移动鼠标滚轮，负值表示向上，正值向下
     */
    private void clickRollingWheel(Robot robot, ClickTypeEnum type, int wheelAmt) {
        robot.mouseWheel(wheelAmt);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    /**
     * 拖拽
     *
     * @param robot
     * @param type
     * @param x
     * @param y
     */
    private void clickDragDrop(Robot robot, ClickTypeEnum type, int x, int y) {
        robot.mousePress(type.getCode());
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.mouseMove(x, y);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.mouseRelease(type.getCode());
        robot.delay(ShellConstant.ROBOT_DELAY);
    }


    /**
     * 鼠标点击
     *
     * @param robot
     * @param type
     */
    private void click(Robot robot, ClickTypeEnum type, int count) {
        for (int i = 0; i < count; i++) {
            robot.mousePress(type.getCode());
            robot.delay(ShellConstant.ROBOT_DELAY);
            robot.mouseRelease(type.getCode());
            robot.delay(ShellConstant.ROBOT_DELAY);
        }
    }
}
