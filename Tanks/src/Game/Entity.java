package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import IO.Input;

/**
 * <h1>Main Entity</h1>
 * Basic class for all  objects in the game. 
 * 
 * @author Dmitry
 * @version 1.0 Mar 1, 2018
 */
public abstract class Entity {
	
	public final EntityType type;
	
	protected float x;
	protected float y;
	protected boolean active;
	
	protected Entity(EntityType type, float x, float y){
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	public abstract void update(Input input);
	
	public abstract void render(Graphics2D g);

}
