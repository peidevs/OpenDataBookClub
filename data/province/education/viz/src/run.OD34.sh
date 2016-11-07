
groovy OD34_Transformer.groovy ../../OD34.csv ../../OD34.transform.csv

groovy OD34_Validator.groovy ../../OD34.csv ../../OD34.transform.csv

groovy OD34_Generator.groovy ../../OD34.transform.csv template.OD34.bar.html ../OD34.bar.html
