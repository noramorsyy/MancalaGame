package mancala;

import java.io.Serializable;
import java.util.ArrayList;

public class MancalaGame implements Serializable {
    private GameRules rules;
    private Player currentPlayer;
    final private ArrayList<Player> players;

    public MancalaGame() {
        rules = new KalahRules();
        players = new ArrayList<>();
        setBoard(rules);
    }

    public GameRules getBoard() {
        return rules;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumStones(final int pitNum) throws PitNotFoundException {
        return rules.getNumStones(pitNum);
    }

    /**
     * 
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getStoreCount(final Player player) {
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        int playerOneStones = getStoreCount(players.get(0));
        int playerTwoStones = getStoreCount(players.get(1));
        if (isGameOver()) {
            if (rules.isSideEmpty(0)) {
                for (int i = 7; i <= 12; i++) {
                    playerTwoStones += rules.getDataStructure().removeStones(i);
                }
            } else if (rules.isSideEmpty(7)) {
                for (int i = 1; i <= 6; i++) {
                    playerOneStones += rules.getDataStructure().removeStones(i);
                }
            }
            if (playerOneStones > playerTwoStones) {
                return players.get(0);
            } else if (playerTwoStones > playerOneStones) {
                return players.get(1);
            } else {
                return null;
            }
        } else {
            throw new GameNotOverException();
        }
    }

    public boolean isGameOver() {
        return rules.isSideEmpty(0) || rules.isSideEmpty(7);
    }

    public int move(final int startPit) throws InvalidMoveException {
        int total = 0;
        int player;

        if (currentPlayer.equals(players.get(0))) {
            player = 1;
        } else {
            player = 2;
        }

        rules.moveStones(startPit, player);

        if (startPit < 7) {
            for (int i = 1; i<7; i++) {
                total+=rules.getNumStones(i);
            }
        } else {
            for (int i = 7; i<13; i++) {
                total+=rules.getNumStones(i);
            }
        }

        if (rules.getPlayer() == 1) {
            setCurrentPlayer(players.get(0));
        } else {
            setCurrentPlayer(players.get(1));
        }

        return total;
    }

    public void setBoard(final GameRules theBoard) {
        rules = theBoard;
    }

    public void setCurrentPlayer(final Player player) {
        currentPlayer = player;
    }

    public void setPlayers(final Player onePlayer, final Player twoPlayer) {
        players.add(onePlayer);
        players.add(twoPlayer);
        rules.registerPlayers(onePlayer, twoPlayer);
        setCurrentPlayer(onePlayer);
    }

    public void startNewGame() {
        rules.resetBoard();
    }

    @Override
    public String toString() {
        final String stringMancalaGame = "\nCurrent Player: " + currentPlayer.getName() + "\n" + rules.toString();
        return stringMancalaGame;
    }
}