/**
 * Created on 2016. 3. 22.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package decorator;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author cskim
 *
 */
public abstract class DecoratorImageModel {
	
	private DecoratorImageFrameView frameView = null;
	private DecoratorImageController controller = null;
	
	private File saveFile = null;
	
	private BufferedImage mainImage = null;

	public DecoratorImageModel(){
		
	}
	/**
	 * @return the frameView
	 */
	public DecoratorImageFrameView getFrameView() {
		return frameView;
	}
	/**
	 * @param frameView the frameView to set
	 */
	public void setFrameView(DecoratorImageFrameView frameView) {
		this.frameView = frameView;
	}
	/**
	 * @return the controller
	 */
	public DecoratorImageController getController() {
		return controller;
	}
	/**
	 * @param controller the controller to set
	 */
	public void setController(DecoratorImageController controller) {
		this.controller = controller;
	}
	/**
	 * @return the saveFile
	 */
	public File getSaveFile() {
		return saveFile;
	}
	/**
	 * @param saveFile the saveFile to set
	 */
	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
	/**
	 * @return the mainImage
	 */
	public BufferedImage getMainImage() {
		return mainImage;
	}
	/**
	 * @param mainImage the mainImage to set
	 */
	public void setMainImage(BufferedImage mainImage) {
		this.mainImage = mainImage;
	}
	
	public abstract BufferedImage changeImage();
	
}
