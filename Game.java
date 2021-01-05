//William Taylor
//10/13/2020
//010840237
//Programming Paradigms Assignment 5 
//University of Arkansas


import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{	
	Model model;
	Controller controller;
	View view;
	public Game()
	{
		model = new Model();
	    controller = new Controller(model);
		view = new View(controller, model);
		
		this.setTitle("Will's Project");
		this.setSize(800, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
		view.addMouseListener(controller);
	}

	public static void main(String[] args)
	{	
		Game g = new Game();
		g.run();
	}
	
	public void run()
	{
		while(true)
			{
				model.previous();		//saves old values before they are gone
				controller.update();	//updates the controller
				model.update();			//updates everything in the game
				view.repaint(); // Indirectly calls View.paintComponent
				Toolkit.getDefaultToolkit().sync(); // Updates screen

				// Go to sleep for 40 miliseconds
				try
				{
					Thread.sleep(40);
				} catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
	}
}
