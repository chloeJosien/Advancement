import model.Advancement

class AdvancementController {
    private val advancementCalculator: AdvancementCalculator = AdvancementCalculator()

    //GET /advancement
    open fun getAdvancment(): List<Advancement>{
        // input validation
        return advancementCalculator.liveAdvancement()
    }
}