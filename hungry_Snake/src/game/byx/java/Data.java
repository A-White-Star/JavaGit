package game.byx.java;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //头部的图片 URL
    public static URL sHeadreurl = Data.class.getResource("/res/header.png");
    public static ImageIcon ic_header = new ImageIcon(sHeadreurl);
}
