package com.BonBon003.calliope.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.BonBon003.calliope.framework.GameObject;
import com.BonBon003.calliope.framework.ID;
import com.tutorial.main.Game;
import com.tutorial.main.Handler;

public class Player extends GameObject{
	
	private float gravity = 1;
	private final float MAX_SPEED = 20;
	private final int height, width;
	private Handler handler;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		height = 64;
		width = 32;
		
	}

	
	public void tick(LinkedList<GameObject> object) {
		velX += accX;
		//velY += accY;
		x += velX;
		y += velY;
		if (falling || jumping) {
			velY+= gravity;
			if (velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		Collision(object);
		
		//x = Game.clamp((int)x, 0, Game.WIDTH-37);
		//y = Game.clamp((int)y, 0, Game.HEIGHT-70);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			
			if (tempObject.getId() ==ID.Block) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
				}
				
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else 
					falling = true;
				
				
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - 35;
				}
				
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 34;
					
				
				}
			}
				
		}
	}

	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 64);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x + (width/2) - (width/4), (int)y + (height/2), width/2, height/2);
	}
	
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x + (width/2) - (width/4), (int)y, (width/2), height/2);
	}
	
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x+width-7, (int)y+5, 7, height-10);
	}
	
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y+5, 7, height-10);
	}

}
