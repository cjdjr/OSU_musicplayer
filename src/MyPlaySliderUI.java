import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class MyPlaySliderUI extends BasicSliderUI
{

	public MyPlaySliderUI(JSlider b) {
		super(b);
	}
	@Override
	public void paintThumb(Graphics g) {
        //绘制指示物
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.gray);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width,
                thumbRect.height);//修改为圆形
        //也可以帖图(利用鼠标事件转换image即可体现不同状态)
        //g2d.drawImage(image, thumbRect.x, thumbRect.y, thumbRect.width,thumbRect.height,null);

	}
	public void paintTrack(Graphics g) {
        //绘制刻度的轨迹
		int cy,cw;
		Rectangle trackBounds = trackRect;
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(new Color(211, 211, 211));//将背景设为灰色
			cy = (trackBounds.height/2) - 2;
			cw = trackBounds.width;
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.translate(trackBounds.x, trackBounds.y + cy);
			g2.fillRect(0, -cy + 5, cw, cy);
			
			int trackLeft = 0;
			int trackRight = 0;
			trackRight = trackRect.width - 1;

			int middleOfThumb = 0;
			int fillLeft = 0;
			int fillRight = 0;
			//换算坐标
			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
			middleOfThumb -= trackRect.x;
			
			if (!drawInverted()) {
				fillLeft = !slider.isEnabled() ? trackLeft : trackLeft + 1;
				fillRight = middleOfThumb;
				} else {
				fillLeft = middleOfThumb;
				fillRight = !slider.isEnabled() ? trackRight - 1
				: trackRight - 2;
				}
			//设定渐变,在这里从红色变为红色,则没有渐变,滑块划过的地方自动变成红色
			g2.setPaint(new GradientPaint(0, 0, Color.black, cw, 0,
					Color.black, true));
			g2.fillRect(0, -cy + 5, fillRight - fillLeft, cy);
					
			g2.setPaint(slider.getBackground());
			/*g2.fillRect(10, 10, cw, 5);

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_OFF);*/
			g2.translate(-trackBounds.x, -(trackBounds.y + cy));   				
		}
		else {
			super.paintTrack(g);
			}
	}
}
