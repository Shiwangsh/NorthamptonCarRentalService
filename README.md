# NorthamptonCarRentalService
A vehicle rental system built using Java

1. Aims and Objective:
The key aim of this project is to develop, incorporate, and validate a stand-alone corporate vehicle rental system using object-oriented principles in java that can be accessed via a graphical user interface.
The focal aspect of the project or the Northampton Vehicle Hire System is to design and build a GUI that allow the staff of Northampton Vehicle Hire Company to execute the following tasks via the system:
-	Log onto the system with a username and password
-	Exit the system
-	As the fleet expands, add cars to the system
-	Remove vehicles from the system when the fleet decreases
-	Add new corporate customers to the system
-	Service all the customer accounts in the following ways:
a.	Corporate customers can rent vehicles from the fleet
b.	Return vehicles from the corporate customers to the fleet
c.	Query vehicles i.e., view all details of a specified vehicle
d.	Query the corporate customer accounts i.e., view details of a specified corporate customer and details of the vehicles (if any) they currently have hired out.
Beside the staffs, the system should also allow the corporate customers to do the following tasks within the system:
-	Log onto the system with their username and password
-	Exit the system
-	Inquire about his/her account, including his/her personal information and all of the vehicles he/she has out on loan.
-	View all the vehicles available for hire.

2. Screenshots:

Login:
 
![image](https://user-images.githubusercontent.com/61860925/181877047-0a784256-527a-46cc-b8ae-492eff69783f.png)


  2.1 ADMIN:
 
Add Vehicle menu (Admin): 
![image](https://user-images.githubusercontent.com/61860925/181877065-2299d941-82f6-4ee7-a595-fbc4ca18fd59.png)


Add Customer (Admin): 
  ![image](https://user-images.githubusercontent.com/61860925/181877073-43418df6-4782-45f3-a3a8-4bf1a76c7a26.png)



Hire Menu (Admin): 
![image](https://user-images.githubusercontent.com/61860925/181877082-c7b60029-0359-4643-9ac7-f889ebac9eea.png)


Query Menu (Admin):
![image](https://user-images.githubusercontent.com/61860925/181877089-0c66de12-eeac-4193-bbca-2004f936bea3.png)



  2.2 Customer:
 
Customer Dashboard (Customer): 
![image](https://user-images.githubusercontent.com/61860925/181877097-f9dd7660-4154-41c3-83c3-f0e42b142d8e.png)

 
 
Vehicle menu (Customer):
![image](https://user-images.githubusercontent.com/61860925/181877098-200537d5-23c0-495f-846c-336b4551a988.png)


3.List of bugs and weaknesses 
	The proposed final system does have few flaws or weaknesses that could not be accounted for in time. Listed below are all the bugs discovered in the current system:
S.N.	Bug	                                                                                                      Possible solution 
1.	When renting out a vehicle, the vehicle table does not get updated unless the “Hire Menu” tab is reopened.      	--
2.	Every menu for admin dashboard opens a new frame, which might be considered as a bad practice 	                Break up the code into classes that extend JPanel and add panels to a single JFrame.
3.	Currently multiple vehicles or customers can be added with the same ID. (No unique ID check available)	         When adding a new vehicle or customer check the binary file if there already exists a record with similar ID. 

