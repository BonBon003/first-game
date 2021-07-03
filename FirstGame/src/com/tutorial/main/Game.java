package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.BonBon003.calliope.framework.ID;
import com.BonBon003.calliope.framework.KeyInput;
import com.BonBon003.calliope.objects.BasicEnemy;
import com.BonBon003.calliope.objects.Player;

public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
	
	private Thread thread;
	private boolean running = false;
	private BufferedImage level = null;
	//private HUD hud;
	
	
	/*public Game() {
		
		
		
		 
	}*/
	
	Camera cam;
	Handler handler;
	 
	private void init() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		handler = new Handler();
		cam = new Camera(0, 0);		
		File f = new File("levels.png");
		try {
		    System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		/////////////////////////////////////// BIG PROBLEM
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		level = loader.loadImage("/levels.png"); // loading the level
		
		// Not finding a file or something because I get a null pointer exception. Maybe it can't find the file in my res for some reason. 
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2 - 32 , ID.Player, handler));
		handler.addObject(new BasicEnemy(WIDTH/2, HEIGHT/2, ID.BasicEnemy));
		handler.createLevel();
		this.addKeyListener(new KeyInput(handler));
		
	}
	

	public synchronized void start() {
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta>=1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player)
				cam.tick(handler.object.get(i));
		}
		
		//hud.tick);
	}
	
	private void render() { // creates buffers to lower FPS
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics g2d = (Graphics2D) g;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g2d.translate((int)cam.getX(), (int)cam.getY());
		
			handler.render(g);
		
		g2d.translate(-(int)-cam.getX(), (int)-cam.getY());
		
		//hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var<= min)
			return var=min;
		else
			return var;
			
	}
	
	

	public static void main(String[] args) {

		new Window(WIDTH, HEIGHT, "Let's Build a Game!", new Game());
		
	}

	
	
}
