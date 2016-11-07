
package org.peidevs.watermeter.model

import org.peidevs.watermeter.util.*

class Team {
    TeamType teamType
    String teamId
    String teamProfile

    String toString() {
        def su = new StringUtils()
        def result = "${su.q(teamId)}${su.q(teamType.id)}${su.q(teamProfile,false)}"
        result
    }
}
