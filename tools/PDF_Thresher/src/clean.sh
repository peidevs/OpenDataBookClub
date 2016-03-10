
cd ..
jar xf data.zip 
cd -

find ../data -name "*.txt" -exec rm -f {} \; 

find ../bin -name "*.class" -exec rm -f {} \; 
