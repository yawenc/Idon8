package charity

class ManageDrawJob {
    def drawService

    static triggers = {
      cron name: 'drawTrigger', cronExpression: "0 0 0 1 * ?"
    }

    def execute() {
        drawService.createCurrentDraw()
    }
}
