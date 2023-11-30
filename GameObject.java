import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	protected Image img;

	// constructor for the GameObject
	GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 100;
		this.height = 100;
	}

	abstract void update();
//draw an object
	abstract void draw(Graphics g, ImageObserver o);

}
