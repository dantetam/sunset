package system;

import sunset.Game;

public abstract class BaseSystem {

	public Game main;
	
	public BaseSystem(Game g)
	{
		main = g;
	}
	
	public abstract void tick();
	
}
