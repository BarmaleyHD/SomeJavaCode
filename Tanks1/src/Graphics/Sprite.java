package Graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import utils.Utils;

public class Sprite {
	
	private SpriteSheet sheet;
	private float scale;
	private BufferedImage image;
	
	// Constructor
	public Sprite(SpriteSheet sheet, float scale){
		this.scale = scale;
		this.sheet = sheet;
		image  = sheet.getSprite(0);
		image = Utils.resize(image, (int)(image.getWidth() * scale), (int)(image.getHeight() * scale));
	}
	
	// Draws image on set X and Y 
	public void render(Graphics2D g, float x, float y){		
		g.drawImage(image, (int)(x), (int)(y), null);
		
	}

}
