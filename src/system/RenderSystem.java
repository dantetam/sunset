package system;

import java.util.ArrayList;

import data.Data;
import entity.Entity;
import entity.LivingEntity;
import entity.Resource;
import level.Grid;
import level.Tile;
import sunset.Game;

public class RenderSystem extends BaseSystem {

	public int cameraX = 16, cameraY = 16, widthX = 48, widthY = 27; //Defines a rectangle with cX, cY at center

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
		ArrayList<Entity> entities = new ArrayList<Entity>();
		ArrayList<Integer> rrs = new ArrayList<Integer>();
		ArrayList<Integer> ccs = new ArrayList<Integer>();
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
				/*Entity test = main.colonistSystem.colonists.get(0);
				Tile near = grid.nearestResource(test.location().r, test.location().c, 1);
				ArrayList<Tile> path = main.path.findAdjustedPath(test.location().r, test.location().c, near.r, near.c);
				if (path.contains(t))
					main.fill(0,0,255);
				else*/
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
						main.rect(
								(rr+((double)res.spriteX-(double)r)+0.25F)*width, 
								(cc+((double)res.spriteY-(double)c)+0.25F)*height, 
								width/2, 
								height/2);
						main.popMatrix();
					}
				}
				for (int i = 0; i < t.occupants.size(); i++)
				{
					entities.add(t.occupants.get(i));
					rrs.add(rr);
					ccs.add(cc);
				}
				//if (c*height > widthY) break; //Edge of screen
			}
			rr++;
			cc = 0;
			//if (r*width > widthX) continue; //Edge of screen
		}
		for (int i = 0; i < entities.size(); i++)
		{
			if (entities.get(i) instanceof LivingEntity)
			{
				LivingEntity person = (LivingEntity)entities.get(i);
				main.fill(Data.personMap.get(person.id));
				main.pushMatrix();
				main.translate(cameraX - widthX/2, cameraY - widthY/2);
				main.rect(
						(rrs.get(i)+((double)person.spriteX-(double)person.location().r)+0.25F)*width, 
						(ccs.get(i)+((double)person.spriteY-(double)person.location().c)+0.25F)*height, 
						width/2, 
						height/2);
				main.popMatrix();
			}
		}
		/*for (int i = 0; i < grid.entities.size(); i++)
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
			else if (en instanceof )
		}*/
	}

}
