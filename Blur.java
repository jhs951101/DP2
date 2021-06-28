package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Blur extends ImageDecorator {
	
	DecoratorImageModel model = null;

	public Blur(DecoratorImageModel model){
		this.model = model;
	}
	
	@Override
	public BufferedImage changeImage(){
		
		BufferedImage bimage = model.getMainImage();
		
		BufferedImage result = new BufferedImage(bimage.getWidth(), bimage.getHeight(),
				bimage.getType());

		int n = 5;
		Kernel kernel = KernelFactory.createBlurKernel(n);
		ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
		convolveOp.filter(bimage, result);

		return result;
	}
	
}