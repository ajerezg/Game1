package com.basicGame.main;

import java.awt.Color;
import java.awt.Graphics;

import com.basicGame.main.Game.STATE;

public class HUD{
	
	public static int HEALTH = 100;
	
	private Game game;
	
	private int score = 0;
	private int level = 1;

	public HUD(Game game) {
		this.game = game;
	}


	public void tick() {
		// TODO Auto-generated method stub
		//System.out.println("HUD ticking");
		HEALTH=Game.clamp(HEALTH, 100, 0);
		score++;
		if(HEALTH<=0){
			game.gameState=STATE.Dead;
			game.getHandler().removeAll();
			game.addMouseListener(game.getDeadMenu());
		}
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 32);
		g.setColor(Color.green);
		g.fillRect(10, 10, HEALTH*2, 32);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 32);
		
		g.drawString("Puntaje: "+score, 10, 60);
		g.drawString("Nivel: "+level, 10, 76);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void reset(){
		score=0;
		level=1;
		HEALTH=100;
	}

}
