package game.byx.java;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


//容器类，面板
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;//蛇的长度
    String fx = null ;
    int[] snakeX = new int[600];//蛇的坐标x
    int[] snakeY = new int[500];//蛇的坐标y
    static boolean isStart;//游戏是否开始
    static boolean isFail;
    static int score;
    int foodX;  //定义一个食物
    int foodY;
    Random random = new Random();

    Timer timer = new Timer(100,this); //每隔一百毫秒动一下
    //构造
    GamePanel(){
        init();
        ///获取键盘的监听事件
        this.setFocusable(true);    //键盘焦点聚集在游戏上
        this.addKeyListener(this);//设置监听者
        timer.start();
    }
    //初始
    void init(){
        isStart = false;
        isFail = false;
        length = 3;
        score =0;
        fx = "R";
        snakeX[0]=100;snakeY[0]=100;//头部坐标
        snakeX[1]=75;snakeY[1]=100;//第一节身体坐标
        snakeX[2]=50;snakeY[2]=100;//第二节身体坐标
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
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
        //积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));//设置字体
        g.drawString("长度"+length,750,35);
        g.drawString("分数"+score,750,60);
        //食物
        Data.IC_FOOD.paintIcon(this,g,foodX,foodY);
        //游戏提示：是否开始
        if(!isStart){
            //画一个文字：String
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格绘制游戏",300,300);
        }
        //失败
        if(isFail){
            //画一个文字：String
            isStart=!isStart;
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("GAME OVER",300,300);
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
            if(isFail){
                init();
                isStart=true;
            }
            repaint();//刷新界面
        }
        if(isStart){
            switch (keyCode){
                case KeyEvent.VK_LEFT:fx="L";break;
                case KeyEvent.VK_RIGHT:fx="R";break;
                case KeyEvent.VK_UP:fx="U";break;
                case KeyEvent.VK_DOWN:fx="D";break;
            }
        }

    }

    //键盘释放
    @Override
    public void keyReleased(KeyEvent e) {}
    //键盘按下弹起
    @Override
    public void keyTyped(KeyEvent e) {}
    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart){
            for(int i = length-1;i>0;i--){
                snakeX[i]=snakeX[i-1];
                snakeY[i]=snakeY[i-1];
            }
            //通过控制方向让头部移动
            if(fx.equals("R")){
                snakeX[0]=snakeX[0]+25;
                if(snakeX[0]>850){ snakeX[0]=25; }//边界判断
            }else if(fx.equals("L")){
                snakeX[0]=snakeX[0]-25;
                if(snakeX[0]<25){ snakeX[0]=850; }//边界判断
            }else if(fx.equals("U")){
                snakeY[0]=snakeY[0]-25;
                if(snakeY[0]<25){ snakeY[0]=650; }//边界判断
            }else if(fx.equals("D")){
                snakeY[0]=snakeY[0]+25;
                if(snakeY[0]>650){ snakeY[0]=75; }//边界判断
            }

            //如果头部和食物坐标重合
            if(snakeX[0]==foodX && snakeY[0]==foodY){
                length++;
                score+=10;
                foodX=25+25*random.nextInt(34);
                foodY=75+25*random.nextInt(24);
            }
            //失败
            for(int i=1;i<length;i++){
                if (snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]) isFail=true;
            }
            repaint();
        }
        timer.start(); //让时间动起来
    }

}
