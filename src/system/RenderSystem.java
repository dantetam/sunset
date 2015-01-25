package system;

import java.util.ArrayList;

import data.Data;
import entity.Entity;
import entity.Item;
import entity.LivingEntity;
import entity.Resource;
import level.Grid;
import level.Tile;
import level.Zone;
import sunset.Game;

public class RenderSystem extends BaseSystem {

	public int cameraX = 16, cameraY = 16, widthX = 48, widthY = 27; //Defines a rectangle with cX, cY at center
	public Tile mh = null;
	public Tile firstBound = null;

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
		ArrayList<Tile> boxSelect = new ArrayList<Tile>();
		if (firstBound != null)
		{
			boxSelect = grid.box(firstBound, mh);
		}
		for (int r = cameraX - widthX/2; r < cameraX + widthX/2; r++)
		{
			for (int c = cameraY - widthY/2; c < cameraY + widthY/2; c++)
			{
				/*if (r*width < cameraX - widthX/2 || 
						r*width > cameraX + widthX/2 || 
						c*height < cameraY - widthY/2 || 
						c*height > cameraY + widthY/2) continue;*/
				cc++;
				Tile t = grid.getTile(r,c);
				if (t == null) continue;
				/*Entity test = main.colonistSystem.colonists.get(0);
				Tile near = grid.nearestResource(test.location().r, test.location().c, 1);
				ArrayList<Tile> path = main.path.findAdjustedPath(test.location().r, test.location().c, near.r, near.c);
				if (path.contains(t))
					main.fill(0,0,255);
				else*/
				main.fill(Data.terrainMap.get(t.terrain));
				for (int i = 0; i < grid.zones.size(); i++)
				{
					Zone z = grid.zones.get(i);
					if (z.tiles.contains(t))
						main.fill(z.r,z.g,z.b,100);
				}
				if (main.renderSystem.mh != null)
					if (t.equals(main.renderSystem.mh))
						main.fill(0,0,255,100);
				main.pushMatrix();
				main.translate(cameraX - widthX/2, cameraY - widthY/2);
				main.rect(rr*width, cc*height, width, height);
				main.popMatrix();
				if (main.mouseX > rr*width && main.mouseY > cc*height && main.mouseX < (rr+1)*width && main.mouseY < (cc+1)*height)
					mh = t;
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
					else if (t.item instanceof Item)
					{
						Item item = (Item)t.item;
						main.fill(Data.itemMap.get(item.id));
						main.pushMatrix();
						main.translate(cameraX - widthX/2, cameraY - widthY/2);
						double x = (rr+((double)item.spriteX-(double)item.location().r)+0.25F)*width;
						double y = (cc+((double)item.spriteY-(double)item.location().c)+0.25F)*height;
						main.rect(
								x, 
								y, 
								width/2, 
								height/2);
						main.fill(0);
						main.text(item.number + "", x, y);
						if (item.forbid)
						{
							main.fill(255,0,0);
							main.text("X",x + width/4, y + height/4);
						}
						main.popMatrix();
					}
				}
				main.fill(0,0,255,50);
				if (boxSelect.contains(t))
				{
					main.pushMatrix();
					main.translate(cameraX - widthX/2, cameraY - widthY/2);
					main.rect(
							(rr+0.375F)*width, 
							(cc+0.375F)*height, 
							width/4,
							height/4
							);
					main.popMatrix();
				}
				main.fill(255,255,255,255);
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
			else if (entities.get(i) instanceof Item)
			{

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
