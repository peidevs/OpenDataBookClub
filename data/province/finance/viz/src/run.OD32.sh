
groovy OD32_Transformer.groovy ../../OD32.csv ../../OD32.transform.csv

groovy OD32_Validator.groovy ../../OD32.csv ../../OD32.transform.csv

groovy OD32_Generator.groovy ../../OD32.csv template.OD32.step.chart.html ../OD32.step.chart.html

echo "Ready."
