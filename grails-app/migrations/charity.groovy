import com.bertazoli.charity.auth.*
import com.bertazoli.charity.user.*
import com.bertazoli.charity.*
import com.bertazoli.charity.enums.*

databaseChangeLog = {
	changeSet(author: "vitor (generated)", id: "add-charity") {
		grailsChange {
			change {
				int line = 0;
				new File("grails-app/migrations/charities.csv").splitEachLine("\t", "ISO-8859-1") { fields ->
					if (line == 30) {
						return;
					}
					Charity charity = new Charity(registrationNumber: fields[0],
											name: fields[1],
											status: CharityStatus.getByDescription(fields[2]),
											effectiveDateOfStatus: new Date().parse('yyyy-MM-dd', fields[3]),
											sanction: CharitySanction.getByDescription(fields[4]),
											designationCode: fields[5],
											category: fields[6]
											).save(flush: true);
		
					Country country = Country.findByCode(fields[10])
					State state = State.findByCode(fields[9]);
					Address address = new Address(country: country, state: state, street: fields[7], city: fields[8], postalCode: fields[11], charity: charity).save(flush:true);
					line++
				}
			}
		}
		rollback {
			sql("delete from address")
			sql("delete from charity")
		}
	}
}