package com.gameObjects;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.gameObjects.Coins;
import com.sphereBreak.Assets;

public class CentreCoin extends Coins
{
	public CentreCoin(int _x, int _y)
	{
		super(_x, _y);
	}
	
	@Override
	public void Render(Game _game)
	{
		Graphics g = _game.GetGraphics();
		////int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight)
		g.DrawPixmap(Assets.centerCoins, xPos, yPos, (coinSize * (coinValue - 1)), 0, coinSize, coinSize);
	}
	
	@Override
	public void ChangeSelected()
	{ return; }
	
}
