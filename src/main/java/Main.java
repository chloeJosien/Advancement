import model.Advancement;
import model.Team;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int season = 2024; // the year kickoff was held in
        String eventCode = "USUTCMP"; //find event code on ftc-events
        boolean offlineMode = true; // update depending on when in use

        OfflineFileReader reader = new OfflineFileReader();
        List<Team> teams = new ArrayList<>();
        if(offlineMode){
            //read from files
            teams = reader.readRankFile();

        }
        else{
            //call ftc events
        }
        AdvancementCalculator advancementCalculator = new AdvancementCalculator();

        List<Advancement> advancement = advancementCalculator.advancement(teams);

        for(Advancement team : advancement){
            System.out.println("team: " +team.getTeamNumber() + " " +team.getTeamName() + ", points: " + team.getAdvancementPoints());
        }
    }
}