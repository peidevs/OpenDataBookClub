
describe("SQLUtils", function() {
    var sqlUtils;
    var C;
    var qualifiers;

    beforeEach(function() {
        sqlUtils = new odbc_SQLUtils();
        C = new odbc_Constants();
        qualifiers = {};
    });

    describe( "clause", function () {
        it("can build a clause", function() {
            var result = sqlUtils.clause("abc", C.AND, "def");
            
            expect(result).toBe("'abc'  AND  'def'");
        });
    });

    describe( "whereClause", function () {
        it("can build a whereClause", function() {
            var result = sqlUtils.whereClause("abc", C.AND, "def");
            
            expect(result).toBe("\"'abc'  AND  'def'\"");
        });
    });

    describe( "buildTextWhereClause", function () {
        it("can build a text-where-clause when needed", function() {
            qualifiers[C.TEXT_QUALIFIER] = "Van Halen";
            var result = sqlUtils.buildTextWhereClause(qualifiers);
            
            expect(result).toBe("'Name' LIKE '%Van Halen%'");
        });
    });

    describe( "buildTextWhereClause", function () {
        it("can build a trim text-where-clause  when needed", function() {
            qualifiers[C.TEXT_QUALIFIER] = "  Van Halen ";
            var result = sqlUtils.buildTextWhereClause(qualifiers);
            
            expect(result).toBe("'Name' LIKE '%Van Halen%'");
        });
    });

    describe( "buildTextWhereClause", function () {
        it("can stay silent when needed", function() {
            var result = sqlUtils.buildTextWhereClause(qualifiers);
            
            expect(result).toBe("");
        });
    });

    describe( "buildExistsWhereClause", function () {
        it("can build exists-where-clause for exists", function() {
            qualifiers[C.EXISTS_QUALIFIER] = true;
            var result = sqlUtils.buildExistsWhereClause(qualifiers);
            
            expect(result).toBe("'Exists' IN ('Y')");
        });
    });

    describe( "buildExistsWhereClause", function () {
        it("can build exists-where-clause for both exists and not", function() {
            qualifiers[C.EXISTS_QUALIFIER] = true;
            qualifiers[C.DOES_NOT_EXIST_QUALIFIER] = true;
            var result = sqlUtils.buildExistsWhereClause(qualifiers);
            
            expect(result).toBe("'Exists' IN ('Y','N')");
        });
    });

    describe( "buildExistsWhereClause", function () {
        it("can stay silent when needed", function() {
            var result = sqlUtils.buildExistsWhereClause(qualifiers);
            
            expect(result).toBe("");
        });
    });

    describe( "buildTypeWhereClause", function () {
        it("can build type-where-clause for one type", function() {
            qualifiers[C.BUSINESS_QUALIFIER] = true;
            var result = sqlUtils.buildTypeWhereClause(qualifiers);
            
            expect(result).toBe("'Type' IN (" + C.BUSINESS_ID + ")");
        });
    });

    describe( "buildTypeWhereClause", function () {
        it("can build type-where-clause for many types", function() {
            qualifiers[C.BUSINESS_QUALIFIER] = true;
            qualifiers[C.NATURE_QUALIFIER] = true;
            qualifiers[C.MONUMENT_QUALIFIER] = true;
            qualifiers[C.STRUCTURE_QUALIFIER] = true;
            qualifiers[C.WORSHIP_QUALIFIER] = true;

            var result = sqlUtils.buildTypeWhereClause(qualifiers);
            
            expect(result).toBe("'Type' IN (" + C.BUSINESS_ID + C.COMMA 
                                              + C.NATURE_ID + C.COMMA
                                              + C.MONUMENT_ID + C.COMMA
                                              + C.STRUCTURE_ID + C.COMMA
                                              + C.WORSHIP_ID 
                                              + ")");
        });
    });

    describe( "buildTypeWhereClause", function () {
        it("can stay silent when needed", function() {
            var result = sqlUtils.buildTypeWhereClause(qualifiers);
            
            expect(result).toBe("");
        });
    });

    describe( "buildWhereClause", function () {
        it("can build where-clause for many inputs", function() {
            qualifiers[C.TEXT_QUALIFIER] = "Van Halen";
            qualifiers[C.EXISTS_QUALIFIER] = true;
            qualifiers[C.BUSINESS_QUALIFIER] = true;
            qualifiers[C.NATURE_QUALIFIER] = true;

            var result = sqlUtils.buildWhereClause(qualifiers);
            
            var expectedText = "'Name' LIKE '%Van Halen%'";
            var expectedExists = "'Exists' IN ('Y')";
            var expectedType = "'Type' IN (" + C.BUSINESS_ID + C.COMMA + C.NATURE_ID + ")";
            var expected = "\"" + expectedText + C.AND + expectedExists + C.AND + expectedType + "\"";

            expect(result).toBe(expected);
        });
    });

    describe( "buildWhereClause", function () {
        it("can stay silent when needed", function() {
            var result = sqlUtils.buildWhereClause(qualifiers);
            
            expect(result).toBe("\"\"");
        });
    });

    describe( "addOperator", function () {
        it("can add an operator when needed", function() {
            var result = sqlUtils.addOperatorAsNeeded("abc", C.AND, "def");
            
            expect(result).toBe("abc AND def");
        });

        it("can stay silent when operator not needed", function() {
            var result = sqlUtils.addOperatorAsNeeded("", C.AND, "def");
            
            expect(result).toBe("def");
        });
    });
});
