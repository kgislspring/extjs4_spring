
Current version of Source contains  2 Projects,

  1.EXTJS4FileUpload_Rest 
  2.EXTJS4FileUpload


** 'EXTJS4FileUpload_Rest' contains the Webservices used for file Uploading.

** 'EXTJS4FileUpload' to invoke the webservice which acts as a Webservice client.


**  In 'EXTJS4FileUpload' project,Webservice call has been handled in FileUploadDAOImpl  Class.
 
**  Below annotations has been handled in x class of EXTJS4FileUpload_Rest project
   @Path("/send/{fileName}/{fileSize}")



Basic Steps to create and run a project:


Step 1 : Create a table as mentioned in DBscript.txt.

Step 2 : Create a Dyanamic web Project in Eclipse and import the src  EXTJS4FileUpload in File--> Import option

Step 3 : Download the Driver for Sybase and add it in Lib Folder

Step 4 : Change the DB Settings in dispatcher-servlet.xml

Step 5 : If any issue occurs, Please change the java Class path of the project as per below steps
               
          1.select the Project name and Right Click 
          2.Select the Build Path and Configure Build Path
          3.click the Library Tab and select JRE System Library
          4.Click the Edit Button and change the execution environment

	            