package charity

import com.bertazoli.charity.Draw
import com.bertazoli.charity.enums.DrawStatus

class ManageDrawJob {
    def drawService
    def donationService

    static triggers = {
      cron name: 'drawTrigger', cronExpression: "0 0 0 1 * ?"
    }

    def execute() {
        // every first of the month we close the current draw and create a new one
        Draw currentDraw = drawService.getCurrentDraw();

        drawService.createCurrentDraw(true)

        currentDraw.setStatus(DrawStatus.FINALIZED)
        currentDraw.setActive(false)
        currentDraw.save(flush: true)

        donationService.calculateWinner(currentDraw)
    }
}
