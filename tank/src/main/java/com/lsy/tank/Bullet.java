package com.lsy.tank;

import java.awt.*;

/**
 * @author lsy
 * 子弹
 */
public class Bullet {
    /**
     * 定义子弹坐标、尺寸和速度
     */
    private int x, y, width = ResourceMgr.bulletD.getWidth(), height = ResourceMgr.bulletD.getHeight(), speed = 15;
    TankFrame tankFrame = null;
    Tank tank = null;

    /**
     * 子弹默认向下打出
     */
    Dir dir = Dir.DOWN;

    public Bullet(int x, int y, Dir dir, Tank tank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tank = tank;
    }

    /**
     * 发射子弹的坐标的变化
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
        if (this.x < 0 || this.y < 0 || this.x > tankFrame.gameWidth || this.y > tankFrame.gameHeight){
            tank.bullets.remove(this);
            -- TankFrame.bulletNum;
        }
    }

    /**
     * 画笔，将子弹显示到屏幕
     * @param g
     */
    public void bulletPaint(Graphics g) {
        g.setColor(Color.magenta);
        //g.fillOval(x+(tankFrame.tank.width-width)/2, y+(tankFrame.tank.height-height)/2, width, height);
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.bulletL,x+(tank.width-width)/2,y+(tank.height-height)/2,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x+(tank.width-width)/2,y+(tank.height-height)/2,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x+(tank.width-width)/2,y+(tank.height-height)/2,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x+(tank.width-width)/2,y+(tank.height-height)/2,null);
                break;
        }
        biubiubiu();
    }
}
