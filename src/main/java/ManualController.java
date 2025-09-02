import model.Advancement;
import java.util.List;

/*
To be used when a team does not have access to the internet at a competition
Files should be created or updated in the resources' folder.
users will then need to update the file path that they want to use.
 */

public class ManualController {
    //easy update for offline workings
    private static final String rankFilePath = "/rank.txt";
    private static final String allianceFilePath= "/alliances.txt";
    private static final String eliminationOrderFilePath= "/eliminationOrder.txt";
    private static final String awardFilePath ="/awards.txt";

    public static void main(String[] args) {
        AdvancementCalculator advancementCalculator = new AdvancementCalculator();

        List<Advancement> advancement = advancementCalculator.manualAdvancement(rankFilePath, allianceFilePath,eliminationOrderFilePath,awardFilePath);

        for(Advancement team : advancement){
            System.out.println("team: " +team.getTeamNumber() + " " +team.getTeamName() + ", points: " + team.getAdvancementPoints());
        }
    }
}