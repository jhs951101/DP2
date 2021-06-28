package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Sharpen extends ImageDecorator {
	
	DecoratorImageModel model = null;
	
	public Sharpen(DecoratorImageModel model){
		this.model = model;
	}
	
	@Override
	public BufferedImage changeImage(){
		
		BufferedImage bimage = model.getMainImage();
		
		BufferedImage result = new BufferedImage(bimage.getWidth(), bimage.getHeight(),
				bimage.getType());

		Kernel kernel = KernelFactory.createSharpenKernel();
		ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
		convolveOp.filter(bimage, result);
		return result;
	}
	
}