/**
 * Created on 2016. 3. 22.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package decorator;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author cskim
 *
 */
public class DecoratorImageController {

	DecoratorImageModel model = null;
	private DecoratorImageFrameView frameView = null;
	private DrawPanelView drawPanelView = null;

	public DecoratorImageController(){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createFrameView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void createFrameView(){
		model = new defaultdecorator();
		
		frameView = new DecoratorImageFrameView(this, model);
		frameView.setVisible(true);
		model.setController(this);
		
	}
	/**
	 * 
	 */
	public void loadNDrawImageFile() {

		try {
			model.setMainImage(ImageIO.read(model.getSaveFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * @return the drawPanelView
	 */
	public DrawPanelView getDrawPanelView() {
		return drawPanelView;
	}
	/**
	 * @param drawPanelView the drawPanelView to set
	 */
	public void setDrawPanelView(DrawPanelView drawPanelView) {
		this.drawPanelView = drawPanelView;
	}
	
}
