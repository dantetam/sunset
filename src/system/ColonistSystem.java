package system;

import java.util.ArrayList;

import level.Grid;
import entity.Colonist;
import game.Order;
import sunset.Game;

public class ColonistSystem extends BaseSystem {

	public ArrayList<Colonist> colonists = new ArrayList<Colonist>();
	
	public ColonistSystem(Game g) {
		super(g);
	}

	public void tick() 
	{
		for (int i = 0; i < colonists.size(); i++)
		{
			Colonist col = colonists.get(i);
			if (col.queue.size() == 0)
			{
				Order o = new Order("moveNearestTree", -1);
				col.queue.add(o);
			}
		}
	}
	
	public void init()
	{
		Grid grid = main.grid();
		for (int i = 0; i < 3; i++)
		{
			int r,c;
			do
			{
				r = (int)(Math.random()*grid.rows);
				c = (int)(Math.random()*grid.cols);
			} while (grid.getTile(r,c).item != null || 
					grid.getTile(r,c).dist(grid.getTile(main.renderSystem.cameraX,main.renderSystem.cameraY)) > 10);
			Colonist colonist = new Colonist();
			colonist.move(grid.getTile(r,c));
			main.colonistSystem.colonists.add(colonist);
		}
	}

}
