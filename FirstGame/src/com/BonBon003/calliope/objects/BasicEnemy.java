package com.BonBon003.calliope.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.BonBon003.calliope.framework.GameObject;
import com.BonBon003.calliope.framework.ID;
import com.tutorial.main.Game;

public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
		
	}


	public void tick(LinkedList<GameObject> object) {
		velX += accX;
		velY += accY;
		x += velX;
		y += velY;		
		
		if (y<=0 || y>= Game.HEIGHT-32)
			velY*=-1;
		if (x>=0 || x>= Game.WIDTH-32)
			velX*=-1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
