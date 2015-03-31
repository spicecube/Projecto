//Plain scene that the just shows a picture with dialog
//maybe animation?

package contents;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scene extends JPanel
{
	
	Scene(ImageIcon picture, String dialog)
	{
		JLabel pic = new JLabel(picture);
		pic.setBounds(0,0,850,500);
		add(pic);
	}

}
