package level;

import java.util.ArrayList;

import entity.*;

public class Tile {

	public int r,c;
	public ArrayList<LivingEntity> occupants;
	public Entity item;
	public int terrain;
	public Grid grid;
	
	public Tile(Grid g, int r, int c)
	{
		occupants = new ArrayList<LivingEntity>();
		grid = g;
		this.r = r; this.c = c;
	}
	
	public double dist(Tile t)
	{
		return Math.sqrt(Math.pow(t.r-r,2) + Math.pow(t.c-c,2));
	}
	
}
