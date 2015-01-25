package entity;

public class Item extends Entity {

	public int id, number;
	public boolean forbid = true;
	
	public Item(int i, int n)
	{
		id = i; number = n;
	}
	
	public Item(Item i)
	{
		id = i.id; number = i.number;
	}
	
	public boolean cost(Item i)
	{
		if (i == null) return true;
		return id == i.id && number >= i.number;
	}
	
}
