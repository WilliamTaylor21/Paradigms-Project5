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

class Fireball extends Sprite
{
	static BufferedImage image = null;
	double vert_velocity;
	Model model;
	final int width = 47;
	final int height = 47;
	boolean down = true;
	
	public Fireball (int x, int y, Model m)
	{
		if(image==null) //if image hasbt been loaded it gets loaded
		{
			image = loadImage("fireball.png");
		}
		this.x = x;
		this.y = y;
		this.model = m;
		this.type = "fireball";
		this.model = m;
		direction = true;
	}
	
	void update() //gravity and x movement
	{
		x+=8;
		vert_velocity += 2.9;
		y += vert_velocity;
		if(y>= 355)
		{
			y=355;
			vert_velocity = -vert_velocity; //if it hits the ground it bounces
		}
			
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
	
	boolean isFireball()
	{
		return true;
	}
		
	void pxpy()
	{
	}	
	
	void draw(Graphics g)
	{
		g.drawImage(image, x - model.mario.x + model.mario.Offset, y, null); //draws fireball
	}
	
	Json marshal()
	{
        Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
        return ob;
	}
}