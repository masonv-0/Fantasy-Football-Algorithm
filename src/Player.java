public class Player {

    private String name;
    private String teamName;
    private String currentOpponent;
    private int defenseAgainst;

    Player (String n, String t, String c, int d) {
        name = n;
        teamName = t;
        currentOpponent = c;
        defenseAgainst = d;
    }

    Player (String t, String c) {
        name = null;
        teamName = t;
        currentOpponent = c;
        defenseAgainst = 0;
    }

    public void printAll() {
        System.out.println(name + " " + teamName + " " + currentOpponent + " " + defenseAgainst);
    }

}

