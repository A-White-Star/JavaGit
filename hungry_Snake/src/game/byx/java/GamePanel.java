package game.byx.java;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//容器类，面板
public class GamePanel extends JPanel implements KeyListener {
    int length;//蛇的长度
    String fx ;
    int[] snakeX = new int[600];//蛇的坐标x
    int[] snakeY = new int[500];//蛇的坐标y
    boolean isStart = false;//游戏是否开始

    //构造
    GamePanel(){ init(); }
    //初始
    void init(){
        length = 3;
        fx = "R";
        snakeX[0]=100;snakeY[0]=100;//头部坐标
        snakeX[1]=75;snakeY[1]=100;//第一节身体坐标
        snakeX[2]=50;snakeY[2]=100;//第二节身体坐标
    }

    //画板：画蛇
    //Graphics 可以理解为一个画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏作用
        this.setBackground(Color.WHITE);    //设置背景
        Data.IC_HEAD.paintIcon(this,g,25,11);//绘制头部广告
        g.fillRect(25,75,850,600); //绘制游戏区域
        //画静态小蛇
        switch (fx){
            case "R":Data.IC_RIGHT.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "L":Data.IC_LEFT.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "U" :Data.IC_UP.paintIcon(this,g,snakeX[0],snakeY[0]);break;
            case "D" :Data.IC_DOWN.paintIcon(this,g,snakeX[0],snakeY[0]);break;
        }
        for(int i = 1;i<length;i++){
            Data.IC_BODY.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //游戏提示：是否开始
        if(!isStart){
            //画一个文字：String
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格绘制游戏",300,300);
        }

    }



    //键盘按下
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘是哪个键
        int keyCode = e.getKeyCode();

        if(keyCode==KeyEvent.VK_SPACE){
            isStart=!isStart;
            System.out.println("开始游戏/暂停游戏");
            repaint();//刷新界面
        }
    }

    //键盘释放
    @Override
    public void keyReleased(KeyEvent e) {}
    //键盘按下弹起
    @Override
    public void keyTyped(KeyEvent e) {}
}
