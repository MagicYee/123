package simulation;


import base.BaseUav;
import base.BlueUav;
import base.RedUav;
import interfaces.IGamblingService;
import interfaces.ISimulationServer;
import interfaces.IUav;
import services.GamblingService;
import simulation.enums.SimulationStatus;
import ui.GUI.NotificationForms;
import ui.MainPanel;
import utils.CalculateUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 实现后台仿真的主进程
 */

public class SimulationServer implements ISimulationServer {

    //存储红蓝双方uav的队列
    private CopyOnWriteArrayList<BaseUav> blueUAVs;
    private CopyOnWriteArrayList<BaseUav> redUAVs;
    MainPanel panel;
    NotificationForms notificationForms;
    //仿真状态
    public SimulationStatus simulationStatus;

    public Thread simThread;

    public static int areaWidth, areaHeight;
    //通信半径
    public static int radius = 160;
    public static double  m, theta = 1L;
    public static boolean directFlag = true;
    //服务
    IGamblingService gamblingService;

    //仿真线程运行次数
    private int simCount = 0;

    public SimulationServer(MainPanel panel) {
        this.panel = panel;
        this.blueUAVs = new CopyOnWriteArrayList<>();
        this.redUAVs = new CopyOnWriteArrayList<>();
        this.simulationStatus = SimulationStatus.UNOPENED;
        initService();
    }

    @Override
    public void startSim() {
        simThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (simulationStatus == SimulationStatus.RUNNING) {
                    simCount += 50;
                    if (simCount % 1000 == 0 && simCount > 2000) {
                        processBar();
                    }
                    if (simCount % 1000 == 0 && simCount > 1500) {
                        gamblingService.basicCompute(redUAVs, blueUAVs);
                    }
                    for (BaseUav uav : blueUAVs) {
                        uav.move();
                    }
                    for (BaseUav uav : redUAVs) {
                        uav.move();
                    }
                    if (directFlag) {
                        directFlag = isDirectValid(blueUAVs);
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                }
            }
        });
        simThread.start();
    }

    @Override
    public boolean isDirectValid(CopyOnWriteArrayList<BaseUav> UAVs) {
        int count = 0;
        for(BaseUav uav: UAVs) {
            if (uav.yPosition > areaHeight/2) count++;
        }
        if (count>= UAVs.size()/3){
            return false;
        }
        return true;
    }

    @Override
    public void setNotificationForms(NotificationForms notificationForms) {
        this.notificationForms = notificationForms;
    }

    @Override
    public void draw(Graphics g) {
        for (BaseUav uav : blueUAVs) {
            uav.drawUav(g);
        }
        for (BaseUav uav : redUAVs) {
            uav.drawUav(g);
        }
    }

    @Override
    public void addUav(BaseUav uav) {
        if (uav instanceof BlueUav) {
            this.blueUAVs.add(uav);
        } else {
            this.redUAVs.add(uav);
        }
    }

    @Override
    public void initUAVs() {
        if (simulationStatus == SimulationStatus.UNOPENED  || simulationStatus == SimulationStatus.TERMINATED ) {
            initUAVsRandomly();
            simulationStatus = SimulationStatus.INIT;
        }

    }

    @Override
    public void initUAVsRandomly() {
        this.areaWidth = panel.getBounds().width;
        this.areaHeight = panel.getBounds().height;
        for(int i=0; i<16;i++) {
            int x = CalculateUtil.getRandomXLocation(0, areaWidth/2);
            int y = CalculateUtil.getRandomYLocation(0, areaHeight/2);
            blueUAVs.add(new BlueUav(x, y));
        }
        for (int j=0; j<16;j++) {
            int x = CalculateUtil.getRandomXLocation(areaWidth/2, areaWidth);
            int y = CalculateUtil.getRandomYLocation(areaHeight/2, areaHeight);
            redUAVs.add(new RedUav(x, y));
        }
        this.panel.repaint();
    }

    @Override
    public void addBlueUav(int n) {
//        JOptionPane.showInputDialog()
        this.blueUAVs.add(new BlueUav(n));
        panel.repaint();
    }

    @Override
    public void addBlueUav(int x, int y) {
        this.blueUAVs.add(new BlueUav(x, y));
        panel.repaint();
    }

    @Override
    public void addRedUav(int n) {
        this.redUAVs.add(new RedUav(n));
        panel.repaint();
    }

    @Override
    public void addRedUav(int x, int y) {
        this.redUAVs.add(new RedUav(x, y));
        panel.repaint();
    }

    @Override
    public void initService() {
        gamblingService = new GamblingService();
    }

    @Override
    public void processBar() {
        notificationForms.progressBar1.addValue(ThreadLocalRandom.current().nextInt(0, 5));
        notificationForms.progressBar3.addValue(ThreadLocalRandom.current().nextInt(0, 5));
        notificationForms.progressBar2.addValue(ThreadLocalRandom.current().nextInt(0, 5));
        notificationForms.progressBar4.addValue(ThreadLocalRandom.current().nextInt(0, 5));
    }

    @Override
    public void stopSim() {
        simulationStatus = SimulationStatus.TERMINATED;
        simThread.stop();
        this.blueUAVs.clear();
        this.redUAVs.clear();
        this.panel.repaint();
    }

}
