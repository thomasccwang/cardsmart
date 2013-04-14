CARDSMART
=========

Background
=========
This Grails app demonstrates interfacing with the Yelp Search API and the Google Maps Javascript API.

Description
=========
Some cash back or points reward credit cards have increased benefits for special categories, i.e. groceries or gas.

CARDSMART stores those special credit card reward categories and finds matching stores near by that match those categories and displays them on a map.

Technical details
=========
Yelp: Yelp API requests must be authenticated in compliance with OAuth 1.0a specification. CARDSMART uses the Scribe library to create the authenticated requests.

For further details, see:
https://github.com/Yelp/yelp-api/tree/master/v2/java


Google Maps: The Google Maps Javascript API is used to display nearby stores matching credit card reward categories.

For further details, see:
https://developers.google.com/maps/documentation/javascript/
https://developers.google.com/maps/articles/phpsqlsearch_v3

Usage
=========
To generate authentication credentials for Yelp API access, visit 
http://www.yelp.com/developers/documentation/v2/authentication

Put the authentication credentials in the following file:
grails-app/conf/oauth.properties.template
Rename the file to "oauth.properties" before running the app.

Desired features
=========



