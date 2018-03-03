package Game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Collision {
	static List<Entity> objects = new ArrayList<Entity>();
	
	public Collision(){
	}
	
	public static boolean detectCollision(){
		boolean result = false;
		for (int i = 0; i < objects.size(); i++){
			for (int j = i + 1; j < objects.size(); j++){
				result = objects.get(i).getPosition().intersects(objects.get(j).getPosition());
				System.out.println(objects.get(i).getPosition());
			}
		}
		return result;
	}
	
	public static void addObject(Entity ent){
		objects.add(ent);
	}

}
