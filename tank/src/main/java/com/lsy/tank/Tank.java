package com.lsy.tank;

import java.awt.*;

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
    private int speed=10;
    private Dir dir = Dir.DOWN;

    public boolean isMoving() {
        return moving;
    }

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
    }

    int width=50;
    int height=50;

    public Tank(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Tank() {
    }

    public Tank(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    /**
     * 坦克移动
     */
    public void moving(){
        //if(!moving)  return;
        switch (dir) {
            case LIFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
    }
    public void tankPaint(Graphics g){
        g.fillRect(x, y, width, height);
        moving();
    }
}
