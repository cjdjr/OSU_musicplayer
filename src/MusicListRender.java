import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class MusicListRender extends JButton implements ListCellRenderer 
{
		/*
		 重写JList样式
		 */

		public MusicListRender() 
		{
            this.setOpaque(false);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            
            System.out.println(index);
            this.setFont(new Font("幼圆", Font.PLAIN, 16));
            this.setBorderPainted(false);
            Color background = Color.WHITE;
            Color foreground = Color.BLACK;

            if (isSelected) {
                this.setFont(new Font("幼圆", Font.BOLD, 16));
            }
            //System.out.println(Play_Frame.Playingmusic_index);
            if(index==Play_Frame.Playingmusic_index)//正在播放的歌曲颜色变红
            {
            	foreground=Color.RED;
            }
            setBackground(background);
            setForeground(foreground);
            /*if(index>=0)
            {
            	setForeground(Color.RED);
            }*/

            return this;

        }


    }
class LyricsListRender extends JButton implements ListCellRenderer 
{
		/*
		 重写JList样式
		 */

		public LyricsListRender() 
		{
            this.setOpaque(false);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            
            this.setFont(new Font("方正舒体", Font.PLAIN, 18));
            this.setBorderPainted(false);
            Color background = Color.WHITE;
            Color foreground = Color.BLACK;

            //System.out.println(Play_Frame.Playingmusic_index);
            if(index==Play_Frame.Playinglyrics_index)//正在播放的歌曲颜色变红
            {
            	foreground=Color.YELLOW;
            }
            setBackground(background);
            setForeground(foreground);
            /*if(index>=0)
            {
            	setForeground(Color.RED);
            }*/

            return this;

        }


    }