//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas

import java.util.ArrayList;
import java.util.Iterator;

public class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	
	Model()
	{
		sprites = new ArrayList<Sprite>();  //creates home for sprites
		mario = new Mario(200,50);
		sprites.add(mario);				//add mario to list, always at index '0'
		Json j = Json.load("map.json");
		unmarshal(j);
	}

	public void update()
	{
		//System.out.println(sprites.size());
		for (int i = 0; i < sprites.size(); i++)//loops through all sprites so they can update
		{
			if (sprites.get(i).onFire) //checks to see if goomba is on fire
			{
				sprites.get(i).killCount++;	//if so it is one frame closer to dying
				if((sprites.get(i).killCount++)>150)//if it has been burning for 150 frames, it dies
				sprites.get(i).dead = true;
			}
			sprites.get(i).update();//gets current sprite
			if (i == 0)		//mario is always in index 0
				marioCollision();	//checks to make sure mario hasnt done anything illegal
			if (sprites.get(i).isGoomba()) //checks to see if current sprite is a goomba
			{
				if(goombaCollision(i)) //function only returns true if goomba collides with fireball
				{					   //returns false if it collides with pipe
					sprites.get(i).onFire = true; 	//sets goomba on fire (for 150 frames)
				}
			}
			if(sprites.get(i).isFireball())	//checks to see if current sprite is fireball
			{
				if ((sprites.get(i).x)>mario.x+550) //checks to see if its out of marios view
					sprites.remove(i);				// if it is it stops existing
			}
		}
	}
	
	 void unmarshal(Json ob)// loads file 
	 {
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		Json jsonList = ob.get("sprites");
		Json tubesList = jsonList.get("tubes");
		Json goombasList = jsonList.get("goombas");
		for (int i=0; i<tubesList.size(); i++)
		{
			sprites.add(new Tube(tubesList.get(i), this));
		}
		for (int i=0; i<goombasList.size(); i++)
		{
			sprites.add(new Goomba(goombasList.get(i), this));
		}
	 }

	Json marshal() //saves file (disabled)
	{
         Json ob = Json.newObject();
         Json spritesOb = Json.newObject();
		 Json tempList = Json.newList();
         ob.add("sprites", spritesOb);
		 spritesOb.add("tubes", tempList);
         for(int i = 0; i < sprites.size(); i++)
		 {
			 if (sprites.get(i).isTube())
			 {
				 Tube t = (Tube)sprites.get(i);
				 tempList.add(t.marshal());
			 }
		 }
		 tempList = Json.newList();
		 spritesOb.add("goombas", tempList);
		 for(int i = 0; i < sprites.size(); i++)
		 {
			 if (sprites.get(i).isGoomba())
			 {
				 Goomba g = (Goomba)sprites.get(i);
				 tempList.add(g.marshal());
			 }
		 }
         return ob;
	}
	
	public void addTube(int mouse_x, int mouse_y) //disabled
		{	
		Tube t = new Tube(mouse_x, mouse_y, this);
		boolean already = false;
		//Iterator<Sprite> spriteIterator = sprites.iterator();
		System.out.println(sprites.size());
			for (int i = 0; i < sprites.size(); i++)
			{
				if(sprites.get(i).isTube())
				{
					//Tube temp = spriteIterator.next();
					Tube temp = (Tube)sprites.get(i);
					if (temp.DidClick(mouse_x, mouse_y))
					{
						sprites.remove(temp); // removes tube if one alreadt exists
						already = true;
						break;
					}
				}
			}
			if (!already)
				{
					sprites.add(t);
				}
		}
		
	public void addGoomba(int mouse_x, int mouse_y) //Disabled
		{	
		Goomba g = new Goomba(mouse_x, mouse_y, this);
		boolean already = false;
		//Iterator<Sprite> spriteIterator = sprites.iterator();
		System.out.println(sprites.size());
			for (int i = 0; i < sprites.size(); i++)
			{
				if(sprites.get(i).isGoomba())
				{
					//Tube temp = spriteIterator.next();
					Goomba temp = (Goomba)sprites.get(i);
					if (temp.DidClick(mouse_x, mouse_y))
					{
						sprites.remove(temp);
						already = true;
					}
				}
			}
			if (!already)
				{
					sprites.add(g);//asss goomba if one doesn't exist (disabled)
				}
		}
	public void addFireball()
	{
		Fireball f = new Fireball(mario.x+30, mario.y+10, this);
		sprites.add(f);
	}
	public void marioCollision() //checks for mario colliding with pipes
	{
		int xTube;
		int yTube;
		int px = sprites.get(1).px;
		int py = sprites.get(1).py;
		int x = sprites.get(1).x;
		int y = sprites.get(1).y;
		for(int i = 0; i < sprites.size(); i++)
		{
			if (sprites.get(i).isTube())
			{
				xTube = sprites.get(i).spriteX();
				yTube = sprites.get(i).spriteY();
			
				if ((mario.x+mario.width>xTube)&&(mario.px+mario.width<xTube)&&(mario.y+mario.height>yTube))
				{
					mario.x = xTube - mario.width - 1;
				}
				if ((mario.x < xTube + 55) && (mario.px > xTube + 55)&&(mario.y+mario.height>yTube))
				{
					mario.x = xTube + mario.width + 1 ;
				}
				if ((mario.py<mario.y+mario.height)&&(yTube<mario.y+mario.height)&&(mario.y+mario.height<yTube+400)&&(mario.x+mario.width>xTube-1) && (mario.x < xTube + 56 ))
				{
					mario.y = yTube - mario.height - 1;
					mario.vert_velocity = 0;
					mario.jumper=0;
				}
			}
		}
	}
	
	public boolean goombaCollision(int goomba)
	{
		int xTube,yTube;
		int xFire,yFire;	
		int width = 99;
		int height = 118;
		int px = sprites.get(goomba).px; //goombas previos y positin
		int py = sprites.get(goomba).py; //goombas previous x position
		int x = sprites.get(goomba).x;// gombas x position
		int y = sprites.get(goomba).y; //goomba y position
		for(int i = 0; i < sprites.size(); i++)
		{
			if (sprites.get(i).isTube())
			{
				xTube = sprites.get(i).spriteX();
				yTube = sprites.get(i).spriteY();
				
				if ((x+width>xTube)&&(px+width<=xTube)&&(y+height>yTube))//checks for pipe on right
				{
					sprites.get(goomba).x = xTube - width;
					sprites.get(goomba).direction = !sprites.get(goomba).direction;
					return false;
				}
				if ((x < xTube + 55) && (px >= xTube + 55)&&(y+height>yTube))//checks for pipe on left
				{
					sprites.get(goomba).x = xTube + 50;
					sprites.get(goomba).direction = !sprites.get(goomba).direction;//changes goomba direction if it hits a pipe
					return false;
				}	
			}
			if (sprites.get(i).isFireball())
			{
				xFire = sprites.get(i).spriteX();//gets fireballs x location
				yFire = sprites.get(i).spriteY();//gets firballs y location
				
				if ((x+width>xFire)&&(xFire>x)&&(yFire>=y))//checks to see if fireball hits goomba
				{
					if (sprites.get(goomba).dead) //if goomba is already dead fireball ignores it
					return false;
					sprites.remove(i);
					return true;
				}	
			}
		}
		return false;
	}
	
	void previous()
	{
		for(int i=0; i < sprites.size(); i++)
		{
			sprites.get(i).pxpy(); //saves all sprites location to compare in the next frame
		}
	}
}