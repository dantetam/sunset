package sunset;

import processing.core.PApplet;

public class Game extends PApplet {

	public static void main(String[] args)
	{
		PApplet.main(new String[]{Game.class.getName()});
	}
	
	public void setup()
	{
		size(1600,900);
	}
	
	public void draw()
	{
		ellipse(50,50,50,50);
	}
	
}
