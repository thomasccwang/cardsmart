package com.cardsmart

import com.cardsmart.CardIssuer;
import com.cardsmart.CardNetwork;

class Card {
	String cardname
	CardNetwork network
	CardIssuer issuer
	static hasMany = [rewards:CardReward]

	static constraints = {
		cardname blank : false
	}
	
	String toString() {
		"${issuer} ${cardname} ${network}"
	}
}
