package com.yfny.yys.script.instruction;

import com.yfny.yys.script.common.exceptions.ShellException;

import java.awt.*;
import java.util.Map;

/**
 * 命令接口
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public interface ICmd<R> {

    Map<String, String> execute(Robot robot, R r) throws ShellException;
}
