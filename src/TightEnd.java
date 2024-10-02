public class TightEnd extends Player{

    private double[] weeklyStats;
    private int offensivePassRank;
    private double avgTargets;
    private int QBpassRank;
    private int redZoneTargets;

    TightEnd (String n, String t, String c, int d, double[] w, int o, double a, int q, int r) {
        super(n,t,c,d);
        weeklyStats = w;
        offensivePassRank = o;
        avgTargets = a;
        QBpassRank = q;
        redZoneTargets = r;
    }

    public void printAll() {
        super.printAll();
        for (int i = 0;i<weeklyStats.length;i++) {
            System.out.print(weeklyStats[i] + " ");
        }
        System.out.println();
        System.out.println(offensivePassRank + " " + avgTargets + " " + QBpassRank + " " + redZoneTargets);
    }
}
