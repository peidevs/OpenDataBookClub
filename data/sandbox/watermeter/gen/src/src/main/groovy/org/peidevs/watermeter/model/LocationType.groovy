
package org.peidevs.watermeter.model

enum LocationType {
    L01(id: "L01", desc: "fully qualified"),
    L02(id: "L02", desc: "unstructured"),
    L03(id: "L03", desc: "postal code only"),
    L04(id: "L04", desc: "town, postal code")

    def id
    def desc
}

