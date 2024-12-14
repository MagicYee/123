package services;

import base.BaseUav;
import interfaces.IGamblingService;
import simulation.SimulationServer;
import utils.CalculateUtil;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 效能实时计算的实现类
 * */
public class GamblingService implements IGamblingService {

    @Override
    public void compute(CopyOnWriteArrayList<BaseUav> blueUAVs, CopyOnWriteArrayList<BaseUav> redUAVs) {
        for (BaseUav blueUav : blueUAVs) {
            for (BaseUav redUav : redUAVs) {
                if (CalculateUtil.calculateDistance(blueUav, redUav) <= SimulationServer.radius) {
                    double efficiency = CalculateUtil.calculateEfficiency(SimulationServer.m, SimulationServer.theta, blueUav, redUav, SimulationServer.radius);
                    blueUav.beAttacked(efficiency);
                    redUav.beAttacked(efficiency);
                }
            }
        }
    }

    @Override
    public void basicCompute(CopyOnWriteArrayList<BaseUav> blueUAVs, CopyOnWriteArrayList<BaseUav> redUAVs) {
        for (BaseUav blueUav : blueUAVs) {
            for (BaseUav redUav : redUAVs) {
                if (CalculateUtil.calculateDistance(blueUav, redUav) <= SimulationServer.radius) {
                    double efficiency = ThreadLocalRandom.current().nextDouble(0, 0.07);
                    blueUav.beAttacked(efficiency);
                    redUav.beAttacked(efficiency);
                }
            }
        }
    }
}
