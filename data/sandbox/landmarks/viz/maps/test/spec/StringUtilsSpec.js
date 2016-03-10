
describe("StringUtils", function() {
    var stringUtils;

    beforeEach(function() {
        stringUtils = new odbc_StringUtils();
    });

    describe( "paren", function () {
        it("can wrap in parens", function() {
            var result = stringUtils.paren("abc");
            
            expect(result).toBe("(abc)");
        });
    });
});
