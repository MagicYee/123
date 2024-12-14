package simulation;

import base.BaseUav;
import base.RedUav;
import base.Uav1;
import interfaces.IUav;

public class test {

    public static void main(String[] args){

        IUav uav1 = new Uav1(1, "红方");
        boolean res = uav1 instanceof BaseUav;
        System.out.println(res);

    }
}
