# Group Assignment 1 - ICS372
DUE: February 11, 2019

## Introduction
Your assignment is to design and build a program for a transportation company that sends freight. Admins will need to be able to record shipments that arrive at the various warehouses.  It will be evaluated against the following requirements:
```
1.The software shall read a file that is in JSON format containing various shipment information.
2.The software shall support 4 different types of shipping methods in the input file: air freight, rail freight, ship freight, and truck freight.
3.The software shall read and store the shipment ID and gross weight for each entry and associate it with the specified warehouse ID.
4.The software shall read and store the associated metadata for each shipment.
5.The software shall support the following commands for each warehouse: add incoming shipment, enable freight receipt, and end freight receipt.
6.The software shall only allow adding adding incoming shipments to a warehouse that has enabled freight receipt.
7.The software shall keep records for a warehouse that has ended freight receipt, but will not allow new incoming shipments.
8.The software shall be able to export all shipments from a warehouse into a single JSON file.
9.The software shall show the list of received shipments for each warehouse.
```

Usage of the Java standard libraries or other libraries as part of your program is expected.  Make sure you include external jar files with your source when you submit it.  Documentation of the software is expected as well.  Be sure to include a class diagram of the program and a sequence diagram of the add incoming shipment operation.  I will attempt to build/execute your code as soon as possible after I receive it, so if you turn it in early and I can’t get it to work, you can re-submit (within reason).

## Format
As a group deliver the code as a git repo including all the necessary code to execute it including libraries (excluding the Java runtime).  If you used an IDE, please tell me which one you used and include necessary files for opening your project in that IDE.  Include class diagrams of the code you created.  As an individual, submit a 1 paragraph write up of what you contributed to the project and what you learned.  

## Submission
The individual portion can be submitted via D2L to the professor prior to or on the due date.The group portion can be submitted via email to the professor prior to or on the due date.

## Evaluation
This assignment will be evaluated/graded based on:
```
1) Functionality - Does the program meet the requirements?
2) Design - Were good design principles used in the construction of the program?
3) Style - Do you have comments and well written code?
4) Documentation - Do the diagrams indicate how the software is structured?
5) Self Evaluation - Did you contribute, did you learn anything?
```

## Example JSON input file:
```
{   
	"warehouse_contents":[
		{         
			"warehouse_id":"12513",  
			"shipment_method":"air",         
			"shipment_id":"48934j",  
			"weight": 84,  
			"receipt_date": 1515354694451      
		},      
		{         
			"warehouse_id":"15566",  
			"shipment_method":"truck",         
			"Shipment_id":"1adf4",  
			"weight":354,  
			"receipt_date": 1515354694451      
		},  
		{         
			"warehouse_id":"15566",  
			"shipment_method":"ship",         
			"shipment_id":"1a545",  
			"weight": 20.6,  
			"receipt_date": 1515354694451      
		},  
		{         
			"warehouse_id":"336558",  
			"shipment_method":"rail",         
			"shipment_id":"85545",  
			"weight": 760,  
			"receipt_date": 1515354694451      
		}   
	]
}
```