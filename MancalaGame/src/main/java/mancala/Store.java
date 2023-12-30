package mancala;
import java.io.Serializable;

public class Store implements Countable, Serializable{
    private Player owner;
    private int totalStones;

    public Store() {
        owner = null;
        totalStones = 0;
    }

    @Override
    public void addStones(final int amount) {
        totalStones += amount;
    }

    public int emptyStore() {
        final int stonesInStore = totalStones;
        totalStones = 0;
        return stonesInStore;
    }

    public Player getOwner() {
        return owner;
    }

    public int getTotalStones() {
        return totalStones;
    }

    public void setOwner(final Player player) {
        owner = player;
    }

    @Override
    public String toString() {
        return owner.getName() + "'s Store:" + Integer.toString(totalStones);
    }

    @Override
    public int getStoneCount(){
        return totalStones;

    }

    @Override
    public void addStone(){
        totalStones +=1;
    }

    @Override
    public int removeStones(){
        final int removedStones = totalStones;
        totalStones = 0;
        return removedStones;
    }
}
