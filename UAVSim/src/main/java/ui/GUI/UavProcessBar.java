package ui.GUI;

import interfaces.IUavProcessBar;

import javax.swing.*;
import java.awt.*;

public class UavProcessBar extends JProgressBar implements IUavProcessBar {

    //进度条的值
    private int value;

    public UavProcessBar() {
        super();
        this.setPreferredSize(new Dimension(350,25));
        this.setStringPainted(true);
        this.value = 0;
    }

    public UavProcessBar(int value) {
        super();
        this.setPreferredSize(new Dimension(350,25));
        this.setStringPainted(true);
        this.value = value;
        this.setValue(value);
    }

    @Override
    public void addValue(int value) {
        this.value += value;
        this.setValue(this.value);
    }

    @Override
    public void reduceValue(int value) {
        this.value -= value;
        this.setValue(this.value);
    }

    @Override
    public void setValues(int value) {
        this.setValue(value);
        this.value = value;
    }


}
