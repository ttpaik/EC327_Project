package game;

public class Play {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board test = new Board(10, 10);
	
	
		test.direct_add("O",6, 5);
		test.direct_add("O",5, 4);
		test.direct_add("O",4, 3);
		test.direct_add("O",3, 2);
		test.direct_add("O",2, 1);

		System.out.println(test.getBoard());
		System.out.println(test.is_full());
		if (test.posdiag_win("O") == true)
			System.out.println("O wins");
		
		}
		
	}
