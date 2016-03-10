
@Grab(group='org.apache.pdfbox', module='pdfbox', version='2.0.0-RC2')

import org.apache.pdfbox.pdmodel.*
import org.apache.pdfbox.text.*

import java.awt.Rectangle

def x = 710
def y = 130
def width = 100
def height = 600

def extractText = { def file ->
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

def folder = new File(args[0])

folder.eachFile { def file ->
    def text = extractText(file)
    println text
}

