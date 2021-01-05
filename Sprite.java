//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas

import java.awt.Graphics;

abstract class Sprite
{
	Model model;
	int x,y;
	int px,py;
	String type = " ";
	boolean direction;	//goombas direction
	boolean onFire = false;		//
	boolean dead = false;	//kills goomba after 150 cycles
	int killCount = 0; //kill count for goomba
	
	boolean isTube() {return false;}
	boolean isMario() {return false;}
	boolean isGoomba() {return false;}
	boolean isFireball() {return false;}
	
	abstract void update();
	{
	}
	
	abstract void draw(Graphics g); //all sprites draw themselves
	{
	}
	
	abstract void pxpy();	//all sprites save their previous location
	{
	}
	int spriteX()	//returns current sprites x position
	{
		return x;
	}
	int spriteY()			//returns current sprites y positons
	{
		return y;		// if this is set to y instead of x it takes about 3 hours to figure out what went wrong. lol.
	}

}
