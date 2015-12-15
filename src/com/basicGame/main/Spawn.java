package com.basicGame.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random rand;

	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
		rand = new Random();
	}
	
	public void tick(){
		
		if(hud.getScore()>=500){
			hud.setScore(0);
			hud.setLevel(hud.getLevel()+1);
			
			if(hud.getLevel()<5){
				handler.object.add(new BasicEnemy(rand.nextInt(Game.WIDTH-30)+1,
						rand.nextInt(Game.HEIGHT-30)+1, ID.BasicEnemy));
			}
			if(hud.getLevel()%4==0){
				handler.object.add(new GreenHealth(rand.nextInt(Game.WIDTH-30)+1,
						rand.nextInt(Game.HEIGHT-30)+1, ID.GreenHealth));
			}
			
		}
	}
	
	
}
