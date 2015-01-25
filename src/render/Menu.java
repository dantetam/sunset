package render;

import java.util.ArrayList;

public class Menu {

	public ArrayList<Button> buttons;
	public boolean active = false;
	
	public Menu()
	{
		buttons = new ArrayList<Button>();
	}
	
	public Button addButton(String s1, String s2, float w, float x, float y, float z)
	{
		Button b = new Button(s1,s2,w,x,y,z);
		buttons.add(b);
		return b;
	}
	
}
