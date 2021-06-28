package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EdgeDetect extends ImageDecorator {

	DecoratorImageModel model = null;

	public EdgeDetect(DecoratorImageModel model){
		this.model = model;
	}
	
	@Override
	public BufferedImage changeImage (){
		
		/*JFrame f = new JFrame("0");
		JLabel p = new JLabel();
		f.add(p);
		p.setIcon(new ImageIcon(model.getMainImage()));
		f.setVisible(true);*/
		
		BufferedImage bimage = model.getMainImage();
		
		BufferedImage result = new BufferedImage(bimage.getWidth(), bimage.getHeight(),
				bimage.getType());

		Kernel kernel = KernelFactory.createEdgeKernel();
		ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
		convolveOp.filter(bimage, result);
		
		/*JFrame ff = new JFrame("1");
		JLabel pp = new JLabel();
		ff.add(pp);
		pp.setIcon(new ImageIcon(result));
		ff.setVisible(true);*/
		
		return result;
	}
	
}