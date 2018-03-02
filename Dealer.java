

public class Dealer {
	
	private String name;
	public int dealerScore;
	int[] hand;
	
	
	// This constructor creates the dealer
	public Dealer(String playerName, int score)
	{
		this.name = playerName;
		this.dealerScore = score;
		this.hand = new int[52];
	}
	
	// This method sets the dealer's score (after being passed an int)
	public void setScore(int dealerScore)
	{
		this.dealerScore = dealerScore;
	}
	
	// This method gets the dealer's score
	public int getScore()
	{
		return this.dealerScore;
	}
	
	// This method gets the dealer's hand
	public int[] getHand()
	{
		return this.hand;
	}
	
	// This method sets a value into an element in the array
	public void setHand(int z, int cardValue)
	{
		hand[z] = cardValue;
	}

}
