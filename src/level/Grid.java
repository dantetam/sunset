package level;

import java.util.ArrayList;

import entity.Resource;
import terrain.DiamondSquare;

public class Grid {

	private Tile[][] tiles;
	public final int rows, cols;
	public DiamondSquare ds;
	public double[][] terrain;
	public boolean[][] revealed;

	public Grid(int rows, int cols)
	{
		tiles = new Tile[rows][cols];
		revealed = new boolean[rows][cols];
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
				{
					t.terrain = 1;
					Resource rock = new Resource(0, 300);
					rock.move(t);
					revealed[r][c] = false;
				}
				else if (terrain[r][c] > 0)
				{
					t.terrain = 1;
					revealed[r][c] = true;
				}
				else
				{
					t.terrain = 2;
					revealed[r][c] = true;
				}
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

	public ArrayList<Tile> adjacent(int r, int c)
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		if (getTile(r+1,c) != null) {temp.add(getTile(r+1,c));} 
		if (getTile(r-1,c) != null) {temp.add(getTile(r-1,c));} 
		if (getTile(r,c-1) != null) {temp.add(getTile(r,c-1));} 
		if (getTile(r,c+1) != null) {temp.add(getTile(r,c+1));} 
		if (getTile(r+1,c+1) != null) {temp.add(getTile(r+1,c+1));} 
		if (getTile(r+1,c-1) != null) {temp.add(getTile(r+1,c-1));} 
		if (getTile(r-1,c+1) != null) {temp.add(getTile(r-1,c+1));} 
		if (getTile(r-1,c-1) != null) {temp.add(getTile(r-1,c-1));} 
		return temp;
	}

}
