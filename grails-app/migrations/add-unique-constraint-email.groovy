databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1408500703943-1") {
		createIndex(indexName: "email_uniq_1408500703733", tableName: "user", unique: "true") {
			column(name: "email")
		}
	}
}
