package sunset;

import processing.core.PApplet;

import java.util.ArrayList;

import data.Color;
import data.Data;
import level.Level;
import system.*;

public class Game extends PApplet {

	public Level level;
	
	public ArrayList<BaseSystem> systems;
	public OrderSystem orderSystem = new OrderSystem(this);
	public RenderSystem renderSystem = new RenderSystem(this);
	public MenuSystem menuSystem = new MenuSystem(this);
	
	public static void main(String[] args)
	{
		PApplet.main(new String[]{Game.class.getName()});
	}
	
	public void setup()
	{
		size(1600,900);
		Data.setup();
		
		level = new Level(1,100,100);
		
		systems = new ArrayList<BaseSystem>();
		systems.add(orderSystem);
		systems.add(renderSystem);
		systems.add(menuSystem);
	}
	
	public void draw()
	{
		for (int i = 0; i < systems.size(); i++)
		{
			systems.get(i).tick();
		}
	}
	
	public void fill(Color c)
	{
		super.fill(c.r, c.g, c.b);
	}
	
}
