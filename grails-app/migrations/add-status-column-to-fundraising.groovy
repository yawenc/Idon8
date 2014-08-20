databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1407992383979-1") {
		addColumn(tableName: "fund_raising") {
			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
