package gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Images {
	private static String[] playerImagesPath = {"/images/empty.png", "/images/circle.png",
			"/images/cross.png", "/images/triangle.png", "/images/square.png"};
	
	private Images() {
		
	}
	
	public static BufferedImage getBG() {
		BufferedImage retVal = null;
		
		try {
			retVal = ImageIO.read(getUrl("/images/bg.png"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return retVal;
	}
	
	public static BufferedImage getLogo() {
		BufferedImage retVal = null;
		
		try {
			retVal = ImageIO.read(getUrl("/images/logo.png"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return retVal;
	}
	
	public static int getMaxNumberOfPlayers() {
		return playerImagesPath.length - 1;
	}
	
	public static ArrayList<BufferedImage> getPlayerImages(int amount) {
		ArrayList<BufferedImage> retVal = new ArrayList<>();
		
		for (int i = 0; i < (amount + 1) ; i++) {
			
			try {
				retVal.add(ImageIO.read(getUrl(playerImagesPath[i])));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return retVal;
	}
	
	private static URL getUrl(String path) {
		return new Images().getClass().getResource(path);
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
}
