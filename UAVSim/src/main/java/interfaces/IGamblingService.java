package interfaces;


import base.BaseUav;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 实现博弈的接口
 * */
public interface IGamblingService {

    /**
     * 实时计算网络的效能
     * */
    void compute(CopyOnWriteArrayList<BaseUav> blueUAVs, CopyOnWriteArrayList<BaseUav> redUAVs);

    void basicCompute(CopyOnWriteArrayList<BaseUav> blueUAVs, CopyOnWriteArrayList<BaseUav> redUAVs);




}

