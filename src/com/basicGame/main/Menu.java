package com.basicGame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.basicGame.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	Font titleFont = new Font("Monospaced", 1, 50);
	Font optionFont = new Font("Monospaced", 1, 40);
	Font textFont = new Font("Monospaced",1,12);
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random rand;
	
	

	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler =  handler;
		this.hud = hud;
		this.rand = new Random();
		
		addMenuBackground(20);
	}

	public void mousePressed(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		//Play
		if(mouseOver(x,y,40,200,150,64)){
			game.gameState = STATE.Game;
			hud.reset();
			handler.removeAll();
			handler.addObject(new Player(Game.WIDTH/2 -32, Game.HEIGHT/2 -32,ID.Player, handler));
			handler.addObject(new BasicEnemy(Game.WIDTH/4 *1, Game.HEIGHT/4 *1,ID.BasicEnemy));
			handler.addObject(new BasicEnemy(Game.WIDTH/4 *3, Game.HEIGHT/4 *3,ID.BasicEnemy));
			game.removeMouseListener(this);
		}
		//Quit
		if(mouseOver(x,y,40,300,150,64)){
			System.exit(1);
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
		g.setFont(titleFont);
		g.drawString("Vale por un Título", 50, 105);
		
		g.setFont(optionFont);
		g.drawString("Jugar", 55, 242);
		g.drawRect(40, 200, 150, 64);
		g.drawString("Salir", 55, 342);
		g.drawRect(40, 300, 150, 64);
		g.setFont(textFont);
		//g.drawString("Vale por una portada D:", 320, 240);
		g.drawString("Usa WASD para moverte", 325, 240);
		g.drawString("y evitar los enemigos", 325, 264);
	}
	
	public void addMenuBackground(int n){
		for(int i=0;i<n;i++){
			handler.addObject(new MenuEnemy(rand.nextInt(Game.WIDTH-20)+2,
					rand.nextInt(Game.WIDTH-20)+2,ID.MenuEnemy));
		}
	}
}
