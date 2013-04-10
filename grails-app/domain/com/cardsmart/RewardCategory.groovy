package com.cardsmart

class RewardCategory {
	String name
	String description
	
	RewardCategory parent
	static hasMany = [children:RewardCategory]
	
    static constraints = {
		name blank:false
		description blank:false
		parent nullable:true
    }
	
	String toString() {
		"${description}"
	}
}
