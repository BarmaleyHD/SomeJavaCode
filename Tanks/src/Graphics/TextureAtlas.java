package Graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.ResourceLoader;

public class TextureAtlas {
	
	BufferedImage image;
	
	public TextureAtlas(String imageName){
		image = ResourceLoader.loadImage(imageName);
	}
	
	public BufferedImage cut(int x, int y, int w, int h){
		BufferedImage subimage = getImage(image.getSubimage(x, y, w, h), w , h);
		return subimage;		
	}
	
	private BufferedImage getImage(BufferedImage oldImage, int w, int h){
		BufferedImage outputImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics g = outputImg.createGraphics();
	    g.drawImage(oldImage, 0, 0, w, h, null);
	    g.dispose();
	    return outputImg;
	}

}
