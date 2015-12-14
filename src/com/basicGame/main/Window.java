package com.basicGame.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	
	private static final long serialVersionUID = 2286213169232193746L;

	public Window(int width, int height, String title, Game game){
		JFrame frame =new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		//termina todo el proceso, o si no el thread queda andando
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//con null crea la ventana al medio de la pantalla
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
	
}
