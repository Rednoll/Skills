package inwaiders.redn.rpg.gui.helpers;

import inwaiders.redn.rpg.core.Core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TableHarrington {

	static List<CharRer> text = new ArrayList();
	
	public static CharRer getCharRer(int id){
		
		for(int i = 0;i<text.size();i++){
			
			if(text.get(i).id == id)
				return text.get(i);
		}
		
		return null;
	}
	
	public static void renderSuperFont(int x, int z, String text, Gui gui){
		
		char [] textsChar = text.toCharArray();
		Minecraft.getMinecraft().renderEngine.bindTexture(Core.guirlgen.generate("harrington"));
		
		int xT = x;
		int zT = z;
		
		for(int i = 0;i<textsChar.length;i++){
			
			CharRer c = TableHarrington.getCharRer((int)textsChar[i]);
			
			int xh = xT;
			int zh = zT;
			GL11.glColor3f(0, 0, 0);
			gui.drawTexturedModalRect(xh+c.getXofest(), zh+c.getYofest(), c.getX()/2, c.getY()/2, c.getWidth()/2, c.getHeight()/2);
			xT += c.getXadvance();
			
			c = null;
		}
		
	}
	
	public static int getWeiht(String text){
		
		int x = 0;
		
		char [] textsChar = text.toCharArray ();
		
		for(int i = 0;i<textsChar.length;i++){
			
			CharRer c = getCharRer((int)textsChar[i]);
			
			x += c.getXadvance();
		}
		
		return x;
	}
	
	public static void initialize(){
		
		text.add(new CharRer(32, 0, 0, 0, 0, 0, 71, 25));
		text.add(new CharRer(124, 0, 0, 12, 81, 6, 11, 28));
		text.add(new CharRer(102, 12, 0, 29, 81, -4, 15, 28));
		text.add(new CharRer(93, 41, 0, 21, 73, -3, 15, 27));
		text.add(new CharRer(91, 62, 0, 20, 73, 5, 15, 27));
		text.add(new CharRer(125, 82, 0, 30, 72, -2, 15, 30));
		text.add(new CharRer(123, 112, 0, 30, 72, -1, 15, 30));
		text.add(new CharRer(41, 142, 0, 25, 72, -4, 15, 27));
		text.add(new CharRer(40, 167, 0, 24, 72, 2, 15, 27));
		text.add(new CharRer(106, 191, 0, 20, 72, -2, 19, 25));
		text.add(new CharRer(89, 211, 0, 48, 72, -1 , 15, 52));
		text.add(new CharRer(80, 259, 0, 53, 72, -2, 15, 56));
		text.add(new CharRer(65, 312, 0, 58, 71, -2, 16, 62));
		text.add(new CharRer(64, 370, 0, 71, 70, 2, 15, 78));
		text.add(new CharRer(82, 441, 0, 63, 69, -2 , 15, 57));
		text.add(new CharRer(127, 0, 81, 61, 68, 1, 7, 66));
		text.add(new CharRer(92, 61, 81, 49, 68, -8, 12, 28));
		text.add(new CharRer(47, 110, 81, 50, 68, -9, 12, 28));
		text.add(new CharRer(75, 160, 81, 58, 68, 0, 15, 50));
		text.add(new CharRer(66, 218, 81, 54, 68, -2, 15, 58));
		text.add(new CharRer(103, 272, 81, 42, 67, -1, 24, 46));
		text.add(new CharRer(74, 314, 81, 30, 67, -7, 16, 27));
		text.add(new CharRer(36, 344, 81, 42, 66, -3, 16, 43));
		text.add(new CharRer(86, 386, 81, 50, 66, 0, 11, 54));
		text.add(new CharRer(67, 436, 81, 57, 65, 0, 11, 51));
		text.add(new CharRer(87, 0, 149, 65, 64, 1, 15, 71));
		text.add(new CharRer(81, 65, 149, 51, 64, 0, 15, 56));
		text.add(new CharRer(71, 116, 149, 53, 64, 0, 12, 57));
		text.add(new CharRer(104, 169, 149, 41, 63, -1, 14, 47));
		text.add(new CharRer(84, 210, 149, 53, 63, -3, 13, 46));
		text.add(new CharRer(70, 263, 149, 53, 63, 0, 13, 50));
		text.add(new CharRer(57, 316, 149, 43, 62, -2, 20, 46));
		text.add(new CharRer(100, 359, 149, 42, 62, 1, 14, 48));
		text.add(new CharRer(88, 401, 149, 48, 62, -2, 14, 50));
		text.add(new CharRer(38, 449, 149, 53, 61, 0, 15, 54));
		text.add(new CharRer(63, 0, 213, 34, 61, 2, 15, 42));
		text.add(new CharRer(33, 34, 213, 16, 61, 1, 15, 22));
		text.add(new CharRer(108, 50, 213, 19, 61, -1, 14, 27));
		text.add(new CharRer(107, 69, 213, 40, 61, -1, 15, 42));
		text.add(new CharRer(98, 109, 213, 43, 61, 0, 15, 47));
		text.add(new CharRer(90, 152, 213, 49, 61, -1, 15, 51));
		text.add(new CharRer(83, 201, 213, 47, 61, -2, 15, 49));
		text.add(new CharRer(79, 248, 213, 52, 61, 0, 15, 56));
		text.add(new CharRer(69, 300, 213, 55, 61, 0, 15, 57));
		text.add(new CharRer(68, 355, 213, 55, 61, -2, 15, 58));
		text.add(new CharRer(113, 410, 213, 43, 60, 0, 31, 47));
		text.add(new CharRer(112, 453, 213, 44, 60, -1, 31, 48));
		text.add(new CharRer(85, 0, 274, 51, 60, 1, 16, 55));
		text.add(new CharRer(78, 51, 274, 50, 60, 0, 16, 56));
		text.add(new CharRer(77, 101, 274, 61, 60, -1, 16, 64));
		text.add(new CharRer(76, 162, 274, 47, 60, 0, 16, 49));
		text.add(new CharRer(73, 209, 274, 20, 60, 0, 16, 26));
		text.add(new CharRer(72, 229, 274, 58, 60, -3, 16, 60));
		text.add(new CharRer(121, 287, 274, 38, 59, 2, 32, 47));
		text.add(new CharRer(37, 325, 274, 62, 58, 0, 20, 66));
		text.add(new CharRer(53, 387, 274, 40, 58, -3, 18, 42));
		text.add(new CharRer(56, 427, 274, 41, 57, 1, 19, 45));
		text.add(new CharRer(35, 0, 334, 53, 56, -1, 20, 55));
		text.add(new CharRer(48, 53, 334, 42, 56, 1, 20, 47));
		text.add(new CharRer(54, 95, 334, 40, 56, 1, 20, 46));
		text.add(new CharRer(50, 135, 334, 37, 56, -1, 20, 42));
		text.add(new CharRer(116, 172, 334, 22, 56, -1, 20, 28));
		text.add(new CharRer(105, 194, 334, 17, 56, 1, 19, 27));
		text.add(new CharRer(55, 221, 334, 43, 55, -1, 21, 38));
		text.add(new CharRer(52, 254, 334, 45, 55, -1, 21, 48));
		text.add(new CharRer(51, 299, 334, 41, 55, -2, 21, 44));
		text.add(new CharRer(49, 340, 334, 20, 55, 1, 21, 30));
		text.add(new CharRer(120, 360, 334, 38, 54, -3, 28, 40));
		text.add(new CharRer(101, 398, 334, 39, 52, 1, 31, 43));
		text.add(new CharRer(119, 437, 334, 53, 50, -2, 26, 55));
		text.add(new CharRer(59, 490, 334, 19, 49, -1, 34, 23));
		text.add(new CharRer(118, 0, 390, 38, 48, -1, 28, 42));
		text.add(new CharRer(110, 38, 390, 41, 46, -1, 31, 47));
		text.add(new CharRer(109, 79, 390, 56, 46, -1, 31, 62));
		text.add(new CharRer(122, 135, 390, 37, 45, 0, 30, 41));
		text.add(new CharRer(115, 172, 390, 37, 45, 0, 31, 42));
		text.add(new CharRer(111, 209, 390, 45, 45, 0, 31, 49));
		text.add(new CharRer(99, 254, 390, 40, 45, 0, 31, 43));
		text.add(new CharRer(97, 294, 390, 40, 45, 1, 31, 46));
		text.add(new CharRer(117, 334, 390, 39, 44, 2, 32, 48));
		text.add(new CharRer(114, 373, 390, 35, 44, -2, 31, 36));
		text.add(new CharRer(58, 408, 390, 15, 42, 1, 34, 23));
		text.add(new CharRer(62, 423, 390, 25, 32, 0, 34, 29));
		text.add(new CharRer(60, 448, 390, 26, 32, -1, 34, 29));
		text.add(new CharRer(42, 474, 390, 30, 31, 0, 16, 36));
		text.add(new CharRer(43, 0, 438, 31, 31, 0, 33, 35));
		text.add(new CharRer(94, 31, 438, 42, 27, -1, 29, 43));
		text.add(new CharRer(39, 73, 438, 13, 26, 0, 15, 18));
		text.add(new CharRer(34, 86, 438, 25, 26, 0, 15, 30));
		text.add(new CharRer(126, 111, 438, 42, 22, -2, 31, 43));
		text.add(new CharRer(44, 153, 438, 19, 22, -1, 61, 23));
		text.add(new CharRer(96, 172, 438, 23, 20, 9, 1, 43));
		text.add(new CharRer(61, 195, 438, 31, 18, 0, 39, 35));
		text.add(new CharRer(45, 226, 438, 28, 16, 0, 44, 33));
		text.add(new CharRer(46, 254, 438, 15, 15, 1, 61, 23));
		text.add(new CharRer(95, 269, 438, 45, 11, -3, 75, 43));
		
	}
}
