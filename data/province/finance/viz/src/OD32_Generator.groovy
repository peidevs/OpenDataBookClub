@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

def parseFile = { file ->
    def entitySet = new HashSet() 

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def source = line.getAt(0).trim()
        def type = line.getAt(1).trim()
        def entity = "${source} (${type})"
        entitySet << entity
    }

    def entities = []
    entities.addAll(entitySet)

    return entities.sort()
}

def buildOptions = { def list ->
    def result = ""

    def format = '<option value="%s">%s</option>\n'
    list.each { result += String.format(format, it, it) }

    return result
}

def buildFilter = { def list ->
    def result = ""

    def format = '"%s",\n'
    list.each { result += String.format(format, it) }

    return result
}

// --------- main 

if (args.size() < 3) {
    println "Usage: groovy OD32_Generator.groovy inCsv templateHtml outHtml"
    System.exit(-1)
}

def csvFile = args[0]
def templateHtml = args[1]
def outputHtml = args[2]

def entities = parseFile(csvFile)

def templateText = new File(templateHtml).getText("UTF-8")

final String DATA_AUTOGEN = "DATA_AUTOGEN"
final String DATA_OPTIONS = "DATA_OPTIONS" 
final String DATA_CSV_URL = "DATA_CSV_URL"
final String DATA_FILTER = "DATA_FILTER"
final String DATA_PREDICATE_FIELD = "DATA_PREDICATE_FIELD"
final String DATA_X_FIELD = "DATA_X_FIELD"
final String DATA_Y_FIELD = "DATA_Y_FIELD"

def dataAutoGen = "THIS FILE IS AUTO-GENERATED. DO NOT EDIT (use the template instead)."
def dataOptions = buildOptions(entities) 
def dataCsvURL = '"https://raw.githubusercontent.com/peidevs/OpenDataBookClub/master/data/province/finance/OD32.transform.csv"'
def dataFilter = buildFilter(entities)
def dataPredicateField = '"Source (Type)"' 
def dataXField = '"Fiscal Year"'
def dataYField = '"Amount"'

def newText = templateText
.replaceAll(DATA_AUTOGEN, dataAutoGen)
.replaceAll(DATA_OPTIONS, dataOptions) 
.replaceAll(DATA_CSV_URL, dataCsvURL) 
.replaceAll(DATA_FILTER, dataFilter) 
.replaceAll(DATA_PREDICATE_FIELD, dataPredicateField) 
.replaceAll(DATA_X_FIELD, dataXField)
.replaceAll(DATA_Y_FIELD, dataYField)

new File(outputHtml).withWriter("UTF-8") { writer ->
    writer.write(newText)
}

