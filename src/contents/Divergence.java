//These are the scenes where the options are shown
//

package contents;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//For now, it extends Scene because it should have all its qualities plus selections
public class Divergence extends Scene
{
	
	Divergence(ImageIcon picture, String dialog)
	{
		super(picture, dialog);
		JLabel pic = new JLabel(picture);
		pic.setBounds(0,0,850,500);
		add(pic);
		
	}

}
