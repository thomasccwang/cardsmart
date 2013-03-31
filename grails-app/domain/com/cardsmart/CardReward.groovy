package com.cardsmart

class CardReward {
	Card card
	RewardCategory category
	String description
	Integer startMonth
	Integer endMonth
	
    static constraints = {
		description (blank:false)
		startMonth (range:1..12)
		endMonth (range:1..12, 
			validator:{ val, obj ->
				if (val.compareTo(obj.startMonth) < 0) return false
			}	
		)
    }
	
	String toString() {
		"${description}"
	}
}
