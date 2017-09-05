package com.vuongtc_58.ShotPlane.funtion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.vuongtc_58.ShotPlane.Layout.GUI;

public class MyPlane extends Actor{
	
	private int score;
	private int heart,kill;
	public  Image myPlaneImage;
	
	private ArrayList<Bullet> arrayMyBullets = new ArrayList<Bullet>();
		
	public static final int TYPE_MYPlane_1 = 1;
	public static final int TYPE_MYPlane_2 = 2;
	
	public MyPlane(int x,int y,int speed,int type,int orient) {
		super(x,y,orient);
		this.type = type;
		this.heart = 9;
		this.orient = UP;
		this.score = 0;
		this.setKill(0);
		if(type == TYPE_MYPlane_1){
			this.speed = 2;
		}
		else{
			this.speed = 1;
		}
		this.myPlaneImage = new ImageIcon(getClass().getResource("/Image/MyPlane.png")).getImage();
	}
	
	public void drawMyPlane(Graphics2D g){
		g.drawImage(myPlaneImage, x, y,null);
	}
	
	public void drawMyBullet(Graphics2D g){
		for(int i=0;i<arrayMyBullets.size();i++){
			arrayMyBullets.get(i).drawBullet(g);
		}
	}
	
	public void moveMyPlane(int count){
		
		if(count%speed!=0){
			return;
		}
		
		switch (orient) {
		case LEFT:
			if(x<=0){
				return;
			}
			x--;
			break;
			
		case RIGHT:
			if(x>=GUI.WIDTHFRAME-myPlaneImage.getWidth(null)-15){
				return;
			}
			x++;
			break;
			
		case UP:

			if(y<=0){
				return;
			}
			y--;
			break;
			
		case DOWN:
			if(y>=GUI.HEIGHTFRAME-myPlaneImage.getHeight(null)*2+20){
				return;
			}
			y++;
			break;

		default:
			break;
		}
		
	}
	
	public void moveMyBullet(int count){
		for(int i=0;i<arrayMyBullets.size();i++){
			arrayMyBullets.get(i).moveBullet(count);
		}
	}

	public void changeOrient(int orient) {
		if(this.orient==orient){
			return;
		}
		this.orient = orient;
	}

	public void fire(){
		int xDan = x-7;
		int yDan = y-30;
		int typeX;
		if(this.type==TYPE_MYPlane_1){
			typeX = Bullet.TYPE_MY_BULLET_1;
		}
		else{
			typeX = Bullet.TYPE_MY_BULLET_2;
		}
		int orientDan = Bullet.UP;
		
		Bullet myBullet = new Bullet(xDan, yDan,typeX, orientDan);
		arrayMyBullets.add(myBullet);
	}
	
	public boolean isVaChamPlane(Plane enemyPlane){
		Rectangle rec_one = new Rectangle(this.x,this.y,myPlaneImage.getWidth(null),myPlaneImage.getHeight(null));
		Rectangle rec_two = new Rectangle(enemyPlane.getX(),enemyPlane.getY(),
				enemyPlane.getImage().getWidth(null),enemyPlane.getImage().getHeight(null));
		return rec_one.intersects(rec_two);
	}
	
	public boolean isVaChamBullet(Bullet bullet){
		Rectangle rec_one = new Rectangle(this.x,this.y,myPlaneImage.getWidth(null),myPlaneImage.getHeight(null));
		Rectangle rec_two = new Rectangle(bullet.getX(),bullet.getY(),
				bullet.getBulletImage().getWidth(null),bullet.getBulletImage().getHeight(null));
		
		return rec_one.intersects(rec_two);
	}
	
	public boolean isVaChamSkill(Skill mySkill){
		
		Rectangle rec_one = new Rectangle(this.x,this.y,myPlaneImage.getWidth(null),myPlaneImage.getHeight(null));
		Rectangle rec_two = new Rectangle(mySkill.getX(),mySkill.getY(),
				mySkill.getImage().getWidth(null),mySkill.getImage().getHeight(null));
		return rec_one.intersects(rec_two);
	}
	
	public void destroy(int i){
		arrayMyBullets.remove(i);
	}
	
	public boolean isLose(){
		return this.getHeart()<=0;
	}
	
	public boolean isWin(){
		return this.getKill()==200;
	}
	
	public ArrayList<Bullet> getArrayBullets(){
		return arrayMyBullets;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void addScore(int score){
		this.score += score;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getHeart() {
		return heart;
	}

	public void removeMyBulet(){
		for(int i=0;i<arrayMyBullets.size();i++){
			if(arrayMyBullets.get(i).getY()<0)
				arrayMyBullets.remove(i);
		}
	}
	
	public void setTypeMyPlane(int type){
		this.type = type;
	}
	
	public void addHeart(int heart){
		this.heart+=heart;
	}

	public void setHeart(int i) {
		this.heart = i;
	}
	
}
