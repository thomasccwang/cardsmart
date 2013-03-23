import com.cardsmart.CardNetwork
import com.cardsmart.CardIssuer
import com.cardsmart.Card

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
		
		def cap1cashrewards = new Card(cardname: "Cash Rewards", network: masc, issuer: cap1).save()
		masc.addToCards(cap1cashrewards)
		cap1.addToCards(cap1cashrewards)
		
		def cap1venture = new Card(cardname: "Venture Rewards", network: visa, issuer: cap1).save()
		visa.addToCards(cap1venture)
		cap1.addToCards(cap1venture)
		
		def cap1venture1 = new Card(cardname: "VentureOne", network: visa, issuer: cap1).save()
		visa.addToCards(cap1venture1)
		cap1.addToCards(cap1venture1)
    }
	
    def destroy = {
    }
}
