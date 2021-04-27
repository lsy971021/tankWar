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
    private int x;
    private int y;
    private int width = ResourceMgr.bulletD.getWidth();
    private int height = ResourceMgr.bulletD.getHeight();
    private int speed = 15;
    private boolean die = false;
    Rectangle rectangle = new Rectangle();
    private Group group = Group.Red;
    boolean team;
    Dir dir = Dir.DOWN;
    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = width;
        rectangle.height = height;
        this.group = group;
    }

   /* public void isTeam(Tank t){
        team=t.group==group;
        if(!team&&!tank.isDie()&&!this.die&&tank.rectangle.intersects(this.rectangle)){
            tank.goDie();
        }
    }*/

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
            this.goDie();
        }
    }

    /**
     * 子弹消失
     */
    public void goDie(){
        die = true;
    }

    /**
     * 移出消失的子弹
     */
    public void rmBullet(){
        TankFrame.bullets.remove(this);
    }

    /**
     * 画笔，将子弹显示到屏幕
     * @param g
     */
    public void bulletPaint(Graphics g) {
        if(die) rmBullet();
        g.setColor(Color.magenta);
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMgr.bulletL, x + (Tank.width - width) / 2, y + (Tank.height - height) / 2, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x + (Tank.width - width) / 2, y + (Tank.height - height) / 2, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x + (Tank.width - width) / 2, y + (Tank.height - height) / 2, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x + (Tank.width - width) / 2, y + (Tank.height - height) / 2, null);
                break;
        }
        biubiubiu();
    }
}
