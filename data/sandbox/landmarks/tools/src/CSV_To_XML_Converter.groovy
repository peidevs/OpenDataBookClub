
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

import groovy.xml.*

class Landmark {
    def lat
    def lon
    def name
    def exists
    def type
    def description
    def notes
}

def handleSingleQuotes = { def s ->
    s.replaceAll(/'/,/\\'/)
}

def buildLandmark = { def line -> 
    def landmark = new Landmark()

    landmark.lat = line.getAt(0) as double
    landmark.lon = line.getAt(1) as double
    landmark.name = handleSingleQuotes(line.getAt(2))
    landmark.exists = true
    if (line.getAt(3) == "N") { landmark.exists = false } 
    landmark.type = line.getAt 4
    landmark.description = line.getAt 5 
    landmark.notes = line.getAt 6

    return landmark 
}

def parseFile = { def file, def builder ->
    def results = [] 
    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        try {
            results << builder(line)
        } catch (Exception ex) {
            System.err.println "caught exception on line >>> " + line
        }
    }

    return results
}

def buildXML = { def landmarks ->
    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)

    def counter = 1000

    xml.Landmarks() {
        landmarks.each { landmark ->
             Landmark() {
                 id(counter)
                 latitude(landmark.lat)
                 longitude(landmark.lon)

                 name(lang: "en_CA", landmark.name)
                 Aliases()
                 nowKnownAsName(lang: "en_CA")
                 description(lang: "en_CA", landmark.description)

                 exists(landmark.exists)
                 type(landmark.type)
                 startDate()
                 endDate()
                 ExternalLinks() {
                     def notes = landmark.notes
                     if (notes == null || notes.trim().isEmpty()) {
                         externalLink()
                     } else {
                         externalLink(notes)
                     } 
                 }
                 AliasLinks()
                 nowKnownAsLink()
             }
             counter++
        }
    }

    return writer.toString() 
}

// ------- MAIN 

def landmarks = parseFile("../../landmarks.csv", buildLandmark)

def xml = buildXML(landmarks)

def xmlHeader = '<?xml version="1.0" encoding="UTF-8"?>'
def xmlFile = new File("Landmarks.xml")
xmlFile.withWriter { 
    it.write(xmlHeader + "\n")
    it.write(xml) 
}

