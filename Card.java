import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

//// Card class extends GameObject to represent individual cards in the game
public class Card extends GameObject {

	public boolean flipped = false;
	public int pictureNumber;

	public Card(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
	}

	//Method to set the image of the card
	public void setImage(Image i) {
		super.img = i;
	}
	// Method to get the image of the card
	public Image getImage() {
		return super.img;
	}

	//Method to flip the card
	public boolean flip() {
		flipped = !flipped;
		return flipped;
	}
	//// Drawing the card on the canvas
	@Override
	public void draw(Graphics g, ImageObserver o) {
		if (super.img != null) {
			g.drawImage(super.img, x, y, width, height, o);
		} else {
			System.out.println("No image to draw! D:");
		}
	}

}
