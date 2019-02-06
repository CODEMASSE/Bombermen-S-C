package conrolleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Coordinate;
import model.Obstacle;
import model.Player;

public class GameServer  {
	
	
	private ArrayList <Obstacle> listObstacles;
	///private ArrayList <Player> players=new ArrayList<>();

	private Thread server;
	String activePlayer;
	boolean canConnect;
	ArrayList<Player> players;
	ArrayList<HandelClient> connectedPlayers;

	ServerSocket ss = null;
	private static int playerID = 0;


	private Coordinate [] startsCoordiante ={ new Coordinate(0,0),new Coordinate(0,100),new Coordinate(100,0),new Coordinate(100,100)};


	public GameServer ( ) {



		server = new Thread(new Server());
		server.start();

		//Player player =new Player(GameServer.this,startsCoordiante[0],"zebi" );
		//players.add(player);



	}


	public class Server implements Runnable{


		public Server(){
			canConnect = true;
			players = new ArrayList<Player>();
			connectedPlayers = new ArrayList<>();
			activePlayer = new String();

			try{
				ss = new ServerSocket(1234);
			}
			catch(IOException e){
				e.printStackTrace();
			}

		}

		public void run(){

			while(server != null)
			{
				HandelClient st;
				try{
					Thread.sleep(100);
					st = new HandelClient(ss.accept(), this);
					if(!canConnect)
						server.interrupt();
					connectedPlayers.add(st);
					Thread t = new Thread(st);
					t.start();
				}
				catch (IOException e) {
					System.out.println("Client accept connection failed!");
				}
				catch (InterruptedException e){
					server.interrupt();
				}
			}
		}





		public boolean gameRunning(){
			if (true)
				return true;
			else
				return false;
		}

		public void interruptServer(){
			canConnect = false;
			server.interrupt();
		}

		public boolean checkReady(){
			for(HandelClient p: connectedPlayers){
				if(!(p.getReady())){
					return false;
				}
			}
			activePlayer = players.get(0).getName();
			return true;
		}


	}

	public class HandelClient implements Runnable{

		private Socket socket = null;
		private Server server;
		private Thread client;
		private PrintWriter out = null;
		private BufferedReader in = null;
		private boolean continueServer;
		private boolean readyPlayer;
		private String name;




		public HandelClient(Socket socket, Server server) {
			this.server = server;
			this.socket = socket;
			readyPlayer = false;
			continueServer = true;
		}

		public void run() {

			try {
				try{
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
				}
				catch(IOException e){
					System.out.println("I/O Failed");
					e.printStackTrace();
					System.exit(-1);
				}

				while (continueServer) {
					try{
						processInput(in.readLine());

					}
					catch(IOException e){
						System.out.println("Could Not Read From Server");
						e.printStackTrace();
						System.exit(-1);
					}
					Thread.sleep(100);
					Thread.yield();
				}

			}
			catch (InterruptedException e) {
				continueServer = false;
				if(client != null){
					client.interrupt();
				}
			}
		}

		public void processInput(String theInput){



			if(theInput.equals("addPlayer"))
				addPlayer(startsCoordiante[3]);
			else if(theInput.equals("startGame"))
				startGame();
			else if(theInput.equals("ready"))
				playerReady();
			else if(theInput.equals("activePlayer"))
				getActivePlayer();
			else if(theInput.equals("getPlayers"))
				getAllPlayers();
			/*else if((theInput.equals("getIndex")))
				getIndex();
			else if((theInput.equals("whosReady")))
				ready();*/


		}

		public void startGame(){
			//activePlayer = players.get(turnIndex).getName();
		}

		public void getActivePlayer(){
			out.println(activePlayer);
		}

		public void addPlayer(Coordinate coordinate){
			try{

				Player newPlayer = new Player(GameServer.this,coordinate,in.readLine());
				System.out.println("Adding "+ newPlayer.getName());
				players.add(newPlayer);
				name = new String(newPlayer.getName());
			}
			catch(IOException e){
				e.printStackTrace();
			}

		}


		//envois des utilisateurs au clients
		public void getAllPlayers(){
			for(Player p: players){
				out.println(p.getName());
			}
			out.println("stop");
		}

		public void playerReady(){
			readyPlayer = !readyPlayer;
		}
		public boolean getReady(){
			return readyPlayer;
		}
		public void ready(){
			if(server.checkReady()){
				out.println("start");
				server.interruptServer();
			}
			else{
				out.println("no");
			}

		}


	}







	/*public void createPlayer (int x,int y) {
		
		
	}*/






	public ArrayList<Obstacle> getListObstacles() {
		return listObstacles;
	}




	public ArrayList<Player> getPlayers() {
		return players;
	}




	/*public static void main(String[] args) {
		GameServer gs = new GameServer();
	}*/





}
