package entity;

public class Item extends Entity {

	public int id, number;
	
	public Item(int i, int n)
	{
		id = i; number = n;
	}
	
	public Item(Item i)
	{
		id = i.id; number = i.number;
	}
	
}
