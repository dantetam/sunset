package entity;

import game.Order;

import java.util.ArrayList;

public abstract class LivingEntity extends Entity {

	public ArrayList<Order> queue;
	public Item item = null;
	public float health;
	
	public LivingEntity()
	{
		queue = new ArrayList<Order>();
	}
	
	public abstract void tick();
	
}
