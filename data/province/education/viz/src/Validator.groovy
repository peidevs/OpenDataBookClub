@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

class Info {
    int year
    int size
}

def sanitizeSchool = { s ->
    def result = s.replaceAll(" School", "").trim()

    if (result ==~ /St. Peter.s.*/) { result = "St. Peter's Consolidated" }
    if (result ==~ /St. Teresa.s.*/) { result = "St. Teresa's Consolidated" }

    return result
}

def sanitizeSize = { s ->
    def size = 0
    
    if (s != null && (! s.isEmpty())) {
        size = s as int
    }

    return size
}

def parseFile = { def file ->
    def mapBySchool = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def school = sanitizeSchool(line.getAt(0))
        for (i in 0..16) {
            int year = 1999 + i
            int col = i + 1 
            int size = sanitizeSize(line.getAt(col))

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

def findSize = { map, school, year ->
    def size = -1
    
    def infos = map[school]
    def info = infos.find { (it.year as int) == (year as int)}
    size = info.size 

    return size
}

// --------- main 

def mapBySchool = parseFile(args[0])

def isFirstLine = true
new File("OD9.transform.csv").eachLine { line ->
    if (isFirstLine) {
        isFirstLine = false
    } else { 
        def tokens = line.split(",")
        def thisSchool = tokens[0]
        def thisYear = tokens[1] as int
        def thisSize = tokens[2] as int
        assert thisSize == findSize(mapBySchool, thisSchool, thisYear)
    }
}

println "Ready."
