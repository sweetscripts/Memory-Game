import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

//// The Window class, responsible for creating the game window and handling game rendering and interaction.
public class Window extends Canvas implements Runnable, MouseListener, ImageObserver {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Thread thread;
	private BufferStrategy bs = null;
	private Graphics graphics = null;
	private GameState state;
	private Image dflt;
	private Card tryCard;

	// user object
	ArrayList<Card> cards = new ArrayList<Card>();
	int cols = 5;
	int rows = 4;
	// Constructor: sets up the game window and initializes game components
	public Window() {
		// setting up the program
		JFrame frame = new JFrame("Memory Game");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		state = new GameState();
		Cache c = new Cache();
		
		try {
			dflt = ImageIO.read(new File("src/images/leaf.jpg"));
		} catch (Exception e) {
			System.out.println("Couldn't load default image! " + e);
		}

		// set up images
		c.insertImage("download.jpg");
		c.insertImage("download (1).jpg");
		c.insertImage("download (2).jpg");
		c.insertImage("download (3).jpg");
		c.insertImage("download (4).jpg");
		c.insertImage("download (5).jpg");
		c.insertImage("download (6).jpg");
		c.insertImage("download (7).jpg");
		c.insertImage("download (8).jpg");
		c.insertImage("download (9).jpg");
		c.insertImage("download (10).jpg");
		c.insertImage("download (11).jpg");
		c.insertImage("download (12).jpg");
		c.insertImage("download (13).jpg");
		c.insertImage("download (14).jpg");
		c.insertImage("download (15).jpg");
		c.insertImage("download (16).jpg");
		c.insertImage("download (17).jpg");
		int[] x = c.shuffle();
		int columns = 6;
		int rows = 6;
		int counter = 0;
		Card card;
		for (int g = 0; g < rows; g++) {
			for (int i = 0; i < columns; i++) {
				card = new Card(i * 100, 95 * g);
				card.pictureNumber = x[counter];
				card.setImage(c.getImage(x[counter]));
				cards.add(card);
				counter++;
			}
		}

		// add canvas things
		frame.add(this);// adding Test/Canvas to the frame

		frame.setVisible(true);

		addMouseListener(this);// adding to Test/Canvas

		thread = new Thread(this);
		run();
	}

	public void render() {
		graphics.clearRect(0, 0, WIDTH, HEIGHT);
	}
	// The game loop: runs continuously to update and render the game
	@Override
	public void run() {
		if (bs == null) {
			createBufferStrategy(2);
			bs = getBufferStrategy();
			graphics = bs.getDrawGraphics();
			thread.start();
		}
		// game loop
		while (state.getState() != "EXITING") {
			render();
			Card current;
			for (int i = 0; i < cards.size(); i++) {
				current = cards.get(i);
				if (current.flipped) {
					current.draw(graphics, this);
				} else {
					graphics.drawImage(dflt, current.x, current.y, current.width, current.height, this);
				}
			}

			bs.show();// showing another buffer
			Thread.currentThread();
			try {
				Thread.sleep(10);

			} catch (InterruptedException e) {

			}
		}
	}
	//Method to handle actions based on mouse interaction and game state
	private void handleAction(Card c) {
		String s = state.getState();

		if (s == "BEGINPROGRAM") {
			// Startup image?
			state.setState("NOGUESSES");
		} else if (s == "NOGUESSES") {
			// find card and flip it
			// switch state to 'oneflipped'
			if (c.flipped) {
				/* do nothing */ } else {
				System.out.println("Flipped is " + c.flip());
				tryCard = c;
				state.setState("ONEFLIPPED");
			}
		} else if (s == "ONEFLIPPED") {
			// find card clicked, and compare to first card flipped
			// if the card is flipped, don't change the state
			// if not the same, compare the cards' pictureNumbers
			// if they are the same, keep them flipped, change state to
			// 'midgame'
			// if not the same, change state to 'clearboard' and flip them both
			//
			if (c.flipped) {
				// do nothing
			} else {
				c.flip();
				if (tryCard.pictureNumber == c.pictureNumber) {
					System.out.println("Success!");
					tryCard = null;
					checkIfGameWon();
					if (state.getState() == "SUCCESS") {

					} else {
						state.setState("NOGUESSES");
					}
				} else {
					System.out.println("Wrong guess!");
					try {
						//timer cards wait
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					c.flip();
					tryCard.flip();
					tryCard = null;
					state.setState("NOGUESSES");
				}
			}
			//These for the future modification
		} else if (s == "MIDGAME") {
		} else if (s == "SUCCESS") {

		} else if (s == "EXITING") {

		}
	}

	// final of the game.
	private void checkIfGameWon() {
		boolean won = true;
		for (int iter = 0; iter < cards.size(); iter++) {
			if (!cards.get(iter).flipped) {
				won = false;
				break;
			}
		}
		if (won) {
			state.setState("SUCCESS");
			System.out.println("You won the game!");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < cards.size(); i++) {
			if (e.getX() >= cards.get(i).x && e.getX() <= cards.get(i).x + cards.get(i).width
					&& e.getY() >= cards.get(i).y && e.getY() <= cards.get(i).y + cards.get(i).height) {
				handleAction(cards.get(i));
			}
		}
	}

	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
