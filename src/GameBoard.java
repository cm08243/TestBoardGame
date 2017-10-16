 
import java.util.Random;
import javafx.geometry.Pos;


public class GameBoard {

	
	public static GridTiles[] squares=new GridTiles[101];

	
	public static int snakes=2;

	
	public static int ladders=2;

	
	static Random rnd = new Random();


	public static void initialiseSquares(){
		int k=1;
		for(int i =9; i>=0;i--){
			if(i%2!=0){
				for(int j=0; j<10;j++){
					GridTiles sq = new GridTiles(k,j,i);
					sq.setMinSize(50, 50);
					sq.setAlignment(Pos.CENTER);
					squares[k]=sq;
					k++;
				}
			}
			else
			{
				for(Integer j=9; j>=0;j--){
					GridTiles sq = new GridTiles(k,j,i);
					sq.setMinSize(50, 50);
					sq.setAlignment(Pos.CENTER);
					squares[k]=sq;
					k++;
				}
			}
		}
	}


	public static void initialiseSnL(){
		
		GridTiles tempSq;
		GridTiles tempDestSq;
		Integer ind;
		for(Integer i =0;i<GameBoard.snakes;i++){
			tempSq=GameBoard.squares[rnd.nextInt(93)+2];
			if(tempSq.getDestSquare()==null&&tempSq.getIsDestSquare()==Boolean.FALSE){

				do{
					ind=rnd.nextInt(93)+2;
				}while(ind>tempSq.getSqNumber());

				tempDestSq=GameBoard.squares[ind];

				if(tempDestSq.getIsDestSquare()==Boolean.TRUE||tempSq.getSqNumber()==tempDestSq.getSqNumber()
						||tempDestSq.getDestSquare()!=null){
					i--;
					continue;
				}
				else{
					tempDestSq.setIsDestSquare(Boolean.TRUE);
					tempSq.setDestSquare(tempDestSq);
				}
			}
			else{
				i--;
				continue;
			}
		}
		
		for(Integer i =0;i<GameBoard.ladders;i++){
			tempSq=GameBoard.squares[rnd.nextInt(93)+2];
			if(tempSq.getDestSquare()==null && tempSq.getIsDestSquare()==Boolean.FALSE){

				do{
					ind=rnd.nextInt(93)+2;
				}while(ind<tempSq.getSqNumber());
				tempDestSq=GameBoard.squares[ind];

				if(tempDestSq.getIsDestSquare()==Boolean.TRUE||tempSq.getSqNumber()==tempDestSq.getSqNumber()
						||tempDestSq.getDestSquare()!=null){
					i--;
					continue;
				}
				else{
					tempDestSq.setIsDestSquare(Boolean.TRUE);
					tempSq.setDestSquare(tempDestSq);
				}

			}
			else{
				i--;
				continue;
			}
		}
	}
}