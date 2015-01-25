package system;

import java.util.ArrayList;

import data.Data;
import entity.Blueprint;
import entity.Entity;
import entity.Item;
import entity.LivingEntity;
import entity.Resource;
import game.Order;
import level.Grid;
import level.Tile;
import sunset.Game;

//Handles and executes orders generated by entities

public class OrderSystem extends BaseSystem {

	public OrderSystem(Game g) {super(g);}

	public void tick() 
	{
		Grid grid = main.grid();
		for (int i = 0; i < grid.entities.size(); i++)
		{
			Entity en = grid.entities.get(i);
			if (en instanceof LivingEntity)
			{
				LivingEntity person = (LivingEntity)en;
				/*for (int j = 0; j < person.queue.size(); j++)
				{
					//main.println("j: " + j + "/" + n);
					//main.println(t.occupants.size());
					Order o = person.queue.get(j);
					executeOrder(grid, person, o);
					if (o.destroy)
					t.occupants.get(i).queue.remove(o);
				}*/
				if (person.queue.size() > 0)
				{
					do
					{
						Order o = person.queue.get(0);
						boolean didSomething = executeOrder(grid, person, o);
						if (o.frames == 0 || !didSomething) 
						{
							person.queue.remove(o);
							if (person.queue.size() == 0) break;
						}
						if (didSomething) break;
					} while (true);
				}
				else if (person.secondQueue.size() > 0)
				{
					do
					{
						Order o = person.secondQueue.get(0);
						boolean didSomething = executeOrder(grid, person, o);
						if (o.frames == 0 || !didSomething) 
						{
							person.secondQueue.remove(o);
							if (person.secondQueue.size() == 0) break;
						}
						if (didSomething) break;
					} while (true);
				}
			}
		}
	}

