@Grab('com.xlson.groovycsv:groovycsv:1.0')
@Grab(group='joda-time', module='joda-time', version='2.9.4')

import static com.xlson.groovycsv.CsvParser.parseCsv

import org.joda.time.*
import org.joda.time.format.*

class Info {
    def location
    def year
    def month
    def visitors
}

def parseFile = { def file ->
    def mapByLocation = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def location = line.getAt(0)
        def year = line.getAt(1)
        def month = line.getAt(2)
        def visitors = line.getAt(3)
        mapByLocation[location] << new Info(location: location, year: year, month: month, visitors: visitors)
    }

    return mapByLocation.sort()
}

def mapByLocation = parseFile(args[0])

def buildData = { map, key ->
    def data = ""

    def formatter = DateTimeFormat.forPattern("dd/MM/yyyy")
    def dt = formatter.parseDateTime("01/01/2004")

    def done = false

    while(! done) {
        def year = dt.year().get()
        def month = dt.monthOfYear().get()
        dt = dt.plusMonths(1)
 
        def infos = map[key]
        def info = infos.find { (it.year as int) == year && (it.month as int) == month }
        data += "{ x: new Date(${year},${month-1}), y: ${info.visitors} }, \n"

        if (dt.getYear() == 2016) {
            done = true
        }
    }

    return data
}

def KEY_ACADIAN = "Acadian"
def KEY_BASIN_HEAD = "Basin Head"
def KEY_BEACONSFIELD = "Beaconsfield"
def KEY_ELMIRA = "Elmira"
def KEY_EPTEK = "Eptek"
def KEY_GREEN_PARK = "Green Park"
def KEY_ORWELL = "Orwell"

def DATA_ACADIAN = buildData(mapByLocation, KEY_ACADIAN)
def DATA_BASIN_HEAD = buildData(mapByLocation, KEY_BASIN_HEAD)
def DATA_BEACONSFIELD = buildData(mapByLocation, KEY_BEACONSFIELD)
def DATA_ELMIRA = buildData(mapByLocation, KEY_ELMIRA)
def DATA_EPTEK = buildData(mapByLocation, KEY_EPTEK)
def DATA_GREEN_PARK = buildData(mapByLocation, KEY_GREEN_PARK)
def DATA_ORWELL = buildData(mapByLocation, KEY_ORWELL)

def template = new File(args[1]).getText()

def newText = template
.replaceAll("DATA_ACADIAN", DATA_ACADIAN)
.replaceAll("DATA_BASIN_HEAD", DATA_BASIN_HEAD)
.replaceAll("DATA_BEACONSFIELD", DATA_BEACONSFIELD)
.replaceAll("DATA_ELMIRA", DATA_ELMIRA)
.replaceAll("DATA_EPTEK", DATA_EPTEK)
.replaceAll("DATA_GREEN_PARK", DATA_GREEN_PARK)
.replaceAll("DATA_ORWELL", DATA_ORWELL)

new File("OD19.html").withWriter { def writer ->
    writer.write(newText)
}
