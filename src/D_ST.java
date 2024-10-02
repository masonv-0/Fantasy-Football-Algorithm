public class D_ST extends Player{

    private double[] weeklyStats;
    private int opposingOffenseRank;

    D_ST (String t, String c, double[] w, int o) {
        super(t, c);
        weeklyStats = w;
        opposingOffenseRank = o;
    }

    public void printAll() {
        super.printAll();
        for (int i = 0;i<16;i++) {
            System.out.print(weeklyStats[i] + " ");
        }
        System.out.println(opposingOffenseRank);
    }
}
