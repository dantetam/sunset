package entity;

import java.util.ArrayList;

import level.Tile;

public class Blueprint extends Building {

	public ArrayList<Item> items = new ArrayList<Item>(); //Remove them off the list at they are received
	public int work = 50, maxWork = 50;
	
	public Blueprint(Building build)
	{
		super(build);
	}
	
	public void remove()
	{
		Building build = new Building(this);
		//System.out.println(location + " ::::: " + grid);
		build.grid = location.grid;
		//build.move(location); Do not call twice...
		build.setLocation(location);
		//System.out.println(build.name + " " + build.location());
		location.grid.buildings.add(build);
		
		super.remove();
	}
	
}
