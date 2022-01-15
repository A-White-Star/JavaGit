package game.byx.java;

import javax.swing.*;

//启动游戏
public class StartGame {
    public static void main(String[] args){
        //1.静态窗口JFrame 绘制
        JFrame frame = new JFrame("Hungry Snake");
        frame.setBounds(10,10,900,720); //设置界面大小
        frame.setResizable(false); //窗口大小不可改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭事件

        //2.面板对象
        frame.add(new GamePanel());

        frame.setVisible(true); //窗口可见
    }
}
