package com.lsy.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lsy
 * 坦克类
 */
public class Tank {
    /**
     * 定义坦克的位置坐标，x、y、速度speed、坦克宽度width、坦克高度height
     */
    private int x;
    private int y;
    Group group = Group.Blue;
    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        this.isMaster = master;
    }

    private boolean isMaster;
    private boolean die = false;
    Rectangle rectangle = new Rectangle();

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    private int speed = 10;
    private Dir dir = Dir.DOWN;

    private TankFrame tankFrame = null;

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving = false;

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = width;
        rectangle.height = height;
    }

    static int width = 50;
    static int height = 50;

    public Tank(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    /**
     * 坦克被打死消失
     */
    public void goDie(){
        die = true;
    }

    /**
     * 坦克移动坐标的变化及边界问题
     */
    public void moving() {
        if (!moving) return;
        switch (dir) {
            case LIFT:
                x -= speed;
                if (x < 0) x += speed;
                break;
            case RIGHT:
                x += speed;
                if (x > tankFrame.gameWidth - width) x -= speed;
                break;
            case UP:
                y -= speed;
                if (y < 0) y += speed;
                break;
            case DOWN:
                y += speed;
                if (y > tankFrame.gameHeight - height) y -= speed;
                break;
        }
    }

    /**
     * 显示坦克到屏幕
     * @param g
     */
    public void tankPaint(Graphics g) {
        if(die) TankFrame.tankList.remove(this);

        g.setColor(Color.GREEN);
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
        }
        moving();
    }

    /**
     * 创建坦克开火的的子弹对象并对子弹数量加1
     */
    public void fire() {
        Bullet bullet = new Bullet(this.x, this.y, getDir(), group);
        TankFrame.bullets.add(bullet);
    }
}
