
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

def markerMap = ['001_B': 'blue',  '002_S': 'purple', '003_W': 'white', '004_N': 'green', '005_M': 'yellow', '006_R': 'brown']

def buildChartRows = { def landmarks, def types ->
    def chartRows = []

    landmarks.each { landmark ->
        def chartRow = new ChartRow()

        chartRow.lat = landmark.lat
        chartRow.lon = landmark.lon
        def t = types.find { def t -> t.type == landmark.type }
        def type = t.type
        def typeDesc = t.description
        def existsDesc = ""
        if ((type != "004_N") && (type != "005_M") && (type != "006_R")) {
            existsDesc = (landmark.exists) ? "current " : "former " 
        }
        chartRow.name = "${landmark.name} (${existsDesc}${typeDesc})"

        if (landmark.name ==~ /.*Purple.*/) {
            chartRow.name = "You found where the ${chartRow.name} used to be!\\n\\nA dandy landmark:\\n- used in everyday conversation\\n- not on a typical map" 
        }
        if (    (landmark.name ==~ /.*Ken.*/) 
             || (landmark.name ==~ /.*Rogers.*/)
             || (landmark.name ==~ /.*Rainbow.*/)) {
            chartRow.name += "\\n\\nHistorians: yes, we hope to link\\nto external archives!" 
        }
        if (landmark.name ==~ /.*Highest.*/) {
            chartRow.name += "\\n\\nNous espérons à traduire dans\\n de nombreuses langues!"
        }
        if (    (landmark.name ==~ /.*Dairy.*/) 
             || (landmark.name ==~ /.*Mix.*/)
             || (landmark.name ==~ /.*Seabreeze.*/)) {
            chartRow.name += "\\n\\nWork-in-progress: we hope to link to\\nhistorical archives and sites\\nfor current businesses." 
        }
        if (landmark.name ==~ /.*Up.West.*/) {
            chartRow.name = "Hey, Up West: we need your help!\\nNous avons besoin de votre aide!\\nPlease share some landmarks."
        }

        chartRow.marker = markerMap[type]

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

