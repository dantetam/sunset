package sunset;

import processing.core.PApplet;
import game.Pathfinder;

import java.util.ArrayList;

import data.Color;
import data.Data;
import level.Grid;
import level.Level;
import system.*;

public class Game extends PApplet {

	public Level level;
	public Pathfinder path;
	
	public ArrayList<BaseSystem> systems;
	public OrderSystem orderSystem = new OrderSystem(this);
	public InputSystem inputSystem = new InputSystem(this);
	public RenderSystem renderSystem = new RenderSystem(this);
	public MenuSystem menuSystem = new MenuSystem(this);
	public ColonistSystem colonistSystem = new ColonistSystem(this);
	
	public static void main(String[] args)
	{
		PApplet.main(new String[]{Game.class.getName()});
	}
	
	public void setup()
	{
		size(1600,900);
		Data.setup();
		
		level = new Level(1,32,32);
		
		systems = new ArrayList<BaseSystem>();
		systems.add(orderSystem);
		systems.add(inputSystem);
		systems.add(renderSystem);
		systems.add(menuSystem);
		systems.add(colonistSystem);
		colonistSystem.init();
		
		path = new Pathfinder(grid());
		
		frameRate(30);
	}
	
	public void draw()
	{
		for (int i = 0; i < systems.size(); i++)
		{
			systems.get(i).tick();
		}
	}
	
	public void keyPressed()
	{
		inputSystem.queueKey(key);
	}
	
	public void keyReleased()
	{
		inputSystem.keyReleased(key);
	}
	
	public void fill(Color c)
	{
		super.fill(c.r, c.g, c.b);
	}
	
	public void rect(double a, double b, double c, double d)
	{
		rect((float)a, (float)b, (float)c, (float)d);
	}
	
	public Grid grid() {return level.levels.get(level.activeGrid);}
	
}
