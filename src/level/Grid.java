package level;

public class Grid {

	private Tile[][] tiles;
	public final int rows, cols;
	
	public Grid(int rows, int cols)
	{
		tiles = new Tile[rows][cols];
		this.rows = rows; this.cols = cols;
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile t = new Tile(r,c);
				t.terrain = (int)(Math.random()*3);
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
