package com.basicGame.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD{
	
	public static int HEALT = 100;
	
	private int score = 0;
	private int level = 1;

	public void tick() {
		// TODO Auto-generated method stub
		score++;
		
	}


	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 32);
		g.setColor(Color.green);
		g.fillRect(10, 10, HEALT*2, 32);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 32);
		
		g.drawString("Score: "+score, 10, 60);
		g.drawString("Level: "+level, 10, 76);
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

}
