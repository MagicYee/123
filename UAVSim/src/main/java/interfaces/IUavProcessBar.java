package interfaces;

/**
 * 进度条的接口
 * */
public interface IUavProcessBar {

    void addValue(int value);

    void reduceValue(int value);

    void setValues(int value);

}
