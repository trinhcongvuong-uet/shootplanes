package com.vuongtc_58.ShotPlane.funtion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Bullet extends Actor{
	
	private Image bulletImage;
	
	public static final int TYPE_MY_BULLET_1 = 0;
	public static final int TYPE_BULLET_NORMAL = 1;
	public static final int TYPE_BULLET_SUPER = 2;
	public static final int TYPE_MY_BULLET_2 = 3;
	
	public Bullet(int x,int y,int type,int orient) {
		super(x, y,orient);
		this.type = type;
		if(this.type==TYPE_BULLET_NORMAL){
			this.speed = 3;
			this.bulletImage = new ImageIcon(getClass().getResource("/Image/EnemyBulletNormal.png")).getImage();
		}
		else{
			if(this.type==TYPE_BULLET_SUPER){
				this.speed = 2;
				this.bulletImage = new ImageIcon(getClass().getResource("/Image/EnemyBulletNormal.png")).getImage();
			}
			else{
				if(this.type==TYPE_MY_BULLET_1){
					this.speed = 2;
					this.setBulletImage(new ImageIcon(getClass().getResource("/Image/MyBullet_1.png")).getImage());
				}
				else{
					this.speed = 1;
					this.setBulletImage(new ImageIcon(getClass().getResource("/Image/MyBullet_2.png")).getImage());
				}
			}
		}
		this.x = x+this.getBulletImage().getWidth(null)+4;
		this.y = y+this.getBulletImage().getHeight(null);
		this.orient = orient;
	}
	
	public void drawBullet(Graphics2D g){
		g.drawImage(getBulletImage(), x, y, null);
	}
	
	public void moveBullet(int count){
		if(count%speed!=0){
			return;
		}
		
		switch (orient) {
		case LEFT:
			x--;
			break;
			
		case RIGHT:
			x++;
			break;
			
		case UP:
			y--;
			break;
			
		case DOWN:
			y++;
			break;

		default:
			break;
		}
	}
	
	public boolean isVaChamPlane(Plane Plane){
		Rectangle rec_one = new Rectangle(this.x,this.y,getBulletImage().getWidth(null),getBulletImage().getHeight(null));
		Rectangle rec_two = new Rectangle(Plane.getX(),Plane.getY(),
				Plane.getImage().getWidth(null),Plane.getImage().getHeight(null));
		return rec_one.intersects(rec_two);
	}
	
	public boolean isVaChamBullet(Bullet bullet){
		Rectangle rec_one = new Rectangle(this.x,this.y,getBulletImage().getWidth(null),getBulletImage().getHeight(null));
		Rectangle rec_two = new Rectangle(bullet.getX(),bullet.getY(),
				bullet.getBulletImage().getWidth(null),bullet.getBulletImage().getHeight(null));
		return rec_one.intersects(rec_two);
	}

	public Image getBulletImage() {
		return bulletImage;
	}

	public void setBulletImage(Image bulletImage) {
		this.bulletImage = bulletImage;
	}
	
	
	
	
}
