package mancala;

public class PitNotFoundException extends Exception {
    //This class is taken from ChatGPT
    public PitNotFoundException() {
        super("Pit not found.");
    }

    public PitNotFoundException(final String message) {
        super(message);
    }
}


