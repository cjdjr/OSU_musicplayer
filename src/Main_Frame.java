import java.applet.Applet;  
import java.applet.AudioClip; 
import java.net.MalformedURLException;  
import java.net.URL;  
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.StyleContext.NamedStyle;
import javax.swing.*;
import static javax.swing.JFrame.*; //引入JFrame的静态常量
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import java.util.Timer;

class Main_Frame extends JFrame implements ActionListener
{ //窗口类
	 JLabel background;//背景控件
	 JButton buttonPlay;//播放按钮

	 Menu_Frame menu_frame;
	 Play_Frame play_frame;
	 
	public Main_Frame(){
		

		setTitle("播放器");//软件名
		setBounds(160,100,1024,630);	//设置窗口大小		
		setLayout(null);//空布局			
		init();   //添加控件的操作封装成一个函数         
		setVisible(true);//放在添加组件后面执行
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void match(Menu_Frame menu_frame,Play_Frame play_frame)
	{
		this.menu_frame=menu_frame;
		this.play_frame=play_frame;
	}
	void init(){//添加的控件	  		
		 Icon img=new ImageIcon(".//主页图片.gif");     //创建图标对象			
		 background = new JLabel(img);//设置背景图片
		 background.setBounds(0,0,1020,595);//设置背景控件大小
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
						
		  buttonPlay=new JButton();//添加播放按钮
	      buttonPlay.setBounds(330,115,340,340); //设置播放按钮大小
	      //Icon icon=new ImageIcon(".//play.jpg");//创建播放图标对象
	     // buttonPlay.setIcon(icon);	      //设置播放图标
	      buttonPlay.setContentAreaFilled(false);//透明
	      buttonPlay.setBorderPainted(false);//去掉边框
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      add(buttonPlay);//添加播放按钮至窗口中
		
	}		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("yes");
		play_frame.setVisible(false);
		menu_frame.setVisible(true);
		this.setVisible(false);
		
	}
}

