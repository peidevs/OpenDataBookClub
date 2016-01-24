
import org.apache.pdfbox.pdmodel.*
import org.apache.pdfbox.text.*

import java.awt.Rectangle

def extractText = { def file, def x, def y, def width, def height ->
    def document = PDDocument.load(file)
    def thresher = new PDFTextStripperByArea()
    thresher.setSortByPosition( true )

    def rect = new Rectangle(x, y, width, height)
    thresher.addRegion( "class1", rect )
    def firstPage = document.getPage(0)
    thresher.extractRegions( firstPage )

    def text =  thresher.getTextForRegion( "class1" )
    document.close()

    return text
}

// ----- main

try {
    def utils = new Utils()

    utils.confirmAreaArgs(args)

    def folder = new File(args[0])
    def x = args[1] as int
    def y = args[2] as int
    def width = args[3] as int
    def height = args[4] as int

    folder.eachFile { def file ->
        if (utils.isPDF(file)) {
            def text = extractText(file, x, y, width, height)
            utils.writeTextFile(file, text)
        }
    }
} catch(Exception ex) {
    System.err.println "internal error! Contact us at opendatapei@gmail.com"
    System.err.println "ex is : " + ex.message
}
