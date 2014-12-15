package com.example.spherebreak.framework;

import com.example.spherebreak.framework.inGraphics.PixmapFormat;

import android.graphics.Bitmap;

public class Pixmap implements iPixmap
{
	public Bitmap bitmap;
	private PixmapFormat format;
	
	public Pixmap(Bitmap _bmp, PixmapFormat _fmt)
	{
		this.bitmap = _bmp;
		this.format = _fmt;
	}
	
	public int GetWidth()
	{
		return bitmap.getWidth();
	}
	
	public int GetHeight()
	{
		return bitmap.getHeight();
	}
	
	public PixmapFormat GetFormat()
	{
		return format;
	}
	
	public void Dispose()
	{
		bitmap.recycle();
	}
}
