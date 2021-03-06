package sunset;

import processing.core.PApplet;
import processing.core.PImage;
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
	
	public int len = 64; //<=128
	
	public static void main(String[] args)
	{
		PApplet.main(new String[]{Game.class.getName()});
	}
	
	public void setup()
	{
		size(1600,900,P2D);
		Data.setup();
		
		level = new Level(1,len,len);
		
		systems = new ArrayList<BaseSystem>();
		systems.add(orderSystem);
		systems.add(inputSystem);
		systems.add(renderSystem);
		systems.add(menuSystem);
		systems.add(colonistSystem);
		renderSystem.init();
		menuSystem.init();
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
	
	public void mousePressed()
	{
		if (mouseButton == LEFT)
		{
			inputSystem.queueLeftClick(mouseX, mouseY);
		}
		else if (mouseButton == RIGHT)
		{
			menuSystem.tool = "";
		}
	}
	
	public void mouseReleased()
	{
		if (mouseButton == LEFT)
		{
			inputSystem.mouseReleased();
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
	
	public void fill(Color c) {super.fill(c.r, c.g, c.b);}
	
	public void text(String text, double a, double b) {super.text(text, (float)a, (float)b);}
	public void rect(double a, double b, double c, double d) {super.rect((float)a, (float)b, (float)c, (float)d);}
	public void image(PImage image, double a, double b, double c, double d) {super.image(image, (float)a, (float)b, (float)c, (float)d);}
	
	public Grid grid() {return level.levels.get(level.activeGrid);}
	
}
