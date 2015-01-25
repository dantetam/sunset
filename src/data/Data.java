package data;

import java.util.HashMap;

public class Data {

	public static HashMap<Integer, Color> terrainMap = new HashMap<Integer, Color>();
	public static HashMap<Integer, Color> resourceMap = new HashMap<Integer, Color>();
	public static HashMap<Integer, Color> personMap = new HashMap<Integer, Color>();
	
	public static void setup()
	{
		terrainMap.put(0, new Color(150, 225, 255)); //ice
		terrainMap.put(1, new Color(125, 125, 125)); //rock
		terrainMap.put(2, new Color(50, 200, 50));   //soil
		
		resourceMap.put(0, new Color(125, 125, 125)); //a rock
		resourceMap.put(1, new Color(140, 70, 0)); //a tree
		
		personMap.put(0, new Color(255, 0, 0)); //a test person
	}
	
}
