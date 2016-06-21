@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

class Info {
    def year
    def size
}

def sanitizeSchool = { s ->
    def result = s.replaceAll(" School", "").trim()

    if (result ==~ /St. Peter.s.*/) { result = "St. Peter's Consolidated" }
    if (result ==~ /St. Teresa.s.*/) { result = "St. Teresa's Consolidated" }

    return result
}

def parseFile = { def file ->
    def mapBySchool = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def school = sanitizeSchool(line.getAt(0))
        for (i in 0..16) {
            def year = 1999 + i
            def col = i + 1 
            def size = line.getAt(col)
            if (size == null || size.isEmpty()) { size = 0 }

            mapBySchool[school] << new Info(year: year, size: size)
        }
    }

    return mapBySchool.sort()
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

def mapBySchool = parseFile(args[0])

new File("OD9.transform.csv").withWriter { def writer ->
    writer.write("School,Year,Size\n")

    mapBySchool.each { school, infos ->
        infos.each { info ->
            def size = info.size
            def year = info.year
            writer.write("${school},${year},${size}\n")
        }
    }
}

def templateText = new File("template.OD9.html").getText()

final String DATA_SCHOOL_AUTOGEN = "DATA_SCHOOL_AUTOGEN"
final String DATA_SCHOOL_OPTIONS = "DATA_SCHOOL_OPTIONS" 
final String DATA_SCHOOL_FILTER = "DATA_SCHOOL_FILTER"

def schools = []
schools.addAll(mapBySchool.keySet())
schools.sort()

def dataSchoolAutoGen = "THIS FILE IS AUTO-GENERATED. DO NOT EDIT (use the template instead)."
def dataSchoolOptions = buildOptions(schools) 
def dataSchoolFilter = buildFilter(schools)

def newText = templateText
.replaceAll(DATA_SCHOOL_AUTOGEN, dataSchoolAutoGen)
.replaceAll(DATA_SCHOOL_OPTIONS, dataSchoolOptions) 
.replaceAll(DATA_SCHOOL_FILTER, dataSchoolFilter) 

new File("../OD9.chart.html").withWriter { writer ->
    writer.write(newText)
}

