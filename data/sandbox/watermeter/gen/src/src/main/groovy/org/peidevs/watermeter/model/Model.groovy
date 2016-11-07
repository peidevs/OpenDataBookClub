
package org.peidevs.watermeter.model

import org.peidevs.watermeter.util.*

/*
- fields:
    team id,team type,team profile,usage,location,location type,period,before_after

team id: unique
team type: household, business
team profile:
    H.a.t.c  # adults, # teens, # children
    B.e.i # employees, industrial usage
location: 
location type:
before_after:
period: Q3 2016

*/

class Model {
    Team team
    Location location
    int usageInLitres
    String billingPeriod
    boolean isAfterInstallation

    String getHeader() {
        def result = new StringBuilder()
        def su = new StringUtils()

        result.append(su.q("Team Id"))
        result.append(su.q("Team Type"))
        result.append(su.q("Team Profile"))
        result.append(su.q("Usage In Litres"))
        result.append(su.q("Location Type"))
        result.append(su.q("Street Address"))
        result.append(su.q("Town"))
        result.append(su.q("Postal Code"))
        result.append(su.q("Location Misc"))
        result.append(su.q("Billing Period"))
        result.append(su.q("Is After Install", false))

        result.toString()
    }

    String toString() {
        def su = new StringUtils()
        def teamStr = team.toString()
        def locationStr = location.toString()

        def result = "${teamStr}," + su.q(usageInLitres) + "${locationStr}," + su.q(billingPeriod) + su.q(isAfterInstallation, false)

        result
    }
}
