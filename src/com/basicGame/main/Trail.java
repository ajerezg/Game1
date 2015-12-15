package com.basicGame.main;

import java.awt.Color;
import java.awt.Graphics;

public class Trail {
	
	private int[][] trail;
	private Color[] color;
	private int dx,dy;
	private float[] colorComponent;
	
	public Trail(int i, Color c, int x, int y, int dx, int dy) {
		this.dx=dx;
		this.dy=dy;
		this.trail = new int[i][2];
		this.color = new Color[i];
		this.colorComponent = new float[3];
		c.getRGBColorComponents(colorComponent);
		float alpha=1;
		for(int j=0; j<i;j++){
			alpha -= (1.0/(i+1));
			//System.out.println(alpha);
			this.trail[j][0]=x;
			this.trail[j][1]=y;
			this.color[j]= new Color(colorComponent[0],colorComponent[1],colorComponent[2], alpha);
			
		}
	}
	
	public void tick(int x, int y){
		int nx=x;
		int ny=y;
		int ox=0;
		int oy=0;
		for(int i=0;i<color.length;i++){
			ox=trail[i][0];
			oy=trail[i][1];
			trail[i][0]=nx;
			trail[i][1]=ny;
			nx=ox;
			ny=oy;
		}
	}
	
	public void render(Graphics g){
		
		for(int i=0;i<color.length;i++){
			g.setColor(this.color[i]);
			g.fillRect(this.trail[i][0], this.trail[i][1], this.dx, this.dy);
		}
	}
	
	public void setColor(Color c){
		c.getRGBColorComponents(colorComponent);
		int alpha = 1;
		for(int j=0; j<color.length;j++){
			alpha -= (1.0/(color.length+1));
			this.color[j]= new Color(colorComponent[0],colorComponent[1],colorComponent[2], alpha);
			
		}
	}

	

	
}

	