
@Grab('com.xlson.groovycsv:groovycsv:1.0')
import static com.xlson.groovycsv.CsvParser.parseCsv

// -------- Parse CSV

def text = new File("../001_Survey_Generic.csv").getText()
def data = parseCsv text

// -------- Table Rows

def tableHeader = "<tr>"

def columns = []
columns << "Timestamp"
columns << "Where?"
columns << "Notice?"
columns << "Consider myself"
columns << "Musician"
columns << "Weather"
columns << "Landmark"
columns << "Sports team"
columns << "School"
columns << "EI"
columns << "Bud the Spud"
columns << "Pet peeve"
columns << "Skate Country"
columns << "Month"
columns << "Author"
columns << "Trump / Auction"
columns << "Tip to tip"
columns << "Beach"
columns << "Positive Aspects"

data.columns.eachWithIndex { def column, def index ->
    if (index > 0 && index < 18) {
        tableHeader += "<th>${columns[index]}</th>"
    }
}

tableHeader += "</tr>"

// -------- Body
def tableRows = []

data.each { def line ->
    def tableRow = "<tr>"
    for (fieldNum in 1..17) {
        def field = line.getAt fieldNum
        def cleanField = field.replace("&","&amp;")
        tableRow += "<td>${cleanField}</td>"
    }
    tableRow += "</tr>\n"
    tableRows << tableRow
}

def html = """
<html>
<head>
    <script type='text/javascript' src='https://code.jquery.com/jquery-1.11.3.min.js'></script>
    <script type='text/javascript' src='https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js'></script>

    <link rel="stylesheet" media="all" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" />

    <script>
        \$(document).ready( function() {
            \$('#surveyResults').DataTable();
        });
    </script>
</head>
<body>
<hr/>

<table id="surveyResults" class="display" cellspacing="0" width="100%">
    <thead>
    ${tableHeader}
    </thead>

    <tbody>
"""

tableRows.each { def tableRow ->
    html += tableRow
}

html += "</tbody></table></body></html>"

println html
