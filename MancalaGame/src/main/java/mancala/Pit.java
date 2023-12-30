package mancala;
import java.io.Serializable;

public class Pit implements Countable, Serializable{
    private int stoneCount;

    public Pit() {
        this.stoneCount = 0;
    }

    @Override
    public void addStone() {
        stoneCount++;
    }

    @Override
    public void addStones(final int amount) {
        for(int i = 0; i < amount; i++){
            stoneCount++;
        }
    }

    @Override
    public int getStoneCount() {
        return stoneCount;
    }

    @Override
    public int removeStones() {
        final int removedStones = stoneCount;
        stoneCount = 0;
        return removedStones;
    }

    @Override
    public String toString() {
        return "Pit{stoneCount=" + stoneCount + '}';
    }
}
