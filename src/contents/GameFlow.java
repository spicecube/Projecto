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
	Scene arrested = new Scene(new ImageIcon(getClass().getResource("/imgs/arrested.png")),"You're coming with me! And then I was taken to prison");
	Scene cheer = new Scene(new ImageIcon(getClass().getResource("/imgs/cheer.png")),"The crowd cheered for me, chanting my name as if I was some kinda hero");
	Scene court = new Scene(new ImageIcon(getClass().getResource("/imgs/court.png")),"They took me to court, what did I even do?");
	Scene gDead = new Scene(new ImageIcon(getClass().getResource("/imgs/gDead.png")),"And then I killed him, if you asked me why I did it, I wouldnt know");
	Scene guilty = new Scene(new ImageIcon(getClass().getResource("/imgs/guilty.png")),"The judge raised his gavel, pounded it on the table. The verdict: guilty");
	Scene gunners = new Scene(new ImageIcon(getClass().getResource("/imgs/gunners.png")),"Our story stars with two gunners outside a door");
	Scene introClass = new Scene(new ImageIcon(getClass().getResource("/imgs/introClass.png")),"It was 2:43 Mr. Thomas's class, just another day");
	

	Scene ProsecutionA = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsectutionA.png")),"Look I just did what I thought was best for everyones safety");
	Scene ProsecutionB = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsecutionB.png")),"He deserved it. That bastard would have killed everyone.");
	Scene ProsecutionS = new Scene(new ImageIcon(getClass().getResource("/imgs/divProsectutionS.png")),"So ______ what do you have to say for yourself?");
	
	Scene ShotA = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotA.png")),"Before I knew it, I picked up the gun and fired");
	Scene ShotB = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotB.png")),"I couldn't do it.. This man may have been a murder, but I sure wasn't");
	Scene ShotS = new Scene(new ImageIcon(getClass().getResource("/imgs/divShotS.png")),"Time froze, the gun was right next to me, what would I do");
	
	Scene end = new Scene(new ImageIcon(getClass().getResource("/imgs/end.png")),"And the story of our hero ends");
	
	Scene current;
	
	boolean first = true; // checks to see if this is the start or not
	
	
	//linking up and initializing all of the Scenes
	void init()
	{
		gunners.setNexts(introClass, null, null, null); 
		gunners.setName("gunners");
		gunners.setBounds(0,0,850,500);
		
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
		end.setName("end");
		end.setBounds(0,0,850,500);
	
	}
	
	public GameFlow() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		
				
				
//				AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/arrested.wav"));
//				Clip arrestedWav = AudioSystem.getClip();
//				arrestedWav.open(audioIn);
//				arrestedWav.start();
				
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
