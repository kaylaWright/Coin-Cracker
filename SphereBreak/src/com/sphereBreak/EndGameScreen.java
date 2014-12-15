package com.sphereBreak;

import java.util.List;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.example.spherebreak.framework.Screen;
import com.example.spherebreak.framework.iInput.TouchEvent;

public class EndGameScreen extends Screen
{
	private boolean isWin = false;
	
	public EndGameScreen(boolean _isWin, Game _game)
	{
		super(_game);
		
		isWin = _isWin;
		
		if(Settings.isMusicEnabled)
		{
			Assets.backgroundMusic.Play();
		}
	}

	@Override
	public void update(float _dt) 
	{
		Graphics g = game.GetGraphics();
		List<TouchEvent> events = game.GetInput().GetTouchEvents();
		game.GetInput().GetKeyEvents();
		
		int l = events.size();
		for(int i = 0; i < l; i++)
		{
			TouchEvent e = events.get(i);
			
			//unpause
			if(InBounds(e, 0, 70, 194, 70))
			{
				Settings.areSFXEnabled = !Settings.areSFXEnabled;
				if(Settings.areSFXEnabled)
					Assets.menuSFX.Play(1.0f);
				
				game.SetScreen(new GameScreen(game));
			}
			
			
			//to main menu
			if(InBounds(e, 0, 140, 194, 70))
			{
				Settings.areSFXEnabled = !Settings.areSFXEnabled;
				if(Settings.areSFXEnabled)
					Assets.menuSFX.Play(1.0f);
				
				game.SetScreen(new MainMenuScreen(game));
			}
			
			//quit
			if(InBounds(e, 0, 210, 194, 70))
			{
				Settings.areSFXEnabled = !Settings.areSFXEnabled;
				if(Settings.areSFXEnabled)
					Assets.menuSFX.Play(1.0f);
				
				//quit logic. 
			}
			
			if(InBounds(e, 0, 140, 192, 64))
			{
				if(Settings.areSFXEnabled)
					Assets.menuSFX.Play(1.0f);
				
				game.SetScreen(new OptionScreen(game));
			}
		
			//quit
			if(InBounds(e, 0, 210, 192, 64))
			{
				if(Settings.areSFXEnabled)
					Assets.menuSFX.Play(1.0f);
				
				game.SetScreen(new QuitScreen(game));
			}
		}
	}

	@Override
	public void present(float _dt) 
	{
Graphics g = game.GetGraphics();
		
		g.DrawPixmap(Assets.background, 0, 0);
		
		if(isWin)
		{
			g.DrawPixmap(Assets.winMenu, 0, 0);
		}
		else
		{
			g.DrawPixmap(Assets.lossMenu, 0, 0);
		}
		
		//int _x, int _y, int _srcX, int _srcY, int _srcWidth, int _srcHeight)
		if(Settings.areSFXEnabled)
		{
			g.DrawPixmap(Assets.guiElements, 0, 416, 0, 64, 64, 64);
		}
		else
		{
			g.DrawPixmap(Assets.guiElements, 0, 416, 64, 64, 64, 64);
		}
		
		//to the right of the sound toggler.
		if(Settings.isMusicEnabled)
		{
			g.DrawPixmap(Assets.guiElements, 64, 416, 0, 128, 64, 64);
		}
		else
		{
			g.DrawPixmap(Assets.guiElements, 64, 416, 64, 128, 64, 64);
		}
	}

	@Override
	public void pause() 
	{
	}

	@Override
	public void resume() 
	{
	}

	@Override
	public void dispose() 
	{
	}

}
