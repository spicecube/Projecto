package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScriptBox extends JPanel implements ActionListener
{
	String mainString;
	JLabel [] arrLabel = new JLabel[82];
	char [] charArr;
	Timer letterTime;
	int count =0;
	int placer = -10;
	//
	
	ScriptBox(int gameWidth)
	{
		//handlerClass handle = new handlerClass();
		this.setLayout(null);
		//this.add
		
		
//		textLabel.setFont( new Font("Monospaced", Font.BOLD, 16));
//		textLabel.setForeground(Color.white);
//		textLabel.setBounds(20,10,gameWidth-40,80); //this allows for a line of <= 82 to display
//		System.out.println(textLabel.getBounds());
		
		letterTime = new Timer(100, this);
		
		
		
	}
	
	void reset()
	{
		arrLabel = new JLabel[82];
		count =0;
		placer = -10;
		mainString = "";
		letterTime.stop();
	}
	
	void setLabels(String inp)
	{
		System.out.println("inp was " + inp);
		letterTime.start();
		charArr = inp.toCharArray();
		System.out.println("inp goes " + charArr.toString());
		for (int i=0; i<charArr.length; i++) System.out.println(charArr[i]);
		System.out.println(charArr.length);
		mainString = inp;
		//letterTime.start();
		
//		for (int i =0; i<81; i++)
//		{
//			arrLabel[i] = new JLabel(charArr[i] + "");
//			arrLabel[i].setOpaque(true);
//			arrLabel[i].setFont( new Font("Monospaced", Font.BOLD, 16));
//			arrLabel[i].setForeground(Color.white);
//			if (i%2==0)arrLabel[i].setBackground(Color.pink);
//			else arrLabel[i].setBackground(Color.orange);
//			//arrLabel[i].setSize(20,20);
//			this.add(arrLabel[i]);
//			System.out.println(arrLabel[i].getBounds());
//			//this.pack();
//			this.validate();
//			this.repaint();
//			
//		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		// TODO Auto-generated method stub
		if (e.getSource() == letterTime)
		{
			
			System.out.println("count goes to " + count);
			arrLabel[count] = new JLabel(charArr[count] + "");
			arrLabel[count].setOpaque(true);
			arrLabel[count].setFont( new Font("Monospaced", Font.BOLD, 16));
			arrLabel[count].setForeground(Color.white);
//			if (count%2==0)
			arrLabel[count].setBackground(Color.pink);
			arrLabel[count].setBounds(25+ (placer+=10),40,10,15);
			//else arrLabel[count].setBackground(Color.orange);
			//arrLabel[i].setSize(20,20);
			this.add(arrLabel[count]);
			System.out.println(arrLabel[count].getBounds() + "" + arrLabel[count].getText());
			//this.pack();
			this.validate();
			this.repaint();
			
			count++;
			
			if (count == charArr.length ) letterTime.stop();
			
		}
	}
	

}
