
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

class Fields {
    def isHeading = false
    def isRow = false

    def heading

    def code
    def name
    def min
    def max
    def enroll
    def web
    def waits
    def open
    def income
}

def cleanName = { def name ->
    return name.replaceAll("'","`")
}

def cleanIncome = { def income ->
    return income.replaceAll(/\$/,"").replaceAll(",","") // .replaceAll(/\.00/,"")
}
   

def parseLine = { def line ->
    def fields = new Fields()

    def code = line.getAt 0
    def matcher = (code =~ /Stratford \- (.*)/)
 
    if (matcher.matches()) {
        def heading = matcher[0][1]
        fields.heading = heading
        fields.isHeading = true
    } else {
        def income = line.getAt 8

        def hasAmount = (income.indexOf('$') != -1)
        def isTotal = (code.toLowerCase().indexOf('total') != -1)
   
        if (hasAmount && (!isTotal)) {
            fields.code = code
            fields.name = cleanName(line.getAt(1))
            fields.min = line.getAt 2 
            fields.max = line.getAt 3
            fields.enroll = line.getAt 4
            fields.web = line.getAt  5
            fields.waits = line.getAt 6
            fields.open = line.getAt 7
            fields.income = cleanIncome(income)
            fields.isRow = true
        }
    }

    return fields
}

def parseFile = { def domain, def file ->
    def rows = "" 
    def text = new File(file).getText()
    def data = parseCsv text
    def heading = null

    data.each { def line ->
        def fields = parseLine(line)

        if (fields.isHeading) {
             heading = fields.heading
        } else if (fields.isRow) {
             assert heading != null
             def f = fields
             def row = "['${domain}','${heading}','${f.code}'," + 
                        "'${f.name}',${f.min},${f.max},${f.enroll}," +
                         "${f.web},${f.waits},${f.open},${f.income}],\n"
             rows += row
        }
    }

    return rows
}

// ------- MAIN 

def rows = "" 

rows += parseFile("Winter", "../../Winter_2015.csv")
rows += parseFile("Spring", "../../Spring_2015.csv")
rows += parseFile("Summer", "../../Summer_2015.csv")
rows += parseFile("Baseball", "../../Baseball_2015.csv")

def html = """
<html>
  <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1.1", {packages:["table"]});
      google.setOnLoadCallback(drawTable);

      function drawTable() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Domain');
        data.addColumn('string', 'Heading');
        data.addColumn('string', 'Code');
        data.addColumn('string', 'Name');
        data.addColumn('number', 'Min');
        data.addColumn('number', 'Max');
        data.addColumn('number', 'Enroll');
        data.addColumn('number', 'Web Enroll');
        data.addColumn('number', 'Waits');
        data.addColumn('number', 'Open');
        data.addColumn('number', 'Income');
        data.addRows([
${rows}
        ]);

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true, width: '90%', height: '2000'});
      }
    </script>
  </head>
  <body>
    <div id="table_div"></div>
  </body>
</html>
"""

println html
