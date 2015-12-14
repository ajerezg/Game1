package com.basicGame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1289820157265803938L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9 ;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	
	public Game(){
		handler = new Handler();
		handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2 -32,ID.Player, handler));
		
		handler.addObject(new BasicEnemy(WIDTH/4 *1, HEIGHT/4 *1,ID.BasicEnemy));
		handler.addObject(new BasicEnemy(WIDTH/4 *1, HEIGHT/4 *3,ID.BasicEnemy));
		handler.addObject(new BasicEnemy(WIDTH/4 *3, HEIGHT/4 *3,ID.BasicEnemy));
		handler.addObject(new BasicEnemy(WIDTH/4 *3, HEIGHT/4 *1,ID.BasicEnemy));
		
		hud = new HUD();
		this.addKeyListener(new KeyInput(handler));
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
		int frames = 0;
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
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
		hud.tick();
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
		
		handler.render(g);
		
		hud.render(g);
		
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

	
	
}
