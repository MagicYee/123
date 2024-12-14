package simulation.enums;

/**
 * 仿真状态的枚举类
 * */
public enum SimulationStatus {
    UNOPENED(0, "仿真未启动"),
    INIT(1, "仿真初始化中"),
    RUNNING(2,"仿真运行中"),
    TERMINATED(3, "仿真结束");

    int number;
    String status;

    SimulationStatus(int number, String status ) {
        this.number = number;
        this.status = status;
    }
}
