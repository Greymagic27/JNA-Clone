package io.github.greymagic27.jna_clone;


import io.github.greymagic27.jna_clone.WinDef.*;
import io.github.greymagic27.jna_clone.platform.*;

@SuppressWarnings("unused")
public class EasyMethods {

    static void main() {
        createWindow("", 800, 600);
    }

    public static void createWindow(String title, int width, int height) {
        HINSTANCE hInstance = Kernel32.INSTANCE.GetModuleHandleW(null);
        WinUser.WndProc wndProc = (hWnd, uMsg, wParam, lParam) -> {
            LRESULT result = User32.INSTANCE.DefWindowProcW(hWnd, uMsg, wParam, lParam);
            if (uMsg == WinUser.WM_DESTROY) {
                User32.INSTANCE.PostQuitMessage(0);
                return new LRESULT(0);
            }
            return result;
        };
        WinUser.WNDCLASSEXW wc = new WinUser.WNDCLASSEXW();
        wc.cbSize = wc.size();
        wc.lpfnWndProc = wndProc;
        wc.hInstance = hInstance;
        wc.lpszClassName = "WindowClass";
        User32.INSTANCE.RegisterClassExW(wc);
        HWND hwnd = User32.INSTANCE.CreateWindowExW(0, "WindowClass", title, WinUser.WS_OVERLAPPEDWINDOW, 0, 0, width, height, null, null, hInstance, null);
        User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_SHOW);
        User32.INSTANCE.UpdateWindow(hwnd);
        WinUser.MSG msg = new WinUser.MSG();
        while (User32.INSTANCE.GetMessageW(msg, null, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessageW(msg);
        }
    }
}