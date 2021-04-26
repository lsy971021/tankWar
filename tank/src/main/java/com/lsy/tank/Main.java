package com.lsy.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * @author lsy
 * 主应用类
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {
        TankFrame tankFrame = new TankFrame();
        List<Tank> tankList = tankFrame.tankList;
        for (int i = 1; i < 4; i++) {
            tankList.add(new Tank(100*i, 100));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
