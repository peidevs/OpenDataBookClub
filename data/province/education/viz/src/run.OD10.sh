
groovy OD10_Transformer.groovy ../../OD10.csv ../../OD10.transform.csv

groovy OD10_Validator.groovy ../../OD10.csv ../../OD10.transform.csv

groovy OD10_Generator.groovy ../../OD10.transform.csv ../OD10.step.group.html
