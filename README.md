cardsmart
=========
This app  is mostly a test to interface with the Yelp search API as well as the Google Maps javascript API.

Developed with Grails 2.2.1.

Since Yelp API requests must be authenticated, the app requires Yelp API access credentials.

To generate authentication credentials, visit http://www.yelp.com/developers/documentation/v2/authentication

Put the authentication credentials in the following file:
grails-app/conf/oauth.properties.template

Then rename the file to "oauth.properties".

The app can then be run.
