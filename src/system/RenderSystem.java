package system;

import data.Data;
import entity.Entity;
import entity.Resource;
import level.Grid;
import level.Tile;
import sunset.Game;

public class RenderSystem extends BaseSystem {
	
	public int cameraX = 50, cameraY = 50, widthX = 50, widthY = 50; //Defines a rectangle with cX, cY at center
	
	public RenderSystem(Game g) {
		super(g);
	}

	public void tick() 
	{
		main.background(255);
		Grid grid = main.grid();
		float width = main.width/(float)widthX;
		float height = main.height/(float)widthY;
		//main.println(cameraX + " " + cameraY + " " + widthX + " " + widthY);
		//main.println((cameraX - widthX/2) + " " + (cameraY - widthY/2) + " " + (cameraX + widthX/2) + " " + (cameraY + widthY/2));
		int rr = 0, cc = 0;
		for (int r = cameraX - widthX/2; r < cameraX + widthX/2; r++)
		{
			for (int c = cameraY - widthY/2; c < cameraY + widthY/2; c++)
			{
				/*if (r*width < cameraX - widthX/2 || 
						r*width > cameraX + widthX/2 || 
						c*height < cameraY - widthY/2 || 
						c*height > cameraY + widthY/2) continue;*/
				Tile t = grid.getTile(r,c);
				cc++;
				if (t == null) continue;
				main.fill(Data.terrainMap.get(t.terrain));
				main.pushMatrix();
				main.translate(cameraX - widthX/2, cameraY - widthY/2);
				main.rect(rr*width, cc*height, width, height);
				main.popMatrix();
				if (t.item != null)
				{
					if (t.item instanceof Resource)
					{
						Resource res = (Resource)t.item;
						main.fill(Data.resourceMap.get(res.id));
						main.pushMatrix();
						main.translate(cameraX - widthX/2, cameraY - widthY/2);
						main.rect(((float)rr+(res.spriteX-r)+0.25F)*width, ((float)cc+(res.spriteX-r)+0.25F)*height, width/2, height/2);
						main.popMatrix();
					}
				}
				//if (c*height > widthY) break; //Edge of screen
			}
			rr++;
			cc = 0;
			//if (r*width > widthX) continue; //Edge of screen
		}
		for (int i = 0; i < grid.entities.size(); i++)
		{
			Entity en = grid.entities.get(i);
			if (en instanceof Resource)
			{
				Resource res = (Resource)en;
				main.fill(Data.resourceMap.get(res.id));
				main.pushMatrix();
				main.translate(cameraX - widthX/2, cameraY - widthY/2);
				main.rect(((float)rr+0.25F)*width, ((float)cc+0.25F)*height, width/2, height/2);
				main.popMatrix();
			}
		}
	}

}
