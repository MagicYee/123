package interfaces;

import java.awt.*;

/**
 * @author : 王捷
 * @data: 2022/11/16
 * @description: 定义所有无人机的接口
 * */

public interface IUav {

    /**
     * 定义无人机的移动
     * */
    void move(int x, int y);

    void move();

    void moveDirection(int n);

    /**
     * 在图中绘制自己
     * */
    void drawUav(Graphics g);

    void setPosition(int x, int y);

    void setRandomPosition();

    /**
     * 遭受有效的效能攻击后，增加的受损率
     * */
    void beAttacked(double power);

    /**
     * 绘制通信范围
     * */
    void drawComScope(Graphics g);

    /**
     * 判断边界
     * */
    void boundTouch();


}
