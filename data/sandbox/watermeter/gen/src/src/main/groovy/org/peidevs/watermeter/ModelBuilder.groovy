
package org.peidevs.watermeter

import org.peidevs.watermeter.model.*

class ModelBuilder {
    def buildTeam(def configModel) {
        def teamMap = [:]

        teamMap["teamType"] = configModel.teamTypeId as TeamType
        teamMap["teamId"] = configModel.teamId
        teamMap["teamProfile"] = configModel.teamProfile

        def team = new Team(teamMap)
        team
    }

    def buildLocation(def configModel) {
        def locationMap = [:]

        locationMap["locationType"] = configModel.locationTypeId as LocationType
        locationMap["streetAddress"] = configModel.streetAddress
        locationMap["town"] = configModel.town 
        locationMap["postalCode"] = configModel.postalCode 
        locationMap["freeFormData"] = configModel.freeFormData 

        def location = new Location(locationMap)
        location
    }

    def convert(def configModel, def billingPeriods, def installationPeriods) {
        def models = []

        def map = [:]

        def usageList = configModel.usageList

        if (usageList.size() != billingPeriods.size()) {
            throw new IllegalArgumentException("mismatch # of params")
        }
        if (usageList.size() != installationPeriods.size()) {
            throw new IllegalArgumentException("mismatch # of params")
        }

        def numItems = billingPeriods.size()

        numItems.times { 
            map["team"] = buildTeam(configModel) 
            map["location"] = buildLocation(configModel) 
            map["billingPeriod"]  = billingPeriods[it]
            map["usageInLitres"] = usageList[it] 
            map["isAfterInstallation"] = installationPeriods[it]
 
            def model = new Model(map)
            models << model
        }

        models
    }
}
