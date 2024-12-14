package interfaces;

/**
 * 主展示区域panel的接口
 * */
public interface IMainPanel {

    /**
     * 初始化区域中的uav初试状态
     * */
    void initUAVs();

    /**
     * 开始仿真
     * */
    void startSim();

    void stopSim();

    void addBlueUav(int n);

    void addBlueUav(int x, int y);

    void addRedUav(int n);

    void addRedUav(int x, int y);
}
