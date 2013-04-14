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

Sample data, including some yelp categories, credit cards, and rewards info will be loaded at runtime by BootStrap.groovy.

From the homepage, click the CardReward controller to see a list of the credit card rewards in the system. Click on "Show Map" to view nearby stores matching the credit card reward categories. Browser must support user geolocation and have it enabled. The map is zoomable and moveable; click "Update Map" to refresh results after zoom or move actions.

Desired features
=========
1. Support separate user accounts, each with their own credit cards/rewards.
2. Support credit card reward active periods.
3. Support rewards that apply to specific stores instead of categories.
4. UI improvements.
