# Hospital_Database

_**This project was made for a Database class during my degree program. The project entails building a database based with a very simple java program to access the information. Questions were given, plus I added additional prompts and features to better organize the informaiton in the database. The simple java program is used by selecting numbers for each givne menu. The project wasn't the interface, it was the database after all!**_

This program and README were made using IntelliJ IDE in java, as well as MySQL Workbench. A local host connection will need to be created in MySQL Workbench to conntect to IntelliJ IDE. 

1) Download the repositoy from Github. 

2) Import the project into your IDE of choice. IntelliJ was used for this project. The executable program file is in the src file.

3) Connecting the Database - 
	We will need to connect the java program to the MySQL Workbench database. 
	a) In IntelliJ, click on the DB Navigator menu, then Database Browser.
	b) Click on the New Connection icon, then MySQL.
	c) It was ask to connect to your local host. After putting in the username and password (for example, Username and Password is root), click test connection to ensure        your connection is good. Click Apply and OK. You should now see the database under Schema.
	d) Now, we need to add a MySQL driver to the project. Click on the Project table in IntelliJ, right click the project name (database-main), click Open Module Settings,      click Libraries, and add a Library From Maven.
	e) Search mysql-connector, and choose the latest driver. Click Apply and then OK.

_**Now that the database is connected to IntelliJ, we are able to run the program!**_

4) Click src file, then click the java file "HospitalMenu".

5) In the console you will be given 4 options in a menu. Enter your selected menu option below the printed menu in the console.

6) Repeat for the following menu. Some menu options will prompt for information. You will be given the correct format, as well as some examples of valid responses that are saved in the database to make use of the program easier for grading purposes.
