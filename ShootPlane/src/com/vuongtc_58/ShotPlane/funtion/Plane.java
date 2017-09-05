package com.vuongtc_58.ShotPlane.funtion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.vuongtc_58.ShotPlane.Layout.GUI;

public class Plane extends Actor{
	
	private int score;
	private int heart;
	private Image image;
	
	private ArrayList<Bullet> arrayBullets = new ArrayList<Bullet>();
	
	public static final int TYPE_Plane_NORMAL = 1;
	public static final int TYPE_Plane_SUPER = 2;
	
	public static final int TYPE_STONE_SMALL = 3;
	public static final int TYPE_STONE_MEDIUM = 4;
	public static final int TYPE_STONE_BIG = 5;
	
	public static final int TYPE_UFO = 6;
	
	Random rd = new Random();
	
	public Plane(int x,int y,int type,int orient,int score) {
		super(x, y, orient);
		this.score = score;
		this.type = type;
		if(this.type == TYPE_Plane_NORMAL){
			this.setImage(new ImageIcon(getClass().getResource("/Image/EnemyPlaneNormal.png")).getImage());
			this.setHeart(2);
			this.speed = 7;
		}
		if(this.type ==TYPE_Plane_SUPER){
			this.setImage(new ImageIcon(getClass().getResource("/Image/EnemyPlaneSuper.png")).getImage());
			this.setHeart(3);
			this.speed = 7;
		}
		if(this.type ==TYPE_STONE_SMALL){
			this.setImage(new ImageIcon(getClass().getResource("/Image/Stone_Small.png")).getImage());
			this.setHeart(1);
			this.speed = 8;
		}
		if(this.type ==TYPE_STONE_MEDIUM){
			this.setImage(new ImageIcon(getClass().getResource("/Image/Stone_Medium.png")).getImage());
			this.setHeart(2);
			this.speed = 7;
		}
		if(this.type ==TYPE_STONE_BIG){
			this.setImage(new ImageIcon(getClass().getResource("/Image/Stone_Big.png")).getImage());
			this.setHeart(3);
			this.speed = 6;
		}
		if(this.type ==TYPE_UFO){
			this.setImage(new ImageIcon(getClass().getResource("/Image/UFO.png")).getImage());
			this.setHeart(5);
			this.speed = 6;
		}
	}
	
	public void drawPlane(Graphics2D g){
		g.drawImage(getImage(), x, y, null);
	}
	
	public void movePlane(int count){
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
			if(y>GUI.HEIGHTFRAME){
				break;
			}
			break;
			
		case RIGHT:
			x++;
			y++;
			if(x>GUI.WIDTHFRAME-getImage().getWidth(null)){
				x = -getImage().getWidth(null);
			}
			if(y>GUI.HEIGHTFRAME){
				y=0;
			}
			break;
			
		case UP:
			y--;
			if(y<-getImage().getHeight(null)){
				y = GUI.HEIGHTFRAME + getImage().getHeight(null);
			}
			break;
			
		case DOWN:
			y++;
			if(y>GUI.HEIGHTFRAME){
				break;
			}
			break;
			
		default:
			break;
		}
	}
	
	public void move(int count){
		if(count%speed!=0){
			return;
		}
		
		switch (orient) {
		case LEFT:
			x--;
			if(x<0){
				x=GUI.WIDTHFRAME;
			}
			break;
		case RIGHT:
			x++;
			if(x>GUI.WIDTHFRAME){
				x=0;
			}
			break;
		case UP:
			y--;
			break;
		case DOWN:
			if(y<300){
				y++;
			}
			if(y>=300){
				if(x<500){
					orient = LEFT;
				}
			}
			else
				if(y>200&&y<300){
					if(x>=500){
						orient = RIGHT;
					}
				}
		default:
			break;
		}
	}
	
	public int getType(){
		return type;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getScore(){
		 return score;
	}
	
	public void destroy(int i){
		getArrayBullets().remove(i);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public ArrayList<Bullet> getArrayBullets() {
		return arrayBullets;
	}

	public void removeBullet(){
		for(int i=0;i<arrayBullets.size();i++){
			if(arrayBullets.get(i).getY()>GUI.WIDTHFRAME){
				arrayBullets.remove(i);
			}
		}
	}
}
