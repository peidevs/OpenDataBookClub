---
layout: post
title: PEI Open Data Hackathon
---

## Team Presentations
* [event photos](https://www.flickr.com/photos/100794919@N05/albums/72157663224471673)
* [CBC article](http://www.cbc.ca/news/canada/prince-edward-island/data-computer-apps-open-government-1.3478962)
* [Caravaggio](https://twitter.com/30_for_60/status/706992363185577985)

#### Restful GIS 
![Team Restful GIS](https://farm2.staticflickr.com/1489/24938363134_e142478510_z.jpg "Mark Wright presenting Address Liberation project"){: .postImage } 
**Team Members**: Mark Wright, Mike Berger, Chris Stewart

The focus of the the Restful GIS team was to work with the existing governments GIS data and provide a restful API to 
the data which would provide users of the data an easier mechanism to consume the data. The GIS SHP files were extracted 
to a database which allowed queries to be written which allowed for advanced usage of the data. In the current format it 
was hard for users to cross reference data in multiple SHP files. The team was able to write queries on that data which 
would allow them to answer data requests for things like "All properties in Stratford" or "Police coverage within 5km of 
me". 

This project demonstrates the need for easy to consume data. SHP files provide lots of information, but are not 
easy to access for a general user. Providing the restful API provided users a cheaper entry point to the provinces GIS 
data.

**Team Resources**

#### Bursary
![Team Bursary](https://farm2.staticflickr.com/1501/25542728046_334649dd05_n.jpg "Christian Southgate presenting for the Bursary Team"){: .postImage }
**Team Members**: Michael Easter, Evan Porter, Christian Southgate, Ifo Ikede

A student looking for financial aid has to know about many different resources in order to apply for bursaries. For any
student, they may look on their schools financial aid's office website, go to multiple government websites, visit
their local municipal offices or local church. One example was on the PEI government site. The team began looking at the 
website for the Department of Workforce and Advanced Learning. This was a good starting point as this is the department 
responsible for assisting in financial aid. What they later discovered is that they found more bursary information on 
the PEI Government website, this time in the Advisory Council for the Status of Women section. Two departments on the 
same website hosting busary information. This makes information harder to discover.

Because there is no consolidated effort to list bursary information, a good chunk of this financial aid is going 
unclaimed every year. 

The goal of this team was to create an aggregate of the information listed on multiple sites and show how an easy 
search/filter can be used to help students find and apply for financial aid. The team found that their was lots of 
information available for bursaries in many different formats. They noticed that the Holland College website offered a 
similar service but the information was not easy to consume and required the effort to scrape the data in order to use 
it. Other sites had closed off the information in PDF's and other formats which made consolidating the information 
difficult.

The groups original approach to the project was to create an automatic scrapper to pull the information into their
application. They realized this approach was not realistic because they would have to create a transformation for each
new resource which they discovered. Their goal shifted throughout the day and in the end their goal was to start the 
conversation that if more groups opened the information or consolidated efforts, the end result is a better experience
for the people who need this information most.

**Team Resources**
* [View the Application](http://peidevs.github.io/OpenDataBookClub/bursary/)
* [Source Control](https://github.com/peidevs/OpenDataBookClub/tree/gh-pages/bursary)


#### Civil Aircraft Register
![Airplane](https://farm2.staticflickr.com/1457/25273254090_c855113396_n.jpg "Nathan presenting for his team"){: .postImage }
**Team Members**: Nathan Murnaghan, Nitheen Rao

Nitheen and Nathan set out to tackle the Canadian Civil Arcraft Register database, which contains information about 
all of the privatly owned airplanes in Canada. The current site offers two CSV files for download which contain 
information about the planes and their address information. Comments on the existing site, indicate that the data 
is not usable and unfriendly to work with. Even pilots who owned aircraft were having trouble finding their own information
to make sure everything was up to date and not expired. 

The teams goal was to massage the data and come up with a user friendly way for this information to be displayed. By 
making the data friendlier to use, the team was able to create a web application which let users find information about
planes quicker and easier than before.

While working and massaging the data into a usable format, the team quickly discovered that their was lots of bad data
in the records. Because the information was so hard to search in the original format it may not have been caught easily.
One example they discovered was the expiry dates on the registration for a few planes was 1900. 3 Years before the Wright
brothers flew their first plane.

**Team Resources**


#### Apple TV Street Condition
* [presentation photo](https://www.flickr.com/photos/100794919@N05/25450302532/in/album-72157663224471673/)
* **Team Members**: Nolan Phillips, Bob Shand, Kelvin Susam, Calista Tan, Ben Sinnamon
* **Source Control**: Waiting on Bob
* Use Province 511 Data to create an IOS/Apple TV application.
* Trying to Enhance the existing weather reports and traffic conditions
* Challenge, data contained points that were not labeled what spatial data format it was. Caused issues getting the data in lat long. 
* Existing 511 used images instead of spatial data to draw 

#### MapRiddlrgit add
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

