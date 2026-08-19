# Tasks_For_New_Employees
### TASK 1 (2/8/2026)
#### create new customer without using , verion and authorize it 
#### create new user by copying "supervisor" user and paste to new user and also authorize it 
#### Create new version from Account table to be number of authorize "0"

### TASK 2 (3/8/2026)
#### Create a customer record with sector “1001” and fill your name in name fields english and arabic language.
#### Create two versions on account table for example : version 1 “ACCOUNT,MBSC.INPUTT” for input and Version 2 “ACCOUNT,MBSC.AUTH” for authorization.
#### Create an enquiry list unauthorized records and add buttons auth, delete and amend in it,enquiry fields : account id, shortname, category,customer number and customername “in this field you will use follow and link on customer table to get customer name “.
#### Create a composite screen , add your enquiry in the top .
#### 1 - auth and delete buttons open version 2 “ACCOUNT,MBSC.AUTH”
#### 2 - amend button open version 1 “ACCOUNT,MBSC.INPUTT”

### TASK 3 (9/8/2026)
#### Enquiry on Account table
#### Fields:
- Id
- Account title
- Category
- Working balance
- Customer Id
- Sector

#### Selection criteria:
- Id
- Account title
- Category

#### Fixed Selection
- Category range from 1001 1999

### TASK 4 (19/8/2026)
*===============================================================================
TABLE 1 "MBSC.TRAIN."EMPLOYEE NAME"
*===================
Requirement fields:
*===================
DESCRIPTION 	: lenght :- 50  , Type :- ANY     , Multi language
TENOR  		: lenght :- 3   , Type :- Numeric
FREQUENCY 	: lenght :- 3   , Type :- Numeric
RATE  		: lenght :- 6   , Type :- Numeric
INT.RATE 	: lenght :- 8   , Type :- Numeric
CATEGORY 	: lenght :- 8   , Type :- Numeric , linked to table  "CATEGORY"
CURRENCY        : lenght :- 3 	, Type :- ANY     , linked to table  "CURRENCY"
DENOMINATIONS   : lenght :- 34  , Type :- Numeric 
CUSTOMER.ID     : lenght :- 8   , Type :- Numeric , linked to table "CUSTOMER"
BASIC.KEY  	: lenght :- 8   , Type :- Numeric
INT.SPREAD      : lenght :- 8   , Type :- Numeric
BASIC.KEY  	: lenght :- 8   , Type :- Numeric
FIXED           : Options:- YES, NO
ACCUMLATED  	: Options:- YES, NO
FLOATING  	: Options:- YES, NO
MIXED.TYPE	: Options:- YES, NO
WRITE.FLAG      : Options:- YES, NO


10 RESERVED 
*===============================================================================
TABLE 2 "MBSC.CONC."EMPLOYEE NAME"
*===================
Requirement fields:
*===================
FLAG            : lenght :- 3 , Type :- ANY
*===============================================================================
ID ROUTINE :
*===========

ID Should be XXX-YY-BBbb

			XXX: Numbers
			YY : Numbers
			BB:NUMBER
			bb : letters only

			YY NOT GREATER THAN BB

ELSE "INVALID ID FORMAT"
			
*===============================================================================	
CHECK RECORD ROUTINE :
*===================== 

		TENOR field will be filled automatic with XXX
		FREQUENCY will be filled automatic with YY
		-->PROD.TYPE will be filled automatic  :XXXYYBBbb without "-" in id
		 

		if bb equal MX , MIXED.TYPE field should be equal yes
*===============================================================================
VALIDATION RTN:
*===============
			
		floating yes ,and then below mention fields must be mandotry
			BASIC.KEY
			INT.SPREAD
			
		ACCUMLATED  yes , ,and then below mention fields must be mandotry

			RATE  
			INT.RATE

ELSE "(Field name), Should be mandatory"
*===============================================================================
Input ROUTINE
*=============

Check if the CUSTOMER.ID field sector in CUSTOMER table equal 1001 

ELSE "Customer Should be indvidual"
*===============================================================================
AUTHORISATION ROUTINE
*===================

Check If WRITE.FLAG field equal "YES" 

write YES on FLAG field in TABLE 2
