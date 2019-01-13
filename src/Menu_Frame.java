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

class Menu_Frame extends JFrame implements ActionListener
{ //������
	 JLabel background;//�����ؼ�
	 JButton buttonPlay;//���Ű�ť
	 JButton buttonEdit;
	 JButton buttonOptions;
	 JButton buttonExit;
	 
	 Main_Frame main_frame;
	 Play_Frame play_frame;
	 
	public Menu_Frame(){

		setTitle("������");//�����
		setBounds(160,100,1024,630);	//���ô��ڴ�С		
		setLayout(null);//�ղ���			
		init();   //��ӿؼ��Ĳ�����װ��һ������         
		//setVisible(true);//��������������ִ��
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void match(Main_Frame main_frame,Play_Frame play_frame)
	{
		this.main_frame=main_frame;
		this.play_frame=play_frame;
	}
	void init(){//��ӵĿؼ�	  		
		 Icon img=new ImageIcon(".//�˵�ͼƬ.gif");     //����ͼ�����			
		 background = new JLabel(img);//���ñ���ͼƬ
		 background.setBounds(0,0,1020,595);//���ñ����ؼ���С
	     getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));//����ͼƬ�ؼ�������ײ�
		((JPanel)getContentPane()).setOpaque(false); //�ؼ�͸��
						
		  buttonPlay=new JButton();//��Ӳ��Ű�ť
	      buttonPlay.setBounds(510,140,290,60); //���ò��Ű�ť��С
	      //Icon icon=new ImageIcon(".//play.jpg");//��������ͼ�����
	     // buttonPlay.setIcon(icon);	      //���ò���ͼ��
	      buttonPlay.setContentAreaFilled(false);//͸��
	      buttonPlay.setBorderPainted(false);//ȥ���߿�
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonPlay.addActionListener(this);
	      buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonPlay);//��Ӳ��Ű�ť��������
	      
		  buttonEdit=new JButton();//���ð�ť
	      buttonEdit.setBounds(530,220,290,60); //���ò��Ű�ť��С
	      //Icon icon=new ImageIcon(".//play.jpg");//��������ͼ�����
	     // buttonPlay.setIcon(icon);	      //���ò���ͼ��
	      buttonEdit.setContentAreaFilled(false);//͸��
	      buttonEdit.setBorderPainted(false);//ȥ���߿�
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonEdit.addActionListener(this);
	      add(buttonEdit);//��Ӳ��Ű�ť��������
	      
		  buttonOptions=new JButton();//���ð�ť
	      buttonOptions.setBounds(530,300,290,60); //���ò��Ű�ť��С
	      //Icon icon=new ImageIcon(".//play.jpg");//��������ͼ�����
	     // buttonPlay.setIcon(icon);	      //���ò���ͼ��
	      buttonOptions.setContentAreaFilled(false);//͸��
	      buttonOptions.setBorderPainted(false);//ȥ���߿�
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonOptions.addActionListener(this);
	      add(buttonOptions);//��Ӳ��Ű�ť��������
	      
		  buttonExit=new JButton();//���ð�ť
	      buttonExit.setBounds(510,380,290,60); //���ò��Ű�ť��С
	      //Icon icon=new ImageIcon(".//play.jpg");//��������ͼ�����
	     // buttonPlay.setIcon(icon);	      //���ò���ͼ��
	      buttonExit.setContentAreaFilled(false);//͸��
	      buttonExit.setBorderPainted(false);//ȥ���߿�
	      //buttonPlay.setMargin(new Insets(0,0,0,0));//��ͼ���仯
	      buttonExit.addActionListener(this);
	      buttonExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�����С����
	      add(buttonExit);//��Ӳ��Ű�ť��������
		
	 
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

