@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv
import java.text.Collator

def sanitizeSchool = { s ->
    def sTrim = s.replaceAll("School","").trim()
    def result = sTrim

    if (sTrim == "Amherst Cove") { result = "Amherst Cove Consolidated" }
    else if (sTrim == "Bluefield") { result = "Bluefield High" }
    else if (sTrim == "Charlottetown Rural") { result = "Charlottetown Rural High" }
    else if (sTrim == "Colonel Gray") { result = "Colonel Gray High" }
    else if (sTrim ==~ /Centre scolaire cummunautaire fran.aise de Prince-Ouest/) { result = "Centre scolaire cummunautaire française de Prince-Ouest" }
    else if (sTrim == "Elm St. Elementary") { result = "Elm Street Elementary" }
    else if (sTrim == "Glen Stewart Primary") { result = "Glen Stewart Elementary" }
    else if (sTrim ==~ /Hernewood.*/) { result = "Hernewood Junior High" }
    else if (sTrim ==~ /Kensington.*High.*/) { result = "Kensington Intermediate Senior High" }
    else if (sTrim ==~ /Kinkora.*/) { result = "Kinkora Regional High" }
    else if (sTrim ==~ /L. M. Montgomery.*/) { result = "L.M. Montgomery Elementary" }
    else if (sTrim ==~ /M.E. Callaghan.*/) { result = "M.E. Callaghan Intermediate" }
    else if (sTrim ==~ /.*Leary.*Elementary/) { result = "O'Leary Elementary" }
    else if (sTrim == "Prince St. Elementary") { result = "Prince Street Elementary" }
    else if (sTrim == "Queen Charlotte") { result = "Queen Charlotte Intermediate" }
    else if (sTrim == "Somerset") { result = "Somerset Elementary" }
    else if (sTrim == "Souris Regional") { result = "Souris Regional High" }
    else if (sTrim == "St. Jean's Elementary") { result = "St. Jean Elementary" }
    else if (sTrim ==~ /St.*Peter.*Consolidated.*/)  { result = "St. Peter's Consolidated" }
    else if (sTrim ==~ /St.*Teresa.*Consolidated.*/)  { result = "St. Teresa's Consolidated" }
    else if (sTrim == "Three Oaks High") { result = "Three Oaks Senior High" }
    else if (sTrim == "Westisle") { result = "Westisle Composite High" }
    else if (sTrim ==~ /.*cole .*vang.*line/) { result = "École Évangéline" } 
    else if (sTrim ==~ /.*cole Fran.*ois.*Buote/) { result = "École François-Buote" }
    else if (sTrim ==~ /.*cole .a..elle..loche/) { result = "École La-Belle-Cloche" } 
    else if (sTrim ==~ /.*cole Pierre.Chaisson/) { result = "École Pierre-Chaisson" }
    else if (sTrim ==~ /.*cole .t..ugustin/) { result = "École St-Augustin" }
    else if (sTrim ==~ /.*cole-.ur-.er/) { result = "École-Sur-Mer" }

    return result 
}

def levels = []
levels << "K"
for (i in 1..12) {
    levels << "Grade " + i
}

def parseFile = { def file ->
    def schools = []
    def schoolsSet = new HashSet()

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def school = sanitizeSchool(line.getAt(0))
        schoolsSet << school
    }

    schools.addAll(schoolsSet)

    def collator = Collator.getInstance(new Locale("fr","CA"))
    Collections.sort(schools, collator)

    return schools
}

def buildOptions = { def schools ->
    def result = ""

    def format = '<option value="%s">%s</option>\n'
    schools.each { result += String.format(format, it, it) }

    return result
}

def buildFilter = { def schools ->
    def result = ""

    def format = '"%s",\n'
    schools.each { result += String.format(format, it) }

    return result
}

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy DataPopulator.groovy OD10_original"
    System.exit(-1)
}

def schools = parseFile(args[0])

def templateText = new File("template.OD10.step.group.html").getText("UTF-8")

final String DATA_SCHOOL_AUTOGEN = "DATA_SCHOOL_AUTOGEN"
final String DATA_SCHOOL_OPTIONS = "DATA_SCHOOL_OPTIONS" 
final String DATA_SCHOOL_CSV_URL = "DATA_SCHOOL_CSV_URL"
final String DATA_SCHOOL_FILTER = "DATA_SCHOOL_FILTER"
final String DATA_SCHOOL_LEVELS_ASC = "DATA_SCHOOL_LEVELS_ASC"
final String DATA_SCHOOL_LEVELS_DESC = "DATA_SCHOOL_LEVELS_DESC"
final String DATA_SCHOOL_PREDICATE_FIELD = "DATA_SCHOOL_PREDICATE_FIELD"

def dataSchoolAutoGen = "THIS FILE IS AUTO-GENERATED. DO NOT EDIT (use the template instead)."
def dataSchoolOptions = buildOptions(schools) 
def dataSchoolCsvURL = '"https://raw.githubusercontent.com/peidevs/OpenDataBookClub/master/data/province/education/OD10.transform.csv"'
def dataSchoolFilter = buildFilter(schools)
def dataSchoolLevelsAsc = '[ "K", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12"]'
def dataSchoolLevelsDesc = '["Grade 12", "Grade 11", "Grade 10", "Grade 9", "Grade 8", "Grade 7", "Grade 6", "Grade 5", "Grade 4", "Grade 3", "Grade 2", "Grade 1", "K"]'
def dataSchoolPredicateField = '"School"' 

def newText = templateText
.replaceAll(DATA_SCHOOL_AUTOGEN, dataSchoolAutoGen)
.replaceAll(DATA_SCHOOL_OPTIONS, dataSchoolOptions) 
.replaceAll(DATA_SCHOOL_CSV_URL, dataSchoolCsvURL) 
.replaceAll(DATA_SCHOOL_FILTER, dataSchoolFilter) 
.replaceAll(DATA_SCHOOL_LEVELS_ASC, dataSchoolLevelsAsc) 
.replaceAll(DATA_SCHOOL_LEVELS_DESC, dataSchoolLevelsDesc) 
.replaceAll(DATA_SCHOOL_PREDICATE_FIELD, dataSchoolPredicateField) 

new File(args[1]).withWriter("UTF-8") { writer ->
    writer.write(newText)
}

