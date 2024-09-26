public class RunningBack extends Player{
    
    private double[] weeklyStats;
    private int offensiveLineRank;
    private int offensiveRunRank;
    private double avgSnapPercentage;
    private double avgTouches;
    private int totalRedZoneTouches;


    RunningBack(String n, String t, String c, int d, double[] w, int olr, int orr, double avgSP, double avgT, int tRZT) {
        super(n, t, c, d);
        weeklyStats = w;
        offensiveLineRank = olr;
        offensiveRunRank = orr;
        avgSnapPercentage = avgSP;
        avgTouches = avgT;
        totalRedZoneTouches = tRZT;
    }

    public void printAll() {
        super.printAll();
        for (int i = 0;i<weeklyStats.length;i++) {
            System.out.print(weeklyStats[i] + " ");
        }
        System.out.println();
        System.out.println(weeklyStats + " " + offensiveLineRank + " " + offensiveRunRank + " " + avgSnapPercentage + " " + avgTouches + " " + totalRedZoneTouches);
    }

}
