package system;

import java.util.ArrayList;

import level.Grid;
import level.Tile;
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
				Order o = new Order("moveNearestRes1", -1);
				col.secondQueue.add(o);
				o = new Order("constructNearest", -1);
				col.secondQueue.add(o);
				o = new Order("workNearest", -1);
				col.secondQueue.add(o);
				o = new Order("haulAll", -1);
				col.secondQueue.add(o);
			}
			/*boolean fix = false;
			Tile t = main.grid().getTile(col.location().r,col.location().c);
			if (t.item != null)
			{
				fix = true;
			}
			else
			{
				out:
					for (int j = 0; j < main.grid().buildings.size(); j++)
					{
						for (int k = 0; k < main.grid().buildings.get(j).tiles.size(); k++)
						{
							if (t.equals(main.grid().buildings.get(j).tiles.get(k)))
							{
								fix = true;
								break out;
							}
						}
					}
			}
			if (fix)
			{
				ArrayList<Tile> tiles = main.grid().adjacent(col.location().r, col.location().c);
				col.move(tiles.get((int)(Math.random()*tiles.size())));
				col.queue.clear();
			}*/
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
			colonists.add(colonist);
		}
	}

}
