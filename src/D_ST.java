public class D_ST{

    private String teamName;
    private int currentWeek;
    private String currentOpponent;
    private double[] weeklyStats;
    private int opposingOffenseRank;

    D_ST (String t, int cw, String c, double[] w, int o) {
        teamName = t;
        currentWeek = cw;
        currentOpponent = c;
        weeklyStats = w;
        opposingOffenseRank = o;
    }

    public void calculatePoints() {
        //Finds average points over the last 16 weeks
        double totalPoints = 0;
        for (int i = 0;i<16;i++) {
            totalPoints+=weeklyStats[i];
        }

        double expectedPoints = 5.0;
        //Factors in opposing offense rank
        expectedPoints-=getRanges(opposingOffenseRank);

        System.out.println();
        System.out.println("The " + teamName + " D/ST is projected " + expectedPoints + " in Week " + currentWeek + " against " + currentOpponent);
    }

    public double getRanges(int rank) {
        if (rank < 5) {
            return 3.0;
        }

        else if (rank > 4 && rank < 9) {
            return 2.0;
        }

        else if (rank > 8 && rank < 13) {
            return 1.0;
        }

        else if (rank > 12 && rank < 17) {
            return 0;
        }

        else if (rank > 16 && rank <21) {
            return -1.5;
        }

        else if (rank > 20 && rank < 25) {
            return -2.5;
        }

        else if (rank > 24 && rank < 29) {
            return -3.5;
        }

        else {
            return -4.5;
        }
    }
}
