import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Card extends GameObject {

	public boolean flipped = false;
	public int pictureNumber;

	public Card(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
	}

	// setting image
	public void setImage(Image i) {
		super.img = i;
	}

	public Image getImage() {
		return super.img;
	}

	// setting fliped card
	public boolean flip() {
		flipped = !flipped;
		return flipped;
	}

	@Override
	public void draw(Graphics g, ImageObserver o) {
		if (super.img != null) {
			g.drawImage(super.img, x, y, width, height, o);
		} else {
			System.out.println("No image to draw! D:");
		}
	}

}
