package charity

import com.bertazoli.charity.FundRaising
import com.bertazoli.charity.enums.FundRaisingStatus

/**
 * Created by vitor on 14/08/14.
 */
class FundRaisingJob {

    static triggers = {
        cron name: 'fundRaisingTrigger', cronExpression: "0 0 0 * * ?"
    }

    def execute() {
        Set<FundRaising> fundRaisings = FundRaising.findAllByActiveAndStatusAndEndDateLessThanEquals(true, FundRaisingStatus.CURRENT, new Date())
        for (FundRaising fundRaising : fundRaisings) {
            fundRaising.status = FundRaisingStatus.FINALIZED
            fundRaising.save()
        }
    }
}
