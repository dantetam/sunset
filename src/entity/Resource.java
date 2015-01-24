package entity;

public class Resource extends Entity {

	public int id;
	public double life;
	
	public Resource(int id, double l)
	{
		this.id = id; life = l;
	}
	
	public Resource(Resource r)
	{
		id = r.id; life = r.life;
	}
	
}
