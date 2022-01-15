package game.byx.java;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //头部的图片 URL:定位图片的地址 ImageIcon：图片
    public final static URL HEAD_URL = Data.class.getResource("/res/header.png");
    public final static ImageIcon IC_HEAD = new ImageIcon(HEAD_URL);

    //头部（上下左右）
    public final static URL UP_URL =Data.class.getResource("/res/up.png");
    public final static URL DOWN_URL =Data.class.getResource("/res/down.png");
    public final static URL RIGHT_URL =Data.class.getResource("/res/right.png");
    public final static URL LEFT_URL =Data.class.getResource("/res/left.png");
    public final static ImageIcon IC_UP = new ImageIcon(UP_URL);
    public final static ImageIcon IC_DOWN = new ImageIcon(DOWN_URL);
    public final static ImageIcon IC_RIGHT = new ImageIcon(RIGHT_URL);
    public final static ImageIcon IC_LEFT = new ImageIcon(LEFT_URL);

    //身体
    public static URL BODY_URL =Data.class.getResource("/res/body.png");
    public static ImageIcon IC_BODY = new ImageIcon(BODY_URL);
    //食物
    public static URL FOOD_URL =Data.class.getResource("/res/left.png");
    public static ImageIcon IC_FOOD = new ImageIcon(FOOD_URL);
}
