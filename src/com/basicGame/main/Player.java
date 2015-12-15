package com.basicGame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler handler;
	private boolean inmune = false;
	//private int counter = 0;
	private long timeToInmune = 0;
	private long changeTime = 0;
	private Color color = Color.white;
	
	private Trail trail;
	private boolean visible;

	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler=handler;
		this.visible=true;
		
		this.trail = new Trail(10,color,x,y,32,32);
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}
	
	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		this.x = Game.clamp(this.x, Game.WIDTH-38, 0);
		this.y = Game.clamp(this.y, Game.HEIGHT-60, 0);
		
		long currTime = System.currentTimeMillis();
		
		if(this.inmune && this.changeTime<=currTime){
			this.changeTime=currTime+101;
			this.visible=!this.visible;
			
			//this.trail.setColor(this.color);
		}
		
		if(this.inmune && this.timeToInmune<=currTime){
			this.inmune=false;
			//this.counter=0;
			this.visible=true;
			//this.trail.setColor(this.color);
			this.timeToInmune=0;
			this.changeTime=0;
		}
		
		this.trail.tick(this.x, this.y);
		
		//if(this.inmune)
		//	this.counter++;
		
		collision();
	}
	
	public void collision(){
		for(int i = 0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId()== ID.BasicEnemy && !inmune){
				if(this.getBounds().intersects(tempObject.getBounds())){
					//COLLISION
					HUD.HEALTH -= 30;
					this.inmune=true;
					
					this.changeTime = System.currentTimeMillis()+100;
					this.timeToInmune = this.changeTime + 1900;
					this.visible=false;
					//this.trail.setColor(this.color);
					
					
				}
				
			}
			else if(tempObject.getId()== ID.GreenHealth){
				if(this.getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH+=50;
					handler.object.remove(tempObject);
					i--;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(this.visible){
			this.trail.render(g);
			g.setColor(this.color);
			g.fillRect(this.x, this.y, 32, 32);
		}
		
	}

}
