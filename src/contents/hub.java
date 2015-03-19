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
import javax.swing.Timer;



public class hub 
{
	JFrame framer; //the "window"
	HandlerClass handler = new HandlerClass(); //a private class within this "hub class"
											   //Handles keylistening and mouselistening (see below class)
	
	//importing the image to the program
	ImageIcon intro = new ImageIcon(getClass().getResource("/imgs/introSample.png")); 
	//attaching the image to a JLabel
	JLabel introLabel = new JLabel(intro);
	
	
	public static void main(String[] args)
	{
		new hub();
	}
	
	hub()
	{
		
		
		//initializing window{
			framer = new JFrame();
			framer.setSize(850,500);
			
			//perfect center{
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			framer.setLocation(dim.width/2-framer.getSize().width/2, dim.height/2-framer.getSize().height/2);
			//}
			
			framer.addKeyListener(handler);//key handling
			framer.addMouseListener(handler);//mouse handling		
			framer.getContentPane().setBackground( Color.BLACK );//back color
			framer.setUndecorated(true);//when TRUE, removes windowed look
			//framer.setFocusable(true);
			framer.setVisible(true);
		//}
			
			framer.getContentPane().add(introLabel);//add the image to the frame
	
			//lets wrap it boys
			//the below lines make sure everything is "drawn/added" correctly
			framer.getContentPane().validate();
			framer.getContentPane().repaint();
	}
	
	
	private class HandlerClass implements MouseListener, KeyListener,ActionListener 
	{
		Timer maintime = new Timer(1000, (ActionListener)this);

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
	
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}



