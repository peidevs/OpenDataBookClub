
### Thresher 

* Thresher is a simple wrapper around [PDF Box](http://pdfbox.apache.org) to serve as an example.

### SetUp

* This tool requires java: open a terminal window and ensure that 'java -version' works.
* For examples, unzip data.zip in this folder.
* On Unix, in terminal window: <pre>. ./setvars.sh</pre> 
* On Windows, in cmd window: <pre>setvars.bat</pre> 

### Usage: Basic Thresher
* Basic Thresher extracts text from multiple PDF files in a folder, writing parallel TXT files
* <pre>runBasicThesher DIR</pre> 
  * DIR - folder containing PDF files
* see eg_1_Basic_Education as an example

### Usage: Area Thresher
* Area Thresher builds on Basic Thresher by using a specified area region of the PDF
* <pre>runAreaThesher DIR X Y WIDTH HEIGHT</pre>
  * DIR - folder containing PDF files
  * X - x co-ordinate of area region
  * Y - y co-ordinate of area region
  * WIDTH - width of area region
  * HEIGHT - height of area region 
* see eg_2_Area_Agri as an example

