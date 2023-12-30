package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private String playerName;
    private int gameCountKalah;
    private int gameCountAyo;
    private int winCountKalah;
    private int winCountAyo;

    public UserProfile(){
        gameCountKalah = 0;
        gameCountAyo = 0;
        winCountKalah = 0;
        winCountAyo = 0;
    }

    public UserProfile(final String pName){
        gameCountKalah = 0;
        gameCountAyo = 0;
        winCountKalah = 0;
        winCountAyo = 0;
        playerName = pName;
    }

    public void setName(final String userInput){
        playerName = userInput;
    }

    public String getName(){
        return playerName;
    }

    public void countKalah(final boolean win){
        gameCountKalah = gameCountKalah + 1;
        if(win){
            winCountKalah = winCountKalah + 1; 
        }
    }

    public void countAyo(final boolean win){
        gameCountAyo = gameCountAyo + 1;
        if(win){
            winCountAyo = winCountAyo + 1;
        }
    }

    public int getWinCountAyo(){
        return winCountAyo;
    }

    public int getGameCountAyo(){
        return gameCountAyo;
    }

    public int getGameCountKalah(){
        return gameCountKalah;
    }

    public int getWinCountKalah(){
        return winCountKalah;
    }

}