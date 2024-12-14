package ui;

import interfaces.IMainPanel;
import simulation.SimulationServer;
import simulation.enums.SimulationStatus;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel implements IMainPanel {

    public SimulationServer server;

    public MainPanel() {
        this.server = new SimulationServer(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        server.draw(g);
    }

    @Override
    public void initUAVs() {
        server.initUAVs();
    }

    @Override
    public void startSim() {
        server.simulationStatus = SimulationStatus.RUNNING;
        server.startSim();
    }

    @Override
    public void stopSim() {
        server.stopSim();
    }

    @Override
    public void addBlueUav(int n) {
        server.addBlueUav(n);
    }

    @Override
    public void addBlueUav(int x, int y) {
        server.addBlueUav(x, y);
    }

    @Override
    public void addRedUav(int n) {
        server.addRedUav(n);
    }

    @Override
    public void addRedUav(int x, int y) {
        server.addRedUav(x, y);
    }
}
