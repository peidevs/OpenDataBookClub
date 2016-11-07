
import groovy.xml.*

def names = new HashSet()

class CsvInfo {
    def lat
    def lon
    def name
    def exists
    def type
    def keywords
    def startDate
    def endDate
    def infoWindowContent
}

def DIV_START = "<div class='googft-card-view' style='font-family: sans-serif; width: 450px; padding: 4px; border: 1px'>"
def DIV_END = "</div>"

def isTextNotEmpty = { def node ->
    def result = false

    if (node != null) {
        def s = node.text()
        result = (s != null) && (!s.trim().isEmpty())
    }

    return result
}

def buildInfoWindowContent = { def landmark ->
    def content = DIV_START

    content += "<h3>${landmark.name}</h3>"

    // 'now known as'
    if (isTextNotEmpty(landmark.nowKnownAsName)) {
        def nowKnownAs = landmark.nowKnownAsName.text()
        content += "Now known as: ${nowKnownAs}" 
    }

    // external links
    if (landmark.ExternalLinks.Link.size() > 0) {
        content += "<h4>External Links</h4>" 
        landmark.ExternalLinks.Link.each { 
            def url = it.URL.text()
            def description = it.description.text()
            content += "<a href='${url}' target='_blank'>${description}</a><br/>"
        } 
    }

    // 'now known as' links
    if (landmark.NowKnownAsLinks.Link.size() > 0) {
        content += "<h4>Current Links</h4>" 
        landmark.NowKnownAsLinks.Link.each { 
            def url = it.URL.text()
            def description = it.description.text()
            content += "<a href='${url}' target='_blank'>${description}</a><br/>"
        } 
    }

    // aliases

    // alias links

    content += DIV_END

    return content
}

def buildKeywords = { def landmark ->
    def keywords = landmark.name.text()
    return keywords.toLowerCase()
}

def buildCsvInfo = { def landmark ->
    def csvInfo = new CsvInfo()

    csvInfo.lat = landmark.latitude.text()    
    csvInfo.lon = landmark.longitude.text()    
    csvInfo.name = landmark.name.text()    
    csvInfo.exists = landmark.exists.text()
    csvInfo.type = landmark.type.text()
    csvInfo.keywords = buildKeywords(landmark) 
    csvInfo.startDate = landmark.startDate.text()
    csvInfo.endDate = landmark.endDate.text()
    csvInfo.infoWindowContent = buildInfoWindowContent(landmark)

    if (names.contains(csvInfo.name)) {
        println "TRACER DUPE : " + csvInfo.name
        // throw new IllegalStateException("duplicate name: " + csvInfo.name)
    } else {
        names << csvInfo.name
    }

    return csvInfo
}

def field = { def s, def isLast = false ->
    def q = '"'
    def trailer = (isLast) ? "" : ","
    return "${q}${s}${q}${trailer}"
}

def processXML = { ->
    def result = ""
    def landmarksXml = new File("../../../Landmarks.xml")
    def root = new XmlSlurper().parseText(landmarksXml.getText()) 

    root.Landmark.each { def landmark ->
        def csvInfo = buildCsvInfo(landmark)
        def line = ""
        line += field(csvInfo.lat)
        line += field(csvInfo.lon)
        line += field(csvInfo.name)
        line += field(csvInfo.exists)
        line += field(csvInfo.type)
        line += field(csvInfo.keywords)
        line += field(csvInfo.startDate)
        line += field(csvInfo.endDate)
        line += field(csvInfo.infoWindowContent)
        line += "\n"

        result += line
    }

    return result
}

def csvFile = new File("landmarks.fusion.table.csv")
def csvText = processXML()

csvFile.withWriter { def writer ->
    writer.write(csvText)
}
