package ui;

import base.BaseUav;
import base.Uav1;
import interfaces.IMainFrame;
import interfaces.IUav;

/**
 * 仿真入口
 * */
public class MyApplication {

    public static void main(String[] args){

        IMainFrame mainFrame = new MainFrame("仿真");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               mainFrame.init();
            }
        });
    }
}
