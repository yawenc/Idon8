databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1405560268439-1") {
		addColumn(tableName: "ticket") {
			column(name: "draw_id", type: "bigint") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1405560268439-3") {
		createIndex(indexName: "FKCBE86B0C7EADBE3D", tableName: "ticket") {
			column(name: "draw_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1405560268439-4") {
		createIndex(indexName: "draw_id_uniq_1405560268362", tableName: "ticket", unique: "true") {
			column(name: "draw_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1405560268439-2") {
		addForeignKeyConstraint(baseColumnNames: "draw_id", baseTableName: "ticket", constraintName: "FKCBE86B0C7EADBE3D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "draw", referencesUniqueColumn: "false")
	}
}
