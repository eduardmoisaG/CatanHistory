package eduard.catan;

public class Result {
    private int gameId;
    private String name;
    private int points;

    public Result(int gameId, String name, int points) {
        this.gameId = gameId;
        this.name = name;
        this.points = points;
    }

    public int getGameId() {
        return this.gameId;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }
}
