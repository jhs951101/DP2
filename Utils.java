package decorator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Utils {

	public static BufferedImage resizeImageWithHint(BufferedImage originalImage, 
			int imgWidth, int imgHeight, int type){

		BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, imgWidth, imgHeight, null);
		g.dispose();	
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}
	static public float[] getHSBFromRGB (Raster raster, int x, int y){
		int[] rgbColor = raster.getPixel(x, y, new int[3]);
		return Color.RGBtoHSB(rgbColor[0], rgbColor[1], rgbColor[2], null);
	}

	static public float getHueFromRGB (Raster raster, int x, int y){
		return getHSBFromRGB (raster, x, y)[0];
	}
	static public float getSaturationFromRGB (Raster raster, int x, int y){
		return getHSBFromRGB (raster, x, y)[1];
	}
	static public float getBrightnessFromRGB (Raster raster, int x, int y){
		return getHSBFromRGB (raster, x, y)[2];
	}
	static public float[] maxHSB(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();

		Raster raster = image.getData();
		float[] maxVal = { Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY };
		for (int y=0; y<imgHeight; ++y){
			for (int x=0; x<imgWidth; ++x){
				maxVal[0] = Math.max(maxVal[0], getHueFromRGB(raster, x, y));
				maxVal[1] = Math.max(maxVal[1], getSaturationFromRGB(raster, x, y));
				maxVal[2] = Math.max(maxVal[2], getBrightnessFromRGB(raster, x, y));
			}
		}
		return maxVal;
	}
	static public float[] minHSB(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();

		Raster raster = image.getData();
		float[] minVal = { Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY };
		for (int y=0; y<imgHeight; ++y){
			for (int x=0; x<imgWidth; ++x){
				minVal[0] = Math.min(minVal[0], getHueFromRGB(raster, x, y));
				minVal[1] = Math.min(minVal[1], getSaturationFromRGB(raster, x, y));
				minVal[2] = Math.min(minVal[2], getBrightnessFromRGB(raster, x, y));
			}
		}
		return minVal;
	}
	static public int[] getRGBFromHSB(float[] hsb) {
		int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
		Color color = new Color(rgb);
		int[] rgbColor = new int[3];
		//rgbColor[0] = (rgb>>16)&0xFF;
		//rgbColor[1] = (rgb>>8)&0xFF;
		//rgbColor[2] = rgb&0xFF;
		rgbColor[0] = color.getRed();
		rgbColor[1] = color.getGreen();
		rgbColor[2] = color.getBlue();
		
		return rgbColor;
	}

}
