package system;

import data.Data;
import level.Grid;
import level.Tile;
import sunset.Game;

public class RenderSystem extends BaseSystem {

	public RenderSystem(Game g) {
		super(g);
	}

	public void tick() 
	{
		main.background(255);
		Grid grid = main.level.levels.get(main.level.activeGrid);
		float width = main.width/(float)grid.rows;
		float height = main.height/(float)grid.cols;
		for (int r = 0; r < grid.rows; r++)
		{
			for (int c = 0; c < grid.cols; c++)
			{
				Tile t = grid.getTile(r,c);
				main.fill(Data.terrainMap.get(t.terrain));
				main.rect(r*width, c*height, width, height);
			}
		}
	}

}
