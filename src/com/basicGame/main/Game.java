package com.basicGame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1289820157265803938L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9 ;
	
	public enum STATE{
		Menu,
		Game,
		Dead
	};
	
	public STATE gameState = STATE.Menu;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	private Menu menu;
	private DeadMenu deadMenu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD(this);
		menu = new Menu(this, handler, hud);
		deadMenu = new DeadMenu(this, handler);
		spawner = new Spawn(handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		
		
		new Window(WIDTH, HEIGHT, "Pruebamela :$", this);		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >=1){
				tick();
				delta--;
			}
			if(running)
				render();
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
			}
		}
		stop();
	}
	
	private void tick(){
		if(gameState == STATE.Game){
			handler.tick();
			
			spawner.tick();
			hud.tick();
		}
		else if(gameState == STATE.Menu){
			handler.tick();
			menu.tick();
		}
		else if(gameState == STATE.Dead){
			deadMenu.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//System.out.println(gameState);
		if(gameState == STATE.Game ){
			handler.render(g);
			hud.render(g);
		}
		else if(gameState == STATE.Menu){
			//System.out.println("Rendering Menu");
			handler.render(g);
			menu.render(g);
		}
		else if(gameState == STATE.Dead){
			//System.out.println("Rendering Dead Menu");
			handler.render(g);
			hud.render(g);
			deadMenu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]){
		
		new Game();
	}
	
	public static int clamp(int var, int max, int min){
		if(var>=max)
			return max;
		else if(var<=min)
			return min;
		else
			return var;
	}
	
	public DeadMenu getDeadMenu(){
		return this.deadMenu;
	}

	public Menu getMenu(){
		return this.menu;
	}
	
	public Handler getHandler(){
		return this.handler;
	}

	
}
