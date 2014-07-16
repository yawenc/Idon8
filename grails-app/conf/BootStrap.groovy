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
	def searchableService
    def drawService
	
	def init = { servletContext ->
        drawService.createCurrentDraw()
	}
	def destroy = {
	}
}
