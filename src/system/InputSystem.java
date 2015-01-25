package system;

import java.util.ArrayList;

import level.Grid;
import level.Tile;
import sunset.Game;


public class InputSystem extends BaseSystem {

	private ArrayList<Character> keyPresses;

	public InputSystem(Game main)
	{
		super(main);
		keyPresses = new ArrayList<Character>();
	}

	public void tick()
	{
		for (int i = keyPresses.size() - 1; i >= 0; i--)
		{
			executeAction(keyPresses.get(i));
			keyPresses.remove(i);
		}
		for (int i = 0; i < keyHeld.length; i++)
		{
			if (keyHeld[i])
			{
				float dist = 15;
				//System.out.println(i+97);
				Grid grid = main.level.levels.get(main.level.activeGrid);
				if (i == 97 - 97) //a
				{
					if (main.renderSystem.cameraX > 0)
						main.renderSystem.cameraX -= 1;
				}
				else if (i == 100 - 97) //d
				{
					if (main.renderSystem.cameraX < grid.rows)
						main.renderSystem.cameraX += 1;
				}
				else if (i == 115 - 97) //s
				{
					if (main.renderSystem.cameraY < grid.cols)
						main.renderSystem.cameraY += 1;
				}
				else if (i == 119 - 97) //w
				{
					if (main.renderSystem.cameraY > 0)
						main.renderSystem.cameraY -= 1;
				}
			}
		}
		for (int i = clicks.size() - 1; i >= 0; i--)
		{
			Click c = clicks.get(i);
			if (c.type.equals("Left"))
				passLeftMouseClick(c.mouseX, c.mouseY);
			else if (c.type.equals("Right"))
				passRightMouseClick(c.mouseX, c.mouseY);
			clicks.remove(i);
		}
	}

	public boolean[] keyHeld = new boolean[200];
	public void queueKey(char key)
	{
		if (key >= 97 && key <= 122)
		{
			keyHeld[key-97] = true;
		}
		keyPresses.add(0,key);
	}

	public void keyReleased(char key)
	{
		if (key >= 97 && key <= 122)
		{
			keyHeld[key-97] = false;
		}
	}

	public ArrayList<Click> clicks = new ArrayList<Click>();
	public class Click {String type; float mouseX, mouseY; Click(String t, float x, float y) {type = t; mouseX = x; mouseY = y;}}
	public void queueLeftClick(float mouseX, float mouseY) {clicks.add(0, new Click("Left", mouseX, mouseY));}
	public void queueRightClick(float mouseX, float mouseY) {clicks.add(0, new Click("Right", mouseX, mouseY));}

	public void passLeftMouseClick(float mouseX, float mouseY)
	{
		/*int r = (int)((mouseX/(double)main.width)*(double)main.renderSystem.widthX + ((double)main.renderSystem.cameraX-(double)main.renderSystem.widthX/2D));
		int c = (int)((mouseY/(double)main.height)*(double)main.renderSystem.widthY + ((double)main.renderSystem.cameraY-(double)main.renderSystem.widthY/2D));
		Tile t = main.grid().getTile(r,c);
		main.renderSystem.highlighted = t;
		main.println(r + "::::" + c);*/
		if (main.menuSystem.passMouse(mouseX, mouseY))
			return;
		main.renderSystem.firstBound = main.renderSystem.mh;
	}
	
	public void passRightMouseClick(float mouseX, float mouseY)
	{

	}
	
	public void mouseReleased()
	{
		if (main.renderSystem.firstBound != null)
			main.menuSystem.groupSelect(main.renderSystem.firstBound, main.renderSystem.mh);
		main.renderSystem.firstBound = null;
	}

	public void executeAction(char key)
	{
		if (key == 'i')
		{
			main.renderSystem.widthX /= 1.2;
			main.renderSystem.widthY /= 1.2;
		}
		else if (key == 'o')
		{
			main.renderSystem.widthX *= 1.2;
			main.renderSystem.widthY *= 1.2;
		}
	}

}
