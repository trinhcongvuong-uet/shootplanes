package com.vuongtc_58.ShotPlane.Layout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class BackGround {
	private ArrayList<Star> arrStars=new ArrayList<Star>();
	private static final int COUNT_STAR=200;
	private static final int MIN_SIZE=2;
	private static final int MAX_SIZE=4;
	private Random rd=new Random();
	
	
	public void initStars(){
		for (int i = 0; i < COUNT_STAR; i++) {
			int x=rd.nextInt(GUI.WIDTHFRAME);
			int y=rd.nextInt(GUI.HEIGHTFRAME);
			
			int size=rd.nextInt(MAX_SIZE-MIN_SIZE)+MIN_SIZE;
			int red=rd.nextInt(256);
			int green=rd.nextInt(256);
			int blue=rd.nextInt(256);
			
			Color color=new Color(red, green, blue);

			Star star=new Star(x, y, size, color);
			arrStars.add(star);
		}
	}
	public void drawAllStars(Graphics2D g){
		for (int i = 0; i < arrStars.size(); i++) {
			arrStars.get(i).draw(g);
		}
	}
	public void moveAllStars(int count){
		for (int i = 0; i < arrStars.size(); i++) {
			arrStars.get(i).move(count);
		}
	}
}
