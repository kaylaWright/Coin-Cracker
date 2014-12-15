package com.sphereBreak;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.widget.TextView;

import com.example.spherebreak.framework.Screen;
import com.example.spherebreak.framework.Game;

public class SphereBreakGame extends Game
{
	public static boolean isHard;
	
	@Override
	public Screen GetStartScreen() 
	{
		return new LoadingScreen(this);
	}
}
