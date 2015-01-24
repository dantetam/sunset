package entity;

import java.util.ArrayList;

import level.Tile;
import game.Order;

public class Entity {

	private Tile location;
	public float spriteX, spriteY;

	public Entity()
	{

	}

	public boolean move(Tile t)
	{
		if (location != null)
			location.occupants.remove(this);
		location = t;
		if (this instanceof LivingEntity)
		{
			t.occupants.add((LivingEntity)this);
			return true;
		}
		else
			if (t.item == null)
			{
				t.item = this;
				return true;
			}
		return false;
	}

}
