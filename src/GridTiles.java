import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GridTiles extends Label{
	
	private Integer SqNumber;
	private Integer GridX;

	
	private Integer GridY;


	private GridTiles DestSquare=null;

	private Boolean isDestSquare=false;

	
	public GridTiles(int num, int x,int y){
		super();
		this.SqNumber=num;
		this.fontProperty().set(Font.font("Calibri", FontWeight.BOLD, 32));
		this.GridX=x;
		this.GridY=y;
	}

	public Integer getGridX(){
		return this.GridX;
	}
	
	public Integer getGridY(){
		return this.GridY;
	}
	
	public Integer getSqNumber(){
		return this.SqNumber;
	}
	
	public void setIsDestSquare(Boolean b){
		this.isDestSquare=b;
	}
	
	public Boolean getIsDestSquare(){
		return this.isDestSquare;
	}
	
	public void setDestSquare(GridTiles sq){
		this.DestSquare=sq;
	}
	
	public GridTiles getDestSquare(){
		return this.DestSquare;
	}
}