
package org.peidevs.watermeter

import org.peidevs.watermeter.model.*

class Generator {
    def installationPeriods = [false, false, false, false, true, true, true, true]
    def billingPeriods = ["JAN 2016","APR 2016","JUL 2016","OCT 2016",
                          "JAN 2017","APR 2017","JUL 2017","OCT 2017"]

    def readConfigModels(def configFile) {
        def configList = Eval.me(new File(configFile).text)
        def models = configList.collect { new ConfigModel(it) }
        models
    }

    def generate(def configFile) {
        def lines = []

        def configModels = readConfigModels(configFile)        
        def modelBuilder = new ModelBuilder()
        lines << new Model().header

        configModels.each { configModel ->
            def models = modelBuilder.convert(configModel, billingPeriods, installationPeriods)
            models.each { model ->
                lines <<  model.toString() 
            }
        }
        lines
    }

    static void main(def args) {
        def generator = new Generator()
        def lines = generator.generate(args[0])
        def destFile = new File(args[1])
        destFile.withWriter { writer ->
            lines.each { line -> 
                writer.write(line + "\n")
            }
        }
    }
}
