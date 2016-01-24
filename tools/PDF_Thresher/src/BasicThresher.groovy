
// @Grab(group='org.apache.pdfbox', module='pdfbox', version='2.0.0-RC2')

import org.apache.pdfbox.pdmodel.*
import org.apache.pdfbox.text.*

def utils = new Utils()

def extractText = { def file ->
    def document = PDDocument.load(file)
    def thresher = new PDFTextStripper()
    def content = thresher.getText(document) 
    document.close()
    return content
}

// ----- main

try {
    utils.confirmBasicArgs(args)

    def folder = new File(args[0])

    folder.eachFile { def file ->
        if (utils.isPDF(file)) {
            def text = extractText(file)
            utils.writeTextFile(file, text)
        }
    }
} catch(Exception ex) {
    System.err.println "internal error! Contact us at opendatapei@gmail.com"
    System.err.println "ex is : " + ex.message
}
