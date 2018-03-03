package Game.Level;

/**
 * @author Dmitry
 *
 */
public enum TileType {
	
	EMPTY(0), BRICK(1), METAL(2), WATER(3), GRASS(4), ICE(5);
	
	private int n;
	
	TileType(int n){
		this.n = n;
	}
	
	// Get numeric valuer of a type
	public int getNumeric(){
		return n;
	}
	

	/**
	 * @param numeric Numeric value of Type
	 * @return Tile type by it's numeric value
	 */
	public static TileType getType(int numeric){
		switch(numeric){
		case 1: 
			return BRICK;
		case 2: 
			return METAL;
		case 3: 
			return WATER;
		case 4: 
			return GRASS;
		case 5: 
			return ICE;
		default:
			return EMPTY;
		}
	}
	
}
