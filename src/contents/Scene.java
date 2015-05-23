//Plain scene that the just shows a picture with dialog
//maybe animation?

package contents;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scene extends JPanel
{
	//
	boolean isDone = false; //holds true is a Scene has played through
	boolean hasChoice = false;
	boolean hasScript = true;
	int choice = -99;
	int timeCount = -99;
	Scene [] nexts = new Scene[4];
	String dialog;
	
	
	
	
	Scene(ImageIcon picture, String dialog)
	{
		JLabel pic = new JLabel(picture);
		pic.setBounds(0,0,850,500);
		add(pic);
		this.dialog = dialog;
	}
	
	Scene getNext(int selection)
	{
		return null;
	}
	
	void setChoice(boolean input)
	{
		hasChoice = input;
	}
	
	void selectionSetUp()
	{
		
	}
	
	boolean checkChoice()
	{
		return hasChoice;
	}
	
	void setNexts(Scene c1, Scene c2, Scene c3, Scene c4)
	{
		nexts[0] = c1;
		nexts[1] = c2;
		nexts[2] = c3;
		nexts[3] = c4;
	}
	
	 
	
	

}
