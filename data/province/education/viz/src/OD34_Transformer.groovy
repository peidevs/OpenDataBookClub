@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

def sanitize = { def s ->
    def result = s

    if (s ==~ /.*O.*Leary.*/) { 
       result = "O'Leary Elementary School" 
    }

    return result
}

class Info {
    def family
    def metric
    def numStudents
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
        def family = sanitize(line.getAt(1))
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

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy OD34_Transformer.groovy infile outfile"
    System.exit(-1)
}

def mapBySchool = parseFile(args[0])

new File(args[1]).withWriter("UTF-8") { def writer ->
    writer.write("School,Family,Metric,# Students\n")

    mapBySchool.each { school, infos ->
        infos.each { info ->
            def family = info.family
            def metric = info.metric
            def numStudents = info.numStudents
            writer.write("${school},${family},${metric},${numStudents}\n")
        }
    }
}

