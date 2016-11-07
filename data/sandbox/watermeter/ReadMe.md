
# Intro

The file `exampledata.csv` is simply a strawman with mock data to
illustrate a potential layout for reporting water usage data.

# Column Definitions

* `Team Id`: simply a unique id for the household or business.
* `Team Type`: either HOME or BIZ. 
* `Team Profile` 
    * `H.a.t.c` where # of adults,teens,children are indicated by `a`,`t`,`c` respectively. e.g. `H.2.2.0`
    * `B.e.l` where `e` indicates # of employees; `l` indicates level of industrial usage. e.g. `B.8.0`
* `Usage In Litres`: water usage for the billing period
* `Location Type`
    * `L01` fully-specified address
    * `L02` unstructured address in `Location Misc` column
    * `L03` postal code only
    * `L04` town and postal code 
* `Street Address`, `Town`, `Postal Code`, `Location Misc` are populated depending on value in `Location Type`
* `Billing Period` contains first month of the quarter (e.g. JAN 2016)
* `Is After Installation` is true after conservation equipment has been installed. For the example data, this is assumed to be JAN 2017.

