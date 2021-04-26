package com.lsy.tank;

import java.awt.*;

/**
 * @author lsy
 * 子弹
 */
public class Bullet {
    private int x, y, width = 15, height = 15, speed = 15;
    TankFrame tankFrame = null;

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    /**
     * 发射子弹
     */
    public void biubiubiu() {
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
        if (this.x < 0 || this.y < 0 || this.x > tankFrame.gameWidth || this.y > tankFrame.gameHeight)
            tankFrame.bullets.remove(this);
    }

    /**
     * 画笔
     *
     * @param g
     */
    public void bulletPaint(Graphics g) {
        g.setColor(Color.magenta);
        //g.fillOval(x+(tankFrame.tank.width-width)/2, y+(tankFrame.tank.height-height)/2, width, height);
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.bulletL,x+(tankFrame.tank.width-width)/2,y+(tankFrame.tank.height-height)/2,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x+(tankFrame.tank.width-width)/2,y+(tankFrame.tank.height-height)/2,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x+(tankFrame.tank.width-width)/2,y+(tankFrame.tank.height-height)/2,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x+(tankFrame.tank.width-width)/2,y+(tankFrame.tank.height-height)/2,null);
                break;
        }
        biubiubiu();
    }
}
