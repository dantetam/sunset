package system;

import java.util.ArrayList;

import entity.Entity;
import entity.Resource;
import level.Tile;
import render.*;
import sunset.Game;

public class MenuSystem extends BaseSystem {

	public String tool = null;
	public ArrayList<Entity> groupSelected;
	public Entity selected = null;

	public ArrayList<Menu> menus;
	
	public MenuSystem(Game g) {
		super(g);
		groupSelected = new ArrayList<Entity>();
		menus = new ArrayList<Menu>();
	}
	
	public void init()
	{
		Menu menu0 = new Menu();
		menu0.active = true;
		menu0.addButton("menu1", "Orders", 0, main.height/2, main.width/8, main.height/30);
		menus.add(menu0);
		
		Menu menu1 = new Menu(); //OrderMenu
		menu1.addButton("harvest1", "Chop Trees", main.width/8, main.height/2, main.width/16, main.height/30);
		menus.add(menu1);
	}

	public void tick() 
	{	
		main.textAlign(main.CENTER);
		for (int i = 0; i < menus.size(); i++)
		{
			if (menus.get(i).active)
			{
				for (int j = 0; j < menus.get(i).buttons.size(); j++)
				{
					Button b = menus.get(i).buttons.get(j);
					//main.println(b.posX + " " + b.posY + " " + b.sizeX + " " + b.sizeY);
					main.fill(0);
					main.rect(b.posX, b.posY, b.sizeX, b.sizeY);
					main.fill(255);
					main.text(b.display, b.posX + b.sizeX/2, b.posY + b.sizeY/2);
				}
			}
		}
		main.textAlign(main.LEFT);
	}
	
	public void executeCommand(String s)
	{
		closeMenus();
		if (s.contains("harvest"))
		{
			tool = s;
		}
		else if (s.contains("menu"))
		{
			int id = Integer.parseInt(s.substring(4));
			//closeMenus();
			menus.get(id).active = true;
		}
	}
	
	public void closeMenus()
	{
		for (int i = 1; i < menus.size(); i++)
			menus.get(i).active = false;
	}
	
	public boolean passMouse(float x, float y)
	{
		for (int i = 0; i < menus.size(); i++)
		{
			if (menus.get(i).active)
			{
				for (int j = 0; j < menus.get(i).buttons.size(); j++)
				{
					Button b = menus.get(i).buttons.get(j);
					if (b.within(x, y))
					{
						executeCommand(b.command);
						return true;
					}
				}
			}
		}
		return false;
	}

	public void groupSelect(Tile a, Tile b)
	{
		ArrayList<Tile> tiles = main.grid().box(a,b);
		//return tiles;
		if (tool == null)
		{
			select(tiles);
		}
		else
		{
			if (tool == "")
			{
				select(tiles);
			}
			else if (tool.contains("harvest"))
			{
				int id = Integer.parseInt(tool.substring(7));
				for (int i = 0; i < tiles.size(); i++)
				{
					Tile t = tiles.get(i);
					if (t.item instanceof Resource)
					{
						Resource res = (Resource)t.item;
						if (res.id == id)
							res.order = true;
					}
				}
			}
		}
		/*main.println(a + " -> " + b);
		for (int r = 0; r < tiles.size(); r++)
		{
			main.println(tiles.get(r));
		}*/
	}

	private void select(ArrayList<Tile> tiles)
	{
		ArrayList<Entity> candidates = new ArrayList<Entity>();
		for (int i = 0; i < tiles.size(); i++)
		{
			if (tiles.get(i).occupants.size() > 0)
			{
				for (int j = 0; j < tiles.get(i).occupants.size(); j++)
				{
					candidates.add(tiles.get(i).occupants.get(j));
				}
			}
		}
		if (candidates.size() > 0) 
		{

		}
		else
		{
			for (int i = 0; i < tiles.size(); i++)
			{
				if (tiles.get(i).item != null)
					candidates.add(tiles.get(i).item);
			}
		}
		groupSelected = candidates;
		selected = null;
		if (groupSelected.size() == 1) selected = groupSelected.get(0);
	}

}
