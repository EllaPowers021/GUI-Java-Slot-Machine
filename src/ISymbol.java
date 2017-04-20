import javax.swing.JLabel;

//this interface includes all methods for the image controls
public interface ISymbol {
	
	void setImage(JLabel comp);
	
	void getImage(String path);
	
	void setValue(int v);
	
	int getValue();

	void setImage();
}
