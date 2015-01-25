package game;

import java.util.ArrayList;

public class Order {
	
	public String type;
	public int frames; //-1 for no end
	//public boolean destroy = false;
	public ArrayList<Number> data = new ArrayList<Number>();
	
	public Order(String t, int f)
	{
		type = t; frames = f;
	}

}
