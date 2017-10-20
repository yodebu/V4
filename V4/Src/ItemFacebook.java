

/**
 * Created by toan on 15/03/17.
 */
public class ItemFacebook extends Item {


    public ItemFacebook() {
        super();

    }


    /*
    DIFFERENT ATTRIBUTES
    name,streetAddress,extendedAddress,neighborhood,postalCode,locality,region,countryCode,countryName,latitude,longitude,latlong,phone,homepage,email,categories,description,imageUrl,mood,intro,priceRange,verified,closed,creationDate,owner,jsonOpeningHours,outdoorSeating,wifi,happyHour,payments,parkings,transport,facebookId,facebookUserName,facebookUserPage,facebookLikes,facebookCheckins,facebookTips,facebookTalkingAbout,facebookRating,googlePlacesId,googlePlacesUserName,googlePlacesUserPage,googlePlacesLikes,googlePlacesCheckins,googlePlacesTips,googlePlacesTalkingAbout,googlePlacesRating,foursquareId,foursquareUserName,foursquareUserPage,foursquareLikes,foursquareCheckins,foursquareTips,foursquareTalkingAbout,foursquareRating,twitterName,restaurantServices,restaurantMenuUrl,restaurantBookingUrl,lastReferencedDate

     EXAMPLE OF AN ITEM TO CUT
   Warmbader Hof,Rudolf-Grosse-Str. 54,,,10318,Berlin,,DE,Germany,52.49248,13.53201,"52.49248,13.53201",'+49 305030431',,,Restaurant,,,,,,false,false,,,,,,,,,,122099097843137,,https://www.facebook.com/pages/Warmbader-Hof/122099097843137,13,61,,0,,,,,,,,,,,,,,,,,,,,,,
Jenny Linke - Ganzheitliche Massagen,Zwieseler Str.,,,10318,Berlin,,DE,Germany,52.49301,13.53975,"52.49301,13.53975",'+49 17632280085',http://www.linke-personal-training.de/index.php/schwerpunkte/massagen.html,jenny@linke-personal-training.de,"Bar,Sports",,https://scontent.xx.fbcdn.net/v/t1.0-9/12795469_1557040124607867_2207834761912946603_n.jpg?oh=9cc75f5519e4f88ee1dd96c6fa0652bf&oe=58A56FE7,,"Fachpraktiker für Massage und Prävention
     */

    public void cutRawDescription(String partOfDescription[]) throws Exception {
        this.rawDescription.put("facebookLikes", partOfDescription[0]);
        this.rawDescription.put("facebookCheckins", partOfDescription[1]);
        this.rawDescription.put("facebookTalkingAbout", partOfDescription[2]);
        this.rawDescription.put("payments", partOfDescription[3]);
        this.rawDescription.put("parkings", partOfDescription[4]);
        this.rawDescription.put("restaurantServices", partOfDescription[5]);
        this.rawDescription.put("categories", partOfDescription[6]);
    }

    /*private void getValueForAttribute(String[] partOfDescription) {
    }*/

}


