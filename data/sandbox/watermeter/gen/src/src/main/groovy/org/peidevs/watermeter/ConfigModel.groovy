
package org.peidevs.watermeter

class ConfigModel {
    final int NUM_PARAMS = 9

    def teamId
    def teamTypeId
    def teamProfile

    def locationTypeId
    def streetAddress
    def town
    def postalCode
    def freeFormData

    def usageList

    def ConfigModel(def paramList) {
        if (paramList.size() == NUM_PARAMS) {
            this.teamId = paramList[0]
            this.teamTypeId = paramList[1]
            this.teamProfile = paramList[2]
            this.locationTypeId = paramList[3]
            this.streetAddress = paramList[4]
            this.town = paramList[5]
            this.postalCode = paramList[6]
            this.freeFormData = paramList[7]
            this.usageList = paramList[8]
        } else {
            throw new IllegalArgumentException("wrong # of params")
        }
    }
}
