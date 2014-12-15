package com.sphereBreak;

import java.util.List;

import com.example.spherebreak.framework.Game;
import com.example.spherebreak.framework.Graphics;
import com.example.spherebreak.framework.iInput.TouchEvent;
import com.example.spherebreak.framework.Screen;

import com.gameObjects.*;

public class MainMenuScreen extends Screen
{
	public MainMenuScreen(Game _game)
	{
		super(_game);
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
			if(e.type == TouchEvent.TOUCH_UP)
			{
				
				
				//toggle sfx.
				if(InBounds(e, 0, g.GetHeight() - 64, 64, 64))
				{
					Settings.areSFXEnabled = !Settings.areSFXEnabled;
					if(Settings.areSFXEnabled)
						Assets.menuSFX.Play(1.0f);
				}
				
				//toggle music.
				if(InBounds(e, 64, g.GetHeight() - 64, 64, 64))
				{
					Settings.isMusicEnabled = !Settings.isMusicEnabled;
					if(Settings.isMusicEnabled)
						Assets.backgroundMusic.Play();
					else
						Assets.backgroundMusic.Pause();
				}
				
				//move to game.
				if(InBounds(e, 0, 0, 192, 64))
				{
					if(Settings.areSFXEnabled)
						Assets.menuSFX.Play(1.0f);
					
					game.SetScreen(new DifficultySelectionScreen(game));
				}
			
				//move to instructions.
				if(InBounds(e, 0, 70, 192, 64))
				{
					if(Settings.areSFXEnabled)
						Assets.menuSFX.Play(1.0f);
					
					game.SetScreen(new InstructionScreen(game));
				}
			
				//move to high score
				//not in menu gui yet!
				/*if(InBounds(e, 0, 140, 64, 64))
				{
				}*/
			
				//move to options.
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
	}

	@Override
	public void present(float _dt)
	{
		Graphics g = game.GetGraphics();
		
		g.DrawPixmap(Assets.background, 0, 0);
		g.DrawPixmap(Assets.mainMenu, 0, 0);
		
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
		Settings.Save(game.GetFileIO());
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
