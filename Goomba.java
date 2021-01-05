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

class Goomba extends Sprite
{
	static BufferedImage[] goomba_images = null;
	int goombaImageNum;
	double vert_velocity;
	Model model;
	int Offset;
	boolean flip;
	final int width = 37;
	final int height = 45;
	
	public Goomba (int x, int y, Model m)
	{
		if (goomba_images == null)
		{
			goomba_images = new BufferedImage[3]; //array for all goomba states
			goomba_images[0] = loadImage("goomba.png");
			goomba_images[1] = loadImage("goomba_fire.png");
			goomba_images[2] = loadImage("goomba_dead.png");
		}
		
		this.x = x;
		this.y = y;
		this.model = m;
		this.type = "goomba";
		this.model = m;
		direction = true;
	}
	
	public Goomba (Json ob, Model m)
	{
		if (goomba_images == null)
		{
			goomba_images = new BufferedImage[3];
			goomba_images[0] = loadImage("goomba.png");
			goomba_images[1] = loadImage("goomba_fire.png");
			goomba_images[2] = loadImage("goomba_dead.png");
		}
		
        x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		model = m;
		this.type = "goomba";
		this.model = m;
		direction = true;
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
	
	Json marshal()//jason derulo
	{
        Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
        return ob;
	}
	
	void update() //updates goombas pathing
	{
		if (direction)
			x=x+2;
		if (!direction)
			x=x-2;
		y = 285;
	}
	
	void draw(Graphics g)
	{
		if ((!onFire)&&(!dead))	//draws goomba normally if its not on fire and not dead.
		g.drawImage(goomba_images[0], x - model.mario.x + model.mario.Offset, y, null);
		if ((onFire)&&(!dead))	//draws goomba on fire if its on fire, but not dead
		g.drawImage(goomba_images[1], x - model.mario.x + model.mario.Offset, y, null);
		if (dead)	//draws blank png if goomba is dead
		g.drawImage(goomba_images[2], x - model.mario.x + model.mario.Offset, y, null);
	}
	
	boolean DidClick(int mouse_x, int mouse_y) //checks to see if mouse clicked on an aleady existant goomba
		{
				if(mouse_x < x)
				return false;
				if(mouse_x > x + width)
				return false;
				if(mouse_y < y)
				return false;
				if(mouse_y > y + height)
				return false;
			return true;
		}
	boolean isGoomba()
	{
		return true;
	}	

	void pxpy()
	{
		px = x;
		py = y;
	}	
}