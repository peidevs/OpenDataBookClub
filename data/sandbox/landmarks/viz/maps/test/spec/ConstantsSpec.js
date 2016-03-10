
describe("Constants", function() {
    var CONST;

    beforeEach(function() {
        CONST = new odbc_Constants();
    });

    describe( "AND", function () {
        it("can use Constants", function() {
            expect(CONST.AND).toBe(" AND ");
        });
    });
});
