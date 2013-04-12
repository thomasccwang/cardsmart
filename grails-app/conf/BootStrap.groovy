import com.cardsmart.CardNetwork
import com.cardsmart.CardIssuer
import com.cardsmart.Card
import com.cardsmart.CardReward
import com.cardsmart.YelpCategory

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
		
		def cFood = new YelpCategory(name:"food", description:"Food").save()
			def cGrocery = new YelpCategory(name:"grocery", description:"Grocery", parent: cFood).save()
			cFood.addToChildren(cGrocery)
		def cAutomotive = new YelpCategory(name:"auto", description:"Automotive").save()
			def cGas = new YelpCategory(name:"servicestations", description:"Gas & Service Stations", parent:cAutomotive).save()
			cAutomotive.addToChildren(cGas)
		def cShopping = new YelpCategory(name:"shopping", description:"Shopping").save()
			def cDrugstores = new YelpCategory(name:"drugstores", description:"Drug Stores", parent: cShopping).save()
			cShopping.addToChildren(cDrugstores)
		def cRestaurant = new YelpCategory(name:"restaurants", description:"Restaurants").save()
			def cTradamerican = new YelpCategory(name:"tradamerican", description:"American (Traditional)", parent:cRestaurant).save()
			def cHotdog = new YelpCategory(name:"hotdog", description:"Hot Dogs", parent:cRestaurant).save()
			def cMexican = new YelpCategory(name:"mexican", description:"Mexican", parent:cRestaurant).save()
			def cSandwiches = new YelpCategory(name:"sandwiches", description:"Sandwiches", parent:cRestaurant).save()
			cRestaurant.addToChildren(cTradamerican)
			cRestaurant.addToChildren(cHotdog)
			cRestaurant.addToChildren(cMexican)
			cRestaurant.addToChildren(cSandwiches)
		def cArts = new YelpCategory(name:"arts", description:"Arts & Entertainment").save()
			def cCinema = new YelpCategory(name:"movietheaters", description:"Cinema", parent:cArts).save()
			cArts.addToChildren(cCinema)
			
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
