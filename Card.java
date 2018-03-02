

// This class represents one playing card.
public class Card
{
	// Card suits (provided for your convenience - use is optional)
	public static final int SPADES   = 0;
	public static final int HEARTS   = 1;
	public static final int CLUBS    = 2;
	public static final int DIAMONDS = 3;

	// Card faces (provided for your convenience - use is optional)
	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;


	// define fields here
	int suit;
	int face;
	private boolean faceUp;
	private Player players;
	
	// This constructor builds a card with the given suit and face, turned face down.
	public Card(int cardSuit, int cardFace)
	{
		this.suit = cardSuit;
		this.face = cardFace;
		this.faceUp = false;
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit()
	{
		return this.suit;
	}
	
	// This method retrieves the face (ace through king) of this card.
	public int getFace()
	{
		return this.face;
	}
	
	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue()
	{
		if (getFace() > 9){
			return 10;
		}
			
		return getFace();
	}
	
	public int getAceValue(int ace)
	{
		if (getFace() == 1 && getValue() + players.getScore() > 21){
			return 1;
		}else{	
			return 11;
		}
	}
	
	// This method decides if the card is face up
	public boolean isFaceUp()
	{
		return this.faceUp;
	}
	
	// This method decides if the card is face down
	public boolean isFaceDown()
	{
		return !(this.faceUp);
	}
	
	// This method records that the front of the card should be visible.
	public void turnFaceUp()
	{
		this.faceUp = true;
	}
	
	// This method records that only the back of the card should be visible.
	public void turnFaceDown()
	{
		this.faceUp = false;
	}
	
	// This method can translate a card's features into a card's full name
	public String toString()
	{
		String fullCardName = "";
		String suitName = "";
		
		// Suit Names
		if(this.suit == 0)
			suitName = "Spades";
		if(this.suit == 1)
			suitName = "Hearts";
		if(this.suit == 2)
			suitName = "Clubs";
		if(this.suit == 3)
			suitName = "Diamonds";
		
		// Face Names
		if(this.face == 1 || this.face > 10)
		{
			String faceName = "";
			if(this.face == 1)
				faceName = "Ace";
			if(this.face == 11)
				faceName = "Jack";
			if(this.face == 12)
				faceName = "Queen";
			if(this.face == 13)
				faceName = "King";
			
			// Card = Face + Suit -> Face Cards
			fullCardName = faceName + " of " + suitName;
		}
		
		// Card = Face + Suit -> Number Cards
		else if(this.face != 1 && this.face <= 10)
		{
			fullCardName = this.face + " of " + suitName;
		}
		
		return fullCardName;
	}
	
}
