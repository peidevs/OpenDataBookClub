---
layout: post
title: PEI Open Data Hackathon
---

## Team Presentations
* [event photos](https://www.flickr.com/photos/100794919@N05/albums/72157663224471673)
* [CBC article](http://www.cbc.ca/news/canada/prince-edward-island/data-computer-apps-open-government-1.3478962)
* [Caravaggio](https://twitter.com/30_for_60/status/706992363185577985)

#### Address Liberation 
* [presentation photo](https://www.flickr.com/photos/100794919@N05/24938363134/in/album-72157663224471673/)
* **Team Members**: Mark Wright, Mike Berger, Chris Stewart
* **Source Control** - Missing
* Liberate the Address Lookup currently available on the Government of Prince Edward Island website
* Currently a service on a webpage, but needs to be opened for more information
* Built a database from GIS SHP files
* Create a RESTful API of the data, to get information that is easier for applications to consume
* In DB, the data was easier to write queries to do things like Search for all properties in Stratford
* Still contains GIS data, so you can search for all properties in a given area or range
* Search for police coverage in a given area

#### Bursary
* [presentation photo](https://www.flickr.com/photos/100794919@N05/25542728046/in/album-72157663224471673/)
* **Team Members**: Michael Easter, Evan Porter, Christian Southgate, Ifo Ikede
* [View the Application](http://peidevs.github.io/OpenDataBookClub/bursary/)
* [Source Control](https://github.com/peidevs/OpenDataBookClub/tree/gh-pages/bursary)
* Goal - aggregate infomation about Bursaries and Scholarships
* Lots of public info, but it is scattered
* Data is not easy to get
* Initial idea was automated scraping of PDFs and other data
    * unrealistic, have to write a transformer for every single doc
* Goal became to create the start of a conversation
* Show everyone how good and powerful this can be
* Challenge, data is too spread out. On the pei government site alone, there were a few departments hosting bursary data
    * Department of Workforce and Advanced Learning
    * Advisorie Council for the status of Women 
* Holland College offers similar service, but the information is closed and needs to be scraped

#### Airplane
* [presentation photo](https://www.flickr.com/photos/100794919@N05/25273254090/in/album-72157663224471673/)
* **Team Members**: Nathan Murnaghan, Nitheen Rao
* **Source Control** - Missing
* Working with Civil Aircraft Register Database
* Comments on current site indicate data is unfriendly.
* Team wanted to clean up data to make it much more usable. 
* Current site is 2 CSV files, hard to link together
* Civil Private Aircrafts.
* Pilots/Owners loooking for their own planes and were having trouble finding their aircraft
* Team created application which simplified the data and allowed the users to find/search aircrafts
* Data was national
* There was some data which needs cleanup. Showing data in this way it was easier to find bad data. e.g. license expiry dates of 1900
* First time doing web app for the team. Learned AngularJS for the project

#### Apple TV Street Condition
* [presentation photo](https://www.flickr.com/photos/100794919@N05/25450302532/in/album-72157663224471673/)
* **Team Members**: Nolan Phillips, Bob Shand, Kelvin Susam, Calista Tan, Ben Sinnamon
* **Source Control**: Missing
* Use Province 511 Data to create an IOS/Apple TV application.
* Trying to Enhance the existing weather reports and traffic conditions
* Challenge, data contained points that were not labeled what spatial data format it was. Caused issues getting the data in lat long. 
* Existing 511 used images instead of spatial data to draw 

#### MapRiddlr
* [presentation photo](https://www.flickr.com/photos/100794919@N05/25273253320/in/album-72157663224471673/)
* **Team Members**: Sarah Thompson, Patrick Williams, Jullian Keller, Matt White, Nikita Volodin, Celito Felipetto
* [Ron Myers](https://www.flickr.com/photos/100794919@N05/25450295822/in/album-72157663224471673/)
* [Source Control](https://github.com/MatthewWhite/MapRiddlr)
* Created a game where you had to find a person hidden on the map
* Provided user a hint, and the user had to find the user on the map
* Zoom in on the map until you find person
* Point system, user could ask for hints but would lose points. 1000 point base, lose 200 points for each hint
* A flag on the map would tell you how far you are from the point
* Inspired by Landmarks in PEI. Didn't have time to complete the game and create hints for all locations
    *  Difficulty with some of the points was coming up with the questions and hints
    *  Finding information about major landmarks was easier on a global scale
* Once you find the head, you would click the "head" to start a new game
* Great use of open data. You never know what it will be used for

## Closing Remarks
 * Thank you again to our sponsors: Government of PEI, StartUp Zone, and Binary Star

