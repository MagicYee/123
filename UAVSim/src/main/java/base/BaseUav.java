package base;

import interfaces.IUav;
import simulation.SimulationServer;
import utils.CalculateUtil;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : 王捷
 * @data: 2022/11/16
 * @description: 定义所有无人机的基类
 * */

public class BaseUav implements IUav {

    //无人机的实时坐标
    public int xPosition, yPosition;
    //无人机的受损率
    public double damageRatio = 0;
    //无人机的唯一编号
    public int uuid;
    public String name;

    //uav显示的大小
    public final int uavSize = 40;

    //模拟移动的步长
    public int moveStepCount;
    public static int moveStep = 3;
    public int moveDirection; // 方向模拟，1-8；

    public final int comScope = 160;

    public BaseUav(int uuid, String name) {
        this.uuid = uuid;
        this.name = name + uuid;
        setRandomPosition();
    }

    public BaseUav() {
        this.setPosition(0,0);
    }

    public BaseUav(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        this.moveStepCount = CalculateUtil.getMoveStepCount();
        this.moveDirection = CalculateUtil.getMoveDirection(SimulationServer.directFlag);
    }


    @Override
    public void move(int x, int y) {
        this.xPosition += x;
        this.yPosition += y;
        boundTouch();
    }

    @Override
    public void move() {
        if (moveStepCount == 0) {
            moveStepCount = CalculateUtil.getMoveStepCount();
            moveDirection = CalculateUtil.getMoveDirection(SimulationServer.directFlag);
            //随机生成
//            damageRatio += ThreadLocalRandom.current().nextDouble(0, 0.07);
        }
        moveDirection(moveDirection);
        System.out.println(moveStepCount);
        moveStepCount--;
    }

    @Override
    public void moveDirection(int n) {
        switch (moveDirection) {
            case 1:
                move(0, -moveStep);
                break;
            case 2:
                move(moveStep, -moveStep);
                break;
            case 3:
                move(moveStep, 0);
                break;
            case 4:
                move(moveStep, moveStep);
                break;
            case 5:
                move(0, moveStep);
                break;
            case 6:
                move(-moveStep, moveStep);
                break;
            case 7:
                move(-moveStep, 0);
                break;
            case 8:
                move(-moveStep, -moveStep);
                break;
        }
    }

    @Override
    public void drawUav(Graphics g) {
        this.drawComScope(g);
    }

    @Override
    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    @Override
    public void setRandomPosition() {
        int position = new Random().nextInt(3000);
        this.setPosition(position, position);
    }

    @Override
    public void beAttacked(double power) {
        this.damageRatio += power;
    }

    @Override
    public void drawComScope(Graphics g) {
        g.setColor(new Color(247, 145,33));
        g.drawOval(xPosition - (comScope-uavSize)/2, yPosition - (comScope-uavSize)/2, comScope, comScope);
    }

    @Override
    public void boundTouch() {
        if (xPosition <0 && yPosition <0) {
            moveDirection = 4;
            moveStepCount = CalculateUtil.getMoveStepCount();
        } else if (xPosition <0 && yPosition > SimulationServer.areaHeight) {
            moveDirection = 2;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }  else if (xPosition <0) {
            moveDirection = 3;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }
        if (xPosition >SimulationServer.areaWidth && yPosition <0) {
            moveDirection = 6;
            moveStepCount = CalculateUtil.getMoveStepCount();
        } else if (xPosition >SimulationServer.areaWidth && yPosition > SimulationServer.areaHeight) {
            moveDirection = 8;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }  else if (xPosition >SimulationServer.areaWidth) {
            moveDirection = 7;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }
        if(yPosition > SimulationServer.areaHeight) {
            moveDirection =1;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }else if (yPosition <0) {
            moveDirection = 5;
            moveStepCount = CalculateUtil.getMoveStepCount();
        }
    }


}
