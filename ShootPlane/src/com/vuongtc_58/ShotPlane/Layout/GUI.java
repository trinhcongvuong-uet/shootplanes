package com.vuongtc_58.ShotPlane.Layout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame{
	
	public static final int WIDTHFRAME = 1000;
	public static final int HEIGHTFRAME = 600;
	
	private Menu mMenu;
	private PlayGame myPlayGame;
	
	public static boolean IS_RUNNING = false;
	
	public GUI() {
		IS_RUNNING = true;
		setSize(WIDTHFRAME,HEIGHTFRAME);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("SHOT PLANE BY VUONGTC_58");
		
		mMenu = new Menu(this);
		add(mMenu);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e); 
				int result = JOptionPane.showConfirmDialog(GUI.this, "You really want to exit?");
				if(result == JOptionPane.YES_OPTION){
					IS_RUNNING = false;
					GUI.this.dispose();
					Audio.GameSound.getIstance().getAudio(Audio.GameSound.MAIN_PLAY).stop();
				}
			}
		});
	}
	
	public void changeToPlayGame(){
		myPlayGame = new PlayGame();
		add(myPlayGame);
		myPlayGame.requestFocus();
		mMenu.setVisible(false);
	}

}
