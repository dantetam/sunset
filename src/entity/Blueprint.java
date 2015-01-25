package entity;

import java.util.ArrayList;

public class Blueprint extends Building {

	public ArrayList<Item> items = new ArrayList<Item>(); //Remove them off the list at they are received
	public int work = 150;
	
	public Blueprint(Building build)
	{
		super(build);
	}
	
	public void remove()
	{
		Building build = new Building(this);
		super.remove();
		build.move(location);
	}
	
}
