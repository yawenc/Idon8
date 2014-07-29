databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1405638960808-1") {
		addColumn(tableName: "draw") {
			column(name: "winner_id", type: "bigint")
		}
	}

	changeSet(author: "vitor (generated)", id: "1405638960808-3") {
		createIndex(indexName: "FK2F2D449FDE4E6A", tableName: "draw") {
			column(name: "winner_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1405638960808-2") {
		addForeignKeyConstraint(baseColumnNames: "winner_id", baseTableName: "draw", constraintName: "FK2F2D449FDE4E6A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ticket", referencesUniqueColumn: "false")
	}
}
