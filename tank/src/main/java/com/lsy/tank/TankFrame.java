package com.lsy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lsy
 * 坦克大战窗口图形
 */
public class TankFrame extends Frame {
    /**
     * 定义游戏窗口属性
     */
    static final int gameWidth = 800, gameHeight = 600;
    private Dir dir = Dir.DOWN;
    /**
     * 创建主坦克对象
     */
    Tank tank = new Tank(100, 200, this);
    /**
     * 创建电脑坦克list对象
     */
    List<Tank> tankList = new ArrayList<>();
    /**
     * 创建子弹list对象
     */
    List<Bullet> bullets = new ArrayList<>();
    boolean up = false, down = false, lift = false, right = false;

    /**
     * 设置窗口属性
     * @throws HeadlessException
     */
    public TankFrame() throws HeadlessException {
        setSize(gameWidth, gameHeight);
        setTitle("坦克大战");
        setResizable(false);
        setVisible(true);
        addKeyListener(new MyKeyListener());
        /**
         * 创建一个窗口监听器，如果按X 则执行windowClosing方法
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * 双缓冲
     */
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(gameWidth, gameHeight);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, gameWidth, gameHeight);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * 相当于一个画图工具，参数g相当于画笔
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        /**
         * 将坦克list显示到屏幕
         */
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).tankPaint(g);
        }
        Color color = g.getColor();
        g.setColor(Color.magenta);
        g.drawString("子弹数量"+bullets.size(),700,500);
        g.setColor(color);
        tank.tankPaint(g);
        /**
         * 写成这样报错！！！！
         */
//        for (Bullet bullet : bullets) {
//            bullet.bulletPaint(g);
//        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).bulletPaint(g);
        }
    }


    /**
     * 键盘监听器
     */
    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    lift = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                default:
                    break;
            }
            setDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    lift = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_SPACE:
                    /*bullet.setBullet(true);
                    bullet = null;*/
                    tank.fire();
                default:
                    break;
            }
            setDir();
        }

        /**
         * 设置坦克方向
         */
        public void setDir() {
            tank.setMoving(true);
            if (up) dir = Dir.UP;
            if (down) dir = Dir.DOWN;
            if (lift) dir = Dir.LIFT;
            if (right) dir = Dir.RIGHT;
            if (!lift && !right && !up && !down) tank.setMoving(false);
            tank.setDir(dir);
        }
    }
}
