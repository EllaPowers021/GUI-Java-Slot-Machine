import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.xml.stream.events.StartDocument;


import javafx.scene.layout.Border;

public class Container extends JFrame {
	public JFrame frame;
	public JFrame frameStats;
    public JPanel panel;
    public JPanel panelStats;
    public JPanel panelButtons;
    public JPanel panelDisplays;
    public JPanel panelSpin;
    public JPanel reels;
    
    public JLabel creditsLeftLabel;
    public JLabel creditsLeftDisplay;
    public JLabel betAmntLabel;
    public JLabel betAmntDisplay;
    public static JLabel reel1;
    public static JLabel reel2;
    public static JLabel reel3;
    public JLabel winsLabel;
    public JLabel winsDisplay;
    public JLabel lossLabel;
    public JLabel lossDisplay;
    public JLabel avgLabel;
    public JLabel avgDisplay;
    
    public JButton addCoin;
    public JButton betMin;
    public JButton betMax;
    public JButton reset;
    public JButton spin;
    public JButton stats;
    public JButton save;
    
    int crdtRemain = 10;
    int crdtBet = 0;
    int totLossTemp;
    int totWins = 0;
    int totLoss = 0;
    int nGames;
    double avg = 0.0;
    int reel1Val;
    int reel2Val;
    int reel3Val;
    String filename;
   
    
    Thread t1;
    Thread t2;
    Thread t3;
    
    int spinClick = 0;
    int reelClick = 0;
    
    ArrayList<Symbol> imgsList = new ArrayList<Symbol>();
    int valRan;
    
//    static ArrayList<Symbol> imgs = new ArrayList<Symbol>();
   

    public Container() {
    	guiMain();
	}
    
