package level;

import terrain.DiamondSquare;

public class Grid {

	private Tile[][] tiles;
	public final int rows, cols;
	public DiamondSquare ds;
	public double[][] terrain;
	
	public Grid(int rows, int cols)
	{
		tiles = new Tile[rows][cols];
		this.rows = rows; this.cols = cols;
		
		double[][] temp = DiamondSquare.makeTable(-20, -20, -20, -20, 129);
		ds = new DiamondSquare(temp);
		ds.seed(870);
		terrain = ds.generate(new double[]{0, 0, 128, 100, 0.7});
		
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile t = new Tile(r,c);
				if (terrain[r][c] > 30)
					t.terrain = 1;
				else if (terrain[r][c] > 0)
					t.terrain = 1;
				else
					t.terrain = 2;
				tiles[r][c] = t;
			}
		}
	}
	
	public Tile getTile(int r, int c)
	{
		if (r >= 0 && r < rows && c >= 0 && c < cols)
			return tiles[r][c];
		return null;
	}
	
}
