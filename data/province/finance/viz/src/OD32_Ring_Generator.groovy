@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

def parseFile = { file ->
    def resultSet = new HashSet() 

    resultSet << "FY 2011/12"
    resultSet << "FY 2012/13"
    resultSet << "FY 2013/14"
    resultSet << "FY 2014/15"

    def results = []
    results.addAll(resultSet)

    return results.sort()
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
    println "Usage: groovy OD32_Ring_Generator.groovy inCsv templateHtml outHtml"
    System.exit(-1)
}

def csvFile = args[0]
def templateHtml = args[1]
def outputHtml = args[2]

def fiscalYears = parseFile(csvFile)

def templateText = new File(templateHtml).getText("UTF-8")

final String DATA_AUTOGEN = "DATA_AUTOGEN"
final String DATA_OPTIONS = "DATA_OPTIONS" 
final String DATA_CSV_URL = "DATA_CSV_URL"
final String DATA_FILTER = "DATA_FILTER"
final String DATA_PREDICATE_FIELD = "DATA_PREDICATE_FIELD"
final String DATA_MEASURE_FIELD = "DATA_MEASURE_FIELD"
final String DATA_SERIES_FIELD = "DATA_SERIES_FIELD"

def dataAutoGen = "THIS FILE IS AUTO-GENERATED. DO NOT EDIT (use the template instead)."
def dataOptions = buildOptions(fiscalYears) 
def dataCsvURL = '"https://raw.githubusercontent.com/peidevs/OpenDataBookClub/master/data/province/finance/OD32.transform.csv"'
def dataFilter = buildFilter(fiscalYears)
def dataPredicateField = '"Fiscal Year"' 
def dataMeasureField = '"Amount (000' + "'" + 's)"'
def dataSeriesField = '"Source (Type)"'

def newText = templateText
.replaceAll(DATA_AUTOGEN, dataAutoGen)
.replaceAll(DATA_OPTIONS, dataOptions) 
.replaceAll(DATA_CSV_URL, dataCsvURL) 
.replaceAll(DATA_FILTER, dataFilter) 
.replaceAll(DATA_PREDICATE_FIELD, dataPredicateField) 
.replaceAll(DATA_MEASURE_FIELD, dataMeasureField)
.replaceAll(DATA_SERIES_FIELD, dataSeriesField)

new File(outputHtml).withWriter("UTF-8") { writer ->
    writer.write(newText)
}

