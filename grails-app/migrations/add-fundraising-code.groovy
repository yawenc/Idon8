databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1408670701984-1") {
		addColumn(tableName: "fund_raising") {
			column(name: "code", type: "varchar(255)")
		}
	}

	changeSet(author: "vitor (generated)", id: "1408670701984-2") {
		addColumn(tableName: "fund_raising") {
			column(name: "private_fund_raising", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