  //CREATING COMPONENTS OF THE MAIN GAME GUI
    public void guiMain() {
    	
//    	Reel r1 = new Reel();
//    	Reel r2 = new Reel();
//    	Reel r3 = new Reel();
    	
    	//creating the Jframe window for the GUI
    	frame = new JFrame("Slot Machine");
    	frame.setVisible(true);
    	frame.setSize(800,400);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setMinimumSize(new Dimension(900, 420));
    	frame.setLocationRelativeTo(null);
    	
    	//creating the panel that holds the components of the window
    	panel = new JPanel();
    	panelButtons = new JPanel();
    	panelDisplays = new JPanel(new GridBagLayout());
    	panelSpin = new JPanel();
    	panel.setBackground(Color.white);
    	
    	GridBagConstraints layout = new GridBagConstraints();
    
    	//creating objects of components
    	//labels
    	creditsLeftLabel = new JLabel("No. of Credits left");
    	creditsLeftDisplay = new JLabel();
    	creditsLeftDisplay.setText(String.valueOf(crdtRemain));
    	creditsLeftDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    	creditsLeftDisplay.setPreferredSize(new Dimension(50, 20));
    	creditsLeftDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	
    	betAmntLabel = new JLabel("Bet amount");
    	betAmntDisplay = new JLabel();
    	betAmntDisplay.setText(String.valueOf(crdtBet));
    	betAmntDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    	betAmntDisplay.setPreferredSize(new Dimension(50, 20));
    	betAmntDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    	
    	reel1 = new JLabel();
    	reel1.setHorizontalAlignment(SwingConstants.CENTER);
    	reel1.setPreferredSize(new Dimension(200, 310));
    	reel1.setBorder(BorderFactory.createLineBorder(Color.black));
    		
    	reel2 = new JLabel();
    	reel2.setHorizontalAlignment(SwingConstants.CENTER);
    	reel2.setPreferredSize(new Dimension(200, 310));
    	reel2.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	reel3 = new JLabel();
    	reel3.setHorizontalAlignment(SwingConstants.CENTER);
    	reel3.setPreferredSize(new Dimension(200, 310));
    	reel3.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	
    	
    	//buttons
    	addCoin = new JButton("+1 coin");
    	betMin = new JButton("Bet Min");
    	betMax = new JButton("Bet Max");
    	reset = new JButton("Reset");
    	spin = new JButton("SPIN!");
    	spin.setPreferredSize(new Dimension(100, 50));
    	stats = new JButton("Statistics");
    	
    	
    	//adding components to panel
    	layout.insets = new Insets(10, 10, 10, 10);
    	layout.gridx = 1;
    	layout.gridy = 2;
    	panelDisplays.add(creditsLeftLabel, layout);
    	
    	layout.gridx = 2;
    	layout.gridy = 2;
    	panelDisplays.add(creditsLeftDisplay, layout);
    	
    	layout.gridx = 1;
    	layout.gridy = 1;
    	panelDisplays.add(betAmntLabel, layout);
    	
    	layout.gridx = 2;
    	layout.gridy = 1;
    	panelDisplays.add(betAmntDisplay, layout);
    	
    	layout.gridx = 1;
    	layout.gridy = 7;
    	panelDisplays.add(stats, layout);
    	
    	panel.add(reel1);
    	panel.add(reel2);
    	panel.add(reel3);
    	
    	panelButtons.add(addCoin);
    	panelButtons.add(betMin);
    	panelButtons.add(betMax);    	
    	panelButtons.add(reset);
    	panelButtons.add(spin);
    	
    	//adding panels to frame
    	frame.add(panelButtons, BorderLayout.SOUTH);
    	frame.add(panel);
    	frame.add(panelDisplays, BorderLayout.WEST);
    	panelDisplays.setVisible(true);
    	panelButtons.setVisible(true);
    	panel.setVisible(true);
    	
    	validate();
    	
    	
    	//action listeners for the buttons
    	stats.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				guiStats();
				winsDisplay.setText(String.valueOf(totWins));
				lossDisplay.setText(String.valueOf(totLoss));
				
				avg = totWins/nGames;
				avgDisplay.setText(String.valueOf(avg));
				
			}
		});
    	
    	
    	addCoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crdtRemain++;
				creditsLeftDisplay.setText(String.valueOf(crdtRemain));
				
			}
		});
    	
    	betMin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (crdtRemain>0) {
					crdtRemain = crdtRemain - 1;
					crdtBet = crdtBet + 1;
					betAmntDisplay.setText(String.valueOf(crdtBet));
					creditsLeftDisplay.setText(String.valueOf(crdtRemain));
				} else {
					JOptionPane.showMessageDialog(frame, "INSERT COINS TO CONTINUE");
				}
				
			}
		});
    	
    	betMax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (crdtRemain>1) {
					crdtRemain = crdtRemain-3;
					crdtBet = crdtBet+3;
					betAmntDisplay.setText(String.valueOf(crdtBet));
					creditsLeftDisplay.setText(String.valueOf(crdtRemain));
				} else {
					JOptionPane.showMessageDialog(frame, "INSERT COINS TO CONTINUE");
				}
				
			}
		});
    	
    	reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (crdtBet<=0) {
					JOptionPane.showMessageDialog(frame, "Please bet amount to reset");
				}
				crdtRemain = crdtRemain + crdtBet;
				crdtBet = 0;
				betAmntDisplay.setText(String.valueOf(crdtBet));
				creditsLeftDisplay.setText(String.valueOf(crdtRemain));
			
				
			}
		});
    	
    	spin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				if (spinClick == 0 && crdtBet>0) {
					t1 = new Thread(new Reel(reel1));
					t1.start();
					t2 = new Thread(new Reel(reel2));
					t2.start();
					t3 = new Thread(new Reel(reel3));
					t3.start();
					reelClick = 0;
					spinClick++;
					nGames++;
				} else if (crdtBet<1){
					JOptionPane.showMessageDialog(frame, "ERROR: CHECK BET AMOUNT");
				} else {
					JOptionPane.showMessageDialog(frame, "CAN ONLY CLICK SPIN ONCE PER PLAYTHROUGH");
				}
				
			}
		});
    	
    	reel1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (reelClick == 0) {
					
					reel1Val = (int)(Math.random()*6+2);
					reel2Val = (int)(Math.random()*6+2);
					reel3Val = (int)(Math.random()*6+2);
					
					t1.interrupt();
					t2.interrupt();
					t3.interrupt();
				
					
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
			    	
			    	imgsList.add(bell);
			    	imgsList.add(cherry);
			    	imgsList.add(lemon);
			    	imgsList.add(plum);
			    	imgsList.add(seven);
			    	imgsList.add(watermelon);
					
					for (Symbol val1: imgsList) {
						if (val1.getValue()==reel1Val) {
							val1.setImage(reel1);
//							System.out.print(val1.getValue()+"< Value of IMAGE 1");
//							System.out.println("_"+reel1Val+"< Value of RANDOM NUM 1");
							break;
						}
					}
					
					for (Symbol val2: imgsList) {
						if (val2.getValue()==reel2Val) {
							val2.setImage(reel2);
//							System.out.print(val2.getValue()+"< Value of IMAGE 2");
//							System.out.println("_"+reel2Val+"< Value of RANDOM NUM 2");
							break;
						}
					}
					
					for (Symbol val3: imgsList) {
						if (val3.getValue()==reel3Val) {
							val3.setImage(reel3);
//							System.out.print(val3.getValue()+"< Value of IMAGE 3");
//							System.out.println("_"+reel3Val+"< Value of RANDOM NUM 3");
							break;
						}
					}
					spinClick = 0;
				} else {
					JOptionPane.showMessageDialog(frame, "You can only stop a REEL while it is rotating");			
				}
				reelClick++;

				chkWin(reel1Val, reel2Val, reel3Val);
				
			}
		});
    	
    	reel2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (reelClick == 0) {
				
					reel1Val = (int)(Math.random()*6+2);
					reel2Val = (int)(Math.random()*6+2);
					reel3Val = (int)(Math.random()*6+2);
					
					t1.interrupt();
					t2.interrupt();
					t3.interrupt();
					
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
			    	
			    	imgsList.add(bell);
			    	imgsList.add(cherry);
			    	imgsList.add(lemon);
			    	imgsList.add(plum);
			    	imgsList.add(seven);
			    	imgsList.add(watermelon);
					
					for (Symbol val1: imgsList) {
						if (val1.getValue()==reel1Val) {
							val1.setImage(reel1);
//							System.out.print(val1.getValue()+"< Value of IMAGE 1");
//							System.out.println("_"+reel1Val+"< Value of RANDOM NUM 1");
							break;
						}
					}
					
					for (Symbol val2: imgsList) {
						if (val2.getValue()==reel2Val) {
							val2.setImage(reel2);
//							System.out.print(val2.getValue()+"< Value of IMAGE 2");
//							System.out.println("_"+reel2Val+"< Value of RANDOM NUM 2");
							break;
						}
					}
					
					for (Symbol val3: imgsList) {
						if (val3.getValue()==reel3Val) {
							val3.setImage(reel3);
//							System.out.print(val3.getValue()+"< Value of IMAGE 3");
//							System.out.println("_"+reel3Val+"< Value of RANDOM NUM 3");
							break;
						}
					}
					spinClick = 0;
				} else {
					JOptionPane.showMessageDialog(frame, "You can only stop a REEL while it is rotating");			
				}
				reelClick++;
				
				chkWin(reel1Val, reel2Val, reel3Val);
			}
		});
    	
    	reel3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reelClick == 0) {
					
					reel1Val = (int)(Math.random()*6+2);
					reel2Val = (int)(Math.random()*6+2);
					reel3Val = (int)(Math.random()*6+2);
					
					t1.interrupt();
					t2.interrupt();
					t3.interrupt();
					
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
			    	
			    	imgsList.add(bell);
			    	imgsList.add(cherry);
			    	imgsList.add(lemon);
			    	imgsList.add(plum);
			    	imgsList.add(seven);
			    	imgsList.add(watermelon);
					
					for (Symbol val1: imgsList) {
						if (val1.getValue()==reel1Val) {
							val1.setImage(reel1);
//							System.out.print(val1.getValue()+"< Value of IMAGE 1");
//							System.out.println("_"+reel1Val+"< Value of RANDOM NUM 1");
							break;
						}
					}
					
					for (Symbol val2: imgsList) {
						if (val2.getValue()==reel2Val) {
							val2.setImage(reel2);
//							System.out.print(val2.getValue()+"< Value of IMAGE 2");
//							System.out.println("_"+reel2Val+"< Value of RANDOM NUM 2");
							break;
						}
					}
					
					for (Symbol val3: imgsList) {
						if (val3.getValue()==reel3Val) {
							val3.setImage(reel3);
//							System.out.print(val3.getValue()+"< Value of IMAGE 3");
//							System.out.println("_"+reel3Val+"< Value of RANDOM NUM 3");
							break;
						}
					}
					spinClick = 0;
				} else {
					JOptionPane.showMessageDialog(frame, "You can only stop a REEL while it is rotating");			
				}
				reelClick++;
				
				chkWin(reel1Val, reel2Val, reel3Val);
			}
		});
    	
    }
    
    
    
  //CREATING COMPONENTS OF THE STAT SCREEN GUI
    public void guiStats() {
    	
    	//creating the Jframe window for the GUI
    	frameStats = new JFrame("Slot Machine Stat screen");
    	frameStats.setVisible(true);
    	frameStats.setSize(400,400);
    	frameStats.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frameStats.setMinimumSize(new Dimension(400, 400));
    	frameStats.setLocationRelativeTo(frame);
    	
    	panelStats = new JPanel(new GridBagLayout());
    	
    	GridBagConstraints layoutStats = new GridBagConstraints();
    	
    	//adding components to the frame
    	winsLabel = new JLabel("All time wins: ");
    	winsDisplay = new JLabel();
    	winsDisplay.setPreferredSize(new Dimension(50, 20));
    	winsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    	winsDisplay.setText(String.valueOf(totWins));
    	winsDisplay.setBorder(BorderFactory.createLineBorder(Color.RED));
    	
    	lossLabel = new JLabel("All time losses: ");
    	lossDisplay = new JLabel();
    	lossDisplay.setPreferredSize(new Dimension(50, 20));
    	lossDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    	lossDisplay.setText(String.valueOf(totWins));
    	lossDisplay.setBorder(BorderFactory.createLineBorder(Color.RED));
    	
    	avgLabel = new JLabel("Avg crdts earned: ");
    	avgDisplay = new JLabel();
    	avgDisplay.setPreferredSize(new Dimension(50, 20));
    	avgDisplay.setHorizontalAlignment(SwingConstants.CENTER);
    	avgDisplay.setText(String.valueOf(avg));
    	avgDisplay.setBorder(BorderFactory.createLineBorder(Color.RED));
    	save = new JButton("Save stats");
 
    	layoutStats.insets = new Insets(10, 10, 10, 10);
    	layoutStats.gridx = 1;
    	layoutStats.gridy = 1;
    	panelStats.add(winsLabel, layoutStats);
    	layoutStats.gridx = 1;
    	layoutStats.gridy = 2;
    	panelStats.add(winsDisplay, layoutStats);
    	layoutStats.gridx = 2;
    	layoutStats.gridy = 1;
    	panelStats.add(lossLabel, layoutStats);
    	layoutStats.gridx = 2;
    	layoutStats.gridy = 2;
    	panelStats.add(lossDisplay, layoutStats);
    	layoutStats.gridx = 3;
    	layoutStats.gridy = 1;
    	panelStats.add(avgLabel, layoutStats);
    	layoutStats.gridx = 3;
    	layoutStats.gridy = 2;
    	panelStats.add(avgDisplay, layoutStats);
    	layoutStats.gridx = 2;
    	layoutStats.gridy = 3;
    	panelStats.add(save, layoutStats);
    	
    	validate();
    	
    	panelStats.setVisible(true);
    	frameStats.add(panelStats);
    	
    	save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				DateFormat df= new SimpleDateFormat("EEE, MMM d, ''yy");
				String date = df.format(new Date());
				filename= date+".txt";
				File file=new File(filename);
		        
		        
		        FileWriter fw;
		        try {
		            fw=new FileWriter(file);
		            fw.write("No. of Wins = "+totWins+" ");
		            fw.write("/No. of Loses = "+totLoss+" ");
		            fw.write("/No. of Attempts = "+avg);
		           
		            fw.flush();
		            fw.close();
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }
			}
		});	 	
    }
    
        
    public void chkWin(int reel1, int reel2, int reel3) {
    	
    	if (reel1 == reel2 && reel2==reel3 && reel1==reel3) {
    		int wonAmnt = reel1*crdtBet;
    		crdtRemain = crdtRemain+wonAmnt;
    		crdtBet = 0;
    		totWins = totWins+1;
    		creditsLeftDisplay.setText(String.valueOf(crdtRemain));
    		betAmntDisplay.setText(String.valueOf(crdtBet));
    		JOptionPane.showMessageDialog(frame, "CONGRATULATIONS, YOU HAVE WON!");
    		
    	} else if (reel1 == reel2 || reel2==reel3 || reel1==reel3) {
    		
    		JOptionPane.showMessageDialog(frame, "SPIN AGAIN");
    		
    	} else {
    		
    		JOptionPane.showMessageDialog(frame, "YOU LOSE! "+crdtBet+" CREDITS WILL BE LOST");
    		totLoss = totLoss+1;
    		crdtBet = 0;
    		betAmntDisplay.setText(String.valueOf(crdtBet));
    	}
		
	}
    
    
    public static void main(String[] args) {
    	new Container();
    	
	}
}
