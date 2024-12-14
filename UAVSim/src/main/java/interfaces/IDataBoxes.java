package interfaces;

import javax.swing.*;

public interface IDataBoxes {

    /**
     * 添加节点
     * */
    void addRedUavNode(int n);

    void addRedUavNode(int x, int y);

    void addBlueUavNode(int n);

    void addBlueUavNode(int x, int y);

    JPanel getPanel();
}
