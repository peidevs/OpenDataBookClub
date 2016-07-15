
groovy OD31_Transformer.groovy ../../OD31.csv ../../OD31.transform.csv

groovy OD31_Validator.groovy ../../OD31.csv ../../OD31.transform.csv

groovy OD31_Generator.groovy ../../OD31.csv template.OD31.step.chart.html ../OD31.step.chart.html

echo "Ready."
