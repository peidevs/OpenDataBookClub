---
layout: post
title: PEI Open Data Hackathon
---

## Introduction

March 5 was [International Open Data Day](https://en.wikipedia.org/wiki/International_Open_Data_Day), a day intended to promote Open Data [around the world](http://opendataday.org) through various hackathons, seminars, and presentations.

On PEI, organizers from [PEI Devs](http://www.meetup.com/PEI-Developers/) and the [Open Data Book Club](http://www.meetup.com/Open-Data-PEI) teamed up to hold the [PEI Open Data Hackathon](https://www.eventbrite.ca/e/pei-open-data-hackathon-tickets-21554098890) at the [Atlantic Technology Centre](http://atlantictechnologycentre.ca/). The event was sponsored by the [Government of Prince Edward Island](http://www.gov.pe.ca/), the StartUp Zone, and [Binary Star](http://binarystar.ca). Below is a recap of efforts by the various teams. [Here is a full album](https://www.flickr.com/photos/100794919@N05/albums/72157663224471673) of pics from the day. Finally, here is a [piece by CBC](http://www.cbc.ca/news/canada/prince-edward-island/data-computer-apps-open-government-1.3478962) on our event. 

## Team Presentations

#### Restful GIS 

![Team Restful GIS](https://farm2.staticflickr.com/1489/24938363134_e142478510_n.jpg "Mark Wright presenting Address Liberation project"){: .postImage } 
**Team Members**: Mark Wright, Mike Berger, Chris Stewart

The Restful GIS team utilized existing GIS data (from the Province) to provide a RESTful API to 
the data which would provide an easier mechanism to consume the data. The GIS SHP files were extracted 
to a database which allowed both queries and advanced usage of the data. In the current format, it 
was difficult for users to cross-reference data in multiple SHP files. The team wrote queries on that data which 
would facilitated data requests (e.g. "All properties in Stratford", "Police coverage within 5km of me"). 

This project demonstrates the need for simple access to data. SHP files provide copious amounts of information, but are not easy to access for a general user. A RESTful API provides users a simple entry point to the Provincial GIS data.

**Team Resources**

* [PEI GIS Catalog](http://www.gov.pe.ca/gis/index.php3?number=77543)
* Source Control - Waiting on Mark

#### Bursary

![Team Bursary](https://farm2.staticflickr.com/1501/25542728046_334649dd05_n.jpg "Christian Southgate presenting for the Bursary Team"){: .postImage }
**Team Members**: Michael Easter, Evan Porter, Christian Southgate, Ifo Ikede

Students in search of financial aid must consider many different resources: they might look on their schools financial aid's office website, go to multiple government websites, visit their local municipal offices or local church. One example was on the PEI government site. The team began looking at the website for the Department of Workforce and Advanced Learning. This was a good starting point as this is the department responsible for assisting in financial aid. What they later discovered is that they found more bursary information on the PEI Government website, this time in the Advisory Council for the Status of Women section. The bursary information is stored in *information silos*.

Because of the distributed nature of these silos, a non-trivial amount of this financial aid is going unclaimed every year. 

The goal: to create an aggregate of the information listed on multiple sites and offer a easy 
search/filter to help students find and apply for financial aid. The team discovered that much of the information available exists in different formats. They noticed that the Holland College website offered a 
similar service but the information was not easy to consume and required the effort to scrape the data in order to use 
it. Other sites had closed off the information in PDFs and other formats which made consolidating the information 
difficult.

The groups original approach to the project was to create an automatic scraper to pull the information into their
application. They realized this approach was not realistic: they would have to create a transformation for each
new resource which they discovered. Their goal shifted throughout the day: to start a 
conversation to encourage groups to open information for consolidation. The end result is a better experience
for the people who need this information most.

**Team Resources**

* [View the Application](http://peidevs.github.io/OpenDataBookClub/bursary/)
* [Source Control](https://github.com/peidevs/OpenDataBookClub/tree/gh-pages/bursary)


#### Civil Aircraft Register

![Airplane](https://farm2.staticflickr.com/1457/25273254090_c855113396_n.jpg "Nathan presenting for his team"){: .postImage }
**Team Members**: Nathan Murnaghan, Nitheen Rao

Nitheen and Nathan set out to tackle the Canadian Civil Arcraft Register database, which contains information about 
all privately-owned airplanes in Canada. The current site offers two CSV files for download, which contain 
information about the planes and their address information. Comments on the existing site indicate that the data 
is not usable and unfriendly to work with. Even pilots who owned aircraft were having trouble finding *their own information* to ensure data was up-to-date.

The goal: massage the data to enable a user-friendly way for this information to be displayed. By making the data simpler to use, the team created a web application which illustrated the effective search.

While working and massaging the data into a usable format, the team quickly encouraged the scourge of Open Data: bad data. The improved format revealed errors that had been hidden in the past. E.g. the expiry date on the registration for a few planes was **1900**, *three years before* the Wright brothers flew their first plane!

**Team Resources**

* [Existing Civil Aircraft Register Website](http://wwwapps.tc.gc.ca/saf-sec-sur/2/ccarcs-riacc/Menu.aspx?lang=eng)

#### Apple TV Street Condition

![Apple TV](https://farm2.staticflickr.com/1518/25450302532_c8334f895f_n.jpg "Bob Shand showing off some code"){: .postImage }
**Team Members**: Nolan Phillips, Bob Shand, Kelvin Susam, Calista Tan, Ben Sinnamon

It was discovered that the Provincial Open 511 Data portal was already providing open data in JSON format; it was being used to show maps and charts on various road conditions and weather. The team endeavoured to enhance the existing portal by creating a map application on an iOS device to deliver a fresh user-experience. The existing site contains good info, but is difficult to use on mobile devices.

When working with spatial data there are many different formats. The team learned that the Open 511 data contained points but did not indicate which of the spatial formats were being used. Many hours were spent trying to identify the format; as well as discovering the data was in Radians insted of Degrees. 

The *road conditions* map shows is dynamic: it displays different coloured lines based on conditions. Looking at the data, the team identified a code that represents various road conditions. The team anticipated that the road condition code combined with GIS data to render the line on the roads. Instead, the code is used to load an image of a map tile which overlayed the map. The team now needed a second dataset to implement the idea. Given the timeframe of the hackathon, this was impossible. The team could have used the GIS data available on the government portal, but would have had to learn to use SHP files. This could be a great future collaboration with the RESTful GIS team (who were already pulling out the GIS data into RESTful services). 

**Team Resources**

* [PEI 511 Portal](http://511.gov.pe.ca/en/map_report.html)
* [Spatial Reference](http://spatialreference.org/)
* Source Control - Waiting on Bob

#### MapRiddlr
![MapRiddlr](https://farm2.staticflickr.com/1551/25273253320_1c0f83880c_n.jpg "Sarah and Celito Presenting"){: .postImage }
**Team Members**: Sarah Thompson, Patrick Williams, Jullian Keller, Matt White, Nikita Volodin, Celito Felipetto

* *Where's Waldo?* 
* *Where In the World is Carmen Sandiego?* 
* *Where the heck is Ron Myers?* 

This team would like to help you find them. 

The MapRiddlr team created a game using Google Maps: the user receives clues to find a place in the world; a scavenger hunt, of sorts. The clues help narrow the location, and the user gains points for correctly finding it. The game used configurable targets: Mario, Yoshi and (our favourite) *Ron Myers*. Of course, Ron's image featured a bright red hat, so he was easy to find.

If the clues aren't enough, the team places a flag on the map at your location. The flag would tell you the distance between you and the person you were trying to find. The user is directed to various locations accross the world. In the Demo, the team demonstrated clues to find the Eiffel Tower. 

The team wanted to use the [PEI Landmarks](http://peidevs.github.io/OpenDataBookClub/landmarks/landmarks.html), but were not able to find enough hints/clues, a show-stopper for users outside of an area or age-demographic. Finding useful hints and facts about places like the Eiffel Tower are much easier and a click away. So the team focused on world landmarks for the demo.  

The team illustrated the great potential for open data. We never know where Open Data might be used. Who would think a 
landmarks project would be used as game to find the landmarks?

(Note: only a handful of people in the room knew who Carmen Sandiego was, which makes this writer feel old).

**Team Resources**

* [Source Control](https://github.com/MatthewWhite/MapRiddlr)

## Closing Remarks
Thank you again to our sponsors: Government of PEI, StartUp Zone, and Binary Star. Thank you to Tracy and John for 
stopping by and talking about the PEI Governments initiative to move to open data. Thank you to Cody from the StartUp Zone
who introduced many of us to this new location in PEI.
 
## Additional Resources
 * [event photos](https://www.flickr.com/photos/100794919@N05/albums/72157663224471673)
 * [CBC article](http://www.cbc.ca/news/canada/prince-edward-island/data-computer-apps-open-government-1.3478962)
 * [Caravaggio](https://twitter.com/30_for_60/status/706992363185577985)

