package mancala;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private Player playerOne;
    private Player playerTwo;

    public Board() {
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        playerOne = null;
        playerTwo = null;
        setUpPits();
        setUpStores();

        initializeBoard();
    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException {
        // Capture stones from the opponent's pits
        // and return the number of captured stones
        if (stoppingPoint < 0 || stoppingPoint > 11) {
            throw new PitNotFoundException();
        }
        int pitAcross = 11 - stoppingPoint;
        int totalCaptured = pits.get(pitAcross).removeStones();
        totalCaptured += pits.get(stoppingPoint).removeStones();

        if(stoppingPoint>=0&&stoppingPoint<=5){
            stores.get(0).addStones(totalCaptured);
        }else{
            stores.get(1).addStones(totalCaptured);
        }
        return totalCaptured;
    }

    public int distributeStones(int startingPoint) throws PitNotFoundException {
        // Helper method that distributes stones into pits and stores,
        // skipping the opponent's store, and returns the ending point
        int player = 0;
        int total;
        int capture = 1;
        if (startingPoint < 0 || startingPoint > 11) {
            throw new PitNotFoundException();
        }
        int stonesToMove = pits.get(startingPoint).removeStones();
        total = stonesToMove;
        int pitNum = startingPoint;
        if(startingPoint>=0&&startingPoint<=5){
            player = 1;
        }else{
            player = 2;
        }
        while (stonesToMove!=0) {
            if(pitNum == 12){
                if(player == 2){
                    stores.get(1).addStones(1);
                    stonesToMove--;
                }
                pitNum = 0;
            } else if(pitNum==5&&stonesToMove>1){
                pits.get(pitNum).addStone();
                stonesToMove--;
                if(player == 1){
                    stores.get(0).addStones(1);
                    stonesToMove--;
                }
                pitNum++;
            }
            if(stonesToMove > 0){
                capture = pits.get(pitNum).getStoneCount();
                pits.get(pitNum).addStone();
                stonesToMove--;
                pitNum++;
            }
        }
        if(capture==0&&((player == 1 && (pitNum>=0&&pitNum<=5))||(player == 2 && (pitNum>=6&&pitNum<=11)))){
            total += captureStones(pitNum);
        }
        return total;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        // Get the number of stones in a specific pit
        if (pitNum < 0 || pitNum > 11) {
            throw new PitNotFoundException();
        }
        int numStones = pits.get(pitNum).getStoneCount();
        return numStones;
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void initializeBoard() {
        // Initializes the board by distributing stones
        for (Pit i : pits) {
            for (int j = 0; j < 4; j++) {
                i.addStone();
            }
        }
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        // Indicates whether one side of the board is empty
        if (pitNum < 0 || pitNum > 11) {
            throw new PitNotFoundException();
        }
        int count = 0;
        if(pitNum >=0 && pitNum <=5){
            for(int i = 0; i<=5; i++){
                count += pits.get(i).getStoneCount();
            }
            return count == 0;
        }else{
            for(int i = 6; i<=11; i++){
                count += pits.get(i).getStoneCount();
            }
            return count == 0;
        }
    }

    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        // Moves stones for the player starting from a specific pit
        if(startPit<0||startPit>11){
            throw new InvalidMoveException();
        }
        int numStones = pits.get(startPit).getStoneCount();
        if(numStones==0){
            throw new InvalidMoveException();
        }
        if(player.equals(playerOne)){
            if(startPit<0||startPit>5){
                throw new InvalidMoveException();
            }
        }else{
            if(startPit>11||startPit<6){
                throw new InvalidMoveException();
            }
        }
        int numBefore = player.getStoreCount();
        try {
            distributeStones(startPit);
        } catch (PitNotFoundException e) {
            throw new InvalidMoveException();
        }
        int numAfter = player.getStoreCount();
        int totalStones = numAfter - numBefore;
        return totalStones;
    }

    public void registerPlayers(Player one, Player two) {
        playerOne = one;
        playerTwo = two;
        playerOne.setStore(stores.get(0));
        playerTwo.setStore(stores.get(1));
        stores.get(0).setOwner(one);
        stores.get(1).setOwner(two);
    }

    public void resetBoard() {
        // Resets the board by redistributing stones but retains the players
        for (Pit i : pits) {
            i.removeStones();
        }
        initializeBoard();
        for(Store i : stores){
            i.emptyStore();
        }
    }

    public void setUpPits() {
        // Establishes 12 empty Pits in the board
        for(int i = 0; i < 12; i++){
            pits.add(new Pit());
        }
    }

    public void setUpStores() {
        for(int i = 0; i<2;i++){
            stores.add(new Store());
        }
    }

    @Override
    public String toString() {
        // Return a String representation of the board
        String stringMancalaBoard = "Pits: \n";

        for (int i = 12; i >= 7; i--) {
            stringMancalaBoard += String.format("%d ", pits.get(i - 1).getStoneCount());
        }
        stringMancalaBoard += "\n";

        for (int i = 1; i <= 6; i++) {
            stringMancalaBoard += String.format("%d ", pits.get(i - 1).getStoneCount());
        }
        stringMancalaBoard += "\n";
        stringMancalaBoard += playerOne.getStore().toString() + "\n";
        stringMancalaBoard += playerTwo.getStore().toString() + "\n";
        return stringMancalaBoard;
    }
}
