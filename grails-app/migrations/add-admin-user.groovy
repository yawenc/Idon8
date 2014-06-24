import com.bertazoli.charity.auth.*;
import com.bertazoli.charity.user.*;
import groovy.sql.Sql

databaseChangeLog = {
	changeSet(author: "vitor (generated)", id: "add-admin-user") {
		grailsChange {
			change {
				def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush:true)
				def userRole = new Role(authority: 'ROLE_USER').save(flush:true)
				
				def testUser = new User(
					username: 'vitor',
					password: '1q2w3e4r',
					email: 'vitor@bertazoli.com'
				)
				testUser.save(flush:true)
			}
		}
		sql("insert into user_role (user_id,role_id) values ((select id from user where username = 'vitor'), (select id from role where authority = 'ROLE_ADMIN'))");
		sql("insert into user_role (user_id,role_id) values ((select id from user where username = 'vitor'), (select id from role where authority = 'ROLE_USER'))");
		rollback {
			sql("delete from user_role")
		}
	}
}