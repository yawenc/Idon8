package com.bertazoli.charity

import com.bertazoli.charity.util.DateUtil

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_ADMIN"])
class DrawController {
	def drawService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Draw.list(params), model:[drawInstanceCount: Draw.count()]
    }

    def show(Draw drawInstance) {
        respond drawInstance
    }

    def create() {
        respond new Draw(params)
    }
	
	def newdraw() {
		def draw = new Draw()
		def today = new Date()
		def currentDraw = drawService.getCurrentDraw()
		respond draw
	}

    @Transactional
    def save(Draw drawInstance) {
        if (drawInstance == null) {
            notFound()
            return
        }
		
		fixStartDate(params, drawInstance)
		fixEndDate(params, drawInstance)

		drawInstance.clearErrors()
		drawInstance.validate()
		
        if (drawInstance.hasErrors()) {
            respond drawInstance.errors, view:'create'
            return
        }

        drawInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'draw.label', default: 'Draw'), drawInstance.id])
                redirect drawInstance
            }
            '*' { respond drawInstance, [status: CREATED] }
        }
    }
	
	def fixEndDate(params, drawInstance) {
		if (params.endDate) {
			drawInstance.endDate = Date.parse('dd/MM/yyyy', params.endDate)
		}

        drawInstance.endDate = DateUtil.toEndOfTheDay(drawInstance.endDate)
	}
	
	def fixStartDate(params, Draw drawInstance) {
		if (params.startDate) {
			drawInstance.startDate = Date.parse('dd/MM/yyyy', params.startDate)
            drawInstance.startDate = DateUtil.toBeginingOfTheDay(drawInstance.startDate)
		}
	}

    def edit(Draw drawInstance) {
        respond drawInstance
    }

    @Transactional
    def update(Draw drawInstance) {
        if (drawInstance == null) {
            notFound()
            return
        }
		
		fixStartDate(params, drawInstance)
		fixEndDate(params, drawInstance)
		
		drawInstance.clearErrors()
		drawInstance.validate()

        if (drawInstance.hasErrors()) {
            respond drawInstance.errors, view:'edit'
            return
        }

        drawInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Draw.label', default: 'Draw'), drawInstance.id])
                redirect drawInstance
            }
            '*'{ respond drawInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Draw drawInstance) {

        if (drawInstance == null) {
            notFound()
            return
        }

        drawInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Draw.label', default: 'Draw'), drawInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'draw.label', default: 'Draw'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
