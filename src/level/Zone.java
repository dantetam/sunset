package level;

import java.util.ArrayList;

public class Zone {

	public ArrayList<Tile> tiles;
	public boolean[] ids = new boolean[200];
	public float r,g,b;
	
	public Zone()
	{
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < ids.length; i++) ids[i] = true;
		r = (float)(Math.random()*255); g = (float)(Math.random()*255); b = (float)(Math.random()*255);
	}
	
	public Tile getFreeTile()
	{
		for (int i = 0; i < tiles.size(); i++)
			if (tiles.get(i).item == null)
				return tiles.get(i);
		return null;
	}
	
}
