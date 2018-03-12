package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import DisplayGame.Display;
import Graphics.Sprite;
import Graphics.SpriteSheet;
import Graphics.TextureAtlas;
import IO.Input;



public class Shell extends Entity {
		
		public static final int SPRITE_SCALE = 16;
		public static final int SPRITES_PER_HEADING = 1;
		private Graphics2D graphics;
		private boolean turn;
		private int counter;
		private boolean explode = false;
		private String dir;
		
		private enum Status{
			SMALL(16 * SPRITE_SCALE,6 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
			BIG(17 * SPRITE_SCALE,6 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
			EXPLODE_BIG(17 * SPRITE_SCALE,8 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
			EXPLODE_SMALL(16 * SPRITE_SCALE,8 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
			
			private int x,y,h,w; 
			
			Status(int x, int y, int h, int w){
				this.x = x;
				this.y = y;
				this.h = h;
				this.w = w;			
			}
			
			protected BufferedImage texture(TextureAtlas atlas){
				 return atlas.cut(x, y, w, h);
					
			}
		}
		
		
		private Status status;
		private Map<Status, Sprite> spriteMap;
		private float scale;
		private float speed;

		public Shell(float x, float y,float scale, float speed, TextureAtlas atlas, String dir) {			
			super(EntityType.Player, x, y);			
			super.active = true;
			this.dir = dir;
			
			// Default position on the start
			status  = Status.SMALL;
			spriteMap = new HashMap<Status, Sprite>();
			this.scale = scale;
			
			for(Status h : status.values()){
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
			int ttl = 300;			
		    this.speed = (ttl/100);	
			switch (dir) {
			case "East": newX += this.speed;
		    ttl--;
			break;
			case "NORTH": newY -= this.speed;
		    ttl--;
			break;
			case "WEST": newX -= this.speed;
		    ttl--;
			break;
			case "SOUTH": newY += this.speed;
		    ttl--;
			break;
			}
			
			if(newX < 0){
				newX = 0;
				explode = true;
			}else if(newX>= Game.WIDTH - SPRITE_SCALE * scale){
				newX = Game.WIDTH - SPRITE_SCALE * scale;
				explode = true;
			}
			
			if(newY < 0){
				newY = 0;
				explode = true;
			}else if(newY>= Game.HEIGHT - SPRITE_SCALE * scale){
				newY = Game.HEIGHT - SPRITE_SCALE * scale;
				explode = true;
			}			
			x = newX;
			y = newY;	
		}		

		@Override
		public void render(Graphics2D g) {
			if(!explode) {
			if(turn && counter < 10) {
				spriteMap.get(status).render(g, x, y);
				turn = false;
				status  = Status.BIG;
				counter++;
			} else if (counter < 20) {
				spriteMap.get(status).render(g, x, y);
				status  = Status.SMALL;
				turn = true;
				counter++;
			} else {
				counter = 0;
			}
			} else {
				status = Status.EXPLODE_BIG;
				spriteMap.get(status).render(g, x, y);
				active = false;
			}
		}
		
		public Rectangle getBounds(){
			return new Rectangle((int)x, (int)y, SPRITE_SCALE, SPRITE_SCALE);
		}
}
