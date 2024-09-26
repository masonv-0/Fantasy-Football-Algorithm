import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;
import java.util.Scanner;

public class Main {
    

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

            JSONObject opponentLevel = (JSONObject) mainJSONObject.get(currentOpponent);

            JSONArray pastPointsArray = (JSONArray) playerLevel.get("past16FantasyPTs");
            double[] past16FantasyPTs = new double[16];

            for (int i = 0;i<16;i++) {
                past16FantasyPTs[i] = (double) pastPointsArray.get(i);
            }

            int opposingDefenseRank = (int) (long) opponentLevel.get("DefensivePassRank");
            int offensiveLineRank = (int) (long) teamLevel.get("OffensiveLineRank");

            
            if (playerPosition.equals("QB")) {
                int offensivePassRank = (int) (long) teamLevel.get("OffensivePassRank");
                boolean top10receiver = (boolean) playerLevel.get("top10Receiver");
                double avgReceiverRank = (double) playerLevel.get("avgReceiverRank");
                Player player = new Quarterback(playerName, teamIdentifier, currentOpponent, opposingDefenseRank, past16FantasyPTs, offensiveLineRank, offensivePassRank, top10receiver, avgReceiverRank);
            }

            else if (playerPosition.equals("RB")) {
                int offensiveRunRank = (int) (long) teamLevel.get("OffensiveRunRank");
                double avgSnapPercentage = (double) playerLevel.get("avgSnapPercentage");
                double avgTouches = (double) playerLevel.get("avgTouches");
                int totalRedZoneTouches = (int) (long) playerLevel.get("totalRedTouches");
                Player player = new RunningBack(playerName, playerPosition, currentOpponent, opposingDefenseRank, past16FantasyPTs, offensiveLineRank, offensiveRunRank, avgSnapPercentage, avgTouches, totalRedZoneTouches);
                player.printAll();
            }

            else if (playerPosition.equals("WR")) { 
                int offensivePassRank = (int) (long) teamLevel.get("OffensivePassRank");
                double avgTargets = (double) playerLevel.get("avgTargets");
                int QBpassRank = (int) (long) playerLevel.get("QBpassRank");
                int redZoneTargets = (int) (long) playerLevel.get("redZoneTargets");
                //Set up WideReceiver class and create an object here
            }

            else if (playerPosition.equals("TE")) {

            }

            else { // D/ST

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ***REMOVED***
    }
}

        
