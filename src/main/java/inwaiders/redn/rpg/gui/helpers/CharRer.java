package inwaiders.redn.rpg.gui.helpers;

public class CharRer {

	int id = 0;
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	int Xofest = 0;
	int Yofest = 0;
	int xadvance = 0;
	
	public CharRer(int id, int x, int y, int width, int height, int Xofest, int Yofest, int xadvance){
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.Xofest = Xofest;
		this.Yofest = Yofest;
		this.xadvance = xadvance;
	}
	
	public int getID(){
		return id;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getXofest(){
		return Xofest;
	}
	
	public int getYofest(){
		return Yofest;
	}
	
	public int getXadvance(){
		return xadvance;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
