import model.Advancement;
import java.util.List;

/*
To be used when a team does not have access to the internet at a competition
Files should be created or updated in the resources' folder.
users will then need to update the file path that they want to use.
 */

public class ManualController {
    //easy update for offline workings
    //Beehive - Decode
    private static final String rankFilePath = "/Beehive-Decode/beehiveRank.txt";
    private static final String allianceFilePath= "/Beehive-Decode/beehiveAlliances.txt";
    private static final String eliminationOrderFilePath= "/Beehive-Decode/beehiveElim.txt";
    private static final String awardFilePath = "/Beehive-Decode/beehiveAwards.txt";

    //Utah Champs- into the deep
//    private static final String rankFilePath = "/rank.txt";
//    private static final String allianceFilePath= "/alliances.txt";
//    private static final String eliminationOrderFilePath= "/eliminationOrder.txt";
//    private static final String awardFilePath = "/awards.txt";
    public static void main(String[] args) {
        AdvancementService advancementCalculator = new AdvancementService();

        List<Advancement> advancement = advancementCalculator.manualAdvancement(rankFilePath, allianceFilePath,eliminationOrderFilePath,awardFilePath);

        for(Advancement team : advancement){
            System.out.println("team: " +team.getTeamNumber() + " " +team.getTeamName() + ", points: " + team.getAdvancementPoints());
        }
    }
}