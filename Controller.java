//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas



import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements MouseListener, KeyListener
{
	Model model;
	View view;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;			
	boolean keyDown;
	boolean spacebar;
	boolean Q;
	boolean ctrl;
	boolean addTubesEditor = false;
	boolean addGoombaEditor = true;
	boolean hold = false;
	
	public void mousePressed(MouseEvent e)
	{
		// if(addTubesEditor)
			// model.addTube(e.getX()+model.mario.x - model.mario.Offset, e.getY());
		// else if(addGoombaEditor)
			// model.addGoomba(e.getX()+model.mario.x - model.mario.Offset, e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }
	
	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;
			case KeyEvent.VK_Q: Q = true; break;
			case KeyEvent.VK_CONTROL: ctrl = true; break;
		}
		char c = e.getKeyChar();
		int keyCode = e.getKeyCode();
		
		// if (c == 's')
		// {
			// model.marshal().save("map.json");
			// System.out.println("Saved");
		// }
		if (c == 'l')
		{
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("Loaded");
		}
		if (c == 'q')
		{
			addTubesEditor = !addTubesEditor;
		}
			if ( keyCode == KeyEvent.VK_CONTROL)
			{
				model.addFireball();  //fires one fireball, if its held down it will fire rapidly after a while
				ctrl = false;
			}
				
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: spacebar = false; break;
			case KeyEvent.VK_Q: Q = false; break;
			case KeyEvent.VK_CONTROL: ctrl = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight)
		{
			for (int i=0; i < model.sprites.size(); i++)
			{
				if (model.sprites.get(i).isMario())
				{
					model.sprites.get(i).x += 5;
					model.mario.animate();
				}
			}
		}
		if(keyLeft) 
		{
			for (int i=0; i < model.sprites.size(); i++)
			{
				if (model.sprites.get(i).isMario())
				{
					model.sprites.get(i).x -= 5;
					model.mario.animate();
				}
			}
		}
		if(spacebar||keyUp)
		{
			for (int i=0; i < model.sprites.size(); i++)
			{
				if (model.sprites.get(i).isMario())
				{
					model.mario.jump();
				}
			}
		}
		if(ctrl)
		{
			//model.addFireball();
		}
	}
}
