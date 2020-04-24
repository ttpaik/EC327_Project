package game;

public class Board {
	private int height;
	private int width;
	private String[][] slots;
	int cx =0;
	int co =0;
	
	public Board(int h, int w) {
		this.height = h;
		this.width = w;
		this.slots = new String[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				this.slots[i][j] = " ";
			}
		}
	}
	
	public String getBoard() {
		String s = "";
		
		for (int i= 0; i < height; i++) {
			s += "|";
			
			for (int j = 0; j < width; j++) {
				s += this.slots[i][j] + "|";
			}
			s += '\n';
		}
		
		return s;
	}
	
	public void add_checker(String checker, int col) {
		assert (col >=0 && col < this.width);
		int row = 0;
		while (row < this.height && this.slots[row][col] == " ") {
			row += 1;
			
		}
		this.slots[row-1][col] = checker;
		if (checker == "X" || checker == "x")
			cx+=1; 
		else if( checker == "O" || checker == "o")
			co+=1;
	}
	
	public void direct_add(String checker, int col, int row) {
		while (row < this.height && col < this.width && this.slots[row][col] == " ") {
		this.slots[row][col] = checker;
		}
		if (checker == "X" || checker == "x")
			cx+=1; 
		else if( checker == "O" || checker == "o")
			co+=1;
	}
	
	public void reset () {
		Board new_board = new Board(this.height, this.width);
		this.slots = new_board.slots;
	}
	
	public boolean can_add (int col) {
		if (col < 0) 
			return false;
		else if (col > this.width -1)
			return false;
		else if (this.slots[0][col] != " ")
			return false;
		else 
			return true;
	}
	
	public boolean is_full () {
		for (int i = 0; i < this.width; i++) {
			if (this.can_add(i) == true) {
				return false;
			}
		} 
		return true;
	}
	
	public void remove_checker(int col) {
		int row = 0;
		
		while (row < this.height-1 && this.slots[row][col] == " ") {
			row += 1;
		}
		this.slots[row][col] = " ";
	}
	
	
	public boolean horizontal_win(String checker) {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.slots[i][j] == checker &&
					this.slots[i][j+1] == checker &&
					this.slots[i][j+2] == checker &&
					this.slots[i][j+3] == checker &&
					this.slots[i][j+4] == checker)
					return true;
			}
		}
		return false;
	}
	
	public boolean vertical_win(String checker) {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.slots[i][j] == checker &&
					this.slots[i+1][j] == checker &&
					this.slots[i+2][j] == checker &&
					this.slots[i+3][j] == checker &&
					this.slots[i+4][j] == checker)
					return true;
			}
		}
		return false;
	}
	
	public boolean posdiag_win(String checker) {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.slots[i][j] == checker &&
					this.slots[i+1][j+1] == checker &&
					this.slots[i+2][j+2] == checker &&
					this.slots[i+3][j+3] == checker &&
					this.slots[i+4][j+4] == checker)
					return true;
			}
		}
		return false;
	}
	
	public boolean negdiag_win(String checker) {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.slots[i][j] == checker &&
					this.slots[i-1][j-1] == checker &&
					this.slots[i-2][j-2] == checker &&
					this.slots[i-3][j-3] == checker &&
					this.slots[i-4][j-4] == checker)
					return true;
			}
		}
		return false;
	}
	
	
	public void othello_end () {
		if (cx > co) {
			System.out.println("Game Over, X wins");
		}
		else if (co > cx) {
			System.out.println("Game Over, O wins");
		} 
		else 
			System.out.println("Draw");
	}
	
}

	

