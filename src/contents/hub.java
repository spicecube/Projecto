//TODO: Image scaling for switch from "windowed" modes to fullscreen 
//		(right now that image size remains the same but the window does change size)
	//		-gotta find this online, dont know how
		
//TODO:	Find way to have the X, Minimize, and Settings button scroll down
	//		-have the buttons drawn? J's sister?
	//		-check out the "fly" from task program
	//		-implement it but with an invisible rectangle that is on outer layer

//TODO: Set up the JLayeredPane
//			-topmost layer being the inisibox, its buttons, and clickables
//			-base layer being the background
//			-adding others if needed

//TODO: create plainScene and actionScene objects VVVV

//TODO: plainScene
//			-simply accepts an image and expands it on the whole window
//			-should also support dialogue
//					-Pass the string to the object
//					-Have a JLabel across bottom
//					-update th  ,lokkooll0e JLabel isIn a timer-based "loop" incrementing each letter
//							-at ever timer tick advance to the next letter and repaint()

//			-point to the next plainScene or actionScene
//					-?

//TODO: actionScene
//			-practically extends plainScene (not sure if should literally do that)
//			-?	

package contents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLayeredPane;



public class hub extends JFrame
{
	JLayeredPane lp = getLayeredPane(); //the "window"
	HandlerClass handler = new HandlerClass(); //a private class within this "hub class"
											   //Handles keylistening and mouselistening (see below class)
	
	//importing the image to the program
	ImageIcon intro = new ImageIcon(getClass().getResource("/imgs/introSample.png")); 
	
	//attaching the image to a JLabel
	JLabel introLabel = new JLabel(intro);
	
	boolean isIn = false; ////what direction the moving should go
	boolean framed = false; // checks to see if the mouse is isIn
	
	JPanel invisiMenu; // the invisible box for the "menu"
	
	final int reach = 50;
	
	public static void main(String[] args)
	{
		new hub();
		//System.out.println("ayy");
	}
	
	hub()
	{
		
		//storing screen size 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		                                         
		//initializing window{
			
			//lp.setSize(dim.width,dim.height); //this means fullScreen
												  //TODO: find way to resize the image?
		
			setSize(850, 500);
			setUndecorated(true);//when TRUE, removes windowed look
			
			//perfect center{
				//setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
			//}	
			
			setLocation(0,0);
			
		//}
			
		//adding the listeners{
			addKeyListener(handler);//key handling
			addMouseListener(handler);//mouse handling
		//}	
			
		//setting up the intro panel{
			JPanel introPanel = new JPanel(null); // null means that the layout is null	
			introPanel.setBounds(0,0,850, 500);
			introPanel.add(introLabel);//add the image to the JPanel			
			introLabel.setBounds(0, 0, 850,500);
			lp.add(introPanel,new Integer(1));
		//}
			
		//lets wrap it boys
			//the below lines make sure everything is "drawn/added" correctly
			validate();
			repaint();
			setVisible(true);
			
		//set up invisiMenu (for closing/settings aka menu)
			invisiMenu = new JPanel(null);
			invisiMenu.setBounds(0,0,dim.width,50);
			invisiMenu.setBackground(Color.red);
			lp.add(invisiMenu, new Integer(3));
			
		//set up invisiMenu (for closing/settings aka menu)
			JPanel invisiBox = new JPanel(null);
			invisiBox.setBounds(0,0,dim.width,50);
			invisiBox.setBackground(Color.green);
			lp.add(invisiBox, new Integer(2));
	}
	
	public void menuMove(boolean isIn)
	{
		if (((isIn == false) && (invisiMenu.getY() == 0 )) || ((isIn == true) && (invisiMenu.getY() == 50))) 
		{//TODO make the numbers right
			System.out.println("return");
			return;
		}
		
		else
		{
			System.out.println("moving"+isIn);
			int move =0;
			
			if (isIn == true) move = 1;
			if (isIn == false) move = -1;
					

				invisiMenu.setBounds(invisiMenu.getX(), invisiMenu.getY() + move, invisiMenu.getWidth(), invisiMenu.getHeight());

		}
	}
	
	
	private class HandlerClass implements MouseListener, KeyListener,ActionListener 
	{
		Timer maintime = new Timer(1000, (ActionListener)this);
		Timer fly = new Timer(10,(ActionListener)this);

		int moveCount =0;
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if (e.getSource() == maintime)
			{
				if (framed == true && fly.isRunning()== false &&  invisiMenu.getY()==0)
				{
					moveCount =0;
					isIn = true;
					fly.restart();
				}
			
			//if the main timer is running, we're inside the frame, nothing is currently moving and we're opened
			//when we should be closed
			//open up
				if (framed == false && fly.isRunning()!= true &&  invisiMenu.getY()==50)
				{
					moveCount=0;
					isIn = false;
					fly.restart();
				}
				
			}
			
				if (e.getSource() == fly)
				{
				
					moveCount++;
					//System.out.println(mCount + " cause reach was " + reach + " amd isIn being " + isIn);
					//System.out.println(" flys running " + count++ + isIn);
					
					menuMove(isIn);
			
					if (moveCount == reach)
					{
						System.out.println("Move:"+moveCount);
						fly.stop();
						moveCount =0;
					}//end inner if
				}//end outer if
				
			
		}

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
		
			System.out.println("you pressed a key");
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			
	
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			if (!maintime.isRunning()) maintime.start();
			
			System.out.println("ayy we're framed");
			framed = true;
			
			if (fly.isRunning() == false) 
			{
				isIn = true;
				fly.start();
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			framed = false;
			System.out.println("exited");
			if (fly.isRunning() == false) 
			{
				isIn = false;
				fly.start();
			}
		}

		@Override
		public void mousePressed(MouseEvent arg0) 
		{
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			
			
		}
		
	}
}



