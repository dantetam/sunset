package entity;

import game.Order;

import java.util.ArrayList;

public abstract class LivingEntity extends Entity {

	public int id;
	public ArrayList<Order> queue;
	public ArrayList<Order> secondQueue;
	public Item item = null;
	public float health;
	
	public LivingEntity()
	{
		queue = new ArrayList<Order>();
		secondQueue = new ArrayList<Order>();
	}
	
	public abstract void tick();
	
	public String getDirection()
	{
		if (queue.size() > 0)
		{
			for (int i = 0; i < queue.size(); i++)
			{
				Order o = queue.get(i);
				if (o.type == "move")
				{
					int r = o.data.get(0).intValue(), c = o.data.get(1).intValue();
					if (r == 1 && c == 0)
						return "right";
					else if (r == -1 && c == 0)
						return "left";
					else if (r == 0 && c == 1)
						return "down";
					else if (r == 0 && c == -1)
						return "up";
				}
			}
		}
		return "down";
	}
	
}