	public boolean executeOrder(Grid grid, LivingEntity person, Order order)
	{
		if (order.frames != -1 || order.frames != 0)
			order.frames--;
		if (order.type.contains("moveNearestRes"))
		{
			//ArrayList<Tile> adj = grid.adjacent(person.location().r, person.location().c);
			int id = Integer.parseInt(order.type.substring(14));
			Tile tree = grid.nearestResource(person.location().r, person.location().c, id);
			if (tree != null)
			{
				//int index = -1; do {index++;} while (tree.item.reserve != null);
				tree.item.reserve = person;

				ArrayList<Tile> path = main.path.findAdjustedPath(person.location().r, person.location().c, tree.r, tree.c);
				if (path == null) return false;
				for (int i = 0; i < path.size() - 1; i++)
				{
					Order o = new Order("move", 25);
					o.data.add(path.get(i).r - path.get(i+1).r);
					o.data.add(path.get(i).c - path.get(i+1).c);
					//System.out.println((path.get(i+1).r - path.get(i).r) + " " + (path.get(i+1).c - path.get(i).c));
					person.queue.add(o);
				}
				Order o = new Order("harvest", -1);
				o.data.add(tree.r); o.data.add(tree.c);
				person.queue.add(o);
				return true;
			}
			order.frames = 0;
		}
		else if (order.type.equals("constructNearest"))
		{
			Blueprint tree = grid.nearestBlueprint(person.location().r, person.location().c);
			if (tree != null)
			{
				//int index = -1; do {index++;} while (tree.item.reserve != null);
				tree.reserve = person;

				ArrayList<Tile> path = main.path.findAdjustedPath(person.location().r, person.location().c, tree.location().r, tree.location().c);
				if (path == null) return false;
				for (int i = 0; i < path.size() - 1; i++)
				{
					Order o = new Order("move", 25);
					o.data.add(path.get(i).r - path.get(i+1).r);
					o.data.add(path.get(i).c - path.get(i+1).c);
					//System.out.println((path.get(i+1).r - path.get(i).r) + " " + (path.get(i+1).c - path.get(i).c));
					person.queue.add(o);
				}
				Order o = new Order("construct", -1);
				//o.data.add(tree.location().r); o.data.add(tree.location().c);
				o.entityData.add(tree);
				person.queue.add(o);
				return true;
			}
			order.frames = 0;
		}
		else if (order.type.equals("haulAll"))
		{
			Tile tree = grid.nearestItem(person.location().r, person.location().c);
			if (tree != null)
			{
				main.println(tree);
				tree.item.reserve = person;

				ArrayList<Tile> path = main.path.findAdjustedPath(person.location().r, person.location().c, tree.r, tree.c);
				if (path == null) return false;
				for (int i = 0; i < path.size() - 1; i++)
				{
					Order o = new Order("move", 25);
					o.data.add(path.get(i).r - path.get(i+1).r);
					o.data.add(path.get(i).c - path.get(i+1).c);
					//System.out.println((path.get(i+1).r - path.get(i).r) + " " + (path.get(i+1).c - path.get(i).c));
					person.queue.add(o);
				}
				Order o = new Order("take", -1);
				o.data.add(tree.r); o.data.add(tree.c);
				person.queue.add(o);
				o = new Order("store", -1); //Store whatever
				//o.entityData.add(tree.item); o.entityData.add(tree);
				person.queue.add(o);
				order.frames = 0;
				return true;
			}
			order.frames = 0;
		}
		else if (order.type.equals("move"))
		{
			person.spriteX += order.data.get(0).doubleValue()/25D;
			person.spriteY += order.data.get(1).doubleValue()/25D;
			//main.println(order.data.get(0).doubleValue()/40D);
			if (order.frames == 0)
			{
				//System.out.println("moving");
				Tile t = grid.getTile(person.location().r + (int)order.data.get(0), person.location().c + (int)order.data.get(1));
				person.move(t);
			}
			return true;
		}
		else if (order.type.equals("take"))
		{
			if (person.item !=null)
			{
				Order o = new Order("drop", -1);
				o.data.add(person.location().r);
				o.data.add(person.location().c);
				person.queue.add(o);
				return false;
			}
			Item item = (Item) grid.getTile(order.data.get(0).intValue(), order.data.get(1).intValue()).item;
			if (item != null)
			{
				person.item = item;
				item.remove();
				order.frames = 0;
				return true;
			}
		}
		else if (order.type.equals("drop"))
		{
			if (person.item != null)
			{
				Tile t = grid.getTile(order.data.get(0).intValue(), order.data.get(1).intValue());
				if (t.item == null)
				{
					Item item = new Item(person.item);
					item.move(t);
					person.item = null;
					order.frames = 0;
				}
				else
				{
					Order o = new Order("store", -1);
					person.queue.add(o);
				}
				return true;
			}
			order.frames = 0;
		}
		else if (order.type.equals("store"))
		{
			if (person.item != null)
			{
				Tile tree = grid.nearestStockpile(person.location().r, person.location().c);
				if (tree != null)
				{	
					ArrayList<Tile> path = main.path.findAdjustedPath(person.location().r, person.location().c, tree.r, tree.c);
					if (path == null) return false;
					for (int i = 0; i < path.size() - 1; i++)
					{
						Order o = new Order("move", 25);
						o.data.add(path.get(i).r - path.get(i+1).r);
						o.data.add(path.get(i).c - path.get(i+1).c);
						//System.out.println((path.get(i+1).r - path.get(i).r) + " " + (path.get(i+1).c - path.get(i).c));
						person.queue.add(o);
					}
					Order o = new Order("drop", -1);
					o.data.add(tree.r);
					o.data.add(tree.c);
					person.queue.add(o);
					order.frames = 0;
					return true;
				}
			}
			//else
			order.frames = 0;
		}
		else if (order.type.equals("harvest"))
		{
			Entity en = grid.getTile(order.data.get(0).intValue(), order.data.get(1).intValue()).item;
			if (en instanceof Resource)
			{
				Resource res = (Resource)en;
				res.life -= 5;
				if (res.life <= 0)
				{
					res.remove();
					order.frames = 0;
				}
				return true;
			}
		}
		else if (order.type.equals("construct"))
		{
			//Entity en = grid.getTile(order.data.get(0).intValue(), order.data.get(1).intValue()).item;
			Entity en = order.entityData.get(0);
			if (en instanceof Blueprint)
			{
				Blueprint blue = (Blueprint)en;
				blue.work -= 1;
				if (blue.work <= 0)
				{
					blue.remove();
					order.frames = 0;
				}
				return true;
			}
		}
		else
		{
			System.out.println("Invalid order: " + order.type);
			return false;
		}
		return true; //return false since true was not returned before this?
	}

}
