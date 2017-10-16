import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class BoardGame extends Application {

	
	Random rnd = new Random();


	Player currentPlayer;


	ArrayList players = new ArrayList<Player>();

	
	int currentPlayerIndex=0;


	 Spinner snakecounter= new Spinner(2, 6, 1,1);

	
	 Spinner laddercounter= new Spinner(2, 6, 1,1);

	
	 Paint[] fills = new Paint[4];


	 Circle playerCircle = new Circle(15.0);

	 Label winnerLbl = new Label();

	
	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);

		Button rollDice = new Button("Roll Dice");
		
		
		HBox hbox = new HBox(5.0,rollDice);
		hbox.setAlignment(Pos.CENTER);


		VBox vbox = new VBox(5.0, grid,hbox);
		StackPane root = new StackPane();


		 
		Canvas canvas = new Canvas(500, 650);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setLineWidth(5);

		vbox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 500, 650);
		GameBoard.initialiseSquares();

		




		for(Integer i =1;i<101;i++){
			GridTiles sq = GameBoard.squares[i];
			grid.add(sq,sq.getGridX(),sq.getGridY());
			
		}



		playerCircle.setFill(new Color(0, 0, 0, 0));
		Button addPlayer = new Button("Add Player");

		addPlayer.setOnAction(e-> {



				if(Player.NumberOfPlayers<4){
					Player pl = new Player();
					players.add(pl);
					grid.add(pl,0,9);
					fills[Player.NumberOfPlayers-1]=pl.getFill();
					playerCircle.setFill(fills[0]);



			}
		});
		grid.setGridLinesVisible(true);

		root.getChildren().add(canvas);
		root.getChildren().add(vbox);

		
		Rectangle winRect = new Rectangle(500, 650);
		Rectangle winRect2 = new Rectangle(500, 650);

		

		hbox.getChildren().add(addPlayer);

		rollDice.setOnAction(e->{
				if(Player.NumberOfPlayers==0){

				}else{

					

					currentPlayer=(Player)players.get(currentPlayerIndex);
					currentPlayer.move( getSpinnerNum());
					currentPlayerIndex++;


					


					if(currentPlayerIndex>=Player.NumberOfPlayers)
						currentPlayerIndex-=Player.NumberOfPlayers;

					playerCircle.setFill(fills[currentPlayerIndex]);



					 
					if(Player.winnerNum>=0){
						winnerLbl.setText("Player "+Player.winnerNum+" wins!");

						winRect.setFill(fills[Player.winnerNum-1]);
						winRect2.setFill(fills[Player.winnerNum-1]);
						winnerLbl.setBorder(Border.EMPTY);
						root.getChildren().add(winRect);
						root.getChildren().add(winRect2);
						root.getChildren().add(winnerLbl);
					}
				}
			
		});

		 

		{
				GameBoard.snakes=3;
				GameBoard.ladders=3;
				gc.clearRect(0, 0, 500, 650);
				GameBoard.initialiseSnL();

				for(Integer i=1;i<101;i++){
					if(GameBoard.squares[i].getDestSquare()!=null){
						if(GameBoard.squares[i].getDestSquare().getSqNumber()<GameBoard.squares[i].getSqNumber())
							gc.setStroke(Color.RED);
						else if(GameBoard.squares[i].getDestSquare().getSqNumber()>GameBoard.squares[i].getSqNumber())
							gc.setStroke(Color.GREEN);
						gc.strokeLine((GameBoard.squares[i].getGridX())*50+25, (GameBoard.squares[i].getGridY())*50+25,
								(GameBoard.squares[i].getDestSquare().getGridX())*50+25,
								(GameBoard.squares[i].getDestSquare().getGridY())*50+25);

					}
				}
				primaryStage.setScene(scene);
				primaryStage.centerOnScreen();

			}
		 


		primaryStage.setTitle("Snakes and Ladders");
		 
		primaryStage.show();
	}

	
	public int getSpinnerNum() {
		// TODO Auto-generated method stub
		Integer num = rnd.nextInt(6)+1;
		return num;
	}


	public static void main(String[] args) {

		launch(args);
	}


}

