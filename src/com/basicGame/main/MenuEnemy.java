package com.basicGame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuEnemy extends GameObject{
	
	private Trail trail;
	private Random rand;
	private int fact;
	private int vAbs;
	private Color color;
	
	public MenuEnemy(int x, int y, ID id){
		super(x, y, id);
		this.rand=new Random();
		this.fact = rand.nextInt(6)+2;
		this.vAbs = 10;
		this.velX=vAbs*fact/10;
		velX=rand.nextBoolean()?velX*-1:velX;
		this.velY=vAbs*(10-fact)/10;
		velY=rand.nextBoolean()?velY*-1:velY;
		color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		this.trail = new Trail(20, color, x, y, 16, 16);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,16,16);
	}

	@Override
	public void tick() {
		/*if(time<=System.currentTimeMillis()){
			time+=2000;
			vAbs*=1.1;
			this.velX=velX<0?-1*vAbs*fact/10:vAbs*fact/10;
			this.velY=velY<0?-1*vAbs*(10-fact)/10:vAbs*(10-fact)/10;
			
		}*/
		this.x += this.velX;
		this.y += this.velY;
		
		if(this.y <= 0 || this.y >= Game.HEIGHT -40)
			this.velY *= -1;
		if(this.x <= 0 || this.x >= Game.WIDTH -16)
			this.velX *= -1;
		
		this.trail.tick(this.x, this.y);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(this.x, this.y, 16, 16);
		this.trail.render(g);
	}

}
