
var odbc_SQLUtils = function () {};

var SU = new odbc_StringUtils();
var C = new odbc_Constants();

odbc_SQLUtils.prototype.clause = function (field, operator, value) {
    return SU.q(field) + ' ' + operator + ' ' + SU.q(value); 
}

odbc_SQLUtils.prototype.whereClause = function (field, operator, value) {
    return '"' + SU.q(field) + ' ' + operator + ' ' + SU.q(value) + '"'; 
}

odbc_SQLUtils.prototype.buildTextWhereClause = function (qualifiers) {
    var whereClause = "";
    
    if (qualifiers[C.TEXT_QUALIFIER]) {
        whereClause = this.clause('Name','LIKE','%' + qualifiers[C.TEXT_QUALIFIER].trim() + '%'); 
    };

    return whereClause;
}

odbc_SQLUtils.prototype.buildExistsWhereClause = function (qualifiers) {
    var whereClause = "";
    var values = "";

    if (qualifiers[C.EXISTS_QUALIFIER]) {
        values = C.EXISTS_ID;
    } 
    if (qualifiers[C.DOES_NOT_EXIST_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.DOES_NOT_EXIST_ID);
    } 

    if (values) {
        whereClause = SU.q('Exists') + C.IN + SU.paren(values);
    }

    return whereClause;
}

odbc_SQLUtils.prototype.addOperatorAsNeeded = function (a, operator, b) {
    var result = b;

    if (a != "") {
        result = a + operator + b;
    }

    return result;
}

odbc_SQLUtils.prototype.buildTypeWhereClause = function (qualifiers) {
    var whereClause = "";
    var values = "";
 
    if (qualifiers[C.BUSINESS_QUALIFIER]) {
        values = C.BUSINESS_ID;
    } 
    if (qualifiers[C.NATURE_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.NATURE_ID);
    } 
    if (qualifiers[C.MONUMENT_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.MONUMENT_ID);
    } 
    if (qualifiers[C.ROAD_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.ROAD_ID);
    } 
    if (qualifiers[C.STRUCTURE_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.STRUCTURE_ID);
    } 
    if (qualifiers[C.WORSHIP_QUALIFIER]) {
        values = this.addOperatorAsNeeded(values, C.COMMA, C.WORSHIP_ID);
    }
    if (values) {
        whereClause = SU.q('Type') + C.IN + SU.paren(values);
    }

    return whereClause;
}

odbc_SQLUtils.prototype.buildWhereClause = function (qualifiers) {
    var existsWhereClause = this.buildExistsWhereClause(qualifiers);
    var typeWhereClause = this.buildTypeWhereClause(qualifiers);
    var textWhereClause = this.buildTextWhereClause(qualifiers);

    var whereClauseStr = this.addOperatorAsNeeded(existsWhereClause, C.AND, typeWhereClause);
    whereClauseStr = this.addOperatorAsNeeded(textWhereClause, C.AND, whereClauseStr);

    whereClauseStr = SU.dq(whereClauseStr);

    return whereClauseStr;
}

