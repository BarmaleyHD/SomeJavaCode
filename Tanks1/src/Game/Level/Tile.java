package Game.Level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Utils;

/**
 * Creates 
 * 
 * @author Dmitry
 *
 */
public class Tile {
	private BufferedImage image;
	private TileType type;
	
	protected Tile(BufferedImage image, int scale, TileType type){
		this.type = type;
		this.image = Utils.resize(image, image.getWidth()*scale, image.getHeight()*scale);
	}
	
	protected void render(Graphics g, int x, int y){
		g.drawImage(image, x, y, null);
	}	
	
	protected TileType type(){
		return type;
	}
}
