package com.example.spherebreak.framework;

import com.example.spherebreak.framework.inGraphics.PixmapFormat;

public interface iPixmap 
{
	public int GetWidth();
	public int GetHeight();
	public PixmapFormat GetFormat();
	public void Dispose();
}
