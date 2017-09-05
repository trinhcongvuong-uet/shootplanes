package com.vuongtc_58.ShotPlane.funtion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.vuongtc_58.ShotPlane.Layout.GUI;

public class Skill extends Actor{

	
	
	private Image imageSKill;
	private static final int SKILL_BULLET = 1;
	private static final int SKILL_BOOM = 2;
	private static final int SKILL_Heart = 3;
	
	
	private ArrayList<Skill> arraySkills = new ArrayList<Skill>();
	private static final int countSkill = 2;
	
	Random rd = new Random();
	
	private PlaneManager mPlaneManager;
	
	public Skill(PlaneManager mPlaneManager) {
		this.mPlaneManager = mPlaneManager;
	}
	
	public Skill(int x,int y,int orient,int type) {
		super(x, y, orient);
		this.type = type;
		this.speed = 6+rd.nextInt(6);
		if(type == SKILL_BULLET){
			imageSKill = new ImageIcon(getClass().getResource("/Image/SkillBullet.png")).getImage();
		}
		else{
			if(type == SKILL_BOOM){
				imageSKill = new ImageIcon(getClass().getResource("/Image/SkillBoom.png")).getImage();
			}
			else{
				imageSKill = new ImageIcon(getClass().getResource("/Image/SkillHeart.png")).getImage();
			}
		}
		
	}
	
	public void drawSkill(Graphics2D g){
		g.drawImage(imageSKill, x, y, null);
	}
	
	public void drawAllSkill(Graphics2D  g){
		for(int i=0;i<arraySkills.size();i++){
			arraySkills.get(i).drawSkill(g);
		}
	}
	
	public void moveSkill(int count){
		if(count%speed!=0){
			return;
		}
		
		switch (orient) {
		case LEFT:
			x--;
			y++;
			if(x<-getImage().getWidth(null)){
				x = GUI.WIDTHFRAME + getImage().getWidth(null);
			}
			break;
			
		case RIGHT:
			x++;
			y++;
			if(x>GUI.WIDTHFRAME-getImage().getWidth(null)){
				x = -getImage().getWidth(null);
			}
			break;
			
		case DOWN:
			y++;
			break;
			
		default:
			break;
		}
	}
	
	public void moveAllSkill(int count){
		for(int i=0;i<arraySkills.size();i++){
			arraySkills.get(i).moveSkill(count);
		}
	}
	
	
	public void initSkill(){
		for(int i=0;i<countSkill;i++){
			int x,y,orient,type;
			x = rd.nextInt(GUI.WIDTHFRAME);
			y = -10;
			int randomOrient = rd.nextInt(3);
			int randomType = rd.nextInt(3);
			if(randomOrient==0){
				orient = LEFT;
			}
			else{
				if(randomOrient == 1){
					orient = RIGHT;
				}
				else{
					orient = DOWN;
				}
			}
			
			if(randomType==0){
				type = SKILL_BULLET;
			}
			else{
				if(randomType ==1){
					type = SKILL_BOOM;
				}
				else{
					type = SKILL_Heart;
				}
			}
			
			Skill mySkill = new Skill(x, y, orient, type);
			arraySkills.add(mySkill);
		}
	}
	

	public void destroySkill(MyPlane myPlane){
		for(int i=0;i<arraySkills.size();i++){
			if(myPlane.isVaChamSkill(arraySkills.get(i))){
				if(arraySkills.get(i).getType()==SKILL_BOOM){
					for(int k=0;k<mPlaneManager.getArrayPlanes().size();k++){
						myPlane.addScore(mPlaneManager.getArrayPlanes().get(k).getScore());
					}
					for(int k = 0;k<mPlaneManager.getArrayStones().size();k++){
						myPlane.addScore(mPlaneManager.getArrayPlanes().get(k).getScore());
					}
					mPlaneManager.resetArrayPlane();
					mPlaneManager.resetArrayStone();
					arraySkills.remove(i);
				}
				else{
					if(arraySkills.get(i).getType()==SKILL_BULLET){
						myPlane.setTypeMyPlane(MyPlane.TYPE_MYPlane_2);
						arraySkills.remove(i);
					}
					else{
						myPlane.addHeart(2);
						arraySkills.remove(i);
					}
				}
			}
		}
	}
	
	public Image getImage() {
		return this.imageSKill;
	}
}
