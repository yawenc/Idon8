databaseChangeLog = {

	changeSet(author: "vitor (generated)", id: "1403585857845-1") {
		createTable(tableName: "address") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "addressPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "charity_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "postal_code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "street", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-2") {
		createTable(tableName: "charity") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "charityPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "integer")

			column(name: "designation_code", type: "varchar(255)")

			column(name: "effective_date_of_status", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "registration_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "sanction", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-3") {
		createTable(tableName: "country") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "countryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "varchar(4)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-4") {
		createTable(tableName: "donation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "donationPK")
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

			column(name: "gross_amount_currency", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "gross_amount_value", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "payment_status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "payment_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "paypal_token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "percentage_to_keep", type: "integer") {
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

	changeSet(author: "vitor (generated)", id: "1403585857845-5") {
		createTable(tableName: "draw") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "drawPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-6") {
		createTable(tableName: "draw_donation") {
			column(name: "draw_donations_id", type: "bigint")

			column(name: "donation_id", type: "bigint")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-7") {
		createTable(tableName: "registration_code") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-8") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-9") {
		createTable(tableName: "state") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "statePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "code", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "country_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-10") {
		createTable(tableName: "ticket") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "ticketPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "donation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ticket_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-11") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-12") {
		createTable(tableName: "user_details") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "user_detailsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_of_birth", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-13") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-14") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-26") {
		createIndex(indexName: "FKBB979BF438883C17", tableName: "address") {
			column(name: "country_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-27") {
		createIndex(indexName: "FKBB979BF4C0F764D7", tableName: "address") {
			column(name: "charity_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-28") {
		createIndex(indexName: "FKBB979BF4D7B4B977", tableName: "address") {
			column(name: "state_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-29") {
		createIndex(indexName: "FK450B8792629BB97D", tableName: "donation") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-30") {
		createIndex(indexName: "FKDB86382D646BB9FD", tableName: "draw_donation") {
			column(name: "donation_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-31") {
		createIndex(indexName: "FKDB86382DCD01C25B", tableName: "draw_donation") {
			column(name: "draw_donations_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-32") {
		createIndex(indexName: "authority_uniq_1403585857794", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-33") {
		createIndex(indexName: "FK68AC49138883C17", tableName: "state") {
			column(name: "country_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-34") {
		createIndex(indexName: "FKCBE86B0C646BB9FD", tableName: "ticket") {
			column(name: "donation_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-35") {
		createIndex(indexName: "username_uniq_1403585857800", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-36") {
		createIndex(indexName: "FKB7C889CE629BB97D", tableName: "user_details") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-37") {
		createIndex(indexName: "user_id_uniq_1403585857801", tableName: "user_details", unique: "true") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-38") {
		createIndex(indexName: "FK143BF46A629BB97D", tableName: "user_role") {
			column(name: "user_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-39") {
		createIndex(indexName: "FK143BF46ABD70F59D", tableName: "user_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-15") {
		addForeignKeyConstraint(baseColumnNames: "charity_id", baseTableName: "address", constraintName: "FKBB979BF4C0F764D7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "charity", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-16") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "address", constraintName: "FKBB979BF438883C17", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-17") {
		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "address", constraintName: "FKBB979BF4D7B4B977", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-18") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "donation", constraintName: "FK450B8792629BB97D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-19") {
		addForeignKeyConstraint(baseColumnNames: "donation_id", baseTableName: "draw_donation", constraintName: "FKDB86382D646BB9FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "donation", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-20") {
		addForeignKeyConstraint(baseColumnNames: "draw_donations_id", baseTableName: "draw_donation", constraintName: "FKDB86382DCD01C25B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "draw", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-21") {
		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "state", constraintName: "FK68AC49138883C17", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-22") {
		addForeignKeyConstraint(baseColumnNames: "donation_id", baseTableName: "ticket", constraintName: "FKCBE86B0C646BB9FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "donation", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-23") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_details", constraintName: "FKB7C889CE629BB97D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-24") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46ABD70F59D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "vitor (generated)", id: "1403585857845-25") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46A629BB97D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
	
	include file: 'add-admin-user.groovy'
}
