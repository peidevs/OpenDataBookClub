
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

// -------- Parse CSV

def positiveAspectsColumnIndex = 18

def text = new File("../001_Survey_Generic.csv").getText()
def data = parseCsv text

data.each { def line ->
    def field = line.getAt positiveAspectsColumnIndex 

    if (!field.trim().isEmpty()) {
        def cleanField = field.replaceAll("\\n","<br/>")
        println "<div>${cleanField}</div>"
    }
}

