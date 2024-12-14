package interfaces;

import base.BaseUav;
import ui.GUI.NotificationForms;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public interface ISimulationServer {

    /**
     * 获得顶部栏的引用
     * */
    void setNotificationForms(NotificationForms notificationForms);

    /**
     * 绘制元素
     * */
    void draw(Graphics g);

    /**
     * 向指定的阵营添加
     * */
    void addUav(BaseUav uav);

    /**
     * 初始化区域中的uav初试状态v
     * */
    void initUAVs();

    /**
     * 快速随机初始化uav
     * */
    void initUAVsRandomly();

    void addBlueUav(int n);

    void addBlueUav(int x, int y);

    void addRedUav(int n);

    void addRedUav(int x, int y);

    /**
     * 初始化服务
     * */
    void initService();

    /**
     * 处理顶部栏
     * */
    void processBar();

    /**
     * 结束仿真
     * */
    void stopSim();

    void startSim();

    /**
     * 判断通过中轴线的uav数量
     * */
    boolean isDirectValid(CopyOnWriteArrayList<BaseUav> UAVs);

}
