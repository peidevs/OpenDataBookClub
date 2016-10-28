@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

def parseFile = { def file ->
    def schools = []
    def schoolsSet = new HashSet()

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        schoolsSet << line.getAt(0)
    }

    schools.addAll(schoolsSet)
    schools.sort()

    return schools
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
    println "Usage: groovy OD34_Generator.groovy inCsv templateHtml outHtml"
    System.exit(-1)
}

def csvFile = args[0]
def templateHtml = args[1]
def outputHtml = args[2]

def schools = parseFile(csvFile)

def templateText = new File(templateHtml).getText("UTF-8")

final String DATA_SCHOOL_AUTOGEN = "DATA_SCHOOL_AUTOGEN"
final String DATA_SCHOOL_OPTIONS = "DATA_SCHOOL_OPTIONS" 
final String DATA_SCHOOL_CSV_URL = "DATA_SCHOOL_CSV_URL"
final String DATA_SCHOOL_FILTER = "DATA_SCHOOL_FILTER"
final String DATA_SCHOOL_LEVELS_ASC = "DATA_SCHOOL_LEVELS_ASC"
final String DATA_SCHOOL_LEVELS_DESC = "DATA_SCHOOL_LEVELS_DESC"
final String DATA_SCHOOL_PREDICATE_FIELD = "DATA_SCHOOL_PREDICATE_FIELD"

def dataSchoolAutoGen = "THIS FILE IS AUTO-GENERATED. DO NOT EDIT (use the template instead)."
def dataSchoolOptions = buildOptions(schools) 
def dataSchoolCsvURL = '"https://raw.githubusercontent.com/peidevs/OpenDataBookClub/master/data/province/education/OD34.transform.csv"'
def dataSchoolFilter = buildFilter(schools)
def dataSchoolLevelsAsc = '[ "Current Capacity", "2016 Enrollment", "2022 Enrollment", "Capacity at Build" ]'
def dataSchoolLevelsDesc = '[ "Capacity at Build", "2022 Enrollment", "2016 Enrollment", "Current Capacity" ]'
def dataSchoolPredicateField = '"School"'

def newText = templateText
.replaceAll(DATA_SCHOOL_AUTOGEN, dataSchoolAutoGen)
.replaceAll(DATA_SCHOOL_OPTIONS, dataSchoolOptions) 
.replaceAll(DATA_SCHOOL_CSV_URL, dataSchoolCsvURL) 
.replaceAll(DATA_SCHOOL_FILTER, dataSchoolFilter) 
.replaceAll(DATA_SCHOOL_LEVELS_ASC, dataSchoolLevelsAsc) 
.replaceAll(DATA_SCHOOL_LEVELS_DESC, dataSchoolLevelsDesc) 
.replaceAll(DATA_SCHOOL_PREDICATE_FIELD, dataSchoolPredicateField)

new File(outputHtml).withWriter("UTF-8") { writer ->
    writer.write(newText)
}

