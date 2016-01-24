
### Thresher 

* Thresher is a simple wrapper around [PDF Box](http://pdfbox.apache.org) to serve as an example.

### SetUp

* This tool requires java: open a terminal window and ensure that 'java -version' works.
* In a command window, run either setvars.sh or setvar.bat as appropriate

### Usage: Basic Thresher
* Basic Thresher extracts text from multiple PDF files in a folder, writing parallel TXT files
* 'runBasicThesher' script takes 1 argument
* arg 1 - folder containing PDF files
* see eg_1_Basic_Education as an example

### Usage: Area Thresher
* Area Thresher builds on Basic Thresher by using a specified area region of the PDF
* 'runAreaThesher' script takes 5 arguments
* arg 1 - folder containing PDF files
* x - x co-ordinate of area region
* y - y co-ordinate of area region
* width - width of area region
* height - height of area region 
* see eg_2_Area_Agri as an example

