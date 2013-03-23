package com.cardsmart

class CardIssuer {
	String issuername
	static hasMany = [cards:Card]

	static constraints = {
		issuername blank: false
	}
	
	String toString() {
		"${issuername}"
	}
}
