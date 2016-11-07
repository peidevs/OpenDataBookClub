@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

class Info {
    def family
    def metric
    def numStudents
}

def sanitize = { def s ->
    def result = s

    if (s ==~ /.*O.*Leary.*/) { 
       result = "O'Leary Elementary School" 
    }

    result = result.replace("School", "").trim()

    return result
}

final def CAPACITY_AT_BUILD = "Capacity at Build"
final def CURRENT_CAPACITY = "Current Capacity"
final def ENROLLMENT_2016 = "2016 Enrollment"
final def ENROLLMENT_2022 = "2022 Enrollment"

def parseFile = { def file ->
    def mapBySchool = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def school = sanitize(line.getAt(0))
        def family = line.getAt(1)
        def capacityAtBuild = line.getAt(2) as int
        def currentCapacity = line.getAt(3) as int
        def enrollment2016 = line.getAt(4) as int
        def enrollment2022 = line.getAt(5) as int

        mapBySchool[school] << new Info(family: family, metric: CAPACITY_AT_BUILD, numStudents: capacityAtBuild)
        mapBySchool[school] << new Info(family: family, metric: CURRENT_CAPACITY, numStudents: currentCapacity)
        mapBySchool[school] << new Info(family: family, metric: ENROLLMENT_2016, numStudents: enrollment2016)
        mapBySchool[school] << new Info(family: family, metric: ENROLLMENT_2022, numStudents: enrollment2022)
    }

    return mapBySchool.sort()
}

def findNumStudents = { map, school, family, metric ->
    def result = -1
    
    def infos = map[school]
    def info = infos.find {
        (it.family == family) && (it.metric == metric)
    }

    if (info == null) {
        println "TRACER: error, could not find ${school} ${family} ${metric}"
        throw new IllegalStateException("")
    }

    result = info.numStudents 

    return result
}

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy OD10_Validator.groovy infile1 infile2"
    System.exit(-1)
}

def mapBySchool = parseFile(args[0])

def isFirstLine = true
new File(args[1]).eachLine { line ->
    if (isFirstLine) {
        isFirstLine = false
    } else { 
        def tokens = line.split(",")
        def thisSchool = sanitize(tokens[0])
        def thisFamily = tokens[1] 
        def thisMetric = tokens[2] 
        def thisNumStudents = tokens[3] as int
        assert thisNumStudents == findNumStudents(mapBySchool, thisSchool, thisFamily, thisMetric)
    }
}

println "Ready."
