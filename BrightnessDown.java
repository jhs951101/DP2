package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class BrightnessDown extends ImageDecorator {
	
	DecoratorImageModel model = null;

	public BrightnessDown(DecoratorImageModel model){
		this.model = model;
	}
	
	@Override
	public BufferedImage changeImage(){
		
		BufferedImage image = model.getMainImage();
		float incVal = -0.1f;
		
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		BufferedImage result = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		Raster raster = image.getData();
		WritableRaster wraster = raster.createCompatibleWritableRaster();
		
		for (int y=0; y<imgHeight; ++y){
			for (int x=0; x<imgWidth; ++x){
				float[] hsb = Utils.getHSBFromRGB(raster, x, y);
				hsb[2] += incVal;
				if (hsb[2]<0f) hsb[2] = 0f;
				if (hsb[2]>1f) hsb[2] = 1f;
				int[] rgb = Utils.getRGBFromHSB(hsb);
				wraster.setPixel(x, y, rgb);
			}
		}
		result.setData(wraster);
		return result;
	}
	
}