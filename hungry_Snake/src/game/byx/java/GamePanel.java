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
            this.setBackground(Color.BLACK);    //设置背景的颜色
    }
}
