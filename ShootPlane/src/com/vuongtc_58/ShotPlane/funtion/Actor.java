package com.vuongtc_58.ShotPlane.funtion;

public class Actor {
	
	protected int x,y;

	protected int type;

	protected int speed;

	protected int orient;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public Actor() {
		
	}
	
	public Actor(int x,int y,int orient){
		this.x = x;
		this.y = y;
		this.orient = orient;
	}
	

	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getType(){
		return this.type;
	}
}
