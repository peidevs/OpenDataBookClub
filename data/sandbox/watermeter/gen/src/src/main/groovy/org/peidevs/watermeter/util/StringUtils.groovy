package org.peidevs.watermeter.util

class StringUtils {
    static final String C = "," 
    static final String Q = '"'

    def q(int i, def useComma = true) {
        def s = Integer.toString(i)
        q(s, useComma) 
    }

    def q(boolean b, def useComma = true) {
        def s = Boolean.toString(b)
        q(s, useComma) 
    }

    // q [quote] 
    def q(String s, def useComma = true) {
        def result = new StringBuilder()

        result.append(Q)
        if ((s != null) && (!s.isEmpty())) { 
            result.append(s) 
        }
        result.append(Q)
        if (useComma) { result.append(C) }

        result.toString()
    }
}
