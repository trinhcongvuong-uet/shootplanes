package com.vuongtc_58.ShotPlane.funtion;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import com.vuongtc_58.ShotPlane.Layout.GUI;

public class PlaneManager {
	
	private ArrayList<Plane> arrayPlanes = new ArrayList<Plane>();
	private ArrayList<Plane> arrayStones = new ArrayList<Plane>();
	Random rd = new Random();

	public void level_one(){
		for(int i=0;i<10;i++){
			Plane enenmy = new Plane(i*100, -100, Plane.TYPE_Plane_NORMAL, Plane.DOWN, 1);
			Plane ePlane = new Plane(i*100-50, -200, Plane.TYPE_Plane_SUPER, Plane.DOWN, 2);
			arrayPlanes.add(enenmy);
			arrayPlanes.add(ePlane);
		}
	}
	
	public void level_second(){
		int countlevelsecond = 0;
		int levelsecond[][] = new int[10][10];
		falseAll(levelsecond, 10,10 );
		
		for(int i=0;i<10;i++){
			for(int j = 0;j<10;j++){
				levelsecond[i][j] = rd.nextInt(2);
				if(levelsecond[i][j]==1){
					if(countlevelsecond>30){
						return;
					}
					if(countlevelsecond<30){
						int choose = rd.nextInt(2);
						int orient;
						if(choose ==0){
							orient = Plane.LEFT;
						}
						else{
							orient = Plane.RIGHT;
						}
						Plane stone = new Plane(i*100, -j*100, Plane.TYPE_STONE_BIG, orient, 3);
						arrayStones.add(stone);
						countlevelsecond++;
					}
					
				}
			}
		}
	}
	
	public void level_third(){
		int countlevelthird = 0;
		int levelthird[][] = new int[10][2];
		trueAll(levelthird, 10,2 );
		for(int i=0;i<10;i++){
			for(int j = 0;j<2;j++){
				if(levelthird[i][j]==1){
					if(countlevelthird>=50){
						return;
					}
					if(countlevelthird<=46){
						Plane enemy = new Plane(10, -j*100-i*100,
								Plane.TYPE_Plane_SUPER, Plane.DOWN, 3);
						
						Plane enemy1 = new Plane(GUI.WIDTHFRAME-50,
								-j*100-i*100, Plane.TYPE_Plane_SUPER, Plane.DOWN, 3);
						
						Plane enemy2 = new Plane(60, -j*100-i*100-50,
								Plane.TYPE_Plane_SUPER, Plane.DOWN, 3);
						
						Plane enemy3 = new Plane(GUI.WIDTHFRAME-100,
								-j*100-i*100-50, Plane.TYPE_Plane_SUPER, Plane.DOWN, 3);
						
						arrayPlanes.add(enemy);
						arrayPlanes.add(enemy1);
						arrayPlanes.add(enemy2);
						arrayPlanes.add(enemy3);
						countlevelthird+=4;
					}
			}
			}
		}
				
	}
	
	public void drawAllPlane(Graphics2D g){
		for(int i=0;i<arrayPlanes.size();i++){
			arrayPlanes.get(i).drawPlane(g);
		}
		for(int i=0;i<arrayStones.size();i++){
			arrayStones.get(i).drawPlane(g);
		}
	}
	
	public void drawAllBullet(Graphics2D g){
		for(int i=0;i<arrayPlanes.size();i++){
			for(int j=0;j<arrayPlanes.get(i).getArrayBullets().size();j++){
				arrayPlanes.get(i).getArrayBullets().get(j).drawBullet(g); 
			}
		}
	}
	
	public void moveAllPlane(int count){
		for(int i=0;i<arrayPlanes.size();i++){
			arrayPlanes.get(i).move(count);
		}
		for(int i=0;i<arrayStones.size();i++){
			arrayStones.get(i).movePlane(count);
		}
	}
	
	public void moveAllBullet(int count){
		for(int i=0;i<arrayPlanes.size();i++){
			for(int j=0;j<arrayPlanes.get(i).getArrayBullets().size();j++){
				arrayPlanes.get(i).getArrayBullets().get(j).moveBullet(count);
			}
		}
	}
	
	public void fireAll(){
		for(int i=0;i<arrayPlanes.size();i++){
			int xDan;
			int yDan = arrayPlanes.get(i).getY()+20;
			int orientDan =Bullet.DOWN;
			int typeDan;
			
			if(arrayPlanes.get(i).getType()==Plane.TYPE_Plane_NORMAL){
				typeDan = Bullet.TYPE_BULLET_NORMAL;
				xDan = arrayPlanes.get(i).getX()+2;
			}
			else{
					typeDan = Bullet.TYPE_BULLET_SUPER;
					xDan = arrayPlanes.get(i).getX()+2;
			}
			Bullet bullet = new Bullet(xDan,yDan,typeDan,orientDan);
			arrayPlanes.get(i).getArrayBullets().add(bullet);
			
		}
	}
	
