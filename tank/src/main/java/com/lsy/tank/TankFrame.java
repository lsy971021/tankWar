package com.lsy.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author lsy
 * 坦克大战窗口
 */
public class TankFrame extends Frame {
    /**
     * 定义坦克放下，默认向下
     */
    Dir dir =Dir.DOWN;
    /**
     * 定义坦克位置和速度，若有多个坦克则不易增加，需要定义一个坦克类
     */
//    int x = 100, y = 100, speed = 10;
    Tank tank = new Tank(100,100);
    /**
     * 定义子弹，默认为null
     */
    Bullet bullet = null;
    /**
     * 设置是否向某个放下移动
     */
    boolean up = false,down = false,lift = false,right = false;
    public TankFrame() throws HeadlessException {
        setSize(800, 600);
        setTitle("坦克大战");
        /**
         * 固定窗口不可调整大小
         */
        setResizable(false);
        /**
         * 显示窗口,会自动调用paint方法
         */
        setVisible(true);
        /**
         * 创建一个键盘监听器，也可以用lambda表达式
         */
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
     * 相当于一个画图工具，参数g相当于画笔
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        /**
         * 填充一个举行x,y轴代表从计算机坐上开始，往正有方向为+x，正下方向为+y,把画笔给坦克让他自己化
         */
        if(bullet!=null){
            bullet.bulletPaint(g);
        }
        tank.tankPaint(g);

    }


    /**
     * 键盘监听器
     */
    class MyKeyListener extends KeyAdapter {
        /**
         * 当键盘被按下去时
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    lift = true;
                    System.out.println("lift");
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    System.out.println("right");
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    System.out.println("up");
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    System.out.println("down");
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("发射子弹");
                    bullet = new Bullet(tank.getX(),tank.getY());
                default:
                    break;
            }
            setDir();
            /**
             * 执行paint方法
             */
            repaint();
        }

        /**
         * 当键盘被抬起时
         * @param e
         */
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
                    bullet = null;
                default:
                    break;
            }
            setDir();
        }

        /**
         * 设置坦克方向
         */
        public void setDir(){
            if(up || down || lift || down)  {
                tank.setMoving(true);
                if(up) dir = Dir.UP;
                if(down) dir = Dir.DOWN;
                if(lift) dir = Dir.LIFT;
                if(right) dir = Dir.RIGHT;
            }else {
                tank.setMoving(false);
            }
            tank.setDir(dir);
            if(bullet!=null) bullet.setDir(dir);
        }
    }
}
