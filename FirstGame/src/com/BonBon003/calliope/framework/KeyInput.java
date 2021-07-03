package com.BonBon003.calliope.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import com.tutorial.main.Handler;


public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean right = false;
	private boolean left = false;
	private boolean sprint = false;
	private boolean dash = false;
	Timer timer = new Timer();
	private int index;
	
	
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player)
				index = i;
				
		}
			
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		//sprint mechanic
		if (key == KeyEvent.VK_SHIFT) {
			sprint = true;
			if (right)
				handler.object.get(index).setVelX(7);
			if (left)
				handler.object.get(index).setVelX(-7);				
		}
		
		if (key == KeyEvent.VK_E) {
			dash = true;
			if (right)
				handler.object.get(index).setVelX(10);
			if (left)
				handler.object.get(index).setVelX(-10);
			timer.schedule(new DashTask(), 1*200);
			dash = false;
		}
		
		if (key == KeyEvent.VK_SPACE && !handler.object.get(index).isJumping()) {
			//handler.object.get(index).setAccY(1);
			handler.object.get(index).setVelY(-20);
			handler.object.get(index).setJumping(true);
		}
		
		if (key == KeyEvent.VK_A) {
			if (sprint == true)
				handler.object.get(index).setVelX(-7);
			else 
				handler.object.get(index).setVelX(-5);
			left = true;
		}
		if (key == KeyEvent.VK_D) {
			if (sprint == true)
				handler.object.get(index).setVelX(7);
			else
				handler.object.get(index).setVelX(5);
			right = true;		
		}
		
		if (key == KeyEvent.VK_ESCAPE)	{
			System.exit(0);
		}
			
	}
		
		//System.out.println(key);
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
	
				
			/*	if (key == KeyEvent.VK_SPACE) {
					handler.object.get(index).setAccY(1);
					handler.object.get(index).setVelY(-20);
				}
				*/
				if (key == KeyEvent.VK_A) {
					left = false;
					if (right) {
						if (sprint)
							handler.object.get(index).setVelX(7);
						else
							handler.object.get(index).setVelX(5);	
					}
					else {
						handler.object.get(index).setVelX(0);
					}	
				}
				if (key == KeyEvent.VK_D) {
					right = false;
					if (left) {
						if(sprint)
							handler.object.get(index).setVelX(-7);
						else
							handler.object.get(index).setVelX(-5);
					}
					else {
						handler.object.get(index).setVelX(0);
					}
				}
				if (key == KeyEvent.VK_SHIFT) {
					sprint = false;
				}

	}
	
	class DashTask extends TimerTask{
		public void run() {
			if (right == true)
				handler.object.get(index).setVelX(5);
			if (left)
				handler.object.get(index).setVelX(-5);
			
		}
	}
	
	
	
}
