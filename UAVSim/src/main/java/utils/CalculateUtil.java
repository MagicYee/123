package utils;

import base.BaseUav;
import simulation.SimulationServer;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 提供一些计算公式的工具类
 * */

public class CalculateUtil {

    /**
     *  计算两个uav间的通信距离
     */
    public static int calculateDistance(BaseUav uav1, BaseUav uav2) {
        double a =  Math.pow(uav1.xPosition - uav2.xPosition, 2);
        double b =  Math.pow(uav1.yPosition - uav2.yPosition, 2);
        return (int) Math.sqrt(a + b);
    }

    public static int calculateDistance(int x1, int y1, int x2, int y2) {
        double a =  Math.pow(x1 - x2, 2);
        double b =  Math.pow(y1 - y2, 2);
        return (int) Math.sqrt(a + b);
    }

    /**
     * @param m,
     * @return 单个uav产生的效能
     * */
    public static double calculateEfficiency(double m, double theta, BaseUav uav1, BaseUav uav2, int radius) {
        int x = calculateDistance(uav1, uav2);
        double d = x/radius;
        return m * (Math.pow(Math.E, -(Math.pow(d/theta,2)) ));
    }

    public static double calculateEfficiency(CopyOnWriteArrayList<BaseUav> UAVs, BaseUav uav) {
        double res = 0L;
        if(CollectionUtils.isEmpty(UAVs)) {
            return res;
        }
        for(BaseUav member : UAVs) {
            if (member == null) continue;
            res += calculateEfficiency(1L, 1L, member, uav, 0);
        }
        return res;
    }

    /**
     * 获得朝某方向移动次数的随机数
     * */
    public static int getMoveStepCount() {
        int res = ThreadLocalRandom.current().nextInt(20, 35);
        return  res;
    }

    public static int getMoveDirection(boolean flag) {
        if (flag) {
            return ThreadLocalRandom.current().nextInt(1, 15);
        }
        return ThreadLocalRandom.current().nextInt(1, 9);

    }

    public static int getRandomXLocation() {
        return ThreadLocalRandom.current().nextInt(100, SimulationServer.areaWidth);
    }

    public static int getRandomXLocation(int x, int y) {
        return ThreadLocalRandom.current().nextInt(x, y);
    }

    public static int getRandomYLocation() {
        return ThreadLocalRandom.current().nextInt(100, SimulationServer.areaHeight);
    }

    public static int getRandomYLocation(int x, int y) {
        return ThreadLocalRandom.current().nextInt(x, y);
    }




}
