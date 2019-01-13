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

class Menu_Frame extends JFrame implements ActionListener
{ //窗口类
	 JLabel background;//背景控件
	 JButton buttonPlay;//播放按钮
	 JButton buttonEdit;
	 JButton buttonOptions;
	 JButton buttonExit;
	 
	 Main_Frame main_frame;
	 Play_Frame play_frame;
	 
	public Menu_Frame(){

		setTitle("播放器");//软件名
		setBounds(160,100,1024,630);	//设置窗口大小		
		setLayout(null);//空布局			
		init();   //添加控件的操作封装成一个函数         
		//setVisible(true);//放在添加组件后面执行
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void match(Main_Frame main_frame,Play_Frame play_frame)
	{
		this.main_frame=main_frame;
		this.play_frame=play_frame;
	}
	void init(){//添加的控件	  		
		 Icon img=new ImageIcon(".//菜单图片.gif");     //创建图标对象			
		 background = new JLabel(img);//设置背景图片
		 background.setBounds(0,0,1020,595);//设置背景控件大小
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
						
		  buttonPlay=new JButton();//添加播放按钮
	      buttonPlay.setBounds(510,140,290,60); //设置播放按钮大小
	      //Icon icon=new ImageIcon(".//play.jpg");//创建播放图标对象
	     // buttonPlay.setIcon(icon);	      //设置播放图标
	      buttonPlay.setContentAreaFilled(false);//透明
	      buttonPlay.setBorderPainted(false);//去掉边框
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonPlay);//添加播放按钮至窗口中
	      
		  buttonEdit=new JButton();//设置按钮
	      buttonEdit.setBounds(530,220,290,60); //设置播放按钮大小
	      //Icon icon=new ImageIcon(".//play.jpg");//创建播放图标对象
	     // buttonPlay.setIcon(icon);	      //设置播放图标
	      buttonEdit.setContentAreaFilled(false);//透明
	      buttonEdit.setBorderPainted(false);//去掉边框
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonEdit.addActionListener(this);
	      add(buttonEdit);//添加播放按钮至窗口中
	      
		  buttonOptions=new JButton();//设置按钮
	      buttonOptions.setBounds(530,300,290,60); //设置播放按钮大小
	      //Icon icon=new ImageIcon(".//play.jpg");//创建播放图标对象
	     // buttonPlay.setIcon(icon);	      //设置播放图标
	      buttonOptions.setContentAreaFilled(false);//透明
	      buttonOptions.setBorderPainted(false);//去掉边框
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonOptions.addActionListener(this);
	      add(buttonOptions);//添加播放按钮至窗口中
	      
		  buttonExit=new JButton();//设置按钮
	      buttonExit.setBounds(510,380,290,60); //设置播放按钮大小
	      //Icon icon=new ImageIcon(".//play.jpg");//创建播放图标对象
	     // buttonPlay.setIcon(icon);	      //设置播放图标
	      buttonExit.setContentAreaFilled(false);//透明
	      buttonExit.setBorderPainted(false);//去掉边框
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonExit.addActionListener(this);
	      buttonExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonExit);//添加播放按钮至窗口中
		
	 
	}		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonPlay)
		{
			System.out.println("ok");
			this.setVisible(false);
			main_frame.setVisible(false);
			play_frame.setVisible(true);
		}
		if(e.getSource()==buttonExit)
		{
			this.setVisible(false);
			main_frame.setVisible(true);
			play_frame.setVisible(false);
		}
		
	}
}

