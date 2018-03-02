

//This class represents one blackjack player (or the dealer)
public class Player
{
	// define fields.
	private String name;
	public int score;
	public int sScore1;
	public int sScore2;
	private boolean DDsHand1;
	private boolean DDsHand2;
	private boolean splitting;
	private double bet;
	private double OriginalBetResults;
	private double bank;
	private double insurance;
	int[] hand;
	int[] sHand1;
	int[] sHand2;
	int face1;
	int face2;
	
	
	// This constructor creates a player.
	public Player(String playerName, double bank, double bet, int score, int sScore1, int sScore2)
	{
		this.name = playerName;
		this.bank = bank;
		this.bet = bet;
		this.score = score;
		this.sScore1 = sScore1;
		this.sScore2 = sScore2;
		this.hand = new int[52];
		this.sHand1 = new int[52];
		this.sHand2 = new int[52];
	}
	
	// This method gets the player's hand
	public int[] getHand()
	{
		return this.hand;
	}
	
	// This method sets a value into an element in the player's hand
	public void setHand(int z, int cardValue)
	{
		this.hand[z] = cardValue;
	}
	
	// This method gets the index of a card in the player's hand
	public int getIndexOfCard(int index)
	{
		return this.hand[index]; 
	}
	
	// This method gets hand 1 of a player's splitted hand
	public int[] getsHand1()
	{
		return this.sHand1;
	}
	
	// This method sets a value into an element in the player's hand 1 array
	public void setsHand1(int z, int cardValue)
	{
		this.sHand1[z] = cardValue;
	}
	
	// This method gets the index of a card in the player's hand 1 array
	public int getIndexOfsCard1(int index)
	{
		return this.sHand1[index]; 
	}
	
	// This method gets hand 2 of a player's splitted hand
	public int[] getsHand2()
	{
		return this.sHand2;
	}
	
	// This method sets a value into an element in the player's hand 2 array
	public void setsHand2(int z, int cardValue)
	{
		this.sHand2[z] = cardValue;
	}
	
	// This method gets the index of a card in the player's hand 2 array
	public int getIndexOfsCard2(int index)
	{
		return this.sHand2[index]; 
	}
	
	// This method gets the player's name
	public String getName()
	{
		return this.name;
	}
	
	// This method gets the player's bank (balance)
	public double getBank()
	{
		return this.bank;
	}
	
	// This method gets the score for a player's hand
	public int getScore()
	{		
		return this.score;
	}
	
	// This method sets the score for a player's hand
	public void setScore(int score)
	{
		this.score = score;
	}
	
	// This method gets the score for a player's hand 1 (splitting)
	public int getsScore1()
	{		
		return this.sScore1;
	}
	
	// This method sets the score for a player's hand 1 (splitting)
	public void setsScore1(int sScore1)
	{
		this.sScore1 = sScore1;
	}
	
	// This method gets the score for a player's hand 2 (splitting)
	public int getsScore2()
	{		
		return this.sScore2;
	}
	
	// This method sets the score for a player's hand 2 (splitting)
	public void setsScore2(int sScore2)
	{
		this.sScore2 = sScore2;
	}
	
	// This method gets a boolean that determines whether or not a player
	// who split their hand is doubling down in their hand 1
	public boolean getDDsHand1()
	{		
		return this.DDsHand1;
	}
	
	// This method sets a boolean that determines whether or not a player
	// who split their hand is doubling down in their hand 1
	public void setDDsHand1(boolean DDsHand1)
	{
		this.DDsHand1 = DDsHand1;
	}
	
	// This method gets a boolean that determines whether or not a player
	// who split their hand is doubling down in their hand 2
	public boolean getDDsHand2()
	{		
		return this.DDsHand2;
	}
	
	// This method sets a boolean that determines whether or not a player
	// who split their hand is doubling down in their hand 1
	public void setDDsHand2(boolean DDsHand2)
	{
		this.DDsHand2 = DDsHand2;
	}
	
	// This method gets the bet a player made
	public double getBet()
	{
		return this.bet;
	}
	
	// This method sets the bet a player made
	public void setBet(double bet)
	{
		this.bet = bet;
	}
	
	// This method gets the original bet made by a player
	public double getOriginalBetResults()
	{
		return this.OriginalBetResults;
	}
	
	// This method sets the original bet made by a player
	public void setOriginalBetResults(double OriginalBetResults)
	{
		this.OriginalBetResults = OriginalBetResults;
	}
	
	// This method gets the amount of money a player bet for insurance
	public double getInsurance()
	{
		return this.insurance;
	}
	
	// This method sets the amount of money a player bet for insurance
	public void setInsurance(double insurance)
	{
		this.insurance = insurance;
	}
	
	// This method gets a boolean that determines whether or not a player 
	// is splitting their hand
	public boolean getSplitting()
	{		
		return this.splitting;
	}
	
	// This method sets a boolean that determines whether or not a player 
	// is splitting their hand
	public void setSplitting(boolean splitting)
	{
		this.splitting = splitting;
	}

	// This method gets the face value of card 1
	public int getFace1()
	{
		return this.face1;
	}
	
	// This method sets the face value of card 1
	public void setFace1(int face1)
	{
		this.face1 = face1;
	}
	
	// This method gets the face value of card 2
	public int getFace2()
	{
		return this.face2;
	}
	
	// This method sets the face value of card 2
	public void setFace2(int face2)
	{
		this.face2 = face2;
	}
	
}