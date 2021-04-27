package com.lsy.tank;

import java.util.List;

/**
 * @author lsy
 * 主应用类
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {
        TankFrame tankFrame = new TankFrame();
        List<Tank> tankList = TankFrame.tankList;
        for (int i = 1; i < 4; i++) {
            Tank tank = new Tank(100 * i, 100);
            tank.group = Group.Red;
            tankList.add(tank);
            ++ TankFrame.tankNum;
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
