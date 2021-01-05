//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;

class Tube extends Sprite
{
	static BufferedImage image = null;
	Model model;
	final int w = 55;
	final int h = 400;
		
	public Tube(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		model = m;
		type = "tube";
		loadTubeImage();
	}
		
	Tube(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		model = m;
		if (image == null)
		loadTubeImage();
		type = "tube";  //it is a tube
    }
	Json marshal()
	{
        Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
        return ob;
	}
	
	void loadTubeImage()
	{
		if(image==null)
		{
			image = loadImage("tube.png");
		}
	}
		
	void update() //tubes dont update their positions
	{	
	}
	
	void draw(Graphics g) //dtubes drawn themsels
	{
		g.drawImage(image, x - model.mario.x + model.mario.Offset, (y), null);
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

	
		boolean DidClick(int mouse_x, int mouse_y)
		{
				if(mouse_x < x)
				return false;
				if(mouse_x > x + w)
				return false;
				if(mouse_y < y)
				return false;
				if(mouse_y > y + h)
				return false;
			return true;
			
		}	
		
		boolean isTube() //checks to see if the tube is a tube
		{
			return true;
		}
		
		void pxpy() //needed becasue sprite makes it abstrcact
		{
		}	
}