package level;

import java.util.ArrayList;

import entity.*;

public class Tile {

	public int r,c;
	public ArrayList<LivingEntity> occupants;
	public Entity item;
	public int terrain;
	
	public Tile(int r, int c)
	{
		occupants = new ArrayList<LivingEntity>();
		this.r = r; this.c = c;
	}
	
}
