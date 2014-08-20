databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1407707434882-1") {
		createTable(tableName: "fund_raising") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fund_raisingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-2") {
		createTable(tableName: "fund_raising_donation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "fund_raising_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "completed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "donation_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "fee_amount_currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "fee_amount_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "fund_raising_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "gross_amount_currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "gross_amount_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "payment_status_code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "paypal_token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "transaction", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-6") {
		createIndex(indexName: "FKC6312E2F629BB97D", tableName: "fund_raising") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-7") {
		createIndex(indexName: "FKF72F0FA2629BB97D", tableName: "fund_raising_donation") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-8") {
		createIndex(indexName: "FKF72F0FA2A2A03BEC", tableName: "fund_raising_donation") {
			column(name: "fund_raising_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-3") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "fund_raising", constraintName: "FKC6312E2F629BB97D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-4") {
		addForeignKeyConstraint(baseColumnNames: "fund_raising_id", baseTableName: "fund_raising_donation", constraintName: "FKF72F0FA2A2A03BEC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "fund_raising", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1407707434882-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "fund_raising_donation", constraintName: "FKF72F0FA2629BB97D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
