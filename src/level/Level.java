package level;

import java.util.ArrayList;

public class Level {

	public ArrayList<Grid> levels;
	public int activeGrid = 0;
	
	public Level(int n, int r, int c)
	{
		levels = new ArrayList<Grid>();
		for (int i = 0; i < n; i++)
		{
			levels.add(new Grid(r,c));
		}
	}
	
}
