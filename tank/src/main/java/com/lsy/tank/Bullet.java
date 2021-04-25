package com.lsy.tank;

import java.awt.*;

/**
 * @author lsy
 * 子弹
 */
public class Bullet {
    private int x,y,width=15,height=15,speed=15;

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    Dir dir = Dir.DOWN;
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

    public Bullet() {
    }

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 画笔
     * @param g
     */
    public void bulletPaint(Graphics g) {
        g.setColor(Color.magenta);
        g.drawOval(x,y,width,height);

    }
}
