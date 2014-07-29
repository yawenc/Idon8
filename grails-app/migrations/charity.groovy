import com.bertazoli.charity.auth.*
import com.bertazoli.charity.user.*
import com.bertazoli.charity.*
import com.bertazoli.charity.enums.*
import org.apache.commons.lang.WordUtils

databaseChangeLog = {
	changeSet(author: "vitor (generated)", id: "add-charity") {
		grailsChange {
			change {
				
				HashMap<String, Country> countries = new HashMap<String, Country>();
				HashMap<String, State> states = new HashMap<String, State>();
				
				ArrayList<Country> c = Country.getAll()
				ArrayList<State> s = State.getAll()
				
				for (Country item : c) {
					countries.put(item.code, item);
				}
				
				for (State item : s) {
					states.put(item.code, item);
				}
				int line = 0;
				new File("grails-app/migrations/charities.csv").splitEachLine("\t", "ISO-8859-1") { fields ->
					Charity charity = new Charity(registrationNumber: fields[0],
											name: WordUtils.capitalizeFully(fields[1]),
											status: CharityStatus.getByDescription(fields[2]),
											effectiveDateOfStatus: new Date().parse('yyyy-MM-dd', fields[3]),
											sanction: CharitySanction.getByDescription(fields[4]),
											designationCode: fields[5],
											category: fields[6]
											).save();
		
					
					Country country = countries.get fields[10]
					State state = states.get fields[9];
                    def street = WordUtils.capitalizeFully(fields[7].replaceAll("\\s+", " "));
					Address address = new Address(country: country, state: state, street: street, city: fields[8], postalCode: fields[11], charity: charity).save();
					println line++
				}
			}
		}
		rollback {
			sql("delete from ticket")
			sql("delete from donation")
			sql("delete from address")
			sql("delete from charity")
		}
	}
}