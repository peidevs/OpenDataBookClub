### Intro for the public

TODO

### Intro for planning

* It is unclear how specific people will be with their address. One option is to allow a block range (e.g. "within 2xx block of Elm Street"). Another option is to assign users a unique id. The CSV schema doesn't offer either of these yet.
* "Building Type" is a foreign-key into a reference CSV in this directory.

### Field Spec

| Field  | Mandatory? | Notes |
| ------------- | ------------- | ------------- |
| bill date  | Yes  | from Maritime E. bill |
| kwh  | Yes  | from Maritime E. bill | |
| # of days  | Yes  | from Maritime E. bill | 
| rate  | Yes  | from Maritime E. bill | 
| # of adults  | No  | for comparison reporting | 
| # of children  | No  | for comparison reporting | 
| building type  | No  | for comparison reporting, see Building_Types.csv | 
| postal code  | Yes  |  | 
| province  | Yes  | defaults to "PE" | 
| county  | Yes  |  | 
| city  | No  | rural addresses may not have a town | 
| street  | Yes  |  | 
| number  | No  |  per privacy choice | 
| is specific address? | Yes | "true" if user provided specific address | 

