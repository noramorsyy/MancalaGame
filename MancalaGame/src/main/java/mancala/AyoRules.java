package mancala;

public class AyoRules extends GameRules {
    private static final int FIRSTPLAYER = 1;
    private static final int SECONDPLAYER = 2;
    private static final int ENDLIMIT_ONE = 6;
    private static final int STARTLIMIT_TWO = 7;
    private static final int ENDLIMIT_TWO = 13;


    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        isMoveValid(startPit, playerNum);

        int countBeforeMove;
        countBeforeMove = getDataStructure().getStoreCount(playerNum);
        distributeStones(startPit);
        int countAfterMove;
        countAfterMove = getDataStructure().getStoreCount(playerNum);
        int stonesMoved;
        stonesMoved = countAfterMove - countBeforeMove;

        switchPlayer();
        return stonesMoved;
    }

    /**
     * Checks if the move is valid
     * @param startPit the pit number that it starts at
     * @param playerNum the current player
     * @throws InvalidMoveException
     */
    private void isMoveValid(final int startPit, final int playerNum) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12 || getNumStones(startPit) == 0 || playerNum == FIRSTPLAYER && startPit > ENDLIMIT_ONE || playerNum == SECONDPLAYER && startPit < STARTLIMIT_TWO) {
            throw new InvalidMoveException();
        }
    }


    @Override
    public int captureStones(final int stoppingPoint) {
        final int pitOtherSide = 13 - stoppingPoint;
        final int totalCaptured = getDataStructure().removeStones(pitOtherSide);
        if (totalCaptured == 0) {
            return 0;
        } else{
            if (stoppingPoint >= 0 && stoppingPoint <= 5) {
                getDataStructure().addToStore(1, totalCaptured);
            } else {
                getDataStructure().addToStore(2, totalCaptured);
            }
            return totalCaptured;
        }
    }

    @Override
    public int distributeStones(final int startPit) {
        int whichPlayer;
        int total;
        int position;
        int stonesToMove;
        stonesToMove = getDataStructure().removeStones(startPit);
        whichPlayer = 0;
        getDataStructure().setIterator(startPit, whichPlayer, true);
        whichPlayer = getPlayer();
        Countable currPlace;
        total = 0;

        while (stonesToMove > 0) {
            position = getDataStructure().getIteratorPos();
            currPlace = getDataStructure().next();
            if (whichPlayer == 1 && position == 6 || whichPlayer == 2 && position == 13 || position != 6 && position != 13) {
                currPlace.addStone();
                total = total + 1;
                stonesToMove = stonesToMove - 1;
            }

            if (currPlace.getStoneCount() > 1 && stonesToMove == 0) {
                stonesToMove = currPlace.removeStones();
                total= total - 1;
            }
        }

        int endPlace;
        endPlace = getDataStructure().getIteratorPos();
        endPlace = endPlace + 1;
        if((whichPlayer == FIRSTPLAYER && endPlace < ENDLIMIT_ONE || whichPlayer == SECONDPLAYER && endPlace >= STARTLIMIT_TWO && endPlace < ENDLIMIT_TWO) && getDataStructure().getNumStones(endPlace) == 1) {
                total = total + captureStones(endPlace);
        }
        return total;
    }
}

