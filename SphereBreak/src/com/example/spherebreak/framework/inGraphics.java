package com.example.spherebreak.framework;

public interface inGraphics 
{
	public static enum PixmapFormat
	{ ARGB8888, ARGB4444, RGB565 }
	
	public Pixmap NewPixmap(String _name, PixmapFormat _fmt);
	public void Clear(int _rgb);
	public void DrawPixel(int _x, int _y, int _rgb);
	public void DrawLine(int _x, int _y, int _x2, int _y2, int _rgb);
	public void DrawRect(int _x, int _y, int _width, int _height, int _rgb);
	public void DrawPixmap(Pixmap _pixmap, int _x, int _y, int _srcX, int _srcY, int _srcW, int _srcH);
	public void DrawPixmap(Pixmap _pixmap, int _x, int _y);
	public int GetHeight();
	public int GetWidth();
}
