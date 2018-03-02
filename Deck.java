

// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
	
	private Card[] deck;   
	
	
	// This constructor builds a deck of 52 cards.
	public Deck()
	{
		int pos = 0;
		
		deck = new Card[52];
		
		for (int i = 0; i < 4; i++){
			for (int j = 1; j < 14; j++){
				Card temp = new Card(i, j);
				deck[pos] = temp;
				pos++;
			}
		}
		
	}
	
	// This method takes the top card off the deck and returns it.
	public Card deal()
	{
		Card dealCard = null;
		
		for(int i = 0; i < deck.length; i++){
			if(deck[i] != null){
				dealCard = deck[i];
				deck[i] = null;
				break;
			}
		}
		
		return dealCard; 
	}
	
	// This method returns the number of cards remaining in the deck.
	public int getSize()
	{
		int deckSize = 0;
		
		for(int i = 0; i < deck.length; i++){
			if(deck[i] != null){
				deckSize++;
			}
		}
		
		return deckSize;
	}
	
	// This method returns true if there are no more cards to deal, false otherwise.
	public boolean isEmpty()
	{
		if(getSize() == 0){
			return true;
		}
		
		return false;
		
	}
	
	// This method puts the deck int some random order.
	public void shuffle()
	{	
		int index = 0;
		Card temp = null;
		
		for (int i = 0; i < deck.length; i++){
			index = (int)(Math.random()*(deck.length));
			temp = deck[index];
			deck[index] = deck[i];
			deck[i] = temp;
		}
	}
	
}

