package entity;

import java.util.ArrayList;

import level.Grid;
import level.Tile;
import game.Order;

public class Entity {

	protected Tile location; 
	public float spriteX, spriteY;
	public Grid grid;

	public Entity()
	{

	}

	public boolean move(Tile t)
	{
		if (t == null) return false;
		if (grid == null)
		{
			t.grid.entities.add(this);
			grid = t.grid;
		}
		if (location != null)
			location.occupants.remove(this);
		location = t;
		spriteX = t.r;
		spriteY = t.c;
		if (this instanceof LivingEntity)
		{
			t.occupants.add((LivingEntity)this);
			return true;
		}
		else if (t.item == null)
		{
			t.item = this;
			return true;
		}
		else
		{
			location = null;
			return false;
		}
	}

	public void remove()
	{
		grid.entities.remove(this);
		grid = null;
		location.occupants.remove(this);
		if (location.item.equals(this))
			location.item = null;
		location = null;
	}

	public Tile location() {return location;}

}
