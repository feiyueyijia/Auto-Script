package com.yfny.yys.script.event;

import com.yfny.yys.script.common.utils.RobotUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 请填写类描述
 * Author jisongZhou
 * Date  2020/3/6
 */
public class RobotTest {
    public static void main(String[] args) throws AWTException {
        //keyboodPress();
        //mouseClick();
        //automaticFullScreen();

        RobotUtils.showWindow();
        //RobotUtils.selectWindow("");
        //RobotUtils.name();
    }

    public static void keyboodPress() throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();
        robot.delay(5000);
        int a = 0;
        while (true) {

            robot.keyPress(KeyEvent.VK_Y);
            robot.keyRelease(KeyEvent.VK_Y);
            a = Math.abs(random.nextInt()) % 100 + 50;
            robot.delay(a);

            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_B);
            a = Math.abs(random.nextInt()) % 100 + 50;
            robot.delay(a);

            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            a = Math.abs(random.nextInt()) % 100 + 50;
            robot.delay(a);

            robot.keyPress(KeyEvent.VK_U);
            robot.keyRelease(KeyEvent.VK_U);

            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            a = Math.abs(random.nextInt()) % 2000 + 1000;
            System.out.println(a);
            robot.delay(a);
        }
    }

    public static void mouseClick() throws AWTException {
        Robot robot = new Robot();
        Random random = new Random();
        int a = 0;
        robot.delay(3000);

        robot.mouseMove(1200, 700);
        a = Math.abs(random.nextInt())%100+50;
        robot.delay(a);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        a = Math.abs(random.nextInt())%50+50;
        robot.delay(a);

        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    /**
     * 模拟按下键盘单个按键，比如文档下一页：PgDn，上一页是PgUp等
     *
     * @param keycode：按键的值,如：KeyEvent.VK_PAGE_UP
     */
    public static final void pressSingleKeyByNumber(int keycode) {
        try {
            /** 创建自动化测试对象  */
            Robot robot = new Robot();
            /**按下按键*/
            robot.keyPress(keycode);
            /**松开按键*/
            robot.keyRelease(keycode);
            /**可以稍作延时处理*/
            robot.delay(500);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟按下键盘的"Ctrl+*"，比如文档起始位置：Ctrl+Home，结束位置：Ctrl+End
     * 注意：Ctrl键是：KeyEvent.VK_CONTROL
     *
     * @param keycode：按键的值,如End键:KeyEvent.VK_END、Home键：KeyEvent.VK_HOME
     */
    public static final void pressCtrlAndSingleKeyByNumber(int keycode) {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(keycode);
            robot.keyRelease(keycode);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(100);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    /**
     * 模拟用户单击屏幕指定区域,默认单击屏幕最中央
     * 如果是右键，请使用：InputEvent.BUTTON3_DOWN_MASK
     * @param x：x坐标
     * @param y：y坐标
     */
    public static final void clickScreenByXY(Integer x, Integer y) {
        try {
            /**创建工具包对象*/
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            /**创建自动化对象*/
            Robot robot = new Robot();
            /**利用工具包对象获取屏幕分辨率*/
            if (x == null) {
                x = toolkit.getScreenSize().width / 2;
            }
            if (y == null) {
                y = toolkit.getScreenSize().height / 2;
            }
            /**
             * 移动鼠标到指定位置
             * 然后按下鼠标左键，再松开，模拟单击操作
             */
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(100);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动将鼠标移动到指定的位置
     * 如果参数x与y为null,则默认将鼠标放在屏幕右侧中间隐藏
     *
     * @param x：x坐标 ,左上角 为0----设定值超过屏幕分辨率也没关系
     * @param y：y坐标 ,左上角 为0----设定值超过屏幕分辨率也没关系
     */
    public static final void mouseMoveToXY(Integer x, Integer y) {
        try {
            /**创建工具包对象*/
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            /**创建自动化对象*/
            Robot robot = new Robot();
            /**利用工具包对象获取屏幕分辨率*/
            if (x == null) {
                x = toolkit.getScreenSize().width;
            }
            if (y == null) {
                y = toolkit.getScreenSize().height / 2;
            }
            /**
             * 移动鼠标到指定位置
             *  robot.delay(100);延时100毫秒
             */
            robot.mouseMove(x, y);
            robot.delay(100);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自动化-最大化窗口，模拟按 Alt+ 空格 + X
     * 切记当同时按有顺序的组合键时，应该在间隔处添加细微的延时，否则容易引起失败(因为按键速度太快，导致混乱)
     */
    public static void automaticFullScreen() {
        try {
            /**clickScreenByXY(null, null);*/
            //LogWmxUtils.writeLine("Automatic full screen start.....");
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.delay(200);
            robot.keyPress(KeyEvent.VK_X);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_X);
            robot.delay(200);
            //LogWmxUtils.writeLine("Automatic full screen finish.....");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
