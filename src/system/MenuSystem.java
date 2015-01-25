package system;

import java.util.ArrayList;

import entity.Entity;
import level.Tile;
import sunset.Game;

public class MenuSystem extends BaseSystem {

	public String tool = null;
	public ArrayList<Entity> groupSelected;
	public Entity selected = null;

	public MenuSystem(Game g) {
		super(g);
		groupSelected = new ArrayList<Entity>();
	}

	public void tick() 
	{	

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
