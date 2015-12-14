package com.basicGame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter{
	
	Font titleFont = new Font("Monospaced", 1, 50);
	Font optionFont = new Font("Monospaced", 1, 40);

	public void mousePressed(MouseEvent e){
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		//g.drawRect(40, 60, 240, 64);
		g.setFont(titleFont);
		g.drawString("Monospaced", 50, 105);
		
		g.setFont(optionFont);
		g.drawString("Play", 65, 240);
		g.drawRect(40, 200, 150, 64);
		g.drawString("Quit", 65, 340);
		g.drawRect(40, 300, 150, 64);
	}
}
