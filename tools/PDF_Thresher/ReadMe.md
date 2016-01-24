
### Thresher 

* Thresher is a simple wrapper around [PDF Box](http://pdfbox.apache.org) to serve as an example.

### SetUp

* This tool requires java: open a terminal window and ensure that 'java -version' works.
* For examples, unzip data.zip in this folder.
* On Unix, in terminal window: <pre>. ./setvars.sh</pre> 
* On Windows, in cmd window: <pre>setvars.bat</pre> 

### Usage: Basic Thresher
* Basic Thresher extracts text from multiple PDF files in a folder, writing parallel TXT files
* <pre>runBasicThesher</pre> script takes 1 argument:
  * arg 1 - folder containing PDF files
* see <pre>eg_1_Basic_Education</pre> as an example

### Usage: Area Thresher
* Area Thresher builds on Basic Thresher by using a specified area region of the PDF
* <pre>runAreaThesher</pre>' script takes 5 arguments:
  * arg 1 - folder containing PDF files
  * arg 2 - x co-ordinate of area region
  * arg 3 - y co-ordinate of area region
  * arg 4 - width of area region
  * arg 5 - height of area region 
* see <pre>eg_2_Area_Agri</pre> as an example

