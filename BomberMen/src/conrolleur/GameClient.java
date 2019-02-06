package conrolleur;

import model.Player;
import view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class GameClient {

    private Socket socket  ;
    private PrintWriter out;
    private BufferedReader  in ;
    private Window view ;



    public GameClient(Window view){

        this.view=view;
    }




    public void run (){

        try {
            socket =new Socket(InetAddress.getLocalHost(),1234);
            out =new PrintWriter( socket.getOutputStream(),true);
            in =new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (UnknownHostException e){
        System.err.println("Don't know about host ");
        System.exit(1);


        }catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }

    }

    public void addPlayer(){
        out.println("addPlayer");
        out.println(view.players.get(0).getName());
    }

//afficher tout les utilisatueurs
    public void getPlayers()
    {
        out.println("getPlayers");
        System.out.println("Getting Players from Server");

        view.allPlayers = new ArrayList<Player>();

        try{
            while(true)
            {
                String name = new String(in.readLine());
                if(name.equals("stop"))
                    break;

                Player newPlayer = new Player(name);
                System.out.println(newPlayer);
                view.allPlayers.add(newPlayer);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(view.allPlayers);
    }
    public boolean readyCheck()
    {
        System.out.println("Checking if Players are ready");
        out.println("whosReady");
        try{
            String res = new String(in.readLine());
            System.out.println("Res: " + res);

            if(res.equals("start")){
                return true;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }
    public void isReady(){
        out.println("ready");
    }

    public String getActive(){
        System.out.println("Active Player");
        out.println("activePlayer");

        try{
            String res = new String(in.readLine());
            System.out.println("Server: " + res);

            return res;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return "";
    }



    public void closeSocket()
    {
        try{
            socket.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
