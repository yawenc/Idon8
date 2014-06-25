databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1403666190168-1") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "charity_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "postal_code", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "state_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "street", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-2") {
		createTable(tableName: "charity") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "INT")

			column(name: "designation_code", type: "VARCHAR(255)")

			column(name: "effective_date_of_status", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "registration_number", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "sanction", type: "VARCHAR(255)")

			column(name: "status", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-3") {
		createTable(tableName: "country") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "VARCHAR(4)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-4") {
		createTable(tableName: "donation") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "charity_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "completed", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "donation_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "draw_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "fee_amount_currency", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "fee_amount_value", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "gross_amount_currency", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "gross_amount_value", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_status", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "payment_type", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "paypal_token", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "percentage_to_keep", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "transaction", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-5") {
		createTable(tableName: "draw") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-6") {
		createTable(tableName: "registration_code") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-7") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-8") {
		createTable(tableName: "state") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-9") {
		createTable(tableName: "ticket") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "donation_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ticket_number", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-10") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-11") {
		createTable(tableName: "user_details") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "date_of_birth", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-12") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-13") {
		addPrimaryKey(columnNames: "role_id, user_id", tableName: "user_role")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-25") {
		createIndex(indexName: "authority", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-26") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-14") {
		addForeignKeyConstraint(baseColumnNames: "charity_id", baseTableName: "address", baseTableSchemaName: "charity", constraintName: "FKBB979BF4C0F764D7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "charity", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-15") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "address", baseTableSchemaName: "charity", constraintName: "FKBB979BF438883C17", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "country", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-16") {
		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "address", baseTableSchemaName: "charity", constraintName: "FKBB979BF4D7B4B977", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "state", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-17") {
		addForeignKeyConstraint(baseColumnNames: "charity_id", baseTableName: "donation", baseTableSchemaName: "charity", constraintName: "FK450B8792C0F764D7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "charity", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-18") {
		addForeignKeyConstraint(baseColumnNames: "draw_id", baseTableName: "donation", baseTableSchemaName: "charity", constraintName: "FK450B87927EADBE3D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "draw", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-19") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "donation", baseTableSchemaName: "charity", constraintName: "FK450B8792629BB97D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-20") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", baseTableSchemaName: "charity", constraintName: "FK68AC49138883C17", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "country", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-21") {
		addForeignKeyConstraint(baseColumnNames: "donation_id", baseTableName: "ticket", baseTableSchemaName: "charity", constraintName: "FKCBE86B0C646BB9FD", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "donation", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-22") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_details", baseTableSchemaName: "charity", constraintName: "FKB7C889CE629BB97D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-23") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", baseTableSchemaName: "charity", constraintName: "FK143BF46ABD70F59D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403666190168-24") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", baseTableSchemaName: "charity", constraintName: "FK143BF46A629BB97D", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "charity", referencesUniqueColumn: "false")
	}
	
	include file: 'add-admin-user.groovy'
	
	changeSet(author: "vitor (generated)", id: "add-country") {
		sqlFile(path: "country.sql")
		rollback("delete from country")
	}
	
	changeSet(author: "vitor (generated)", id: "add-state") {
		sqlFile(path: "state.sql")
		rollback("delete from state")
	}
	
	include file: 'charity.groovy'
}
