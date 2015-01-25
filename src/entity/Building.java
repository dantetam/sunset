package entity;

import java.util.ArrayList;

import level.Tile;

public class Building extends Entity {

	public int id;
	public ArrayList<Tile> tiles;
	public Item input, output;
	public int rotation = 0; //rotation*90 degrees
	public long name = (long) (System.currentTimeMillis()*Math.random());
	
	public Building()
	{
		tiles = new ArrayList<Tile>();
		setPivot(0,0);
	}

	public Building(Building b)
	{
		tiles = new ArrayList<Tile>();
		for (int i = 0; i < b.tiles.size(); i++)
		{
			tiles.add(new Tile(null, b.tiles.get(i).r, b.tiles.get(i).c));
		}
		id = b.id; input = b.input; output = b.output;
	}
	
	public boolean move(Tile t)
	{
		//if (yes)
		{
			ArrayList<Tile> newTiles = new ArrayList<Tile>();
			for (int i = 0; i < tiles.size(); i++)
			{
				newTiles.add(new Tile(t.grid, t.r + tiles.get(i).r, t.c + tiles.get(i).c));
			}
			tiles = null; tiles = newTiles;
		}
		return super.move(t);
	}
	
	public void remove()
	{
		location.grid.buildings.remove(this);
		super.remove();
	}

	//Note -> only to be used by Data class
	public void setPivot(int r, int c)
	{
		location = new Tile(null,r,c);
	}
	
	public void add(int r, int c)
	{
		tiles.add(new Tile(null,r,c));
	}
	
	public void setLocation(Tile t)
	{
		location = t;
	}
	
	public Tile location() {return location;}
	
	public boolean equals(Building b) {if (b == null) return false; return name == b.name;}
	
}
