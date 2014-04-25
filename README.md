# Databox Excel Service Example


This is a free, open-source, sample application demonstrating use of the Databox Java SDK.

The application reads from the excel file [cycling.xlsx](https://github.com/umajeric/databox-excel-service-example/blob/master/src/main/resources/cycling.xlsx) that can be found in the [resources](https://github.com/umajeric/databox-excel-service-example/tree/master/src/main/resources) folder of the example project.


Snippet from excel file:

|Date	|Total Time	|Moving Time	|Distance	|Distance Unit	|Average Speed	|Max Speed	|Speed Unit|
| -------:| ------:| ------:| ------:|:------ | ------:| ------:|:------|
|7/6/13		| 56	| 56	| 27.00	| km	| 28.93	| 34.00	| km/h |
|7/7/13		| 34	| 34	| 13	  | km	| 22.94	| 30	  | km/h |
|7/8/13		| 56	| 56	| 23  	| km	| 24.54	| 38  	| km/h |
|7/9/13		| 63	| 63	| 28	  | km	| 26.67	| 35  	| km/h |
|7/10/13	| 22	| 22	| 9	    | km	| 24.55	| 35	  | km/h |

For the purpose of this example we are reading 4 KPIs from the excel:
  * moving_time
  * distance
  * average_speed
  * max_speed

We read each row from (with the date component) from the excel file and add it to the data provider.


## Set up Jenkins as a build and cron job executor

You can do a cron scheduled push using Jenkins. Visit the [wiki page](https://github.com/umajeric/databox-excel-service-example/wiki/Setting-up-Jenkins-as-a-build-and-cron-job-executor) to see how to configure the cron job to push the data to the Databox service.
