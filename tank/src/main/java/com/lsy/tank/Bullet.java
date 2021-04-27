package com.lsy.tank;

import java.awt.*;
import java.util.List;

/**
 * @author lsy
 * 子弹
 */
public class Bullet {
    /**
     * 定义子弹坐标、尺寸和速度
     */
    private int x;
    private int y;
    private int width = ResourceMgr.bulletD.getWidth();
    private int height = ResourceMgr.bulletD.getHeight();
    private int speed = 15;

    Tank tank = null;

    Dir dir = Dir.DOWN;
    public Bullet(int x, int y, Dir dir, Tank tank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tank = tank;

    }

    /**
     * 发射子弹的坐标的变化及对子弹消失减1操作
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
        if (this.x < 0 || this.y < 0 || this.x > TankFrame.gameWidth || this.y > TankFrame.gameHeight) {
            this.rmBullet(tank.getBullets());
            --TankFrame.bulletNum;
        }
        /*List<Tank> tankList = TankFrame.tankList;
        if(tank.isMaster()){
            System.out.println("x:"+x+"y:"+y);
            for (int i = 0; i < tankList.size(); i++) {
                System.out.println("outhers x:"+tankList.get(i).getX()+"others y:"+tankList.get(i).getY());
                if(x==tankList.get(i).getX()&&y==tankList.get(i).getY()){
                    tankList.remove(i);
                    System.out.println("=====");
                }
            }
        }*/

    }

    /**
     * 移除出边界的子弹
     * @param bullets
     */
    public void rmBullet(List<Bullet> bullets){
        bullets.remove(this);
    }

    /**
     * 画笔，将子弹显示到屏幕
     * @param g
     */
    public void bulletPaint(Graphics g) {
        g.setColor(Color.magenta);
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.bulletL, x + (tank.width - width) / 2, y + (tank.height - height) / 2, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x + (tank.width - width) / 2, y + (tank.height - height) / 2, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x + (tank.width - width) / 2, y + (tank.height - height) / 2, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x + (tank.width - width) / 2, y + (tank.height - height) / 2, null);
                break;
        }
        biubiubiu();
    }
}
