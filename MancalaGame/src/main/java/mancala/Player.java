package mancala;
import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private Store playerStore;
    private UserProfile userProfile;

    public Player() {
        this.playerName = "";
        this.playerStore = new Store();
    }

    public Player(final UserProfile profile) {
        this.playerName = "";
        this.playerStore = new Store();
        userProfile = profile;
        playerName = profile.getName();
    }

    public UserProfile getProfile(){
        return userProfile;
    }

    public String getName() {
        return playerName;
    }

    public Store getStore() {
        return playerStore;
    }

    public int getStoreCount() {
        return (playerStore != null) ? playerStore.getTotalStones() : 0;
    }

    public void setName(final String name) {
        this.playerName = name;
    }

    public void setStore(final Store store) {
        this.playerStore = store;
    }

    @Override
    public String toString() {
        return "Player{name='"+playerName+"', store="+((playerStore != null) ? playerStore.toString() : "None")+'}';
    }

}