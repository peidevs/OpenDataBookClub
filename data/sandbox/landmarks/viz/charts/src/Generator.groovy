
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

class Landmark {
    def lat
    def lon
    def name
    def exists
    def type
    def description
}

def buildLandmark = { def line -> 
    def landmark = new Landmark()

    landmark.lat = line.getAt(0) as double
    landmark.lon = line.getAt(1) as double
    landmark.name = line.getAt 2
    landmark.exists = true
    if (line.getAt(3) == "N") { landmark.exists = false } 
    landmark.type = line.getAt 4
    landmark.description = line.getAt 5 

    return landmark 
}

class Type {
    def type
    def description
}

def buildType = { def line -> 
    def t = new Type()

    t.type = line.getAt 0
    t.description = line.getAt 1
   
    return t
}

class ChartRow {
    def lat
    def lon
    def name
    def marker
}

def parseFile = { def file, def builder ->
    def results = [] 
    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        results << builder(line)
    }

    return results
}

def buildChartRows = { def landmarks, def types ->
    def chartRows = []

    landmarks.each { landmark ->
        def chartRow = new ChartRow()

        chartRow.lat = landmark.lat
        chartRow.lon = landmark.lon
        def t = types.find { def t -> t.type == landmark.type }
        def type = t.type
        def typeDesc = t.description
        chartRow.name = "${landmark.name} (${typeDesc})"

        def marker = 'blue'
        if (type == "003_C") { marker = 'pink' }
        if (type == "004_N") { marker = 'green' }

        chartRow.marker = marker

        chartRows << chartRow
    }

    return chartRows
}

def buildOutgoingRows = { def chartRows ->
    def result = new StringBuilder() 

    chartRows.each { 
        result.append(/[${it.lat},${it.lon},'${it.name}','${it.marker}'],/ + "\n")
    }

    return result.toString()
}

// ------- MAIN 

def landmarks = parseFile("../../../landmarks.csv", buildLandmark)

def types = parseFile("../../../types.csv", buildType)

def chartRows = buildChartRows(landmarks, types)

def html = new File("../template_landmarks.html").getText()
def landmarkDataRows = buildOutgoingRows(chartRows)

println html.replace("LANDMARK_DATA_ROWS", landmarkDataRows)

