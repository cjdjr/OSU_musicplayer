import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GraphicsEnvironment;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Vector;

class Music
{
	String name;
	String Directory;
	int time;
	String singer;
	Music(String name,String Directory,int time,String singer)
	{
		this.name=name;
		this.Directory=Directory;
		this.time=time;
		this.singer=singer;
	}
	@Override
	public String toString()
	{
		return name;
	}
}
class Lyrics
{
	String text;
	int time;
	Lyrics(String text,int time)
	{
		this.text=text;
		this.time=time;
	}
	@Override
	public String toString()
	{
		return text;
	}
}

class audioplay{//播放音乐类
	   AudioClip adc;// 声音音频剪辑对象
	   URL url;
	   boolean adcFlag=false;
	   boolean playFlag=false;
	   void SetPlayAudioPath(String path){
		   path="file:"+path;
	      try{  
	           url = new URL(path);  
	           if(adcFlag==true){adc.stop();playFlag=false;}
	           adc = Applet.newAudioClip(url);
	           adcFlag=true;
	         }
	      catch (MalformedURLException e1) {
	              e1.printStackTrace();  
	         }  
	   }
	   void play(){     //开始播放
		   System.out.println("Playing...");
	           adc.play();
	           playFlag=true;
	   }   
	   void stop(){     //停止播放
		   System.out.println("Stoping...");
	           adc.stop();  
	           playFlag=false;
	   }
	}

public class play
{
	static Main_Frame main_frame=new Main_Frame();
	static Menu_Frame menu_frame=new Menu_Frame();
	static Play_Frame play_frame=new Play_Frame();
@SuppressWarnings("unchecked")//忽略警告
    public static void main(String[] args) 
  { 
	
        main_frame.match(menu_frame,play_frame);//创建聊天程序窗口  
        
        menu_frame.match(main_frame,play_frame);
        
        play_frame.match(main_frame,menu_frame);
        
		main_frame.setVisible(true);

		//play_frame.setVisible(true);
		
		/*
		//看一看本地有哪些字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontList = ge.getAvailableFontFamilyNames();
        for(int i=0;i<fontList.length;i++)
        {
           System.out.println(fontList[i]);
        }
        */

    }
}