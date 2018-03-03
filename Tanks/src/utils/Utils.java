package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Utils {
	
	private static String filePath = "res/level.lvl";
	
	// Gets Image, resize to given dimensions and redraw on the same X and Y new resized image
	public static BufferedImage resize(BufferedImage image, int width, int height){
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		newImage.getGraphics().drawImage(image, 0, 0, width, height, null);
		return newImage;
	}
	
	// Read level layout from file
	public static Integer[][] levelParser(){
		
		Integer[][] result = null;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
			String line = null;
			List<Integer[]> levelLines = new ArrayList<Integer[]>();
			while((line = reader.readLine()) != null){
				levelLines.add(StringToIntArray(line.split(" ")));
			}
			result = new Integer[levelLines.size()][levelLines.get(0).length];
			for (int i = 0; i< levelLines.size(); i++){
				result[i] = levelLines.get(i);
			}
		} catch (IOException e){
			e.printStackTrace();
		}		
		return result;
	}
	
	// Convert string array to int array
	public static final Integer[] StringToIntArray(String[] strArr) {
		Integer[] result = new Integer[strArr.length];
		
		for(int i = 0; i < strArr.length; i++){
			result[i] = Integer.parseInt(strArr[i]);
		}
		return result;
	}
}
