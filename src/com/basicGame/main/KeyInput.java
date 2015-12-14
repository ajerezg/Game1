package com.basicGame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyPressed;
	
	public KeyInput(Handler handler){
		this.handler = handler;
		this.keyPressed = new boolean[4];
		this.keyPressed[0]=false;
		this.keyPressed[1]=false;
		this.keyPressed[2]=false;
		this.keyPressed[3]=false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		//System.out.println(key);
		
		for(int i=0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
					tempObject.setVelY(-5);
					keyPressed[0]=true;
				}
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
					tempObject.setVelY(5);
					keyPressed[1]=true;
				}
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
					tempObject.setVelX(-5);
					keyPressed[2]=true;
					}
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ 
					tempObject.setVelX(5);
					keyPressed[3]=true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i=0; i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
					keyPressed[0]=false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
					keyPressed[1]=false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
					keyPressed[2]=false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) 
					keyPressed[3]=false;//tempObject.setVelX(0);
				
				if(!keyPressed[0] && !keyPressed[1])
					tempObject.setVelY(0);
				if(!keyPressed[2] && !keyPressed[3])
					tempObject.setVelX(0);
			}
			
		}
		
		
	}
	

}
