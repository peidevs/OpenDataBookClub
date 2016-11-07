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

def findAmount = { map, key, fiscalYear ->
    def result = -1
    
    def infos = map[key]
    def info = infos.find { it.fiscalYear == fiscalYear }
    result = info.amount 

    return result
}

// --------- main 

if (args.size() < 2) {
    println "Usage: groovy OD32_Validator.groovy csvFile transformFile"
    System.exit(-1)
}

def csvFile = args[0]
def transformFile = args[1]

def mapByEntity = parseFile(csvFile)

def transformText = new File(transformFile).getText()
def transformData = parseCsv(transformText)

transformData.each { def line ->
    def thisEntity = line.getAt(0)
    def thisFiscalYear = line.getAt(1)
    def thisAmount = line.getAt(2) as int
    assert thisAmount == findAmount(mapByEntity, thisEntity, thisFiscalYear)
}

println "valid!"
