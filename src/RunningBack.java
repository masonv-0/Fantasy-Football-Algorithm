public class RunningBack {
    
    private String name;
    private String teamName;
    private int currentWeek;
    private String currentOpponent;
    private int defenseAgainst;
    private double[] weeklyStats;
    private int offensiveLineRank;
    private int offensiveRunRank;
    private double avgSnapPercentage;
    private double avgTouches;
    private double avgRedZoneTouches;


    RunningBack(String n, String t, int cw, String c, int d, double[] w, int olr, int orr, double avgSP, double avgT, double aRZT) {
        name = n;
        teamName = t;
        currentWeek = cw;
        currentOpponent = c;
        defenseAgainst = d;
        weeklyStats = w;
        offensiveLineRank = olr;
        offensiveRunRank = orr;
        avgSnapPercentage = avgSP;
        avgTouches = avgT;
        avgRedZoneTouches = aRZT;
    }

    public void calculatePoints(){ 
        
        //Finds average points over the last 16 weeks
        double totalPoints = 0;
        for (int i = 0;i<16;i++) {
            totalPoints+=weeklyStats[i];
        }
        double averagePoints = totalPoints/16;

        //Baseline points expected from RBs
        double expectedPoints = 10.0;

        //Rewards RBs for performing well, 14 pts/week is typically top 15, 16 pts/week is top 3
        if (averagePoints >= 14.0) {
            expectedPoints+=1.5;
            if (averagePoints >= 16.0) {
                expectedPoints+=1.5;
            }
        }

        //Penalizes RBs for performing badly, 8 pts/week is below top 40
        if (averagePoints <= 8.0) {
            expectedPoints-=2;
        }

        //Factors in opposing defense
        expectedPoints-=getRanges(defenseAgainst);

        //Factors in offensive line
        expectedPoints+=getRanges(offensiveLineRank);

        //Factors in rushing offense
        expectedPoints+=getRanges(offensiveRunRank);

        //Factors in snap percentage, high-usage backs are typically 50% or more
        if (avgSnapPercentage >= 50.0) {
            expectedPoints+=1.5;
        }
        else {
            expectedPoints-=1.5;
        }

        //Factors in average touches, high-usage backs typically get 16-24
        if (avgTouches >= 16.0) {
            expectedPoints+=2;
        }
        else if (avgTouches <= 16.0 && avgTouches >= 8.0) {
            expectedPoints+=1;
        }
        else {
            expectedPoints-=1;
        }

        //Factors in average red zone touches, good RBs get 3 or more
        if (avgRedZoneTouches >= 3.0) {
            expectedPoints+=2;
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

