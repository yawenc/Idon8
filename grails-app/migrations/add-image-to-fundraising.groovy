databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1408495942224-1") {
		addColumn(tableName: "fund_raising") {
			column(name: "file_name", type: "varchar(255)")
		}
	}
}
