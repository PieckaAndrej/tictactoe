package gui;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageFactory {
	private int width;
	private int height;
	
	private ArrayList<BufferedImage> images;
	private ArrayList<BufferedImage> originalImages;
	
	public ImageFactory(int width, int height, int noOfImages) {
		images = new ArrayList<>();
		
		this.height = height;
		this.width = width;
		
		originalImages = Images.getPlayerImages(noOfImages);
		
		originalImages.forEach(image -> {
			addImage(image);
		});;
	}

	
	public void addImage(BufferedImage image) {
		if (image.getWidth() != width || image.getHeight() != height) {
			image = Images.resize(image, width, height);
		}
		
		images.add(image);
	}
	
	public BufferedImage getImage(int index) {
		return images.get(index);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setDimensions(int width, int height) {
		if (width != this.width && height != this.height) {
			for (int i = 0; i < images.size(); i++) {
				images.set(i, Images.resize(originalImages.get(i), width, height));
			}
		}
	}
}
