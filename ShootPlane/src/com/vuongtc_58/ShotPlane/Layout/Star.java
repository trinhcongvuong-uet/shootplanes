package com.vuongtc_58.ShotPlane.Layout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class Star {
	private int x, y, size,speed;
	private Color color;
	private Random rd = new Random();

	public Star(int x, int y, int size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.speed = 4;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(new Font("Tahoma", Font.BOLD, size));
		g.drawString("*", x, y);
	}

	public void move(int count) {
		if(count%speed!=0){
			return;
		}
		y++;
		if (count % 10==0) {
			int red = rd.nextInt(256);
			int green = rd.nextInt(256);
			int blue = rd.nextInt(256);

			color = new Color(red, green, blue);
		}
		if (y >GUI.HEIGHTFRAME-size)
			y = - size ;
	}
}
