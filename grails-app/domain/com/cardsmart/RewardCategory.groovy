package com.cardsmart

class RewardCategory {
	String name
	String description
	
    static constraints = {
		name blank:false
		description blank:false
    }
	
	String toString() {
		"${description}"
	}
}
