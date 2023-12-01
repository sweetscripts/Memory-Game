import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Cache {
	
	//Directory where images are stored
	private static final String IMAGE_DIR = "src/images/";
	//List to store loaded images
	private static ArrayList<Image> image = new ArrayList<Image>();

	// constructor for Cache class
	public Cache() {
	}
	//Private method to load an image from a file
	private Image loadImage(String img) {
		try {
			System.out.println("Image loaded fine.");
			return ImageIO.read(new File(IMAGE_DIR + img));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't load image");
			return null;
		}
	}
	
	//Method to return the number of images loaded
	public int length() {
		return image.size();
	}
	//Method to add an image to the cache
	public void insertImage(String filename) {
		image.add(loadImage(filename));
	}

	// // Method to shuffle the images for randomizing card placement
	public int[] shuffle() {
		int[] shuffleArray = new int[image.size() * 2];
		int temp, chosen;
		// Initializing the shuffle array with image indices
		for (int x = 1; x < shuffleArray.length; x++) {
			shuffleArray[x - 1] = x % image.size();
		}
		for (int w = 0; w < shuffleArray.length; w++) {
		}
		//Shuffling the array
		for (int y = shuffleArray.length - 1; y > -1; y--) {
			Random rand = new Random();
			chosen = rand.nextInt(y + 1);
			temp = shuffleArray[chosen];
			shuffleArray[chosen] = shuffleArray[y];
			shuffleArray[y] = temp;
		}
		for (int z = 0; z < shuffleArray.length; z++) {
		}
		return shuffleArray;
	}
	// Method to get an image from the cache by index
	public Image getImage(int index) {
		if (image.size() > index) {
			return image.get(index);
		} else {
			return image.get(index % image.size());
		}
	}
}
