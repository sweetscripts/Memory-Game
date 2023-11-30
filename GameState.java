import java.util.ArrayList;

public class GameState {

	String currentState;
	private ArrayList<String> states;

	// setting up game states
	public GameState() {
		states = new ArrayList<String>();
		states.add("BEGINPROGRAM");
		states.add("NOGUESSES");
		states.add("ONEFLIPPED");
		states.add("SUCCESS");
		states.add("EXITPROGRAM");

		currentState = states.get(1);
	}

	public String getState() {
		return currentState;
	}

	public void setState(String s) {
		if (states.contains(s)) {
			currentState = s;
		} else {
			System.out.println("The string passed didn't match any states.");
		}
	}
}
