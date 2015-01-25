package entity;

import level.Tile;

public class Resource extends Entity {

	public int id, itemId, itemNum;
	public double life;
	public boolean order = false;
	
	public Resource(int id, int id2, int n, double l)
	{
		this.id = id; itemId = id2; itemNum = n; life = l;
	}
	
	public Resource(Resource r)
	{
		id = r.id; itemId = r.itemId; itemNum = r.itemNum; life = r.life;
	}
	
	public void remove()
	{
		Item item = new Item(itemId, itemNum);
		Tile loc = location;
		super.remove();
		item.move(loc);
		System.out.println(item.location.r + " " + item.location.c);
	}
	
}
