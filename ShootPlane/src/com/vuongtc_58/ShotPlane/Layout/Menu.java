package com.vuongtc_58.ShotPlane.Layout;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Audio.GameSound;


public class Menu extends JPanel{
	
	private JLabel lbPlay,lbHightScore,lbCancel;
	private ImageIcon imPlay,imSetting,imCancel;
	private Image imBackground = new ImageIcon(getClass().getResource("/Image/BackGround.png")).getImage();
	
	private GUI gui;
	private ArrayList<Integer> arrayScore = new ArrayList<Integer>();
	
	public Menu(GUI gui) {
		this.gui = gui;
		setLayout(null);
		GameSound.getIstance().getAudio(GameSound.MENU_PLAY).play();
		initComps();
		
	}
	
	public void initComps(){
		imPlay = new ImageIcon(getClass().getResource("/Image/PlayGame.png"));
		imSetting = new ImageIcon(getClass().getResource("/Image/HightScore.png"));
		imCancel = new ImageIcon(getClass().getResource("/Image/Cancel.png"));
		
				
		lbPlay = new JLabel();
		lbPlay.setBounds( 400, 150, imPlay.getIconWidth(), imPlay.getIconHeight());
		lbPlay.setIcon(imPlay);
		lbPlay.addMouseListener(mouseAdapter);
		add(lbPlay);
		
		lbHightScore = new JLabel();
		lbHightScore.setBounds( 400, 150+imPlay.getIconHeight()+50, imSetting.getIconWidth(), imSetting.getIconHeight());
		lbHightScore.setIcon(imSetting);
		lbHightScore.addMouseListener(mouseAdapter);
		add(lbHightScore);
		
		lbCancel = new JLabel();
		lbCancel.setBounds( 400, 150+imPlay.getIconHeight()+50+imSetting.getIconHeight()+50, imCancel.getIconWidth(), imCancel.getIconHeight());
		lbCancel.setIcon(imCancel);
		lbCancel.addMouseListener(mouseAdapter);
		add(lbCancel);

	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(imBackground, 0, 0, null);
	}
	
	MouseAdapter mouseAdapter = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==lbPlay){
				gui.changeToPlayGame();
			}
			if(e.getSource() == lbCancel){
				gui.dispose();
			}
			if(e.getSource() == lbHightScore){
				JOptionPane.showMessageDialog(null, getTop());
			}
		};
	};
	
	public String getTop(){
		getHighScore();
		for(int i=0;i<arrayScore.size();i++){
			for(int j=0;j<arrayScore.size()-i-1;j++){
				if(Integer.valueOf(arrayScore.get(j))<Integer.valueOf(arrayScore.get(j+1))){
					int temp = arrayScore.get(j);
					arrayScore.set(j, arrayScore.get(j+1));
					arrayScore.set(j+1, temp);
				}
			}
		}
		String hightScore = "";
		for(int i=0;i<arrayScore.size();i++){
			hightScore+=arrayScore.get(i)+"\n";
		}
		return hightScore;
	}
	
	public void getHighScore() {
		File file;
		try {
				file = new File("D:/HightScore.txt");
				if(!file.exists()){
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				FileReader fr = new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				String line;
				while((line = br.readLine()) !=null){
					arrayScore.add(Integer.parseInt(line));
				}
				fr.close();
				br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
