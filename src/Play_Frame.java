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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Play_Frame extends JFrame implements ActionListener,ChangeListener,MouseListener
{ //窗口类
	 JLabel background;//背景控件
	 JButton buttonPlay;//播放按钮
	 JButton buttonNext;
	 JButton buttonLast;
	 JButton buttonLoud;
	 JButton buttonAdd;
	 JButton buttonDisplay;
	 JTextPane textLyrics;//歌词控件
	 JLabel playTime;//播放进度条控件
	 JLabel listbackground;
	 JLabel lyricsbackground;
	 JLabel musiclengthdisplay;
	 JList listPlayFile;//播放列表控件
	 JList listLyricsFile;//歌词列表控件
	 Timer PlayTimer;//进度条定时器对象
	 Timer LyricsTimer;//歌词定时器对象
	 Timer CirclePlayTimer;//控制循环播放
	 int MusicTime;//记录当前播放的歌曲的时间（单位：毫秒）
	 Main_Frame main_frame;
	 Menu_Frame menu_frame;
	 Icon play_icon=new ImageIcon(".//播放.jpg");
	 Icon pause_icon=new ImageIcon(".//暂停.jpg");
	 JSlider play_slider;
	 JSlider loud_slider;
	 ArrayList<Music> musiclist=new ArrayList<Music>();
	 ArrayList<Lyrics> lyricslist=new ArrayList<Lyrics>();
	 audioplay audioPlay=new audioplay();
	 public static int Playingmusic_index=-1;		//现在正在播放的音乐的编号
	 public static int Playinglyrics_index=-1;		//现在正在播放的歌词编号
	 
	 
	public Play_Frame(){

		setTitle("播放器");//软件名
		setBounds(160,100,1024,630);	//设置窗口大小		
		setLayout(null);//空布局			
		init();   //添加控件的操作封装成一个函数         
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void match(Main_Frame main_frame,Menu_Frame menu_frame)
	{
		this.main_frame=main_frame;
		this.menu_frame=menu_frame;
	}
	int getMusicTime(String dir)
	{
		return (int)(1.0*(new File(dir)).length()/1024/173*1000);
	}
	void LoadLyrics(String path)	//装载对应歌曲的歌词文件
	{
		System.out.println(path);
		lyricslist=new ArrayList<Lyrics>();
		try(FileReader reader=new FileReader(path);BufferedReader br=new BufferedReader(reader)) // 建立一个对象，它把文件内容转成计算机能读懂的语言
	    {
			String line;
	        while ((line=br.readLine())!=null) 
	        {
	        	System.out.println(line);
	        	int tmp=line.indexOf('#');
	        	lyricslist.add(new Lyrics(line.substring(0, tmp).trim(),Integer.valueOf(line.substring(tmp+1))));
	        }
	    } 
		catch(IOException e) 
		{
			e.printStackTrace();
	    }
		listLyricsFile.setListData(lyricslist.toArray());

	}
	void playmusic(int index)
	{
		audioPlay.SetPlayAudioPath(musiclist.get(index).Directory);
		audioPlay.play();
		buttonPlay.setIcon(pause_icon);
		Playingmusic_index=index;
		Playinglyrics_index=-1;
        MusicTime=getMusicTime(musiclist.get(index).Directory);
        System.out.println(MusicTime);
        LoadLyrics(musiclist.get(index).Directory.replaceAll(".wav", ".txt"));
		timerFun();
		//listPlayFile.setCellRenderer(index);
	}
	void Slide()
	{
		play_slider=new JSlider(0,100,0);
		play_slider.setUI(new MyPlaySliderUI(play_slider));
		play_slider.setBounds(200,553,500,50);
		play_slider.setPaintTicks(false);//不显示标尺
		play_slider.setBackground(Color.BLACK);
		play_slider.setOpaque(false);//设透明
		play_slider.addChangeListener(this);
		play_slider.setFocusable(false);
		add(play_slider);
		
		loud_slider=new JSlider(0,100,100);
		loud_slider.setUI(new MyLoudSliderUI(loud_slider));
		loud_slider.setBounds(810,561,100,25);
		loud_slider.setPaintTicks(false);//不显示标尺
		loud_slider.setBackground(Color.BLACK);
		loud_slider.setOpaque(false);//设透明
		loud_slider.setFocusable(false);
		play_slider.addChangeListener(this);
		add(loud_slider);
	}
	void init(){//添加的控件	  		
		 Icon img=new ImageIcon(".//主页图片.gif");     //创建图标对象			
		 background = new JLabel(img);//设置背景图片
		 background.setBounds(0,0,1024,595);//设置背景控件大小
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
		
		img=new ImageIcon(".//黑框.jpg");
		JLabel tmp=new JLabel(img);
		tmp.setBounds(0,550,1009,47);
		getLayeredPane().add(tmp, new Integer(Integer.MIN_VALUE+4));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
		
		listbackground=new JLabel(new ImageIcon(".//JList背景.jpg"));
		listbackground.setBounds(800,8,213,513);
		getLayeredPane().add(listbackground, new Integer(Integer.MIN_VALUE+8));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
		
		lyricsbackground=new JLabel(new ImageIcon(".//歌词列表背景.jpg"));
		lyricsbackground.setBounds(25,8,260,532);
		getLayeredPane().add(lyricsbackground, new Integer(Integer.MIN_VALUE+8));//背景图片控件置于最底层
		((JPanel)getContentPane()).setOpaque(false); //控件透明
		
		musiclengthdisplay=new JLabel();
		musiclengthdisplay.setBounds(710,550,50,50);
		getLayeredPane().add(musiclengthdisplay, new Integer(Integer.MIN_VALUE+8));
		((JPanel)getContentPane()).setOpaque(false);
		musiclengthdisplay.setForeground(Color.WHITE);
		add(musiclengthdisplay);
		
						
		  buttonPlay=new JButton();//添加播放按钮
	      buttonPlay.setBounds(80,560,28,26); //设置播放按钮大小
	      //创建播放图标对象
	      
	      buttonPlay.setIcon(play_icon);	      //设置播放图标
	      //buttonPlay.setIcon(pause_icon);
	      buttonPlay.setContentAreaFilled(false);//透明
	      buttonPlay.setBorderPainted(false);//去掉边框
	      buttonPlay.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonPlay);//添加播放按钮至窗口中
	      
		  buttonNext=new JButton();//添加播放按钮
	      buttonNext.setBounds(120,560,28,26); //设置播放按钮大小
	      Icon next_icon=new ImageIcon(".//下一曲.jpg");//创建播放图标对象
	      buttonNext.setIcon(next_icon);	      //设置播放图标
	      //buttonPlay.setIcon(pause_icon);
	      buttonNext.setContentAreaFilled(false);//透明
	      buttonNext.setBorderPainted(false);//去掉边框
	      buttonNext.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonNext.addActionListener(this);
	      buttonNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonNext);//添加播放按钮至窗口中
	      
		  buttonLast=new JButton();//添加播放按钮
		  buttonLast.setBounds(40,560,28,26); //设置播放按钮大小
	      Icon last_icon=new ImageIcon(".//上一曲.jpg");//创建播放图标对象
	      buttonLast.setIcon(last_icon);	      //设置播放图标
	      //buttonPlay.setIcon(pause_icon);
	      buttonLast.setContentAreaFilled(false);//透明
	      buttonLast.setBorderPainted(false);//去掉边框
	      buttonLast.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonLast.addActionListener(this);
	      buttonLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonLast);//添加播放按钮至窗口中
	      
		  buttonLoud=new JButton();//添加播放按钮
		  buttonLoud.setBounds(780,560,33,32); //设置播放按钮大小
	      Icon loud_icon=new ImageIcon(".//喇叭.png");//创建播放图标对象
	      buttonLoud.setIcon(loud_icon);	      //设置播放图标
	      //buttonPlay.setIcon(pause_icon);
	      buttonLoud.setContentAreaFilled(false);//透明
	      buttonLoud.setBorderPainted(false);//去掉边框
	      buttonLoud.setMargin(new Insets(0,0,0,0));//随图案变化
	      buttonLoud.addActionListener(this);
	      buttonLoud.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonLoud);//添加播放按钮至窗口中
	      
		  buttonAdd=new JButton();//添加播放按钮
		  buttonAdd.setBounds(960,470,50,50); //设置播放按钮大小
	      buttonAdd.setIcon(new ImageIcon(".//添加歌曲.jpg"));
		  buttonAdd.setContentAreaFilled(false);//透明
		  buttonAdd.setBorderPainted(false);//去掉边框
		  buttonAdd.setMargin(new Insets(0,0,0,0));//随图案变化
		  buttonAdd.addActionListener(this);
		  buttonAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonAdd);//添加播放按钮至窗口中
	      
		  buttonDisplay=new JButton();//添加播放按钮
		  buttonDisplay.setBounds(950,548,50,50); //设置播放按钮大小
		  buttonDisplay.setIcon(new ImageIcon(".//播放列表.jpg"));
		  buttonDisplay.setContentAreaFilled(false);//透明
		  buttonDisplay.setBorderPainted(false);//去掉边框
		  buttonDisplay.setMargin(new Insets(0,0,0,0));//随图案变化
		  buttonDisplay.addActionListener(this);
		  buttonDisplay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标变成小手手
	      add(buttonDisplay);//添加播放按钮至窗口中
	      
	      Slide();//设置进度条
		
	      listPlayFile=new JList();	  //创建播放列表 
	      listPlayFile.setBounds(800,8,213,513); //设置播放列表大小 
	      listPlayFile.setOpaque(false);//设置播放列表透明
	      //listPlayFile.setBackground(new Color(0,0,0,0));
	      //listPlayFile.setForeground(Color.red);//设置播放列表字体颜色
	      listPlayFile.setCellRenderer(new MusicListRender());
	      listPlayFile.addMouseListener(this);
	      add(listPlayFile);       //添加播放列表至窗口中*/
	      
	      listLyricsFile=new JList();
	      listLyricsFile.setBounds(8,8,270,513); 
	      listLyricsFile.setOpaque(false);//设置播放列表透明
	      //listLyricsFile.setBackground(new Color(0,0,0,0));
	      //listLyricsFile.setForeground(Color.red);//设置播放列表字体颜色
	      listLyricsFile.setCellRenderer(new LyricsListRender());
	      //listLyricsFile.addMouseListener(this);
	      add(listLyricsFile);       //添加播放列表至窗口中*/
	      
	      
	}	
	
	public  void timerFun()
	{//定时器函数
		if(PlayTimer!=null){PlayTimer.cancel();}//已经有定时器则关闭
		PlayTimer = new Timer();//创建定时器
		PlayTimer.schedule(new TimerTask(){  //匿名类
        	int nPlayTime=0;  
            public void run() { //定时器函数体
            	if(buttonPlay.getIcon()==pause_icon) nPlayTime+=1;
            	String tmp=nPlayTime/60+":"+nPlayTime%60;
            	musiclengthdisplay.setText(tmp);
            	play_slider.setValue(nPlayTime);
            	//playTime.setBounds(0, 324, nPlayTime+=5, 3);            	
            }
        },0,MusicTime/100);
		
		if(LyricsTimer!=null){LyricsTimer.cancel();}//已经有定时器则关闭
		LyricsTimer = new Timer();//创建定时器
		LyricsTimer.schedule(new TimerTask(){  //匿名类
        	int nPlayTime=0;  
            public void run() { //定时器函数体
            	if(buttonPlay.getIcon()==pause_icon) nPlayTime+=1;
            	if(nPlayTime>=lyricslist.get(Playinglyrics_index+1).time)
            	{
            		Playinglyrics_index+=1;
            	}
            	//playTime.setBounds(0, 324, nPlayTime+=5, 3);            	
            }
        },0,1000);
		
		
		if(CirclePlayTimer!=null){CirclePlayTimer.cancel();}
		CirclePlayTimer = new Timer();//创建定时器
		CirclePlayTimer.schedule(new TimerTask(){  //匿名类
            public void run() { //定时器函数体
            	if(play_slider.getValue()==100) 
            	{
            		buttonNext.doClick();
            	}
            	//playTime.setBounds(0, 324, nPlayTime+=5, 3);            	
            }
        },0,1000);
		
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buttonPlay)
		{
			if(buttonPlay.getIcon()==play_icon)
			{
				if(musiclist.isEmpty()==false)
				{
					int pos=listPlayFile.getSelectedIndex();
					System.out.println(pos);
					if(pos==-1) pos=0;	//如果没有选中，默认播放第一首
					playmusic(pos);
					buttonPlay.setIcon(pause_icon);
				}
			}
			else
			{
				audioPlay.stop();
				buttonPlay.setIcon(play_icon);
			}
		}
		if(e.getSource()==buttonNext)
		{
			if(Playingmusic_index!=-1)
			{
				playmusic((Playingmusic_index+1)%musiclist.size());
			}
		}
		if(e.getSource()==buttonLast)
		{
			if(Playingmusic_index!=-1)
			{
				playmusic((Playingmusic_index-1+musiclist.size())%musiclist.size());
			}
		}
		if(e.getSource()==buttonLoud)
		{
			System.out.println(playTime);
		}
		if(e.getSource()==buttonAdd)
		{
			try{
			 FileDialog openFile=new FileDialog(this,"打开文件");//创建打开文件对话框	
			 /*FilenameFilter filter = new FilenameFilter() {

			      public boolean accept(File dir, String name) {
			        return name.endsWith(".wav");
			      }
			    };
			 openFile.setFilenameFilter(filter);
			 openFile.setFile(".wav");*/
			 openFile.setVisible(true);//对话框可见
			 String playFileName=openFile.getFile();//获取打开的文件名
			 if(playFileName.endsWith(".wav")==true)
			 {
			 String playFileDirectory=openFile.getDirectory();//获取打开的文件路径
			 String playFile=playFileDirectory+playFileName;//完整的路径+文件名
			 System.out.println(playFileName+"   "+playFile);
			 Music tmp=new Music(playFileName,playFile,152,"周杰伦");
			 System.out.println("ok");
			 musiclist.add(tmp);
			 listPlayFile.setListData(musiclist.toArray());
			 }
			}
			catch(Exception e1)
			{
				System.out.println("选择文件错误！");
			}
			 
		}
		if(e.getSource()==buttonDisplay)
		{
			if(listbackground.isVisible()==true)
			{
				System.out.println("xixix");
				listbackground.setVisible(false);
				buttonAdd.setVisible(false);
				buttonAdd.setEnabled(true);
				listPlayFile.setVisible(false);
				listPlayFile.setEnabled(true);
			}
			else
			{
				listbackground.setVisible(true);
				buttonAdd.setVisible(true);
				buttonAdd.setEnabled(true);		//?????这么魔幻，为什么不是setEnabled(false)
				System.out.println("hahaha");
				listPlayFile.setVisible(true);
				listPlayFile.setEnabled(true);
			}
		}
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource()==play_slider)
		{
           System.out.println("当前值: " + play_slider.getValue());
		}
		if(e.getSource()==loud_slider)
		{
			
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==listPlayFile)
		{
			if(e.getClickCount()==2)
			{
				System.out.println("double click");
				int pos=listPlayFile.getSelectedIndex();
				System.out.println(pos);
				System.out.println(musiclist.get(pos).name);
				playmusic(pos);
			}
		}
		// TODO Auto-generated method stub
		
	}
}

