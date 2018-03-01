package Game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DisplayGame.Display;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Graphics.TextureAtlas;
import IO.Input;



public class Player extends Entity {
	
	public static final int SPRITE_SCALE = 16;
	public static final int SPRITES_PER_HEADING = 1;
	private Graphics2D graphics;
	private ArrayList<Shell> shells = new ArrayList<Shell>();
	private TextureAtlas atlas;
	
	private enum Heading{
		NORTH(0 * SPRITE_SCALE,0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
		EAST(6 * SPRITE_SCALE,0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
		SOUTH(4 * SPRITE_SCALE,0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
		WEST(2 * SPRITE_SCALE,0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
		
		private int x,y,h,w; 
		
		Heading(int x, int y, int h, int w){
			this.x = x;
			this.y = y;
			this.h = h;
			this.w = w;			
		}
		
		protected BufferedImage texture(TextureAtlas atlas){
			 return atlas.cut(x, y, w, h);
				
		}
		
		@Override
		public String toString() {
			return this.EAST.toString();			
		}
	}
	
	private Heading heading;
	private Map<Heading, Sprite> spriteMap;
	private float scale;
	private float speed;
	

	public Player(float x, float y,float scale, float speed, TextureAtlas atlas) {
		super(EntityType.Player, x, y);
		super.active = true;
		
		// Default position on the start
		heading = Heading.NORTH;
		spriteMap = new HashMap<Heading, Sprite>();
		this.scale = scale;
		this.speed = speed;
		this.atlas = atlas;
		
		
		for(Heading h : Heading.values()){
			SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE );
			Sprite sprite = new Sprite(sheet, scale);
			spriteMap.put(h, sprite);
		}
	}
		

	@Override
	public void update(Input input) {
		graphics = Display.getGraphics();
		
	
		float newX = x;
		float newY = y;
		
		if(input.getKey(KeyEvent.VK_UP)){
			newY -=speed;
			heading = Heading.NORTH;
		}else if(input.getKey(KeyEvent.VK_RIGHT)){
			newX += speed;
			heading = Heading.EAST;			
		}else if(input.getKey(KeyEvent.VK_DOWN)){
			newY += speed;
			heading = Heading.SOUTH;	
		}else if(input.getKey(KeyEvent.VK_LEFT)){
			newX -= speed;
			heading = Heading.WEST;		
		}
		
		
		
		if(newX < 0){
			newX = 0;
		}else if(newX>= Game.WIDTH - SPRITE_SCALE * scale){
			newX = Game.WIDTH - SPRITE_SCALE * scale;
		}
		
		if(newY < 0){
			newY = 0;
		}else if(newY>= Game.HEIGHT - SPRITE_SCALE * scale){
			newY = Game.HEIGHT - SPRITE_SCALE * scale;
		}
		
		x = newX;
		y = newY;
		
		if(input.getKey(KeyEvent.VK_SPACE)){	
			Shell s;
			switch (heading) {
			case EAST: s = new Shell(x+30, y, 2, 2, atlas, "East");
			shells.add(s);
			break;
			case NORTH: s = new Shell(x, y-30, 2, 2, atlas, "NORTH");
			shells.add(s);
			break;
			case WEST: s = new Shell(x-30, y, 2, 2, atlas, "WEST");
			shells.add(s);
			break;
			case SOUTH: s = new Shell(x, y+30, 2, 2, atlas, "SOUTH");
			shells.add(s);
			break;
			}	 			
		} 
	}
	
	public ArrayList<Shell> getShells(){
		return shells;
	}

	@Override
	public void render(Graphics2D g) {
		
		spriteMap.get(heading).render(g, x, y);		
		
	}

	

}
