package mancala;

public class KalahRules extends GameRules{

    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        isValidMove(startPit, playerNum);
        final int numCountBefore = getDataStructure().getStoreCount(playerNum);
        distributeStones(startPit);
        final int numCountAfter = getDataStructure().getStoreCount(playerNum);
        final int total = numCountAfter - numCountBefore;

        if(getDataStructure().getIteratorPos() != getDataStructure().getStorePos(playerNum)){
            switchPlayer();
        } 
        return total;
    }

    /**
     * 
     * checks if the move is valid
     * @param startPit the pit where it starts at 
     * @param playerNum the current player
     * @throws InvalidMoveException
     */

    private void isValidMove(final int startPit, final int playerNum) throws InvalidMoveException {
        if(startPit<0||startPit>13){
            throw new InvalidMoveException();
        }
        if (getNumStones(startPit) == 0 ||playerNum == 1 && startPit > 6 || playerNum == 2 && startPit < 7) {
            throw new InvalidMoveException();
        }
    }

    @Override
    public int captureStones(final int stoppingPoint){
        final int pitOtherSide = 13 - stoppingPoint;
        int totalCaptured;
        totalCaptured = getDataStructure().removeStones(pitOtherSide);
        if(totalCaptured==0){
            return 0;
        } else{
            final int removedStones = getDataStructure().removeStones(stoppingPoint);
            totalCaptured = totalCaptured + removedStones;

            if(stoppingPoint>0&&stoppingPoint<7){
                getDataStructure().addToStore(1, totalCaptured);
            }else{
                getDataStructure().addToStore(2, totalCaptured);
            }
            return totalCaptured;
        }
    }

    @Override
    public int distributeStones(final int startPit){
        int whichPlayer; 
        int total;
        int stonesToMove;
        stonesToMove = getDataStructure().removeStones(startPit);
        total = 0;
        whichPlayer = getPlayer();
        int stonesTaken = 1;
        
        getDataStructure().setIterator( startPit, whichPlayer, false);
        Countable currPlace;
        int position;
        int pits;
        for (pits = 0; pits < stonesToMove ; pits++){
            currPlace = getDataStructure().next();
            stonesTaken = currPlace.getStoneCount();
            currPlace.addStone();
            total = total + 1;
        }
        position = getDataStructure().getIteratorPos();
        position = position + 1;
        

        if (stonesTaken == 0 && (whichPlayer == 1 && position >= 1 && position < 7 || whichPlayer == 2 && position >= 7 && position < 13)){
            total = total + captureStones(position);
        }

        return total;
    }

}
