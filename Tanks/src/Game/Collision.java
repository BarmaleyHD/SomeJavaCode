package Game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Collision {
	
	private static final int TILE_SIZE = 16;
	 
	public Collision(){
	}
	
	public static boolean detectCollision(Rectangle player, Integer[][] map){
		boolean result = false;
		boolean as = false;
		Rectangle r;
		for (int i = 0; i < map.length; i++){
			for (int j = i + 1; j < map.length; j++){
				r = new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				result = r.intersects(player);
				if(result !=  as){
					System.out.println("Result: " + result + "");
					as = result;					
				}
			}
		}
		return result;
	}

}
