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

class Main_Frame extends JFrame implements ActionListener
{ //������
	 JLabel background;//�����ؼ�
	 JButton buttonPlay;//���Ű�ť

	 Menu_Frame menu_frame;
	 Play_Frame play_frame;
	 
	public Main_Frame(){
		

		setTitle("������");//�����
		setBounds(160,100,1024,630);	//���ô��ڴ�С		
		setLayout(null);//�ղ���			
		init();   //��ӿؼ��Ĳ�����װ��һ������         
		setVisible(true);//��������������ִ��
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void match(Menu_Frame menu_frame,Play_Frame play_frame)
	{
		this.menu_frame=menu_frame;
		this.play_frame=play_frame;
	}
	void init(){//��ӵĿؼ�	  		
		 Icon img=new ImageIcon(".//��ҳͼƬ.gif");     //����ͼ�����			
		 background = new JLabel(img);//���ñ���ͼƬ
		 background.setBounds(0,0,1020,595);//���ñ����ؼ���С
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
						
		  buttonPlay=new JButton();//��Ӳ��Ű�ť
	      buttonPlay.setBounds(330,115,340,340); //���ò��Ű�ť��С
	      //Icon icon=new ImageIcon(".//play.jpg");//��������ͼ�����
	     // buttonPlay.setIcon(icon);	      //���ò���ͼ��
	      buttonPlay.setContentAreaFilled(false);//͸��
	      buttonPlay.setBorderPainted(false);//ȥ���߿�
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      add(buttonPlay);//��Ӳ��Ű�ť��������
		
	}		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("yes");
		play_frame.setVisible(false);
		menu_frame.setVisible(true);
		this.setVisible(false);
		
	}
}

