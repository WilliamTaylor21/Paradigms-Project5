//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansass

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;	
	View view;
	Controller controller;	
	int marioLocation;

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		marioLocation = 200;
	}
	
	
	public void paintComponent(Graphics g)	
	{
		
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(100,255,80));
		g.fillRect(0,400,this.getWidth(), 100);

		for(int i=0; i<model.sprites.size(); i++)
		{
			model.sprites.get(i).draw(g);  //makes each sprite draw itself
		}
	}

}
