public class Quarterback{
    
    private String name;
    private String teamName;
    private String currentOpponent;
    private int currentWeek;
    private int defenseAgainst;
    private double [] weeklyStats;
    private int offensiveLineRank;
    private int offensivePassRank;
    private boolean top10Receiver;

    Quarterback (String n, String t, int cw, String c, int d, double[] w, int olr, int opr, boolean t10) {
        name = n;
        teamName = t;
        currentWeek = cw;
        currentOpponent = c;
        defenseAgainst = d;
        weeklyStats = w;
        offensiveLineRank = olr;
        offensivePassRank = opr;
        top10Receiver = t10;
    }

    public void calculatePoints() {
        
        //Finds average points over the last 16 weeks
        double totalPoints = 0;
        for (int i = 0;i<16;i++) {
            totalPoints+=weeklyStats[i];
        }
        double averagePoints = totalPoints/16;

        //Baseline points expected from QBs
        double expectedPoints = 15.0;

        //Rewards QBs for performing well in the past, 20 pts/week is typically top 15
        if (averagePoints >= 20.0) {
            expectedPoints+=1.5;
        }
        //Penalizes QBs for performing badly in the past, 15 pts/week is below top 32
        if (averagePoints <= 15.0) {
            expectedPoints-=2;
        }
        //Factors in opposing defense
        expectedPoints-=getRanges(defenseAgainst);
        //Factors in offensive line
        expectedPoints+=getRanges(offensiveLineRank);
        //Factors in the passing offense
        expectedPoints+=getRanges(offensivePassRank);
        //Factors in whether the QB is throwing to a top 10 receiver
        if (top10Receiver) {
            expectedPoints+=1.5;
        }
        
        System.out.println();
        System.out.print(name + " is projected ");
        System.out.printf("%.1f", expectedPoints);
        System.out.println(" fantasy points in Week " + currentWeek + " against " + currentOpponent);
    }

    public double getRanges(int rank) {
        if (rank < 5) {
            return 1.5;
        }

        else if (rank > 4 && rank < 9) {
            return 1.0;
        }

        else if (rank > 8 && rank < 13) {
            return 0.5;
        }

        else if (rank > 12 && rank < 17) {
            return 0;
        }

        else if (rank > 16 && rank <21) {
            return -0.5;
        }

        else if (rank > 20 && rank < 25) {
            return -1.0;
        }

        else if (rank > 24 && rank < 29) {
            return -1.5;
        }

        else {
            return -2.0;
        }
    }
}

