package ui;

import interfaces.IMainFrame;
import ui.GUI.DataBoxes;
import ui.GUI.NotificationForms;
import ui.GUI.UavProcessBar;
import ui.GUISource.DataBox;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements IMainFrame  {

    private NotificationForms notificationForm;
    private JPanel notificationPanel;
    private DataBoxes dataBox;
    private JPanel dataBoxPanel;
    MainPanel pw;

    public MainFrame(String name) {
        super(name);
    }

    @Override
    public void  init() {
        pw = new MainPanel();
        pw.setBackground(new Color(220, 220, 220));
        notificationForm = new NotificationForms(pw);
        notificationPanel = notificationForm.panel1;
        pw.server.setNotificationForms(notificationForm);
        dataBox = new DataBoxes(pw);
        dataBox.load();
        dataBoxPanel = dataBox.getPanel();
        this.add(pw, BorderLayout.CENTER);
        this.add(notificationPanel, BorderLayout.NORTH);
        this.add(dataBoxPanel, BorderLayout.WEST);
        this.setVisible(true);        // 设置窗体可视
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setDefaultLookAndFeelDecorated(true);
        this.setResizable(true);    //设置可拉伸

    }

}
