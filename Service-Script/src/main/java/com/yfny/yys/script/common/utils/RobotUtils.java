package com.yfny.yys.script.common.utils;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import com.yfny.utilscommon.util.StringUtils;
import com.yfny.yys.script.common.ShellConstant;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Robot工具类
 * Author JiSong_ZHOU
 * Date  2020/11/17
 */
public class RobotUtils {

    static WinUser.INPUT input = new WinUser.INPUT();

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

        boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer arg);

        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
    }

    public interface ProcessPathKernel32 extends Kernel32 {
        class ModuleEntry32 extends Structure {
            public static class ByReference extends ModuleEntry32 implements Structure.ByReference {
                public ByReference() {
                }

                public ByReference(Pointer memory) {
                    super(memory);
                }
            }

            public ModuleEntry32() {
                dwSize = new WinDef.DWORD(size());
            }

            public ModuleEntry32(Pointer memory) {
                super(memory);
                read();
            }

            public DWORD dwSize;
            public DWORD th32ModuleID;
            public DWORD th32ProcessID;
            public DWORD GlblcntUsage;
            public DWORD ProccntUsage;
            public Pointer modBaseAddr;
            public DWORD modBaseSize;
            public HMODULE hModule;
            public char[] szModule = new char[255 + 1]; // MAX_MODULE_NAME32
            public char[] szExePath = new char[MAX_PATH];

            public String szModule() {
                return Native.toString(this.szModule);
            }

            public String szExePath() {
                return Native.toString(this.szExePath);
            }

            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[]{
                        "dwSize", "th32ModuleID", "th32ProcessID", "GlblcntUsage", "ProccntUsage", "modBaseAddr", "modBaseSize", "hModule", "szModule", "szExePath"
                });
            }
        }

        ProcessPathKernel32 INSTANCE = (ProcessPathKernel32) Native.loadLibrary(ProcessPathKernel32.class, W32APIOptions.UNICODE_OPTIONS);

        boolean Module32First(HANDLE hSnapshot, ModuleEntry32.ByReference lpme);

        boolean Module32Next(HANDLE hSnapshot, ModuleEntry32.ByReference lpme);
    }

    public static Set<Integer> getTaskPID() {
        com.sun.jna.platform.win32.User32 user32 = com.sun.jna.platform.win32.User32.INSTANCE;
        Set<Integer> set = new HashSet<>();
        IntByReference i = new IntByReference();//放PID
        user32.EnumWindows((h, p) -> {
            user32.GetWindowThreadProcessId(h, i);//获取窗口的PID
            if (user32.IsWindow(h) && user32.IsWindowEnabled(h) && user32.IsWindowVisible(h)) {
                set.add(i.getValue());
            }
            return true;
        }, null);
        return set;
    }

    public static void name() {

        HICON[] a = new HICON[12];
        HICON[] b = new HICON[11];
        Set<Integer> pIds = getTaskPID();//获取窗口进程的PID
        int c = 1;
        Kernel32 kernel32 = Native.loadLibrary(Kernel32.class, W32APIOptions.DEFAULT_OPTIONS);
        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();
        WinNT.HANDLE processSnapshot =
                kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new DWORD(0));
        try {
            while (kernel32.Process32Next(processSnapshot, processEntry)) {
                //程序的PID
                DWORD th32ProcessID = processEntry.th32ProcessID;
                //程序的名字（xx.exe）
                Native.toString(processEntry.szExeFile);
                System.out.println(th32ProcessID + Native.toString(processEntry.szExeFile));
//                WinNT.HANDLE moduleSnapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE, processEntry.th32ProcessID);
//                if (pIds.contains(processEntry.th32ProcessID.intValue())) {
//                    String exeName = Native.toString(processEntry.szExeFile).substring(0, Native.toString(processEntry.szExeFile).indexOf(".exe"));
//                    //ShellExperienceHost为开始菜单外壳,syntpenh为触摸板相关程序
//                    if (exeName.toLowerCase().equals("shellexperiencehost") || exeName.toLowerCase().equals("syntpenh")) {
//                        continue;
//                    }
//                }
            }
        } finally {
            kernel32.CloseHandle(processSnapshot);
        }
    }

    public static void showWindow() {
        final User32 user32 = User32.INSTANCE;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;

            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);
                try {
                    if (StringUtils.isNotBlank(wText)) {
                        wText = new String(windowText, "GBK");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                    return true;
                }
                if (wText.contains("test")) {
                    System.out.println("testFolder:" + wText);
                }
                System.out.println("Found window with text " + hWnd + ", total " + ++count + " Text: " + wText);
                return true;
            }
        }, null);
    }

    public static void selectWindow(String windowName) {
        // 第一个参数是Windows窗体的窗体类，第二个参数是窗体的标题。不熟悉windows编程的需要先找一些Windows窗体数据结构的知识来看看，还有windows消息循环处理，其他的东西不用看太多。
        HWND window = com.sun.jna.platform.win32.User32.INSTANCE.FindWindow(null, windowName);
        if (window == null) {
            System.out.println(windowName + " is not running");
        } else {
            // SW_RESTORE
            com.sun.jna.platform.win32.User32.INSTANCE.ShowWindow(window, 9);
            // bring to front
            com.sun.jna.platform.win32.User32.INSTANCE.SetForegroundWindow(window);
        }
    }

    public static void closeWindow(String windowName) {
        //通过窗口标题获取窗口句柄
        HWND window = com.sun.jna.platform.win32.User32.INSTANCE.FindWindow(null, windowName);
        if (window == null)   //throw new RuntimeException("窗口不存在,请先运行程序");
            System.out.println(windowName + " is not running");
        else {
            // 0x10 关闭窗口信号    lresult 0 关闭成功
            LRESULT lresult = com.sun.jna.platform.win32.User32.INSTANCE.SendMessage(window, 0X10, null, null);
            System.out.println("lresult:" + lresult);
        }
    }

    /**
     * 获取屏幕颜色
     *
     * @param x x坐标
     * @param y y坐标
     * @return Color
     */
    public static Color getScreenPixel(Robot robot, int x, int y) { // 函数返回值为颜色的RGB值。
        return robot.getPixelColor(x, y);
    }

    /**
     * 向剪贴板中添加内容
     *
     * @param content 剪切的内容
     */
    public static void setClipbord(String content) {
        StringSelection stringSelection = new StringSelection(content);
        // 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * 清空剪贴板
     */
    public static void clearClipbord() {
        setClipbord("");
    }

    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public static String getClipboard() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * crtl+c:复制
     *
     * @param robot
     */
    public static void ctrlC(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    /**
     * crtl+v:粘贴
     *
     * @param robot
     */
    public static void ctrlV(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    /**
     * crtl+v:全选
     *
     * @param robot
     */
    public static void ctrlA(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

}
