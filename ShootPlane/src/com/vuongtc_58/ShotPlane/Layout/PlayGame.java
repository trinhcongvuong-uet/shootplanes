package com.vuongtc_58.ShotPlane.Layout; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import Audio.GameSound;

import com.vuongtc_58.ShotPlane.funtion.MyPlane;
import com.vuongtc_58.ShotPlane.funtion.PlaneManager;
import com.vuongtc_58.ShotPlane.funtion.Skill;

public class PlayGame extends JPanel implements Runnable{
	
	private Image ImageHeart = new ImageIcon(getClass().getResource("/Image/Heart.png")).getImage();
	private BackGround myBackGround = new BackGround();
	private MyPlane myPlane;
	private PlaneManager mPlaneManager = new PlaneManager();
	private Skill mSkill = new Skill(mPlaneManager);
	private Image imBackGround = new ImageIcon(getClass().getResource("/Image/BackGround.png")).getImage();
	
	private Thread myThread;
	private volatile boolean Press_P = true;
	
	private int count = 0;
	private int mydem=0;
	private int enemydem = 0;
	private int countlevel = 0;
	private BitSet traceKey = new BitSet();
	
	public PlayGame() {
		
		setLayout(null);
		setFocusable(true);
		setBackground(Color.BLACK);
		myPlane = new MyPlane(440,450,0,MyPlane.TYPE_MYPlane_1,MyPlane.UP);
		myBackGround.initStars();
		
		mPlaneManager.level_one();
				
		addKeyListener(keyAdapter);
		
		GameSound.getIstance().getAudio(GameSound.MAIN_PLAY).loop();
		myThread = new Thread(this);
		myThread.start();
		
	}
	
	public void drawBackGround(Graphics2D g){
		g.drawImage(imBackGround, 0, 0, GUI.WIDTHFRAME, GUI.HEIGHTFRAME, null);
	}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		myPlane.drawMyPlane(g2d);
		myPlane.drawMyBullet(g2d);
		mPlaneManager.drawAllPlane(g2d);
		mPlaneManager.drawAllBullet(g2d);
		myBackGround.drawAllStars(g2d);
		mSkill.drawAllSkill(g2d);
		g2d.drawImage(ImageHeart,0,0,null);
		g2d.setFont(new Font("Tahoma", Font.BOLD, 24));
		g2d.drawString(String.valueOf(myPlane.getScore()),0,50 );
		g2d.drawString(String.valueOf(myPlane.getHeart()),25,20);
		
		
	}
	
	private KeyAdapter keyAdapter = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			traceKey.set(e.getKeyCode());
			if(e.getKeyCode() == KeyEvent.VK_P){
				Press_P = !Press_P;
			}
		};
		
		public void keyReleased(KeyEvent e) {
			traceKey.clear(e.getKeyCode());
		};
	};
	
	public void setScore() {
		File file = new File("D:/HightScore.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream output = new FileOutputStream(file,true);
			String S =myPlane.getScore()+"\n";
			byte [] arrayScore =S.getBytes();
			output.write(arrayScore);
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		while(GUI.IS_RUNNING){
			if(Press_P){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mPlaneManager.removePlane();
				mPlaneManager.removeStone();
				mPlaneManager.removeAllBullet();
				myPlane.removeMyBulet();
				
				if(count>1000000000){
					count=0;
				}
				count++;
				
				if(traceKey.get(KeyEvent.VK_LEFT)){
					myPlane.changeOrient(MyPlane.LEFT);
					myPlane.moveMyPlane(count);
				}
				
				if(traceKey.get(KeyEvent.VK_RIGHT)){
					myPlane.changeOrient(MyPlane.RIGHT);
					myPlane.moveMyPlane(count);
				}
				
				if(traceKey.get(KeyEvent.VK_UP)){
					myPlane.changeOrient(MyPlane.UP);
					myPlane.moveMyPlane(count);
				}
				
				if(traceKey.get(KeyEvent.VK_DOWN)){
					myPlane.changeOrient(MyPlane.DOWN);
					myPlane.moveMyPlane(count);
				}
				
				if(mydem==0){
					myPlane.fire();
					Audio.GameSound.getIstance().getAudio(Audio.GameSound.SHOOTING).play();
					mydem=150;
				}
				if(mydem>0){
					mydem--;
				}
				
				if(enemydem ==0){
					mPlaneManager.fireAll();
					enemydem = 1000;
				}
				
				if(enemydem>0){
					enemydem--;
				}
				
				countlevel++;
				if(countlevel==20000){
					mPlaneManager.level_second();
				}
				
				if(countlevel==40000){
					mPlaneManager.level_third();
				}
				
				mSkill.destroySkill(myPlane);
				mPlaneManager.destroyItem(myPlane);
				myPlane.moveMyBullet(count);
				mPlaneManager.moveAllPlane(count);
				mPlaneManager.moveAllBullet(count);
				mSkill.moveAllSkill(count);
				myBackGround.moveAllStars(count);
				repaint();
				if(myPlane.isWin()){
					setScore();
					JOptionPane.showMessageDialog(null, "WIN");
					System.exit(0);
				}
				if(myPlane.isLose()){
					setScore();
					Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DEAD).play();
					JOptionPane.showMessageDialog(null, "LOSE");
					System.exit(0);
				}
				
			}
		}
	}
	
	

}
