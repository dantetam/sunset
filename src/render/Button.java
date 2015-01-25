package render;

public class Button {

	public String command, display;
	public float posX, posY, sizeX, sizeY;
	
	public Button(String stringy, String stringy2, float a, float b, float c, float d)
	{
		command = stringy;
		display = stringy2;
		posX = a; posY = b;
		sizeX = c; sizeY = d;
	}
	
	public boolean within(float x, float y)
	{
		return x > posX && x < posX + sizeX && y > posY && y < posY + sizeY;
	}
	
}
