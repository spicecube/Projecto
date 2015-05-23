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
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JLayeredPane;



public class Hub extends JFrame
{
	GameFlow gamer = new GameFlow();
	//
	Dimension dim; //keep track of width & height
	
	JPanel introPanel; // just the starting panel the shows the intro
	JLayeredPane lp = getLayeredPane(); //the "window"
	HandlerClass handler = new HandlerClass(); //a private class within this "hub class"
											   //Handles keylistening and mouselistening (see below class)
	
	//importing the image to the program
	ImageIcon intro = new ImageIcon(getClass().getResource("/imgs/introSample.png"));
	Image img = intro.getImage();
	
	//attaching the image to a JLabel
	JLabel introLabel;
	
	boolean isIn = false; ////what direction the moving should go
	boolean framed = false; // checks to see if the mouse is isIn
	boolean firstEnter = true; // getting past the introPanel
	boolean showScript = false; // letting you know when the scriptBox 
								//timer has full displayed for text
	
	JPanel invisiMenu; // the invisible box for the "menu"
	ScriptBox scriptBox;  // the box that "plays" the script of a scene
	
	final int reach = 50;
	
	Timer fly = new Timer(10,handler);
	
	int randCount = 0;
	int textCount = 0;
	Scene adder = null;
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		new Hub();
	}
	
	
	
	Hub() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		
		GameFlow gamer = new GameFlow();
		//storing screen size 
		dim = Toolkit.getDefaultToolkit().getScreenSize();
				
			setUndecorated(true);//when TRUE, removes windowed look
		
		
		
		
		double monitorWidth = dim.getWidth();
		double monitorHeight = dim.getHeight();
		
		//setting up a fullscreen'd imageIcon{
		Image newimg = img.getScaledInstance((int)monitorWidth, (int)monitorHeight,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon real = new ImageIcon(newimg);
		//}
		
		//******************prompt for/handle full screen{
		JFrame frame = new JFrame("InputDialog");
		Object[] yOrn = {"Yes", "No"};
		String s = (String)JOptionPane.showInputDialog(frame, "fullscreen?\n", "馬馬馬馬", JOptionPane.QUESTION_MESSAGE, null, yOrn, yOrn[1]);

		introPanel = new JPanel(null);
		
		if (s == "Yes") 
		{
			introLabel = new JLabel(real);
			setSize((int)monitorWidth,(int)monitorHeight);
			
			introPanel.setBounds(0,0,(int)monitorWidth, (int)monitorHeight);
			introPanel.add(introLabel);		
			introLabel.setBounds(0, 0, (int)monitorWidth,(int)monitorHeight);
		}
		
		else 
		{
			introLabel = new JLabel(intro);
			setSize(850,500);
			
			//perfect center{
				setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
			//}	
			
			
			introPanel.setBounds(0,0,850, 500);
			introPanel.add(introLabel);			
			introLabel.setBounds(0, 0, 850,500);
		}
		
		lp.add(introPanel,new Integer(1));
		//}******************finish getInput
		
			
			
			
			
			
			
		//adding the listeners{
			addKeyListener(handler);//key handling
			addMouseListener(handler);//mouse handling
		//}	
			
		//setting up the intro panel{
			/*JPanel introPanel = new JPanel(null); // null means that the layout is null	
			introPanel.setBounds(0,0,850, 500);
			introPanel.add(introLabel);//add the image to the JPanel			
			introLabel.setBounds(0, 0, 850,500);
			lp.add(introPanel,new Integer(1));*/
		//}
			
		//set up scriptBox for holding the manuscript player
			scriptBox = new ScriptBox(850);
			System.out.println("uhh" + 850);
			scriptBox.setBounds(0,500,850,100);
			scriptBox.setBackground(Color.gray);
			lp.add(scriptBox, new Integer(4));
			
		//set up invisiMenu (for closing/settings aka menu)
			invisiMenu = new JPanel(null);
			invisiMenu.setBounds(0,-50,850,50);
			invisiMenu.setBackground(Color.red);
			lp.add(invisiMenu, new Integer(3));
			
		//set up invisiBox (for checking for menu drawout)
			JPanel invisiBox = new JPanel(null);
			invisiBox.setBounds(0,0,850,50);
			invisiBox.setBackground(Color.green);
			invisiBox.addMouseListener(new MouseListener() //listener that checks for menu drop
			{

				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					
				}

				//float down on mouse enter
				@Override
				public void mouseEntered(MouseEvent arg0) 
				{
					System.out.println("yololol");
					System.out.println("ayy we're framed");
					framed = true;
					
					if (fly.isRunning() == false) 
					{
						isIn = true;
						fly.start();
					}
				
				}

				//float up on exit
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
					// TODO Auto-generated method stub
					
				}
				
			});
			
			lp.add(invisiBox, new Integer(2));
			
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/introSample.wav"));
			Clip introSampleWav = AudioSystem.getClip();
			introSampleWav.open(audioIn);
			introSampleWav.start();
			
			//lets wrap it boys
			//the below lines make sure everything is "drawn/added" correctly
			validate();
			repaint();
			setVisible(true);
			//pack();
			/**At this point everything except the gameflow should be set.**/
	}
	
	public void menuMove(boolean isIn)
	{
		if (((isIn == false) && (invisiMenu.getY() == -50 )) || ((isIn == true) && (invisiMenu.getY() == 0))) 
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
		//regular game timer that marks every second
		Timer maintime = new Timer(1000, (ActionListener)this);
		
		//timer that moves the scriptBox at a certain speed
		Timer scriptBoxMove = new Timer(5,(ActionListener)this);
		
		
		Timer textTime = new Timer(10,(ActionListener)this);

		int moveCount =0; // used for moving the menu
		
		void playText()
		{
			//char [] letterSplit = text.toCharArray();
			
			textTime.start();
			scriptBox.setLabels(adder.dialog);
			System.out.println("so yea swag");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if (e.getSource() == maintime)
			{
			
			//if the mouse is with the area and the menu is not moving out and is in the default
			//position of -50 (unseen)
				if (framed == true && fly.isRunning()== false &&  invisiMenu.getY()==-50)
				{
					moveCount =0;
					isIn = true;
					fly.restart();
				}
			
			//if the main timer is running, we're inside the frame, nothing is currently moving and we're opened
			//when we should be closed
			//open up
				if (framed == false && fly.isRunning()!= true &&  invisiMenu.getY()==0)
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
			
				
		//if the timer for moving the scriptBox is on		
			if (e.getSource() == scriptBoxMove)
			{
			
			//incrementally move the scriptBox up to 400 (where we want it to be when fully shown)	
				if (scriptBox.getY() >= 400)
					scriptBox.setLocation(0,scriptBox.getLocation().y - 1);			
				
			//arrived at 400 and is fully shown
				else if (showScript == false)
				{
					System.out.println("heyo");
					showScript = true;
					playText();
				}
//				else
//				{
//					scriptBoxMove.stop();
//					scriptBox.setLocation(0,500);
//				}
				
				repaint();
			}
			
			if (e.getSource() == textTime)
			{
				//scriptBox.setText("");
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
		
			//System.out.println("you pressed a key");

			scriptBox.reset();
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			
			//System.out.println("you released");
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
				
				showScript = false;
					
				//the firstEnter is special since the introPanel is not
				//of Scene type
					if (firstEnter) 
					{
						//Remove the introPanel
						lp.remove(introPanel);
						lp.repaint();
						
						//Grab and add the next Scene
						adder = gamer.getNext();
						
						if (adder.hasScript) 
						{
							scriptBoxMove.stop();
							scriptBox.setLocation(0,500);
							//start the timer that moves the scriptBox up
							scriptBoxMove.start();
							//scriptBox(adder.dialog); //TODO: this is probably needed
						}
						
						lp.add(adder);
						lp.repaint();
						
						//It is no longer the first time
						firstEnter = false;
					}
					
					//regular game flow
					else
					{
						//remove the Scene currently on
						lp.remove(adder);
						lp.repaint();
						scriptBox.removeAll();
						
						//grab the next Scene
						adder = gamer.getNext();
						
						//if it should have a scriptBox
						if (adder.hasScript) 
						{
							scriptBoxMove.stop();
							scriptBox.setLocation(0,500);
							//start the timer that moves the scriptBox up
							scriptBoxMove.start();
							//scriptBox(adder.dialog); //TODO: this is probably needed
						}
						
						System.out.println(adder); //checks what Scene
						lp.add(adder);	//adding that scene
						lp.repaint();	//repainting to ensure display
						
					}
				}
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			//System.out.println("you typed");
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			if (!maintime.isRunning()) maintime.start();
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
	
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



