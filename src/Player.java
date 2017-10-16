
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;


public class Player extends Circle{
	
	public static Integer NumberOfPlayers=0;

	
	private Integer PlayerID;


	private GridTiles currentSquare=GameBoard.squares[1];

	
	private static Color[] colors={new Color(1, 0, 0, 0.6),new Color(0, 1, 0, 0.6),new Color(0, 0, 1, 0.6),new Color(1, 1, 0, 0.6)};

	static Integer winnerNum=-1;

	 
	public Player(){
		super(20.0,colors[Player.NumberOfPlayers]);
		this.centerXProperty().setValue(-5);
		this.PlayerID=Player.NumberOfPlayers+1;
		Player.NumberOfPlayers++;
	}

	 
	public GridTiles getCurrentSquare(){
		return this.currentSquare;
	}
 
	public void setCurrentSquare(GridTiles sq){

		this.currentSquare=sq;
	}

	
	public Integer getPlayerID(){

		return this.PlayerID;
	}
 
	 
	public void move(Integer diceNum){
		if(getCurrentSquare().getSqNumber()+diceNum<=100){
			Path path = new Path();

			path.getElements().add(new MoveTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
			setCurrentSquare(GameBoard.squares[getCurrentSquare().getSqNumber()+diceNum]);

			path.getElements().add(new LineTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(1));
			pathTransition.setPath(path);
			pathTransition.setNode(this);
			pathTransition.play();

			if(getCurrentSquare().getSqNumber()==100){
				Player.winnerNum=this.getPlayerID();
			}
		}
		if(getCurrentSquare().getDestSquare()!=null){
			Path path = new Path();
			path.getElements().add(new MoveTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));

			setCurrentSquare(getCurrentSquare().getDestSquare());
			path.getElements().add(new LineTo(getCurrentSquare().getGridX()*50,-(9-getCurrentSquare().getGridY())*50));
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.seconds(1));
			pathTransition.setPath(path);
			pathTransition.setNode(this);
			pathTransition.play();
		}
	}
}