	public void destroyItem(MyPlane myPlane){
		for(int i=myPlane.getArrayBullets().size()-1;i>=0;i--){
			for(int j=arrayPlanes.size()-1;j>=0;j--){
				if(myPlane.getArrayBullets().get(i).isVaChamPlane(arrayPlanes.get(j))){
					myPlane.destroy(i);
					arrayPlanes.get(j).setHeart(arrayPlanes.get(j).getHeart() - 2);
					if(arrayPlanes.get(j).getHeart()<=0){
						myPlane.setKill(myPlane.getKill() + 1);
						myPlane.addScore(arrayPlanes.get(j).getScore());
						arrayPlanes.remove(j);
					}
					return;
				}
				for(int k= arrayPlanes.get(j).getArrayBullets().size()-1;k>=0;k--){
					if(myPlane.getArrayBullets().get(i).isVaChamBullet(arrayPlanes.get(j).getArrayBullets().get(k))){
						myPlane.destroy(i);
						arrayPlanes.get(j).getArrayBullets().remove(k);
						return;
					}
				}
			}
		}
			
		for(int i=myPlane.getArrayBullets().size()-1;i>=0;i--){
			for(int j = arrayStones.size()-1;j>=0;j--){
				if(myPlane.getArrayBullets().get(i).isVaChamPlane(arrayStones.get(j))){
					myPlane.destroy(i);
					arrayStones.get(j).setHeart(arrayStones.get(j).getHeart() - 2);
					if(arrayStones.get(j).getHeart()<=0){
						myPlane.addScore(arrayStones.get(j).getScore());
						arrayStones.remove(j);
					}
					return;
				}
			}
		}
		
		for(int i=arrayPlanes.size()-1;i>=0;i--){
			for(int j=arrayPlanes.get(i).getArrayBullets().size()-1;j>=0;j--){
				if(myPlane.isVaChamBullet(arrayPlanes.get(i).getArrayBullets().get(j))){
					if(arrayPlanes.get(i).getArrayBullets().get(j).getType()==Bullet.TYPE_BULLET_NORMAL){
						myPlane.setHeart(myPlane.getHeart()-1);
						arrayPlanes.get(i).getArrayBullets().remove(j);
						return;
					}
					else{
						myPlane.setHeart(myPlane.getHeart()-2);
						arrayPlanes.get(i).getArrayBullets().remove(j);
						return;
					}
				}
			}
		}
		
		for(int i = arrayPlanes.size()-1;i>=0;i--){
			if(myPlane.isVaChamPlane(arrayPlanes.get(i))){
				if(arrayPlanes.get(i).getType()==Plane.TYPE_Plane_NORMAL){
					myPlane.setHeart(myPlane.getHeart()-1);
					Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DIE).play();
					myPlane.addScore(arrayPlanes.get(i).getScore());
					arrayPlanes.remove(i);
					return;
				}
				else{
					if(arrayPlanes.get(i).getType()==Plane.TYPE_Plane_SUPER){
						myPlane.setHeart(myPlane.getHeart()-2);
						Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DIE).play();
						myPlane.addScore(arrayPlanes.get(i).getScore());
						arrayPlanes.remove(i);
						return;
					}
					else{
						myPlane.setHeart(myPlane.getHeart()-3);
						Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DIE).play();
						myPlane.addScore(arrayPlanes.get(i).getScore());
						arrayPlanes.remove(i);
						return;
					}
				}
				
			}
		}
		
		for(int i = arrayStones.size()-1;i>=0;i--){
			if(myPlane.isVaChamPlane(arrayStones.get(i))){
				if(arrayStones.get(i).getType()==Plane.TYPE_STONE_SMALL
						||arrayStones.get(i).getType() == Plane.TYPE_STONE_MEDIUM){
					myPlane.setHeart(myPlane.getHeart()-1);
					Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DIE).play();
					myPlane.addScore(arrayStones.get(i).getScore());
					arrayStones.remove(i);
					return;
				}
				else{
						myPlane.setHeart(myPlane.getHeart() - 2);
						Audio.GameSound.instance.getAudio(Audio.GameSound.MYPLANE_DIE).play();
						myPlane.addScore(arrayStones.get(i).getScore());
						arrayStones.remove(i);
						return;
				}
				
			}
		}
		
	}
	
	public void removePlane(){
		for(int i=0;i<arrayPlanes.size();i++){
			if(arrayPlanes.get(i).getY()>GUI.HEIGHTFRAME){
				arrayPlanes.remove(i);
			}
		}
	}
	
	public void removeStone(){
		for(int i=0;i<arrayStones.size();i++){
			if(arrayStones.get(i).getY()>GUI.HEIGHTFRAME){
				arrayStones.remove(i);
			}
		}
	}
	
	public void removeAllBullet(){
		for(int i=0;i<arrayPlanes.size();i++){
			arrayPlanes.get(i).removeBullet();
		}
	}
	
	public void falseAll(int level[][],int n,int m){
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				level[i][j] = 0;
			}
		}
	}
	
	public void trueAll(int level[][],int n,int m){
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				level[i][j] = 1;
			}
		}
	}
	public void resetArrayPlane(){
		arrayPlanes = null;
		arrayPlanes = new ArrayList<Plane>();
	}
	
	public void resetArrayStone(){
		arrayStones = null;
		arrayStones = new ArrayList<Plane>();
	}
	
	public ArrayList<Plane> getArrayPlanes(){
		return arrayPlanes;
	}
	
	public ArrayList<Plane> getArrayStones(){
		return arrayStones;
	}
}
