package com.cardsmart

class YelpCategory {
	String name
	String description
	
	YelpCategory parent
	static hasMany = [children:YelpCategory]
	
	static constraints = {
		name blank:false
		description blank:false
		parent nullable:true
	}
	
	String toString() {
		"${description}"
	}
}
