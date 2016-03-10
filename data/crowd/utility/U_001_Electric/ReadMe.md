### Intro for the public

The Open Data Book Club is interested in crowd-sourcing a data set of electric utility bills.

Here is a [photo of an electric bill](https://www.dropbox.com/s/pjb3imnofs8soil/Example.Electric.Bill.png?dl=0) with the relevant fields.

#### The Data Set

As with all of our projects, the data set will be:

* published on the web, for free
* explicitly licensed for non-commercial or commercial use
* not a PDF (!) (that is, *machine-readable*, so we can use it)

#### Benefits

There are immediate benefits:

* generally, we are more likely to conserve if we measure and report
* the Open Data Book Club provides visualizations of the data

Other benefits are not obvious... We won't know until we make the data available!

#### Privacy

If you'd rather not provide your specific address, that's fine. We hope that you would provide a range on your street/road, such as "within 200-300 of Maple Ave".

If you'd rather not provide # of people in your dwelling, that's fine too. Entirely optional.

This project is **not** about shaming individuals. If you provide a specific address and your usage goes up, we understand. It happens to all of us, just as sometimes we have more waste in our black bins. We are interested in a **large data set**, not individual usage.

### Intro for planning

* It is unclear how specific people will be with their address. One option is to allow a block range (e.g. "within 2xx block of Elm Street"). Another option is to assign users a unique id. The CSV schema doesn't offer either of these yet.
* "Building Type" is a foreign-key into a reference CSV in this directory.

### Field Spec

| Field  | Mandatory? | Notes |
| ------------- | ------------- | ------------- |
| bill date  | Yes  | from Maritime E. bill "Consumption Period To" |
| kwh  | Yes  | from Maritime E. bill "kwh-used" |
| # of days  | Yes  | from Maritime E. bill "days use" | 
| rate  | Yes  | from Maritime E. bill "Energy Charge" | 
| # of adults  | No  | for comparison reporting | 
| # of children  | No  | for comparison reporting | 
| building type  | No  | for comparison reporting, see Building_Types.csv | 
| postal code  | Yes  |  | 
| province  | Yes  | defaults to "PE" | 
| county  | Yes  |  | 
| city  | No  | rural addresses may not have a town | 
| street  | Yes  |  | 
| number  | No  |  per privacy choice; could be in a range e.g. 100-200 block | 
| is specific address? | Yes | "true" if user provided specific address | 

