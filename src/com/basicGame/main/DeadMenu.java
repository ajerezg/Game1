package com.basicGame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.basicGame.main.Game.STATE;

public class DeadMenu extends MouseAdapter{
	Font titleFont = new Font("Monospaced", 1, 50);
	Font optionFont = new Font("Monospaced", 1, 40);
	Font textFont = new Font("Monospaced",1,12);
	private Game game;
	public DeadMenu(Game game, Handler handler) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		//Return
		if(mouseOver(x,y,250,200,150,64)){
			this.game.gameState = STATE.Menu;
			game.removeMouseListener(this);
			game.getMenu().addMenuBackground(20);
			game.addMouseListener(game.getMenu());
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int x, int y, int ix, int iy, int width, int height){
		if(ix<x && x<ix+width && iy<y && y<iy+height)
			return true;
		else
			return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		//g.drawRect(40, 60, 240, 64);
		g.setFont(optionFont);
		g.drawString("Volver", 255, 242);
		g.drawRect(250, 200, 150, 64);
		
	}
}


