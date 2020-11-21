package com.yfny.yys.script.event;

import java.awt.*;
import java.awt.event.*;

/**
 * 请填写类描述
 * Author jisongZhou
 * Date  2020/9/25
 */
public class AwtEventTest {

    public static void main(String[] args){
        AwtEventTest event = new AwtEventTest();
        //event.test1();
        //event.test2();
        //event.test3();
        //event.test4();
        //event.test5();
        event.test6();
    }


    private Frame f = new Frame("测试");
    private TextArea textArea = new TextArea(6, 40);
    private Button b1 = new Button("按钮一");
    private Button b2 = new Button("按钮二");

    public void test1() {
        Frame f=new Frame("我的窗体");//设置窗体名称
        f.setSize(400, 300);//设置宽和高
        f.setLocation(300, 200);//设置在屏幕中所属位置
        f.setVisible(true);//设置窗体可见
        f.addWindowListener(new MyWindowListener1());
    }

    public void test2() {
        Frame f=new Frame("我的窗体");//设置窗体名称
        f.setSize(400, 300);//设置宽和高
        f.setLocation(300, 200);//设置在屏幕中所属位置
        f.setVisible(true);//设置窗体可见
        f.addWindowListener(new MyWindowListener2());
    }

    public void test3() {
        Frame f=new Frame("我的窗体");//设置窗体名称
        f.setSize(400, 300);//设置宽和高
        f.setLocation(300, 200);//设置在屏幕中所属位置
        f.setVisible(true);//设置窗体可见
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                Window window=(Window)e.getComponent();
                window.dispose();
            }
        });
    }

    public void test4() {
        Frame f=new Frame("我的窗体");//设置窗体名称
        f.setSize(400, 300);//设置宽和高
        f.setLocation(300, 200);//设置在屏幕中所属位置
        f.setVisible(true);//设置窗体可见
        Button btn=new Button("点击退出");
        f.add(btn);
        btn.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });
    }

    public void test5() {
        // 创建FirstListener监听器的实例
        FirstListener f1 = new FirstListener();
        // 给b1按钮注册两个事件监听器
        b1.addActionListener(f1);
        b1.addActionListener(new SecondListener());
        // 将f1事件监听器注册给b2按钮
        b2.addActionListener(f1);
        f.add(textArea);
        Panel p = new Panel();
        p.add(b1);
        p.add(b2);
        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }

    public void test6() {
        // 为窗口添加窗口事件监听器
        f.addWindowListener(new MyListener());
        f.add(textArea);
        f.pack();
        f.setVisible(true);
    }


    class MyWindowListener1 implements WindowListener {
        public void windowClosing(WindowEvent e){
            Window window=e.getWindow();
            window.setVisible(false);
            window.dispose();
        }
        public void windowActivated(WindowEvent e){

        }
        public void windowClosed(WindowEvent e){

        }
        public void windowDeactivated(WindowEvent e){

        }
        public void windowDeiconified(WindowEvent e){

        }
        public void windowIconified(WindowEvent e){

        }
        public void windowOpened(WindowEvent e){

        }
    }

    class MyWindowListener2 extends WindowAdapter {
        public void windowClosing(WindowEvent e){
            Window window=(Window)e.getComponent();
            window.dispose();
        }
    }

    class FirstListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("第一个事件监听器被触发，事件源是：" + e.getActionCommand() + "\n");
        }
    }

    class SecondListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea.append("单击了“" + e.getActionCommand() + "” 按钮" + "\n");
        }
    }

    class MyListener implements WindowListener {

        @Override
        public void windowActivated(WindowEvent e) {
            textArea.append("窗口被激活！\n");
        }

        @Override
        public void windowClosed(WindowEvent e) {
            textArea.append("窗口被成功关闭！\n");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            textArea.append("用户关闭窗口！\n");
            System.exit(0);
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            textArea.append("窗口失去焦点！\n");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            textArea.append("窗口被恢复！\n");
        }

        @Override
        public void windowIconified(WindowEvent e) {
            textArea.append("窗口被最小化！\n");
        }

        @Override
        public void windowOpened(WindowEvent e) {
            textArea.append("窗口初次被打开！\n");
        }
    }

}
