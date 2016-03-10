
var odbc_StringUtils = function () {};

odbc_StringUtils.prototype.paren = function (s) {
    return "(" + s + ")";
}

odbc_StringUtils.prototype.q = function (s) {
    return "'" + s + "'";
}

odbc_StringUtils.prototype.dq = function (s) {
    return '"' + s + '"';
}

