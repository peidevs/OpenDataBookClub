---
layout: post
title: PEI Open Data Hackathon
---

## Introduction

## Team Presentations

#### Restful GIS 

![Team Restful GIS](https://farm2.staticflickr.com/1489/24938363134_e142478510_n.jpg "Mark Wright presenting Address Liberation project"){: .postImage } 
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

* [PEI GIS Catalog](http://www.gov.pe.ca/gis/index.php3?number=77543)
* Source Control - Waiting on Mark

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

* [Existing Civil Aircraft Register Website](http://wwwapps.tc.gc.ca/saf-sec-sur/2/ccarcs-riacc/Menu.aspx?lang=eng)

#### Apple TV Street Condition

![Apple Tv](https://farm2.staticflickr.com/1518/25450302532_c8334f895f_n.jpg "Bob Shand showing off some code"){: .postImage }
**Team Members**: Nolan Phillips, Bob Shand, Kelvin Susam, Calista Tan, Ben Sinnamon

It was discovered that the Provincial 511 Data poertal was already providing some open data in json format and it was 
being used to show maps and charts on various road conditions and weather. The team decided to try and enhance the existing
portal by creating a map application on an IOS device which would be more user friendly. The existing site contains
lots of information, but is hard to use and the maps are not very responsive. 

When working with spatial data there are many different formats. The team learned that the 511 data contained points
but did not indicate which of the spatial formats were being used. Many hours were spent trying to decipher which format
the information was in, as well as discovering the data was in Radians insted of Degrees. Once that was completed the 
team moved on to their next problem.

The road conditions map shows roads and is dynamic to to show different colored lines based on the road conditions. Looking 
at the data it was clear to see their was a code representing the various road conditions. The team was expecting to find
that the existing site was using the road condition code along with GIS data to draw the line on the roads indicating the 
road conditions is a user friendy way. Instead they found that code was used to load an image of a map tile which overlayed 
the map. The difficulty now for the team, was they would have to pul in a 2nd dataset to assist in their project. Given
the timeframe of the event, this wasn't possible to do. The team could have used the GIS data available on the government
portal, but would have had to learn to use SHP files. This would have been a great collaboration with the Restful GIS team
who were already pulling out the GIS data into restful services. 

**Team Resources**

* [PEI 511 Portal](http://511.gov.pe.ca/en/map_report.html)
* [Spatial Reference](http://spatialreference.org/)
* Source Control - Waiting on Bob

#### MapRiddlr
![MapRiddlr](https://farm2.staticflickr.com/1551/25273253320_1c0f83880c_n.jpg "Sarah and Celito Presenting"){: .postImage }
**Team Members**: Sarah Thompson, Patrick Williams, Jullian Keller, Matt White, Nikita Volodin, Celito Felipetto

Where's Waldo?, Where In the World is Carmen Sandiego? Where the f*** is Ron Myers? This team would like to help you find
them. 

The MapRiddlr team created a game using google maps which provided the user clues in order to find a place in the world. 
A scavenger hunt of sorts. The clues would help narrow the location of the user. Providing points for correctly finding 
the location. The game was configurable to chase after different people including Mario, Yoshi and Ron Myers. Of
course Ron was wearing a bright red hat so he was easy to find.

If the clues were not enough, the team put a flag on the map at your location. The flag would tell you the distance between
you and the person you were trying to find. The users would be directd to various locations accross the world, in the 
Demo the team showed clues to find the Eiffel Tower. 

The team wanted to use the Landmarks of PEI project, created by Michael (PEI Open Data Bookclub Founder), but were not 
able to find enough information to get this data into the game. Because some of the points in that dataset are not always 
well known to people outside of an area or age demographic the team struggled to come up with enough clues to make the game 
viable in the timeframe expected at the event. Finding useful hints and facts about places liek the Eiffel Tower are 
much easier and a click away. So the team stayed focused on world landmarks for the demo.

The team showed a great potential for open data. You never know what your project could be used for. Who would think a 
landmarks project would be used as game to find the landmarks.

Of note, only a handful of people in the room knew who Carmen Saniego was, which makes this writer feel old.

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

