package com.cardsmart

class CardNetwork {
	String networkname
	static hasMany = [cards:Card]

    static constraints = {
		networkname blank: false
    }
	
	String toString() {
		"${networkname}"
	}
}
