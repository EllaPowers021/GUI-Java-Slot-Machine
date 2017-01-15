import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Symbol implements ISymbol {

	int value;
	String imgPath;
	
	//sets the image to the reel
	@Override
	public void setImage(JLabel labelComponent) {
		// TODO Auto-generated method stub
		labelComponent.setIcon(new ImageIcon(getClass().getResource(imgPath)));
	}

	//gets the image from the path provided and attaches to the object
	@Override
	public void getImage(String path) {
		// TODO Auto-generated method stub
		imgPath = path;
	}

	//sets the value for the object, like an ID
	@Override
	public void setValue(int v) {
		// TODO Auto-generated method stub
		value = v;
	}

	//Retrieves the value given to the object
	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setImage() {
		// TODO Auto-generated method stub
		
	}

}
