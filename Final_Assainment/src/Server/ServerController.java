package Server;

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
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Thread {


    public Label popupmassage;
    public TextArea inbox;
    public TextField typemsgox;
    public Button Send;
    public Button connected;
    public Button disconnect;

//variables

    BufferedReader Reader;
    BufferedWriter Writer;
    Socket connectsocket;
    ServerSocket SKT;

//for connect button
    public void ConnecttoClintAction(ActionEvent event)
    {
        try
        {
            popupmassage.setText("Waiting for client !");
            SKT=new ServerSocket(007);
            connectsocket=SKT.accept();
            popupmassage.setText("Client is online !!! ");
            System.out.println("Client connected");
            Reader=new BufferedReader(new InputStreamReader(connectsocket.getInputStream()));
            Writer=new BufferedWriter(new OutputStreamWriter(connectsocket.getOutputStream()));
            this.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


//for send button
    public void sendmsgAction(ActionEvent event)
    {
        try
        {
            String a = typemsgox.getText();
            Writer.write("Server:" + a + "\n");
            inbox.appendText("Server:"+a+"\n");
            Writer.flush();
            typemsgox.setText("");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public void run()
    {
        try{
            String B;
            while ((B=Reader.readLine())!=null)
            {
                if(B.equalsIgnoreCase("exit"))
                {
                    SKT.close();
                    connectsocket.close();
                    break;
                }
                inbox.appendText(B+"\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }




//for disconnect button
    @FXML
    void disconnectAction(ActionEvent event)
    {
        popupmassage.setText("OFF LINE");
        Platform.exit();

    }






}
