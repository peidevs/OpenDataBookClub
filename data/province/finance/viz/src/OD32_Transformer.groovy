@Grab('com.xlson.groovycsv:groovycsv:1.0')

import static com.xlson.groovycsv.CsvParser.parseCsv

// NOTE: define an Entity to be Source[Type]

class Info {
    String fiscalYear
    int amount
}

def sanitizeAmount = { s ->
    int result = 0
    
    if (s != null && (! s.trim().isEmpty())) {
        result = s as int
    }

    return result
}
assert 0 == sanitizeAmount(null)
assert 0 == sanitizeAmount("")
assert 1000 == sanitizeAmount("1000")

def fiscalYears = [ "FY 2011/12", "FY 2012/13", "FY 2013/14", "FY 2014/15" ]

def parseFile = { def file ->
    def mapByEntity = [:].withDefault { key -> [] }

    def text = new File(file).getText()
    def data = parseCsv text

    data.each { def line ->
        def source = line.getAt(0).trim()
        def type = line.getAt(1).trim()
        def entity = "${source} (${type})" 
        for (fiscalYearIndex in 0..3) {
            def fiscalYear = fiscalYears[fiscalYearIndex]
            def col = 2 + fiscalYearIndex
            def amount = sanitizeAmount(line.getAt(col))

            mapByEntity[entity] << new Info(fiscalYear: fiscalYear, amount: amount)
        }
    }

    return mapByEntity
}

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy OD32_Transformer.groovy infile outfile"
    System.exit(-1)
}

def infile = args[0]
def outfile = args[1]

def mapByEntity = parseFile(infile)

new File(outfile).withWriter("UTF-8") { def writer ->
    writer.write("Source (Type),Fiscal Year,Amount (000's)\n")

    mapByEntity.each { entity, infos ->
        infos.each { info ->
            def fiscalYear = info.fiscalYear
            def amount = info.amount
            writer.write("\"${entity}\",${fiscalYear},${amount}\n")
        }
    }
}

