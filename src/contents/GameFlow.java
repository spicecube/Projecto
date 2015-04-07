package contents;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class GameFlow 
{
	//initializing the Scenes and Scenes
	Scene arrested = new Scene(new ImageIcon(getClass().getResource("/imgs/arrested.png")),"");
	Scene cheer = new Scene(new ImageIcon(getClass().getResource("/imgs/cheer.png")),"");
	Scene court = new Scene(new ImageIcon(getClass().getResource("/imgs/court.png")),"");
	Scene gDead = new Scene(new ImageIcon(getClass().getResource("/imgs/gDead.png")),"");
	Scene guilty = new Scene(new ImageIcon(getClass().getResource("/imgs/guilty.png")),"");
	Scene gunners = new Scene(new ImageIcon(getClass().getResource("/imgs/gunners.png")),"");
	Scene introClass = new Scene(new ImageIcon(getClass().getResource("/imgs/introClass.png")),"");
	

	Scene ProsecutionA = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsectutionA.png")),"");
	Scene ProsecutionB = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsecutionB.png")),"");
	Scene ProsecutionS = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsectutionS.png")),"");
	
	Scene ShotA = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotA.png")),"");
	Scene ShotB = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotB.png")),"");
	Scene ShotS = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotS.png")),"");
	
	Scene end = new Scene(new ImageIcon(getClass().getResource("/imgs/end.png")),"");
	
	Scene current;
	
	boolean first = true; // checks to see if this is the start or not
	
	
	//this should simple decide the order of things?
	void init()
	{
		gunners.setNexts(introClass, null, null, null); 
		gunners.setName("gunners");
		
		introClass.setNexts(ShotS, null, null, null);
		introClass.setName("introClass");
		introClass.setBounds(0,0,850,500);
		
		ShotS.setNexts(ShotA, ShotB, null, null);	
		ShotS.setName("ShotS");
		ShotS.hasChoice = true;
		ShotS.choice = 1;
		ShotS.setBounds(0,0,850,500);
		
		ShotA.setNexts(gDead, null, null, null);	
		ShotA.setName("ShotA");
		ShotA.setBounds(0,0,850,500);
		
		ShotB.setNexts(end, null, null, null);	
		ShotB.setName("ShotB");
		ShotB.setBounds(0,0,850,500);
		
		gDead.setNexts(cheer, null, null, null);	
		gDead.setName("gDead");
		gDead.setBounds(0,0,850,500);
		
		cheer.setNexts(arrested, null, null, null);	
		cheer.setName("cheer");
		cheer.setBounds(0,0,850,500);
		
		arrested.setNexts(court, null, null, null);	
		arrested.setName("arrested");
		arrested.setBounds(0,0,850,500);
		
		court.setNexts(ProsecutionS, null, null, null);
		court.setName("court");
		court.setBounds(0,0,850,500);
		
		ProsecutionS.setNexts(ProsecutionA, ProsecutionB, null, null);
		ProsecutionS.setName("ProsecutionS");
		ProsecutionS.hasChoice = true;
		ProsecutionS.choice=2;
		ProsecutionS.setBounds(0,0,850,500);
		
		ProsecutionA.setNexts(end, null, null, null);
		ProsecutionA.setName("ProsecutionA");
		ProsecutionA.setBounds(0,0,850,500);
		
		ProsecutionB.setNexts(guilty, null, null, null);
		ProsecutionB.setName("ProsecutionB");
		ProsecutionB.setBounds(0,0,850,500);
			
		guilty.setNexts(end, null, null, null);		
		guilty.setName("guilty");
		guilty.setBounds(0,0,850,500);
		
		end.setNexts(end, null, null, null);
	
	}
	
	public GameFlow() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		
				
				
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/arrested.wav"));
				Clip arrestedWav = AudioSystem.getClip();
				arrestedWav.open(audioIn);
				arrestedWav.start();
				
				init();
	}
	
//return the next scene that should play 
	Scene getNext()
	{
		if (first)
		{			
			first = false;
			
			current =  gunners;
			current.setBounds(0,0,850,500);
			
			return current;
		}
		
		else
		{
		
		//if the current scene in the flow was a selector, find out what choice and attribute that to a scene
			if (current.checkChoice())
			{
				if (current.choice == 1)
				{
					current = current.nexts[0];
					return current;
				}
				
				if (current.choice == 2)
				{
					current = current.nexts[1];
					return current;
				}
				
				if (current.choice == 3)
				{
					return null;
				}
				
				if (current.choice == 4)
				{
					return null;
				}
				
			}
			
			else
			{
				current = current.nexts[0];
				return current;
			}
		}
		
		return null;
	}
}
