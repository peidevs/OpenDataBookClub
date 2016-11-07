
package org.peidevs.watermeter.model

import org.peidevs.watermeter.util.*

class Location {
    LocationType locationType 
    String streetAddress
    String town
    String postalCode
    String freeFormData

    String toString() {
        def su = new StringUtils()
        def result = su.q(locationType.id) + su.q(streetAddress) + su.q(town) + su.q(postalCode) + su.q(freeFormData, false)
        result
    }
}

