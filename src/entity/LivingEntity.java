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
	
}
