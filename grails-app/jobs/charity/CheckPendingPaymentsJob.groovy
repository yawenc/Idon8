package charity

class CheckPendingPaymentsJob {
    def donationService

    static triggers = {
//      cron name: 'checkPendingPayments', cronExpression: "0 0 */4 * * ?"
        cron name: 'checkPendingPayments', cronExpression: "0 0 */4 * * ?"
    }

    def execute() {
        println "Check payments"
        donationService.checkPendingPayments()
    }
}
