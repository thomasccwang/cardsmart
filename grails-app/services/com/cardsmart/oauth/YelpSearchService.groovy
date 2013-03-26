package com.cardsmart.oauth

import org.scribe.builder.ServiceBuilder
import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.oauth.OAuthService
import grails.converters.*

class YelpSearchService {

	static transactional = false
	
	def grailsApplication // see Config.groovy and oauth.properties
	
    def search(Map querymap) {
		OAuthService service = new ServiceBuilder()
									.provider(YelpApi2)
									.apiKey(grailsApplication.config.auth.yelp.consumerKey as String)
									.apiSecret(grailsApplication.config.auth.yelp.consumerSecret as String)
									.build()
		Token accessToken = new Token(grailsApplication.config.auth.yelp.token as String, 
			                          grailsApplication.config.auth.yelp.tokenSecret as String)
		OAuthRequest oarequest = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search")
		querymap.each {
			oarequest.addQuerystringParameter(it.key as String, it.value as String)
		}
		service.signRequest(accessToken, oarequest);
		def response = oarequest.send()
		response.body
    }
}