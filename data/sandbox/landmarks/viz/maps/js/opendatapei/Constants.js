
var odbc_Constants = function () {};

var stringUtils = new odbc_StringUtils();

odbc_Constants.prototype.EXISTS_ID = stringUtils.q('Y');
odbc_Constants.prototype.DOES_NOT_EXIST_ID = stringUtils.q('N');
odbc_Constants.prototype.BUSINESS_ID = stringUtils.q('001_B');
odbc_Constants.prototype.STRUCTURE_ID = stringUtils.q('002_S');
odbc_Constants.prototype.WORSHIP_ID = stringUtils.q('003_W');
odbc_Constants.prototype.NATURE_ID = stringUtils.q('004_N');
odbc_Constants.prototype.MONUMENT_ID = stringUtils.q('005_M');
odbc_Constants.prototype.ROAD_ID = stringUtils.q('006_R');

odbc_Constants.prototype.AND = " AND ";
odbc_Constants.prototype.OR = " OR ";
odbc_Constants.prototype.IN = " IN ";
odbc_Constants.prototype.COMMA = ",";

odbc_Constants.prototype.BUSINESS_QUALIFIER = "Business";
odbc_Constants.prototype.NATURE_QUALIFIER = "Nature";
odbc_Constants.prototype.MONUMENT_QUALIFIER = "Monument";
odbc_Constants.prototype.ROAD_QUALIFIER = "Road";
odbc_Constants.prototype.STRUCTURE_QUALIFIER = "Structure";
odbc_Constants.prototype.WORSHIP_QUALIFIER = "Worship";
odbc_Constants.prototype.EXISTS_QUALIFIER = "Exists";
odbc_Constants.prototype.DOES_NOT_EXIST_QUALIFIER = "Does Not Exist";
odbc_Constants.prototype.TEXT_QUALIFIER = "text";

