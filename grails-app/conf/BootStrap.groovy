import com.cardsmart.CardNetwork
import com.cardsmart.CardIssuer
import com.cardsmart.Card
import com.cardsmart.CardReward
import com.cardsmart.RewardCategory

class BootStrap {

    def init = { servletContext ->
		def visa = new CardNetwork(networkname: "VISA").save()
		def masc = new CardNetwork(networkname: "MasterCard").save()
		def disc = new CardNetwork(networkname: "Discover").save()
		def amex = new CardNetwork(networkname: "American Express").save()
		
		def chas = new CardIssuer(issuername: "Chase").save()
		def cap1 = new CardIssuer(issuername: "Capital One").save()
		def bofa = new CardIssuer(issuername: "Bank of America").save()
		def citi = new CardIssuer(issuername: "Citi").save()
		
		def bofacashrewards = new Card(cardname: "Cash Rewards", network: visa, issuer: bofa).save()
		visa.addToCards(bofacashrewards)
		bofa.addToCards(bofacashrewards)
		
		def chasfreedom = new Card(cardname: "Freedom", network: masc, issuer: chas).save()
		masc.addToCards(chasfreedom)
		chas.addToCards(chasfreedom)
		
		//Food (food)
		def cFood = new RewardCategory(name:"food", description:"Food").save()
			def cGrocery = new RewardCategory(name:"grocery", description:"Grocery", parent: cFood).save()
			cFood.addToChildren(cGrocery)
		def cGas = new RewardCategory(name:"servicestations", description:"Gas & Service Stations").save()
		def cDrugstore = new RewardCategory(name:"drugstores", description:"Drug Stores").save()
		def cRestaurant = new RewardCategory(name:"restaurants", description:"Restaurants").save()
			def cTradamerican = new RewardCategory(name:"tradamerican", description:"American (Traditional)", parent:cRestaurant).save()
			def cHotdog = new RewardCategory(name:"hotdog", description:"Hot Dogs", parent:cRestaurant).save()
			def cSandwiches = new RewardCategory(name:"sandwiches", description:"Sandwiches", parent:cRestaurant).save()
			cRestaurant.addToChildren(cTradamerican)
			cRestaurant.addToChildren(cHotdog)
			cRestaurant.addToChildren(cSandwiches)
		def cCinema = new RewardCategory(name:"movietheaters", description:"Cinema").save()
		
		def bofacrgroceries = new CardReward(card:bofacashrewards, category: cGrocery, description:"2% cash back on groceries", startMonth:1, endMonth:12).save()
		bofacashrewards.addToRewards(bofacrgroceries)
		
		def bofacrgas = new CardReward(card:bofacashrewards, category: cGas, description:"3% cash back on gas", startMonth:1, endMonth:12).save()
		bofacashrewards.addToRewards(bofacrgas)
		
		def chasfrrest = new CardReward(card:chasfreedom, category: cRestaurant, description:"5% cash back at restaurants", startMonth:4, endMonth:6).save()
		chasfreedom.addToRewards(chasfrrest)
		
		def chasfrcine = new CardReward(card:chasfreedom, category: cCinema, description:"5% cash back at movie theaters", startMonth:4, endMonth:6).save()
		chasfreedom.addToRewards(chasfrcine)
	}
	
    def destroy = {
    }
}
