# netty-protobuf
A sample application to send an image with Netty 4.0.26 and Google Protocol Buffer 2.6.1. 

#Steps to run the application

1. Import the project in Eclipse
2. Download a .png image on your desktop or wherever you wish the file to be for the client. Edit the path in Client.java with this path. (File f = new File("PATH_TO_FILE_THAT_YOU_WANT_TO_SEND");)
3. Set a path where you want to save the file in ServerHandler.java on this line "FileOutputStream fileOuputStream = new FileOutputStream("PATH_TO_WHERE_YOU_WANT_TO_SAVE/"+i.getFilename()+".png");" 
4. Run Server.java first
5. Run Client.java



