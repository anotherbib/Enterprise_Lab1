//64050400
import java.io.*; 
import java.net.*;
import java.util.*; 
class TCPClient { 
    public static void main(String argv[]) throws Exception 
    { 
         int sum = 0;
         String input1, input2;
         Scanner inFromUser = null;
         Socket clientSocket = null;
         DataOutputStream outToServer = null;
         Scanner inFromServer = null;
        while (true) {
         try { 
            clientSocket = new Socket("localhost", 1667); 
            outToServer = 
               new DataOutputStream(clientSocket.getOutputStream()); 
			inFromServer = new Scanner(clientSocket.getInputStream());
            System.out.print("enter number 1 (to end just press enter): ");
		    inFromUser = new Scanner(System.in);
            input1 = inFromUser.nextLine();
            if(input1.isEmpty()){
                System.exit(0);
            }
            outToServer.writeBytes(input1 + '\n');

            System.out.print("enter number 2 (to end just press enter): ");
            input2 = inFromUser.nextLine();
            if(input2.isEmpty()){
                System.exit(0);
            }
            outToServer.writeBytes(input2 + '\n');
            sum =  Integer.parseInt(inFromServer.nextLine());
            System.out.println("The result is " + sum);

         }
         catch (IOException e) {
             System.out.println("Error occurred: Closing the connection");
             break;
         }
         finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            }
            catch (IOException e) {
               e.printStackTrace();
            }
          } 
        }
    } 
} 