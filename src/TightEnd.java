public class TightEnd{

    private String name;
    private String teamName;
    private int currentWeek;
    private String currentOpponent;
    private int defenseAgainst;
    private double[] weeklyStats;
    private int offensivePassRank;
    private double avgTargets;
    private int QBpassRank;
    private int redZoneTargets;

    TightEnd (String n, String t, int cw, String c, int d, double[] w, int o, double a, int q, int r) {
        name = n;
        teamName = t;
        currentWeek = cw;
        currentOpponent = c;
        defenseAgainst = d;
        weeklyStats = w;
        offensivePassRank = o;
        avgTargets = a;
        QBpassRank = q;
        redZoneTargets = r;
    }

    public void calculatePoints() {
        //Finds average points over the last 16 weeks
        double totalPoints = 0;
        for (int i = 0;i<16;i++) {
            totalPoints+=weeklyStats[i];
        }
        double averagePoints = totalPoints/16;

        //Baseline points expected from TEs
        double expectedPoints = 5.0;

        //Rewards TEs for performing well, 8 pts/week is typically top 10, 10 pts/week is top 5
        if (averagePoints >= 8.0) {
            expectedPoints+=1.0;
            if (averagePoints >= 10.0) {
                expectedPoints+=1.0;
            }
        }

        //Penalizes TEs for performing badly, ~4 pts/week is below top 32
        if (averagePoints <= 9.0) {
            expectedPoints-=2;
        }

        //Factors in opposing defense
        expectedPoints-=getRanges(defenseAgainst);

        //Factors in passing offense
        expectedPoints+=getRanges(offensivePassRank);

        //Factors in average targets
        expectedPoints+=(avgTargets / 3.0);

        //Factors in QB pass rank
        expectedPoints+=getRanges(QBpassRank);

        //Factors in red zone targets
        expectedPoints+=(redZoneTargets/currentWeek);

        System.out.println();
        System.out.print(name + " is projected ");
        System.out.printf("%.1f", expectedPoints);
        System.out.println(" fantasy points in Week " + currentWeek + " against " + currentOpponent);
    }

    public double getRanges(int rank) {
        if (rank < 5) {
            return 1.0;
        }

        else if (rank > 4 && rank < 9) {
            return 0.5;
        }

        else if (rank > 8 && rank < 13) {
            return 0;
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

