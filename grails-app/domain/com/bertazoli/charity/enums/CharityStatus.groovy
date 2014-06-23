package com.bertazoli.charity.enums

enum CharityStatus {
	ANNULLED("Annulled"),
	REGISTERED("Registered"),
	REVOKED_FAILURE_TO_FILE("Revoked - Failure to file"),
	REVOKED_FOR_CAUSE("Revoked - For cause"),
	REVOKED_VOLUNTARY("Revoked - Voluntary");
	
	final String displayName
	
	CharityStatus(String displayName) {
		this.displayName = displayName;
	}
	
	String getKey() {
		name()
	}
	
	String toString() {
		displayName
	}
	
	public static CharityStatus getByDescription(String description) {
		for (CharityStatus status : values()) {
			if (status.displayName == description) {
				return status;
			}
		}
	}
}
