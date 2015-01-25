package game;

import java.util.ArrayList;

import entity.Entity;

public class Order {
	
	public String type;
	public int frames; //-1 for no end
	//public boolean destroy = false;
	public ArrayList<Number> data = new ArrayList<Number>();
	public ArrayList<Entity> entityData = new ArrayList<Entity>();
	
	public Order(String t, int f)
	{
		type = t; frames = f;
	}

}
