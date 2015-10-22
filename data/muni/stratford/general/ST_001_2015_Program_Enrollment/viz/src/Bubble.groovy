
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

        def isTotal = (code.toLowerCase().indexOf('total') != -1)
        def hasIncome = (income.indexOf('$') != -1)

        def incomeAboveThreshold = false
        def cleanIncomeValue = 0d
        if (hasIncome) {
            cleanIncomeValue = cleanIncome(income) as double
            def threshold = 1800d
            if (cleanIncomeValue > threshold) { incomeAboveThreshold = true }
        }
   
        if (hasIncome && (!isTotal) && incomeAboveThreshold) {
            fields.code = code
            fields.name = cleanName(line.getAt(1))
            fields.min = line.getAt 2 
            fields.max = line.getAt 3
            fields.enroll = line.getAt 4
            fields.web = line.getAt  5
            fields.waits = line.getAt 6
            fields.open = line.getAt 7
            fields.income = cleanIncomeValue
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
             def id = fields.name
             def income = fields.income  
             def enroll = fields.enroll as int 
             def max = fields.max as int
             def enrollPercentage = (enroll / max) * 100 
             def row = "['${id}',${income},${max},'${domain}',${enrollPercentage}],\n"
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
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawSeriesChart);

    function drawSeriesChart() {

      var data = google.visualization.arrayToDataTable([
        ['ID', 'Income', 'Max', 'Domain', 'Enroll %'],
${rows}
      ]);

      var options = {
        title: 'Income x Max x Domain x Enroll % (2015)',
        hAxis: {title: 'Income (for programs > \$1800)'},
        vAxis: {title: 'Max Enrollment Capacity'},
        bubble: {textStyle: {fontSize: 11}}
      };

      var chart = new google.visualization.BubbleChart(document.getElementById('series_chart_div'));
      chart.draw(data, options);
    }
    </script>
  </head>
  <body>
    <div id="series_chart_div" style="width: 1400px; height: 900px;"></div>
  </body>
</html>
"""

println html
