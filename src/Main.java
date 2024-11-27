import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import java.util.Scanner;

public class Main {
    
    /*

    STATS LAST UPDATED: 10/22/24

    Offenses/Defenses are ranked by Yards/Game

    */

    //This code is taken from a YouTube video: https://www.youtube.com/watch?v=yLf2-r8w9lQ&t=443s
    
    public static String getJSONFromFile(String filename) {
        String jsonText = "";
        try {
            BufferedReader jsonReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = jsonReader.readLine()) != null) {
                jsonText += line + "\n";
            }

            jsonReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return jsonText;
    }
    public static void main(String[] args){
        String playerJSON = getJSONFromFile("Players.json");
        Scanner integerScan = new Scanner(System.in);
        Scanner stringScan = new Scanner(System.in);
        
        //First 3 lines are from the same video, the rest is my work

        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(playerJSON);
            JSONObject mainJSONObject = (JSONObject) object;

            System.out.println("What week of the NFL season is it?");
            int currentWeek = integerScan.nextInt();
            System.out.println("What team does the player play for?");
            String teamIdentifier = stringScan.nextLine();
            System.out.println("What is your player's name?");
            String playerName = stringScan.nextLine();

            integerScan.close();
            stringScan.close();

            JSONObject teamLevel = (JSONObject) mainJSONObject.get(teamIdentifier);
            JSONObject playerLevel = (JSONObject) teamLevel.get(playerName);
            String playerPosition = (String) playerLevel.get("position");
            
            JSONArray teamScheduleArray = (JSONArray) teamLevel.get("schedule");
            String currentOpponent = (String) (teamScheduleArray.get(currentWeek-1));
            if (currentOpponent.equals("BYE")) {
                System.out.println("That player is on their bye week!");
                System.exit(0);
            }

            JSONObject opponentLevel = (JSONObject) mainJSONObject.get(currentOpponent);

            JSONArray pastPointsArray = (JSONArray) playerLevel.get("past16FantasyPTs");
            double[] past16FantasyPTs = new double[16];

            for (int i = 0;i<16;i++) {
                past16FantasyPTs[i] = (double) pastPointsArray.get(i);
            }

            int opposingDefensePassRank = (int) (long) opponentLevel.get("DefensivePassRank");
            int opposingDefenseRunRank = (int) (long) opponentLevel.get("DefensiveRunRank");

            int offensiveLineRank = (int) (long) teamLevel.get("OffensiveLineRank");

            
            if (playerPosition.equals("QB")) {
                int offensivePassRank = (int) (long) teamLevel.get("OffensivePassRank");
                boolean top10receiver = (boolean) playerLevel.get("top10Receiver");
                Quarterback player = new Quarterback(playerName, teamIdentifier, currentWeek, currentOpponent, opposingDefensePassRank, past16FantasyPTs, offensiveLineRank, offensivePassRank, top10receiver);
                player.calculatePoints();
            }

            else if (playerPosition.equals("RB")) {
                int offensiveRunRank = (int) (long) teamLevel.get("OffensiveRunRank");
                double avgSnapPercentage = (double) playerLevel.get("avgSnapPercentage");
                double avgTouches = (double) playerLevel.get("avgTouches");
                double avgRedZoneTouches = (double) playerLevel.get("avgRedTouches");
                RunningBack player = new RunningBack(playerName, teamIdentifier, currentWeek, currentOpponent, opposingDefenseRunRank, past16FantasyPTs, offensiveLineRank, offensiveRunRank, avgSnapPercentage, avgTouches, avgRedZoneTouches);
                player.calculatePoints();
            }

            else if (playerPosition.equals("WR")) { 
                int offensivePassRank = (int) (long) teamLevel.get("OffensivePassRank");
                double avgTargets = (double) playerLevel.get("avgTargets");
                int QBpassRank = (int) (long) playerLevel.get("QBpassRank");
                int redZoneTargets = (int) (long) playerLevel.get("redZoneTargets");
                WideReceiver player = new WideReceiver(playerName, teamIdentifier, currentWeek, currentOpponent, opposingDefensePassRank, past16FantasyPTs, offensivePassRank, avgTargets, QBpassRank, redZoneTargets);
                player.calculatePoints();
            }

            else { //TEs
                int offensivePassRank = (int) (long) teamLevel.get("OffensivePassRank");
                double avgTargets = (double) playerLevel.get("avgTargets");
                int QBpassRank = (int) (long) playerLevel.get("QBpassRank");
                int redZoneTargets = (int) (long) playerLevel.get("redZoneTargets");
                TightEnd player = new TightEnd(playerName, teamIdentifier, currentWeek, currentOpponent, opposingDefensePassRank, past16FantasyPTs, offensivePassRank, avgTargets, QBpassRank, redZoneTargets);
                player.calculatePoints();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

