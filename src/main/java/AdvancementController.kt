import model.Advancement
import model.EventData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AdvancementController {
    private val advancementService: AdvancementService = AdvancementService()

    // fetch the advancement order given all event data
    @GetMapping("/advancement")
    open fun getAdvancement(@RequestBody eventData: EventData): List<Advancement>{
        // input validation
        return advancementService.liveAdvancement(eventData)
    }

    //get rankings from FIRST ftc-events given year and event code
    //Decode = 2025, In to the Deep = 2024
    // todo: limit the auth of this to be the UI only
    @GetMapping("/rankings/{year}/{eventCode}")
    fun getRankings(@PathVariable("year") year:String,
                    @PathVariable("eventCode") eventCode: String){
        advancementService.getRankings(eventCode,year)
    }
}