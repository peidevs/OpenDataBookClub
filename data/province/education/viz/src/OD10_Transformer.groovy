@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

class Info {
    def year
    def level
    def size
}

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

def sanitizeSize = { s ->
    def size = 0
    
    if (s != null && (! s.trim().isEmpty())) {
        size = s as int
    }

    return size
}

def levels = []
levels << "K"
for (i in 1..12) {
    levels << "Grade " + i
}

def parseFile = { def file ->
    def mapBySchool = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def year = line.getAt(0) as int 
        def school = sanitizeSchool(line.getAt(1))
        for (levelIndex in 0..12) {
            def level = levels[levelIndex]
            def col = 2 + levelIndex
            def size = sanitizeSize(line.getAt(col))

            mapBySchool[school] << new Info(year: year, level: level, size: size)
        }
    }

    return mapBySchool.sort()
}

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy DataPopulator.groovy infile outfile"
    System.exit(-1)
}

def mapBySchool = parseFile(args[0])

new File(args[1]).withWriter("UTF-8") { def writer ->
    writer.write("School,Year,Level,Size\n")

    mapBySchool.each { school, infos ->
        infos.each { info ->
            def size = info.size
            def year = info.year
            def level = info.level
            writer.write("${school},${year},${level},${size}\n")
        }
    }
}

