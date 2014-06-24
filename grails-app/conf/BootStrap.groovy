import com.bertazoli.charity.Address
import com.bertazoli.charity.Charity;
import com.bertazoli.charity.Country;
import com.bertazoli.charity.State;
import com.bertazoli.charity.auth.Role
import com.bertazoli.charity.auth.User
import com.bertazoli.charity.auth.UserRole
import com.bertazoli.charity.enums.CharitySanction;
import com.bertazoli.charity.enums.CharityStatus;
import com.bertazoli.charity.user.UserDetails

import groovy.sql.Sql

class BootStrap {
	def grailsApplication
	def dataSource
	
	def init = { servletContext ->
		/*
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

		def testUser = new User(username: 'vitor', password: '1q2w3e4r', email: 'vitor@bertazoli.com')
		testUser.save(flush: true)
		def userDetails = new UserDetails(firstName: 'Vitor', lastName: 'Bertazoli', dateOfBirth: new Date().parse("yyyy-MM-dd", "1982-03-10"), user:testUser).save(flush: true)

		UserRole.create testUser, adminRole, true
		UserRole.create testUser, userRole, true

		assert User.count() == 1
		assert Role.count() == 2
		assert UserRole.count() == 2

		// INSERT COUNTRIES
		String sqlFilePath = servletContext.getRealPath("/data/country.sql")
		Sql sql = Sql.newInstance(dataSource)
		String sqlString = new File(sqlFilePath).eachLine {
			sql.execute(it)
		}
		
		// INSERT STATE
		sqlFilePath = servletContext.getRealPath("/data/state.sql")
		sqlString = new File(sqlFilePath).eachLine {
			sql.execute(it)
		}
		
		sqlFilePath = servletContext.getRealPath("/data/charities.csv")
		int line = 0;
		new File(sqlFilePath).splitEachLine("\t", "ISO-8859-1") { fields ->
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
		*/
	}
	def destroy = {
	}
}
