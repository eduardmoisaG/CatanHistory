package eduard.catan;

public class CatanGame {

    private int playersNumber;
    private int gameNumber;

    public CatanGame(int playersNumber, int gameNumber) {
        this.playersNumber = playersNumber;
        this.gameNumber = gameNumber;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }
    public int getGameNumber() {
        return gameNumber;
    }
}
