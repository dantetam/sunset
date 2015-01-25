package level;

import java.util.ArrayList;

import entity.Colonist;
import entity.Entity;
import entity.Resource;
import terrain.DiamondSquare;

public class Grid {

	private Tile[][] tiles;
	public final int rows, cols;
	public DiamondSquare ds;
	public double[][] terrain;
	public boolean[][] revealed;
	public ArrayList<Entity> entities; //For quick reference

	public Grid(int rows, int cols)
	{
		tiles = new Tile[rows][cols];
		revealed = new boolean[rows][cols];
		this.rows = rows; this.cols = cols;
		
		entities = new ArrayList<Entity>();

		double[][] temp = DiamondSquare.makeTable(-20, -20, -20, -20, 129);
		ds = new DiamondSquare(temp);
		ds.seed(870);
		terrain = ds.generate(new double[]{0, 0, 128, 100, 0.7});

		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile t = new Tile(this,r,c);
				if (terrain[r][c] > 30)
				{
					t.terrain = 1;
					Resource rock = new Resource(0, 0, 20, 300);
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
					if (Math.random() < 0.02)
					{
						Resource tree = new Resource(1, 1, 20, 100);
						tree.move(t);
					}
					revealed[r][c] = true;
				}
				tiles[r][c] = t;
			}
		}
	}
	
	//Returns all tiles between a and b
	public ArrayList<Tile> box(Tile a, Tile b)
	{
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		//Brute force
		if (a.equals(b))
		{
			tiles.add(a);
			//return tiles;
		}
		if (a.r <= b.r)
		{
			for (int r = a.r; r <= b.r; r++)
			{
				if (a.c <= b.c)
				{
					for (int c = a.c; c <= b.c; c++)
						tiles.add(getTile(r,c));
				}
				else
				{
					for (int c = b.c; c <= a.c; c++)
						tiles.add(getTile(r,c));
				}
			}
		}
		else
		{
			for (int r = b.r; r <= a.r; r++)
			{
				if (a.c <= b.c)
				{
					for (int c = a.c; c <= b.c; c++)
						tiles.add(getTile(r,c));
				}
				else
				{
					for (int c = b.c; c <= a.c; c++)
						tiles.add(getTile(r,c));
				}
			}
		}
		return tiles;
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
	
	public Tile nearestResource(int row, int col, int id)
	{
		ArrayList<Tile> candidates = new ArrayList<Tile>();
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				if (getTile(r,c).item instanceof Resource)
				{
					if (((Resource)getTile(r,c).item).id == id && ((Resource)getTile(r,c).item).order)
					{
						//System.out.println(((Resource)getTile(r,c).item).reserve);
						if (((Resource)getTile(r,c).item).reserve == null)
							candidates.add(getTile(r,c));
					}
				}
			}
		}
		Tile candidate = null; 
		Tile location = getTile(row,col); if (location == null) return null;
		for (int i = 0; i < candidates.size(); i++)
		{
			Tile t = candidates.get(i);
			if (candidate == null) candidate = t;
			else 
			{
				if (t.dist(location) < candidate.dist(location))
					candidate = t;
			}
		}
		return candidate;
	}

}
