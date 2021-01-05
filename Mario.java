//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class Mario extends Sprite
{
	int jumper;
	final int width = 60;
	final int height = 95;
	int marioImageNum;
	double vert_velocity; //hops
	Model model;
	int Offset;
	boolean flip;
	
	
	static BufferedImage[] mario_images = null;
	
	public Mario(int x, int y)
	{
		this.x = x;
		Offset = this.x;
		this.y = y;
		marioImageNum = 0;
		vert_velocity = 12.0;
		type = "mario";
		flip = false;
		

		mario_images = new BufferedImage[5];  //array
		mario_images[0] = loadImage("mario1.png");
		mario_images[1] = loadImage("mario2.png");
		mario_images[2] = loadImage("mario3.png");
		mario_images[3] = loadImage("mario4.png");
		mario_images[4] = loadImage("mario5.png");
	}
	
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		} 
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}
		
	void update()
	{
		vert_velocity += 2.9;
		y += vert_velocity;
			
		if (y > 400-height)
		{
			vert_velocity = 0.0;
			y = 400 - height;
		}
		if (y == 305)
			jumper = 0;
		if (y < 305)
			jumper++;
	}
	
	void draw(Graphics g)
	{
		g.drawImage(mario_images[marioImageNum], Offset, y, width, height, null); //draws mario in 1 of 5 frames
	}
	
	void animate()
	{
		marioImageNum++;
		if(marioImageNum > 4)
			marioImageNum = 0;
	}
		
	void jump()
	{
		if (jumper < 5)
			vert_velocity-=9; //jumpman
	}
	
	void pxpy()
	{
		px = x;
		py = y;
	}
	
	boolean isMario()  //social security
	{
		return true;
	}
	
}