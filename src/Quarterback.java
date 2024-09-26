public class Quarterback extends Player{
    
    private double [] weeklyStats;
    private int offensiveLineRank;
    private int offensivePassRank;
    private boolean top10Receiver;
    private double avgReceiverRank;

    Quarterback(String n, String t, String c, int d, double[] w, int olr, int opr, boolean t10, double avg) {
        super(n,t,c,d);
        weeklyStats = w;
        offensiveLineRank = olr;
        offensivePassRank = opr;
        top10Receiver = t10;
        avgReceiverRank = avg;
    }

    public void printAll() {
        super.printAll();
        for (int i = 0;i<weeklyStats.length;i++) {
            System.out.print(weeklyStats[i] + " ");
        }
        System.out.println();
        System.out.println(weeklyStats + " " + offensiveLineRank + " " + offensivePassRank + " " + top10Receiver + " " + avgReceiverRank);
    }
}