import java.applet.Applet;  
import java.applet.AudioClip; 
import java.net.MalformedURLException;  
import java.net.URL;  
import java.io.*;
import javax.swing.text.*;
import javax.swing.text.StyleContext.NamedStyle;
import javax.swing.*;
import static javax.swing.JFrame.*; //����JFrame�ľ�̬����
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import java.util.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Play_Frame extends JFrame implements ActionListener,ChangeListener,MouseListener
{ //������
	 JLabel background;//�����ؼ�
	 JButton buttonPlay;//���Ű�ť
	 JButton buttonNext;
	 JButton buttonLast;
	 JButton buttonLoud;
	 JButton buttonAdd;
	 JButton buttonDisplay;
	 JTextPane textLyrics;//��ʿؼ�
	 JLabel playTime;//���Ž������ؼ�
	 JLabel listbackground;
	 JLabel lyricsbackground;
	 JLabel musiclengthdisplay;
	 JList listPlayFile;//�����б�ؼ�
	 JList listLyricsFile;//����б�ؼ�
	 Timer PlayTimer;//��������ʱ������
	 Timer LyricsTimer;//��ʶ�ʱ������
	 Timer CirclePlayTimer;//����ѭ������
	 int MusicTime;//��¼��ǰ���ŵĸ�����ʱ�䣨��λ�����룩
	 Main_Frame main_frame;
	 Menu_Frame menu_frame;
	 Icon play_icon=new ImageIcon(".//����.jpg");
	 Icon pause_icon=new ImageIcon(".//��ͣ.jpg");
	 JSlider play_slider;
	 JSlider loud_slider;
	 ArrayList<Music> musiclist=new ArrayList<Music>();
	 ArrayList<Lyrics> lyricslist=new ArrayList<Lyrics>();
	 audioplay audioPlay=new audioplay();
	 public static int Playingmusic_index=-1;		//�������ڲ��ŵ����ֵı��
	 public static int Playinglyrics_index=-1;		//�������ڲ��ŵĸ�ʱ��
	 
	 
	public Play_Frame(){

		setTitle("������");//�����
		setBounds(160,100,1024,630);	//���ô��ڴ�С		
		setLayout(null);//�ղ���			
		init();   //��ӿؼ��Ĳ�����װ��һ������         
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
	void LoadLyrics(String path)	//װ�ض�Ӧ�����ĸ���ļ�
	{
		System.out.println(path);
		lyricslist=new ArrayList<Lyrics>();
		try(FileReader reader=new FileReader(path);BufferedReader br=new BufferedReader(reader)) // ����һ�����������ļ�����ת�ɼ�����ܶ���������
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
		play_slider.setPaintTicks(false);//����ʾ���
		play_slider.setBackground(Color.BLACK);
		play_slider.setOpaque(false);//��͸��
		play_slider.addChangeListener(this);
		play_slider.setFocusable(false);
		add(play_slider);
		
		loud_slider=new JSlider(0,100,100);
		loud_slider.setUI(new MyLoudSliderUI(loud_slider));
		loud_slider.setBounds(810,561,100,25);
		loud_slider.setPaintTicks(false);//����ʾ���
		loud_slider.setBackground(Color.BLACK);
		loud_slider.setOpaque(false);//��͸��
		loud_slider.setFocusable(false);
		play_slider.addChangeListener(this);
		add(loud_slider);
	}
	void init(){//��ӵĿؼ�	  		
		 Icon img=new ImageIcon(".//��ҳͼƬ.gif");     //����ͼ�����			
		 background = new JLabel(img);//���ñ���ͼƬ
		 background.setBounds(0,0,1024,595);//���ñ����ؼ���С
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
		
		img=new ImageIcon(".//�ڿ�.jpg");
		JLabel tmp=new JLabel(img);
		tmp.setBounds(0,550,1009,47);
		getLayeredPane().add(tmp, new Integer(Integer.MIN_VALUE+4));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
		
		listbackground=new JLabel(new ImageIcon(".//JList����.jpg"));
		listbackground.setBounds(800,8,213,513);
		getLayeredPane().add(listbackground, new Integer(Integer.MIN_VALUE+8));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
		
		lyricsbackground=new JLabel(new ImageIcon(".//����б���.jpg"));
		lyricsbackground.setBounds(25,8,260,532);
		getLayeredPane().add(lyricsbackground, new Integer(Integer.MIN_VALUE+8));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
		
		musiclengthdisplay=new JLabel();
		musiclengthdisplay.setBounds(710,550,50,50);
		getLayeredPane().add(musiclengthdisplay, new Integer(Integer.MIN_VALUE+8));
		((JPanel)getContentPane()).setOpaque(false);
		musiclengthdisplay.setForeground(Color.WHITE);
		add(musiclengthdisplay);
		
						
		  buttonPlay=new JButton();//��Ӳ��Ű�ť
	      buttonPlay.setBounds(80,560,28,26); //���ò��Ű�ť��С
	      //��������ͼ�����
	      
	      buttonPlay.setIcon(play_icon);	      //���ò���ͼ��
	      //buttonPlay.setIcon(pause_icon);
	      buttonPlay.setContentAreaFilled(false);//͸��
	      buttonPlay.setBorderPainted(false);//ȥ���߿�
	      buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonPlay);//��Ӳ��Ű�ť��������
	      
		  buttonNext=new JButton();//��Ӳ��Ű�ť
	      buttonNext.setBounds(120,560,28,26); //���ò��Ű�ť��С
	      Icon next_icon=new ImageIcon(".//��һ��.jpg");//��������ͼ�����
	      buttonNext.setIcon(next_icon);	      //���ò���ͼ��
	      //buttonPlay.setIcon(pause_icon);
	      buttonNext.setContentAreaFilled(false);//͸��
	      buttonNext.setBorderPainted(false);//ȥ���߿�
	      buttonNext.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonNext.addActionListener(this);
	      buttonNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonNext);//��Ӳ��Ű�ť��������
	      
		  buttonLast=new JButton();//��Ӳ��Ű�ť
		  buttonLast.setBounds(40,560,28,26); //���ò��Ű�ť��С
	      Icon last_icon=new ImageIcon(".//��һ��.jpg");//��������ͼ�����
	      buttonLast.setIcon(last_icon);	      //���ò���ͼ��
	      //buttonPlay.setIcon(pause_icon);
	      buttonLast.setContentAreaFilled(false);//͸��
	      buttonLast.setBorderPainted(false);//ȥ���߿�
	      buttonLast.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonLast.addActionListener(this);
	      buttonLast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonLast);//��Ӳ��Ű�ť��������
	      
		  buttonLoud=new JButton();//��Ӳ��Ű�ť
		  buttonLoud.setBounds(780,560,33,32); //���ò��Ű�ť��С
	      Icon loud_icon=new ImageIcon(".//����.png");//��������ͼ�����
	      buttonLoud.setIcon(loud_icon);	      //���ò���ͼ��
	      //buttonPlay.setIcon(pause_icon);
	      buttonLoud.setContentAreaFilled(false);//͸��
	      buttonLoud.setBorderPainted(false);//ȥ���߿�
	      buttonLoud.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonLoud.addActionListener(this);
	      buttonLoud.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonLoud);//��Ӳ��Ű�ť��������
	      
		  buttonAdd=new JButton();//��Ӳ��Ű�ť
		  buttonAdd.setBounds(960,470,50,50); //���ò��Ű�ť��С
	      buttonAdd.setIcon(new ImageIcon(".//��Ӹ���.jpg"));
		  buttonAdd.setContentAreaFilled(false);//͸��
		  buttonAdd.setBorderPainted(false);//ȥ���߿�
		  buttonAdd.setMargin(new Insets(0,0,0,0));//��ͼ���仯
		  buttonAdd.addActionListener(this);
		  buttonAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonAdd);//��Ӳ��Ű�ť��������
	      
		  buttonDisplay=new JButton();//��Ӳ��Ű�ť
		  buttonDisplay.setBounds(950,548,50,50); //���ò��Ű�ť��С
		  buttonDisplay.setIcon(new ImageIcon(".//�����б�.jpg"));
		  buttonDisplay.setContentAreaFilled(false);//͸��
		  buttonDisplay.setBorderPainted(false);//ȥ���߿�
		  buttonDisplay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
		  buttonDisplay.addActionListener(this);
		  buttonDisplay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonDisplay);//��Ӳ��Ű�ť��������
	      
	      Slide();//���ý�����
		
	      listPlayFile=new JList();	  //���������б� 
	      listPlayFile.setBounds(800,8,213,513); //���ò����б��С 
	      listPlayFile.setOpaque(false);//���ò����б�͸��
	      //listPlayFile.setBackground(new Color(0,0,0,0));
	      //listPlayFile.setForeground(Color.red);//���ò����б�������ɫ
	      listPlayFile.setCellRenderer(new MusicListRender());
	      listPlayFile.addMouseListener(this);
	      add(listPlayFile);       //��Ӳ����б���������*/
	      
	      listLyricsFile=new JList();
	      listLyricsFile.setBounds(8,8,270,513); 
	      listLyricsFile.setOpaque(false);//���ò����б�͸��
	      //listLyricsFile.setBackground(new Color(0,0,0,0));
	      //listLyricsFile.setForeground(Color.red);//���ò����б�������ɫ
	      listLyricsFile.setCellRenderer(new LyricsListRender());
	      //listLyricsFile.addMouseListener(this);
	      add(listLyricsFile);       //��Ӳ����б���������*/
	      
	      
	}	
	
	public  void timerFun()
	{//��ʱ������
		if(PlayTimer!=null){PlayTimer.cancel();}//�Ѿ��ж�ʱ����ر�
		PlayTimer = new Timer();//������ʱ��
		PlayTimer.schedule(new TimerTask(){  //������
        	int nPlayTime=0;  
            public void run() { //��ʱ��������
            	if(buttonPlay.getIcon()==pause_icon) nPlayTime+=1;
            	String tmp=nPlayTime/60+":"+nPlayTime%60;
            	musiclengthdisplay.setText(tmp);
            	play_slider.setValue(nPlayTime);
            	//playTime.setBounds(0, 324, nPlayTime+=5, 3);            	
            }
        },0,MusicTime/100);
		
		if(LyricsTimer!=null){LyricsTimer.cancel();}//�Ѿ��ж�ʱ����ر�
		LyricsTimer = new Timer();//������ʱ��
		LyricsTimer.schedule(new TimerTask(){  //������
        	int nPlayTime=0;  
            public void run() { //��ʱ��������
            	if(buttonPlay.getIcon()==pause_icon) nPlayTime+=1;
            	if(nPlayTime>=lyricslist.get(Playinglyrics_index+1).time)
            	{
            		Playinglyrics_index+=1;
            	}
            	//playTime.setBounds(0, 324, nPlayTime+=5, 3);            	
            }
        },0,1000);
		
		
		if(CirclePlayTimer!=null){CirclePlayTimer.cancel();}
		CirclePlayTimer = new Timer();//������ʱ��
		CirclePlayTimer.schedule(new TimerTask(){  //������
            public void run() { //��ʱ��������
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
					if(pos==-1) pos=0;	//���û��ѡ�У�Ĭ�ϲ��ŵ�һ��
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
			 FileDialog openFile=new FileDialog(this,"���ļ�");//�������ļ��Ի���	
			 /*FilenameFilter filter = new FilenameFilter() {

			      public boolean accept(File dir, String name) {
			        return name.endsWith(".wav");
			      }
			    };
			 openFile.setFilenameFilter(filter);
			 openFile.setFile(".wav");*/
			 openFile.setVisible(true);//�Ի���ɼ�
			 String playFileName=openFile.getFile();//��ȡ�򿪵��ļ���
			 if(playFileName.endsWith(".wav")==true)
			 {
			 String playFileDirectory=openFile.getDirectory();//��ȡ�򿪵��ļ�·��
			 String playFile=playFileDirectory+playFileName;//������·��+�ļ���
			 System.out.println(playFileName+"   "+playFile);
			 Music tmp=new Music(playFileName,playFile,152,"�ܽ���");
			 System.out.println("ok");
			 musiclist.add(tmp);
			 listPlayFile.setListData(musiclist.toArray());
			 }
			}
			catch(Exception e1)
			{
				System.out.println("ѡ���ļ�����");
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
				buttonAdd.setEnabled(true);		//?????��ôħ�ã�Ϊʲô����setEnabled(false)
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
           System.out.println("��ǰֵ: " + play_slider.getValue());
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

