### **Wrangler Lambda**

A Lambda function to handle incoming requests containing Question Code and value having the following JSON format:

    {
     "period": "201211",
     "reference": "4990012",
     "survey": "066",
     "instance": "instanceid",
     "responses": [
       {
         "questionCode": "601",
         "response": "146"
       },
       {
         "questionCode": "602",
         "response": "150"
       },
       {
         "questionCode": "700",
         "response": "148"
       },
       {
         "questionCode": "603",
         "response": "602"
       },
       {
         "questionCode": "701",
         "response": "603"
       },
       {
         "questionCode": "605",
         "response": "604"
       },
       {
         "questionCode": "606",
         "response": "605"
       },
       {
         "questionCode": "607",
         "response": "606"
       },
       {
         "questionCode": "608",
         "response": "607"
       },
       {
         "questionCode": "609",
         "response": "608"
       }
     ]
    }

    Hard coded  Validation Config which has list of QuestionCodes

                Question Code 601
                Question Code 602


Looping through the Validation Config and prepare Json for calling 'takeon-vp-lambda-dev-run'

The Prepared Json would be similar to

    {
        "questionCode":"601",
        "primaryValue":"150"
    }

 Call the **takeon-vp-lambda-dev-run** Lambda for a each item in Validation Config and stored each ValidationResult in a list

 The Output from **takeon-vp-lambda**  would be similar to

 {
    "triggered": true,
     "valueFormula": "150 != \"\"",
     "questionCode": "601"
 }

 Amend the Question Code to each ValidationResult similar to

 [{"questionCode":"601","valueFormula":"150","triggered":"true"},{"questionCode":"602","valueFormula":"150","triggered":"true"}]

 Add Survey, period, reference and instance parameters to the result list and build the JSON in the following format

 {
   "type": "POST",
   "input": {
     "period": "4990012",
     "reference": "201211",
     "survey": "066",
     "instance": "instanceid",
     "validationName": "VP",
     "validationResults": [
       {
         "questionCode": "601",
         "valueFormula": "146",
         "triggered": "true"
       },
       {
         "questionCode": "602",
         "valueFormula": "150",
         "triggered": "true"
       }
     ]
   }
 }

 Make a call to **Response Persistence Lambda**

 Lambda Name: **response-persistence-lambda**

 Verify the following output

 {"response":"Persisted Successfully"}


### **Configuration**

The following configuration is required:


WRANGLER_NAME - the name of the wrangler lambda i.e VALUE PRESENT LAMBDA being called within the eu-west-2 region - takeon-vp-lambda-dev-run

WRANGLER_NAME_RESPONSE_PERSISTENCE - the name of the Response persistence Lambda - response-persistence-lambda

TOPIC_ARN - BPM stuff - - the arn of the topic that messages will be published to -  arn:aws:sns:eu-west-2:014669633018:Take-On-Validation-SNS


#### **Deployment**

https://eu-west-2.console.aws.amazon.com/lambda/home?region=eu-west-2#/functions/value-present-wrangler-lambda?tab=graph
