import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import javafx.scene.control.Spinner;

public class Reel implements Runnable{
	

	JLabel reel;
	int valRan;
	ArrayList<Symbol> imgs = new ArrayList<Symbol>();
	volatile int reelVal;
	
	public Reel(JLabel reelAssign) {
		reel = reelAssign;
		valRan = (int)(Math.random()*7+2);
	}
	
	public void spin() {
		try {
			
			Symbol bell = new Symbol();
	    	bell.setValue(6);
	    	bell.getImage("images/bell.png");
			
	    	Symbol cherry = new Symbol();
	    	cherry.setValue(2);
	    	cherry.getImage("images/cherry.png");
	    	
	    	Symbol lemon = new Symbol();
	    	lemon.setValue(3);
	    	lemon.getImage("images/lemon.png");
	    	
	    	Symbol plum = new Symbol();
	    	plum.setValue(4);
	    	plum.getImage("images/plum.png");
	    	
	    	Symbol seven = new Symbol();
	    	seven.setValue(7);
	    	seven.getImage("images/redseven.png");
	    	
	    	Symbol watermelon = new Symbol();
	    	watermelon.setValue(5);
	    	watermelon.getImage("images/watermelon.png");
	    	
	    	imgs.add(bell);
	    	imgs.add(cherry);
	    	imgs.add(lemon);
	    	imgs.add(plum);
	    	imgs.add(seven);
	    	imgs.add(watermelon);
	    	
	    	Collections.shuffle(imgs);
	    	
	    	int i = 1;
	    	while (i>0) {	
				for (Symbol val: imgs) {						
					val.setImage(reel);
					reelVal = val.getValue();
					Thread.sleep(90);
				}
				i++;
			}
	    		
			} catch (Exception e) {
				
			}
	}

		
	@Override
	public void run() {
		spin();
		
//		try {
//						
//		Symbol bell = new Symbol();
//    	bell.setValue(6);
//    	bell.getImage("images/bell.png");
//		
//    	Symbol cherry = new Symbol();
//    	cherry.setValue(2);
//    	cherry.getImage("images/cherry.png");
//    	
//    	Symbol lemon = new Symbol();
//    	lemon.setValue(3);
//    	lemon.getImage("images/lemon.png");
//    	
//    	Symbol plum = new Symbol();
//    	plum.setValue(4);
//    	plum.getImage("images/plum.png");
//    	
//    	Symbol seven = new Symbol();
//    	seven.setValue(7);
//    	seven.getImage("images/redseven.png");
//    	
//    	Symbol watermelon = new Symbol();
//    	watermelon.setValue(5);
//    	watermelon.getImage("images/watermelon.png");
//    	
//    	imgs.add(bell);
//    	imgs.add(cherry);
//    	imgs.add(lemon);
//    	imgs.add(plum);
//    	imgs.add(seven);
//    	imgs.add(watermelon);
//    	
//    	Collections.shuffle(imgs);
//    	
//    	int i = 1;
//    	while (i>0) {	
//			for (Symbol val: imgs) {						
//				val.setImage(reel);
//				reelVal = val.getValue();
//				Thread.sleep(90);
//			}
//			i++;
//		}
//    		
//		} catch (Exception e) {
//			
//		}
    	
	}

	public int getReelVal() {
		return reelVal;
	}

}
