package contents;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class mainFrame 
{
	public static void main(String[] args)
	{
		HandlerClass handler = new HandlerClass();
		
		//initializing window{
			framer = new JFrame();
			framer.setSize(1000,500);
			
			//TODO: find a way to set location to the perfect center
			framer.setLocation(250,250);
			
			framer.addKeyListener(handler);//key handling
			framer.addMouseListener(handler);//mouse handling
			
			framer.getContentPane().setBackground( Color.BLACK );//back color
			framer.setUndecorated(true);//removes windowed look
			//framer.setFocusable(true);
			framer.setVisible(true);
		//}
	}
	
	private class HandlerClass implements MouseListener, KeyListener,ActionListener 
	{
		Timer maintime = new Timer(1000, (ActionListener)this);
		
	}
}



