package Client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientController extends Thread{


    //variables

    Socket socket;
    BufferedReader read;
    BufferedWriter write;
    public Label popupmassage;
    public TextArea inbox;
    public TextField typingbox;
    public Button Send;
    public Button connect;
    public Button disconnect;

//connect button

    //popupmassage.setText("please click on connect button to be connect with client");
    public void connecttoserverAction(ActionEvent event)
    {

        try
        {
            popupmassage.setText("please click on connect button to be connect with client");
            socket=new Socket("localhost",007);
            popupmassage.setText("is connected to server :)");
            System.out.println("ONLINE");
            read =new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


    //send button
    public void sendmsgAction(ActionEvent event) {
        try {
            String s = typingbox.getText();
            write.write("Client :"+s+"\n");
            inbox.appendText("Client :"+s+"\n");
            write.flush();
            typingbox.setText("");
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void run()
    {
        try
        {
            String A;
            while ((A=read.readLine())!=null)
            {
                if(A.equalsIgnoreCase("check"))
                {
                    socket.close();
                    break;
                }
                inbox.appendText(A+"\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void DisconnectAction(ActionEvent event)
    {
        popupmassage.setText("OFF LINE");
        Platform.exit();

    }

}
//