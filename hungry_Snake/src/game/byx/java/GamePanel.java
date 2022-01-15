package game.byx.java;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;


//容器类，面板
public class GamePanel extends JPanel {
        //画板：画蛇
        //Graphics 可以理解为一个画笔
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //清屏作用
            this.setBackground(Color.WHITE);    //设置背景

            //绘制头部广告栏
            Data.IC_HEAD.paintIcon(this,g,25,11);
            //绘制游戏区域
            g.fillRect(25,75,850,600);
    }
}
