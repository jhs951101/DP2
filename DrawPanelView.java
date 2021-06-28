/**
 * Created on 2016. 3. 22.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package decorator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawPanelView 	extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DecoratorImageModel model = null;

	public DrawPanelView(DecoratorImageModel model){
		this.model = model;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/*if (model.getMainImage()!=null) {
		JFrame f = new JFrame("3");
		JLabel p = new JLabel();
		f.add(p);
		p.setIcon(new ImageIcon(model.getMainImage()));
		f.setVisible(true);
		System.out.println("paint");
		}*/
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int viewWidth = this.getWidth();
		int viewHeight = this.getHeight();
		if (model.getMainImage()!=null) {
			BufferedImage viewImage = 
					Utils.resizeImageWithHint(model.getMainImage(), viewWidth, viewHeight, 
							model.getMainImage().getType());

			g2.drawImage(viewImage, 0, 0, null);
			
		}
		
	}
	
	public void setModel(DecoratorImageModel model) {
		this.model = model;
	}

}
