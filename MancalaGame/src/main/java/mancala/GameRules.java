package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    private static final int FIRSTPLAYER = 1;
    final private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        gameBoard.setUpPits();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    MancalaDataStructure getDataStructure() {
        //gets the data structure of the game
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    boolean isSideEmpty(final int pitNum) {
        // This method can be implemented in the abstract class.
        // Indicates whether one side of the board is empty
        int count = 0;
        if (pitNum >= 1 && pitNum <= 6) {
            int pits;
            for (pits = 1; pits <= 6; pits++) {
                count = count + getDataStructure().getNumStones(pits);
            }
            return count == 0;
        } else {
            int pits;
            for (pits = 7; pits <= 12; pits++) {
                count = count + getDataStructure().getNumStones(pits);
            }
            return count == 0;
        }
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Get the current player.
     * @return the player who is currently playing. 
     */

    public int getPlayer(){
        return currentPlayer;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
        /*
         * make a new store in this method, set the owner
         * then use the setStore(store,playerNum) method of the data structure
         */
        final Store storeFirst = new Store();
        final Store storeSecond = new Store();
        getDataStructure().setStore(storeFirst, 1);
        getDataStructure().setStore(storeSecond, 2);
        storeFirst.setOwner(one);
        storeSecond.setOwner(two);
        one.setStore(storeFirst);
        two.setStore(storeSecond);
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        String result = "Pits:\n";

        for (int i = 12; i >=7; i--) {
            result += Integer.toString(getNumStones(i)) + " ";
        }

        result += "\n";

        for (int i = 1; i <= 6; i++) {
            result += Integer.toString(getNumStones(i)) + " ";
        }

        result += "\n\n";

        Store playerStore = getDataStructure().getStore(1);
        result += playerStore.toString() + "\n";

        playerStore = getDataStructure().getStore(2);
        result += playerStore.toString() + "\n";

        return result;
    }

    /**
     * Switches the turn of the player 
     *
     * 
     */
    public void switchPlayer() {
        if(currentPlayer == FIRSTPLAYER){
            currentPlayer = 2;
        }else{
            currentPlayer= 1;
        }
    }
}
