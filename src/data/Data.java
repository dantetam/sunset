package data;

import java.util.HashMap;

import entity.Building;
import entity.Item;

public class Data {

	public static HashMap<Integer, Color> terrainMap = new HashMap<Integer, Color>();
	public static HashMap<Integer, Color> resourceMap = new HashMap<Integer, Color>();
	public static HashMap<Integer, Color> personMap = new HashMap<Integer, Color>();
	public static HashMap<Integer, Color> itemMap = new HashMap<Integer, Color>();	
	public static HashMap<String, Building> mapOfBuildings = new HashMap<String, Building>();
	
	public static void setup()
	{
		terrainMap.put(0, new Color(150, 225, 255)); //ice
		terrainMap.put(1, new Color(125, 125, 125)); //rock
		terrainMap.put(2, new Color(50, 200, 50));   //soil
		
		resourceMap.put(0, new Color(125, 125, 125)); //a rock
		resourceMap.put(1, new Color(140, 70, 0)); //a tree
		resourceMap.put(2, new Color(200, 150, 50)); //potato plant
		
		personMap.put(0, new Color(255, 0, 0)); //a test person
		
		itemMap.put(0, new Color(125, 125, 125)); //stones
		itemMap.put(1, new Color(140, 70, 20)); //logs
		itemMap.put(2, new Color(200, 150, 50)); //potatoes
		
		itemMap.put(10, new Color(255, 255, 255)); //meal
		
		Building b;
		 
		b = new Building();
		b.add(1,0);
		b.add(0,0);
		b.add(-1,0);
		b.input = new Item(2,10); b.output = new Item(10,1);
		mapOfBuildings.put("Stove",b);
	}
	
	/*public static Item item(int id)
	{
		return new Item(itemMap.get(id));
	}*/
	
}
