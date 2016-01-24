
class Utils {
    def PDF = ".PDF"
    def TXT = ".txt"

    def hasExtension = { def file, def ext ->
        def result = false

        if (file.name.size() >= 4) {
            result = file.name[-4..-1].equalsIgnoreCase(ext)
        }

        return result 
    }

    def isPDF = { def file ->
        hasExtension(file, PDF)
    }

    def writeTextFile = { def file, def text ->
        def textFilename = file.absolutePath[0..-5] + TXT
        def textFile = new File(textFilename)
        textFile.withWriter { it.write(text) }

        def outputPath = textFile.absolutePath 

        def matcher = (outputPath =~ /.*PDF_Thresher\/(.*)/)
        if (matcher.matches()) { outputPath = matcher[0][1] }

        println "writing: ${outputPath}"
    }

    def confirmPDFFolder = { def folder ->
        def containsPDFs = false

        if (folder.exists() && folder.isDirectory()) { 
            folder.eachFile { def file -> 
                if (hasExtension(file,PDF)) { containsPDFs = true }
            }       
        }
 
        return containsPDFs
    }

    def confirmBasicArgs = {  def args ->
        def doShowUsage = false
        def message = ""

        if (args.length >= 1) {
            def folder = new File(args[0])

            if (! confirmPDFFolder(folder)) {
                message = "NO PDFS FOUND IN ${folder.absolutePath}"
                doShowUsage = true
            }    
        }

        if (doShowUsage) {
            System.err.println "-------------------"
            System.err.println "${message}"
            System.err.println "-------------------"
            System.err.println "./runBasicThresher.sh DIR"
            System.err.println "   DIR - folder containing target PDF files"
            System.exit(-1)
        }
    }

    def isValidNumber = { def x ->
        def result = false

        try {
            int i = x as Integer

            if (i >= 0) { result = true }
        } catch(Exception ex) {
        }

        return result
    }

    def confirmAreaArgs = {  def args ->
        def doShowUsage = false
        def message = ""

        if (args.length >= 5) {
            def folder = new File(args[0])

            if (! confirmPDFFolder(folder)) {
                message = "NO PDFS FOUND IN ${folder.absolutePath}"
                doShowUsage = true
            }    

            args[1..4].each { def arg ->
                if(! isValidNumber(arg)) {
                    message = "ILLEGAL PARAMETER for one of x y width height: ${arg}"
                    doShowUsage = true
                } 
            }
        } else {
            doShowUsage = true
        }

        if (doShowUsage) {
            System.err.println "-------------------"
            System.err.println "${message}"
            System.err.println "-------------------"
            System.err.println "./runAreaThresher.sh DIR"
            System.err.println "   DIR - folder containing target PDF files"
            System.err.println "   x - co-ordinate for area region"
            System.err.println "   y - co-ordinate for area region"
            System.err.println "   width - width of area region"
            System.err.println "   height - height of area region"
            System.exit(-1)
        }
    }
}
