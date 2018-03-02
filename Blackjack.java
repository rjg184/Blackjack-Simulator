

public class Blackjack {

	// This method calls the playGame method (then waits until the game finishes),
	// and then asks user if they want to play the game again (looped)
	public static void main(String[] args) {
		
		// Initializing boolean -> Play again?
		boolean playAgain;
		
		// Plays Blackjack game -> loops until user does not want to play anymore
		do {
			
			playGame();
			System.out.println();
			System.out.println("==============================================");
			System.out.println();
			System.out.println("Would you like to play again? (Y/N)");
			playAgain = IO.readBoolean();
			
			} while (playAgain);
		
		// Exit message for user -- end of replay game loop
		System.out.println();
		System.out.println("==============================================");
		System.out.println();
		System.out.print("Thanks for playing!");
		
		} // end of replay game method
	
	// This method simulates a simple game of Blackjack
	static void playGame() {
		
		// Blackjack Welcome Message
		System.out.println();
		System.out.println("------------[ BLACKJACK ]------------");
		System.out.println("          _______   _______ ");
		System.out.println("         |       | |       |");
		System.out.println("         |       | |       |");
		System.out.println("         |   A   | |   K   |");
		System.out.println("         |       | |       |");
		System.out.println("         |_______| |_______|");
		System.out.println();
		System.out.println("======================================");
		System.out.println();
		System.out.println("Requirements for advanced features in Blackjack: ");
		System.out.println();
		System.out.println("-> Insurance");
		System.out.println("Player cannot bet more than his/her original bet");
		System.out.println("Player must have enough money in balance for insurance bets");
		System.out.println();
		System.out.println("-> Doubling down");
		System.out.println("Player's balance must be greater than player's original bet");
		System.out.println();
		System.out.println("-> Splitting");
		System.out.println("Player's balance must be greater than player's original bet");
		System.out.println();
		System.out.println("======================================");
		System.out.println();
		
		// Number of Players -> Input from user
		System.out.println("How many players? (excluding dealer)?");
		int numPlayers = IO.readInt(); 
		
		// Error condition -> cannot have < 1 player(s)
		while (numPlayers < 1){
			IO.reportBadInput();
			System.out.println("You cannot have 0 or less players!");
			System.out.println("How many players? (excluding dealer)?");
			numPlayers = IO.readInt();
		}
		
		// Error condition -> should not have > 6 players
		while (numPlayers > 6){
			IO.reportBadInput();
			System.out.println("The max is 6 players!");
			System.out.println("How many players? (excluding dealer)?");
			numPlayers = IO.readInt();
		}
		
		// Array of Players -> puts user input into array
		Player[] players = new Player [numPlayers];
		
		// Variables that will be later passed into the player/dealer class 
		int playerScore = 0;
		int dealerScore = 0;
		int sScoreTot1 = 0;
		int sScoreTot2 = 0;
		
		numPlayers = numPlayers + 1; 
		
		// Beginning of Blackjack -> Basic inputs from user
			for(int i = 1; i < numPlayers; i++){
				
				System.out.println();
				System.out.println("- - - - - - - - - - - - - - - - ");
				System.out.println();
				System.out.println("Enter Player " + i + "'s Name: ");
				String name = IO.readString();
				System.out.println("Player " + i + " (" + name + ")" + "'s initial balance: ");
				double bank = 500;
				System.out.println("$" + bank);
				System.out.println("Player " + i + " (" + name + ")" + ", enter your bet: ");
				System.out.print("$");
				double bet = IO.readDouble();
				
				// Error condition 
				// 		-> Player has to bet something
				//		-> Player cannot bet more than he/she has
				while(bet > bank || bet < 1){
					IO.reportBadInput();
					if(bet > bank){
						System.out.println("You cannot bet more than you have!");
					}
					if(bet < 1){
						System.out.println("You have to bet something!");
					}
					System.out.println("Player " + i + " (" + name + ")" + ", enter your bet: ");
					System.out.print("$");
					bet = IO.readDouble();
				}
				
				// Sends info to Player constructor in Player class
				players[i-1] = new Player(name, bank, bet, playerScore, sScoreTot1, sScoreTot2);
			}
			
		// Dealer Information	
		System.out.println();
		System.out.println("- - - - - - - - - - - - - - - - ");
		System.out.println();
		String dealerName = "Dealer";
		System.out.println("Name: " + dealerName);
		System.out.println();
		System.out.println("- - - - - - - - - - - - - - - - ");
		
		// Sends info to Dealer constructor in Dealer class
		Dealer dealer = new Dealer(dealerName, dealerScore); 
				
		System.out.println();
		System.out.println("====================================================");
		System.out.println();
		
		// Shuffles deck before each game
		Deck deck = new Deck();
		deck.shuffle();

		// Initializing variables that will be used later (for splitting process)
		int cardFace1 = 0;  // variable that represents face value of card 1
		int cardFace2 = 0;  // variable that represents face value of card 2
		int sCardValue = 0; // card value that is passed into a splitted hand
		int sCV1 = 0;       // score for card value 1 (for hand 1 after splitting)
		int sCV2 = 0;		// score for card value 2 (for hand 2 after splitting)
		
		// Deals 2 cards to each player -> prints current scores
		for(int i = 0; i < players.length; i++){
			
			// Initializes and sets the score of a player's hand to 0
			int score = 0;
			players[i].setScore(score);
			String name2 = players[i].getName();
			
			// Prints player's names and numbers
			System.out.println("Player " + (i+1) + " (" + name2 + ")" + ":");
			System.out.println();
			
			// Gives each player 2 cards
			for(int j = 0; j < 2; j++){
				int score2 = players[i].getScore();
				Card ccard = deck.deal();
				
				// records face value of card 1
				// 		-> sets card 1's face value to hand 1 (for a split hand)
				if(j == 0){
					cardFace1 = ccard.getFace();
					sCV1 = ccard.getValue();
					players[i].setFace1(cardFace1);
				}
				
				// records face value of card 2
				// 		-> sets card 2's face value to hand 2 (for a split hand)
				if(j == 1){
					cardFace2 = ccard.getFace();
					sCV2 = ccard.getValue();
					players[i].setFace2(cardFace2);
				}
				
				// gets the card values for each card
				int cardValue = ccard.getValue();
				sCardValue = cardValue;
				
				// Determines value of Ace (based on current hand)
				if(cardValue == 1){
					if(score2 + (cardValue + 10) <= 21){
						cardValue = 11;
					}else {
						cardValue = 1;
					}
				}
				
				// sets cards to first 2 elements for each player's hand
				players[i].setHand(j, cardValue); 
				
				// Prints the full name of the cards
				// 		-> sets score for each player
				String fullCard = ccard.toString();
				System.out.println("      " + fullCard);
				score = cardValue + score2;
				players[i].setScore(score);
			}
			
			// Preparation if a player wants to split (if he/she has the same face values)
			if(cardFace1 == cardFace2){
				
				players[i].setFace1(cardFace1);
				players[i].setFace2(cardFace2);
				
				if(cardFace1 == 1 || cardFace1 == 11){
					sCV1 = 11;
				}
				
				if(cardFace2 == 1 || cardFace2 == 11){
					sCV2 = 11;
				}
				
				players[i].setsScore1(sCV1);
				players[i].setsScore2(sCV2);
				players[i].setsHand1(0, sCardValue);
				players[i].setsHand2(0, sCardValue);
			}

			// Printing each player's score
			System.out.println();
			System.out.println("Score = " + score);
			System.out.println();
			System.out.println("- - - - - - - - - - - - - -");
			System.out.println();
		}
		
		// Deals 2 cards to the dealer (1 face up, 1 face down)
		System.out.println(dealerName + ":");
		System.out.println();
		Card dcard1 = deck.deal();
		int cardValue1 = dcard1.getValue();
		
		// Determines value of Ace (based on current hand)
		if(cardValue1 == 1){
			if(dealerScore + (cardValue1+10) <= 21){
				cardValue1 = 11;
			}else {
				cardValue1 = 1;
			}
		}
		
		dealer.setHand(0, cardValue1);
		
		dealerScore = cardValue1;
		String fullCard1 = dcard1.toString();
		System.out.println("      " + fullCard1);
		Card dcard2 = deck.deal();
		int cardValue2 = dcard2.getValue();
		
		if(cardValue2 == 1){
			if(cardValue1 + (cardValue2+10) <= 21){
				cardValue2 = 11;
			}else {
				cardValue2 = 1;
			}
		}
		
		dealer.setHand(1, cardValue2);
		
		String fullCard2 = dcard2.toString();
		if(dcard2.isFaceDown() == true){
			System.out.println("      " + "A face down card");
		}
		dealerScore = cardValue1 + cardValue2;
		dealer.setScore(dealerScore);
		System.out.println();
		if(cardValue1 == 1 | cardValue1 == 11){
			System.out.println("Score: " + "1 or 11" + " + Card 2");
		}else {
			System.out.println("Score: " + cardValue1 + " + Card 2");
		}
		System.out.println();
		System.out.println("- - - - - - - - - - - - - -");
		System.out.println();
		
		// Declaring and initializing variables for later use
		double insuranceBet = 0;
		double oInsurance = 0;
		
		// Asks all players if they want to:
		// 		- Insurance bet
		//		- Split their hand
		//		- Double down
		//		- Hit or Stand
		for(int i = 0; i < players.length; i++){
			
			double initialBet = players[i].getBet();
			players[i].setOriginalBetResults(initialBet);
			
			int pCardCount = 2; // starts from 3rd element in player's hand array
			int pCardCount2 = 1; // starts from 2nd element in player's split hand array
			boolean isSplit = false; // passed to a method in player class later on
			
			// Prints out all of the player's information
			int score = players[i].getScore();
			System.out.println("========================================");
			System.out.println();
			System.out.println("              Player " + (i+1));
			System.out.println("               " + "[ " + players[i].getName() + " ]");
			System.out.println("         Current Score: " + players[i].getScore());
			System.out.println();
			if(players[i].getScore() == 21){
				System.out.println(" You have Blackjack!");
			}
			System.out.println();	
			
			System.out.println("-> Current bet: $" + players[i].getBet());
			System.out.println("-> You have a balance of $" + (500 - players[i].getBet()) + " remaining.");
			System.out.println();
			
			// Insurance bet
			// 		-> only if Dealer has a face-up Ace
			//		-> only if player has enough money
			//		-> insurance bet cannot be greater than original bet
			if(500 - players[i].getBet() > 0){
				if(cardValue1 == 1 | cardValue1 == 11){
					System.out.println("Would you like to bet on insurance?");
					boolean insurance = IO.readBoolean();
					
					if(insurance == true){
						System.out.println("How much would you like to bet? (max amount is your original bet");
						insuranceBet = IO.readDouble();
						
						// Error conditions
						while ((500 - players[i].getBet()) - insuranceBet < 0 || insuranceBet > players[i].getBet() || insuranceBet <= 0){
							IO.reportBadInput();
							if (insuranceBet <= 0){
								System.out.println("You have to bet some type of amount!");
							}else {
							System.out.println("You cannot bet more money than your balance or your original bet!");
							}
							System.out.println("How much would you like to bet? (max amount is your original bet");
							insuranceBet = IO.readDouble();
						}
						
						// Keeps player's fixed insurance bet throughout the game
						oInsurance = insuranceBet;
						
						// Corrects insurance bet amount based on whether the player was right or wrong
						// 		-> applied at the end of the game
						if(cardValue2 == 10){
							insuranceBet = 2*insuranceBet;
						}else{
							insuranceBet = -(insuranceBet);
						}
						
					}
				}
			}
			
			// Hint System -> based on probablility
			System.out.println("Would you like a hint?");
			boolean hint = IO.readBoolean();
			
			if(hint == true){
				
				int hintScore = players[i].getScore();
				
				// Probabilities obtained from:
				// 		->  http://www.blackjackage.com/odds-hand.php
				if(hintScore == 21)
					System.out.println("You have blackjack!");
				if(hintScore == 20) 
					System.out.println("There is about a 92 % chance that you will bust if you hit.");
				if(hintScore == 19)
					System.out.println("There is about a 85 % chance that you will bust if you hit.");
				if(hintScore == 18)
					System.out.println("There is about a 77 % chance that you will bust if you hit.");
				if(hintScore == 17)
					System.out.println("There is about a 69 % chance that you will bust if you hit.");
				if(hintScore == 16)
					System.out.println("There is about a 62 % chance that you will bust if you hit.");
				if(hintScore == 15)
					System.out.println("There is about a 58 % chance that you will bust if you hit.");
				if(hintScore == 14)
					System.out.println("There is about a 56 % chance that you will bust if you hit.");
				if(hintScore == 13)
					System.out.println("There is about a 39 % chance that you will bust if you hit.");
				if(hintScore == 12)
					System.out.println("There is about a 31 % chance that you will bust if you hit.");
				if(hintScore <= 11)
					System.out.println("There is about a 0 % chance that you will bust if you hit.");
			}

// SPLITTING
			
			// Splitting process - only:
			//		-> if player has same 2 face cards
			//		-> if player has enough money
			if(players[i].getFace1() == players[i].getFace2() && (500 - 2*players[i].getBet()) - oInsurance > 0){
				
				System.out.println("Would you like to split?");
				boolean splitting = IO.readBoolean();
				
				if(splitting == true){
					isSplit = true;
					players[i].setSplitting(isSplit);	
					
					for(int q = 0; q < 2; q++){
					
// Split -> 1st Hand
						if(q == 0){
							
							players[i].sHand1[1] = 0; // replaces 2nd card in sHand1 array
						
							// Prints out player's information and score
							System.out.println();
							System.out.println("     +++++++++ " + "Player " + (i+1) + " - HAND 1 +++++++++" );
							System.out.println();
							int getScoreForHand1 = players[i].getsScore1();
							System.out.println("            Current Score: " + getScoreForHand1);
							System.out.println();
						
							// Assigns the card value 11 in the array if an Ace 
							if(players[i].sHand1[0] == 1 | players[i].sHand1[0] == 11){
								players[i].sHand1[0] = 11;
							}
							
							// Doubling down
							//		False -> if no money or player says no
							//		True -> if player says yes
							System.out.println();
							boolean sDoubleDown1; 
							if((500 - 2*players[i].getBet()) - players[i].getBet() - oInsurance < 0 || players[i].getScore() == 21){
								sDoubleDown1 = false;
								players[i].setDDsHand1(false);
							}else {
								System.out.println("Would you like to double down?");
								sDoubleDown1 = IO.readBoolean();
							}
							
							if(sDoubleDown1 == true){
								
								players[i].setDDsHand1(true);
								
								double DDsBet1 =  2*initialBet;
								players[i].setBet(DDsBet1);
								
								int DDsScore1 = players[i].getsScore1();
								System.out.println("Prev score: " + DDsScore1);
								Card DDsCard1 = deck.deal();
								int DDsCardValue1 = DDsCard1.getValue();
								String fullCard = DDsCard1.toString();
								System.out.println("      " + "Drew a " + fullCard);
								
								if(DDsCardValue1 == 1){
									if(DDsScore1 + (DDsCardValue1 + 10) <= 21){
										DDsCardValue1 = 11;
									}else {
										DDsCardValue1 = 1;
									}
								}
								
								sScoreTot1 = DDsCardValue1 + DDsScore1;
								
								// Finds ace in hand -> changes value if necessary
								if(DDsScore1 + DDsCardValue1 > 21){
									for(int a = 0; a < players[i].getsHand1().length; a++){
										if(players[i].sHand1[a] == 11){
											players[i].sHand1[a] = 1;
											
											sScoreTot1 = ((DDsScore1 + DDsCardValue1) - 10);
											players[i].setsScore1(sScoreTot1);
										}
									}
								}
								
								// Adds card to player's hand
								players[i].setsHand1(pCardCount2, DDsCardValue1);
								
								// Prints player's score if <= 21
								if(sScoreTot1 <= 21){
									System.out.println("      " + players[i].getName() + "'s current score for hand 1: " + sScoreTot1);
								}
								
								players[i].setsScore1(sScoreTot1); // sets player's score
								
								// Player busts -> sets score to 0
								if(sScoreTot1 > 21){
									int sBustScore1 = 0;
									players[i].setsScore1(sBustScore1);
									System.out.println("      " + "Player " + (i+1) + "'s hand 1 has busted!");
									System.out.println();
								}
								
								// Player gets Blackjack (21)
								if(players[i].getsScore1() == 21){
									System.out.println("      " + "Player " + (i+1) + "'s hand 1 got Blackjack!");
									System.out.println();
								}
								
							} //end of doubling down if statement
							
							// Hit? (Y/N) 
							// 		-> Only if player does not double down
							// 		-> Asked for each hand for a player who split
							if(sDoubleDown1 == false){
							
								players[i].setDDsHand1(false);
								
								while(players[i].getsScore1() < 21){
									
									System.out.println();
									System.out.println("Hit? (Y/N)");
									boolean sHit1 = IO.readBoolean();
									
									if(sHit1 == true){
										
										// Player is dealt a card
										int sScore1 = players[i].getsScore1();
										Card sCard1 = deck.deal();
										int sCardValue1 = sCard1.getValue();
										String fullCard = sCard1.toString();
										System.out.println("      " + "Drew a " + fullCard);
										
										// Changes value of ace (if necessary) based on hand
										if(sCardValue1 == 1){
											if(sScore1 + (sCardValue1 + 10) <= 21){
												sCardValue1 = 11;
											}else {
												sCardValue1 = 1;
											}
										}
										
										// Adds card value to existing score
										sScoreTot1 = sCardValue1 + sScore1;
										
										// Finds ace in hand -> changes score if necessary
										if(sScore1 + sCardValue1 > 21){
											for(int a = 0; a < players[i].getsHand1().length; a++){
												if(players[i].sHand1[a] == 11){
													players[i].sHand1[a] = 1;
													
													sScoreTot1 = ((sScore1 + sCardValue1) - 10);
													players[i].setsScore1(sScoreTot1);
												}
											}
										}
										
										// Adds card to player's hand
										players[i].setsHand1(pCardCount2, sCardValue1);
										pCardCount2++;
										
										// Player busts -> sets score to 0
										if(sScoreTot1 > 21){
											int sBustScore1 = 0;
											players[i].setsScore1(sBustScore1);
											System.out.println("      " + "Player " + (i+1) + "'s hand 1 has busted!");
											System.out.println();
											break;
										}
										
										// Sets player's score
										players[i].setsScore1(sScoreTot1);
										
										// Player gets Blackjack (21)
										if(players[i].getsScore1() == 21){
											System.out.println("      " + "Player " + (i+1) + "'s hand 1 got Blackjack!");
											System.out.println();
											break;
										}
										
										// Prints player's score (if < 21)
										System.out.println("      " + players[i].getName() + "'s current score for hand 1: " + sScoreTot1);
										
									}else { // if player decides to stand
										System.out.println();
										break;
									}
									
								} //end of while loop
								
							} //end of else statement
							
						}//end of 1st if statement (q = 0)
						
// Split -> 2nd Hand
						if(q == 1){
							
							players[i].sHand2[1] = 0; // replaces 2nd card in sHand2 array
							
							// Prints out player's information and score
							System.out.println();
							System.out.println("     +++++++++ " + "Player " + (i+1) + " - HAND 1 +++++++++" );
							System.out.println();
							int getScoreForHand2 = players[i].getsScore2();
							System.out.println("            Current Score: " + getScoreForHand2);
							System.out.println();
							
							// Assigns the card value 11 in the array if an Ace 
							if(players[i].sHand2[0] == 1 | players[i].sHand2[0] == 11){
								players[i].sHand2[0] = 11;
							}
							
							// Doubling down
							//		False -> if no money or player says no
							//		True -> if player says yes
							System.out.println();
							boolean sDoubleDown2; 
							if((500 - players[i].getBet()) - 2*initialBet - oInsurance < 0 || players[i].getScore() == 21){
								sDoubleDown2 = false;
								players[i].setDDsHand2(false);
							}else {
								System.out.println("Would you like to double down?");
								sDoubleDown2 = IO.readBoolean();
							}
							
							if(sDoubleDown2 == true){
								
								players[i].setDDsHand2(true);
								
								double DDsBet2 = players[i].getBet() + initialBet;
								players[i].setBet(DDsBet2);
								
								int DDsScore2 = players[i].getsScore2();
								Card DDsCard2 = deck.deal();
								int DDsCardValue2 = DDsCard2.getValue();
								String fullCard = DDsCard2.toString();
								System.out.println("      " + "Drew a " + fullCard);
								
								if(DDsCardValue2 == 1){
									if(DDsScore2 + (DDsCardValue2 + 10) <= 21){
										DDsCardValue2 = 11;
									}else {
										DDsCardValue2 = 1;
									}
								}
								
								sScoreTot2 = DDsCardValue2 + DDsScore2;
								
								// Finds ace in hand -> changes value if necessary
								if(DDsScore2 + DDsCardValue2 > 21){
									for(int a = 0; a < players[i].getsHand2().length; a++){
										if(players[i].sHand2[a] == 11){
											players[i].sHand2[a] = 1;
											
											sScoreTot2 = ((DDsScore2 + DDsCardValue2) - 10);
											players[i].setsScore2(sScoreTot2);
										}
									}
								}
								
								// Adds card to player's hand
								players[i].setsHand2(pCardCount2, DDsCardValue2);
								
								// Prints player's score if <= 21
								if(sScoreTot2 <= 21){
									System.out.println("      " + players[i].getName() + "'s current score for hand 2: " + sScoreTot2);
								}
								
								players[i].setsScore2(sScoreTot2); // sets player's score
								
								// Player busts -> sets score to 0
								if(sScoreTot2 > 21){
									int sBustScore2 = 0;
									players[i].setsScore2(sBustScore2);
									System.out.println("      " + "Player " + (i+1) + "'s hand 2 has busted!");
									System.out.println();
								}
								
								// Player gets Blackjack (21)
								if(players[i].getsScore2() == 21){
									System.out.println("      " + "Player " + (i+1) + "'s hand 2 got Blackjack!");
									System.out.println();
								}
								
							} //end of doubling down if statement
							
							// Hit? (Y/N) 
							// 		-> Only if player does not double down
							// 		-> Ask each player if they want to hit or stay
							if(sDoubleDown2 == false){
							
								players[i].setDDsHand2(false);
								
								while(players[i].getsScore2() < 21){
									
									System.out.println();
									System.out.println("Hit? (Y/N)");
									boolean sHit2 = IO.readBoolean();
									
									if(sHit2 == true){
										
										// Player is dealt a card
										int sScore2 = players[i].getsScore2();
										Card sCard2 = deck.deal();
										int sCardValue2 = sCard2.getValue();
										String fullCard = sCard2.toString();
										System.out.println("      " + "Drew a " + fullCard);
										
										// Changes value of ace (if necessary) based on hand
										if(sCardValue2 == 1){
											if(sScore2 + (sCardValue2 + 10) <= 21){
												sCardValue2 = 11;
											}else {
												sCardValue2 = 1;
											}
										}
										
										// Adds card value to existing score
										sScoreTot2 = sCardValue2 + sScore2;
										
										// Finds ace in hand -> changes score if necessary
										if(sScore2 + sCardValue2 > 21){
											for(int a = 0; a < players[i].getsHand2().length; a++){
												if(players[i].sHand2[a] == 11){
													players[i].sHand2[a] = 1;
													
													sScoreTot1 = ((sScore2 + sCardValue2) - 10);
													players[i].setsScore2(sScoreTot2);
												}
											}
										}
										
										// Adds card to player's hand
										players[i].setsHand2(pCardCount2, sCardValue2);
										pCardCount2++;
										
										// Player busts -> sets score to 0
										if(sScoreTot2 > 21){
											int sBustScore2 = 0;
											players[i].setsScore2(sBustScore2);
											System.out.println("      " + "Player " + (i+1) + "'s hand 2 has busted!");
											System.out.println();
											break;
										}
										
										// Sets player's score
										players[i].setsScore2(sScoreTot2);
										
										// Player gets Blackjack (21)
										if(players[i].getsScore2() == 21){
											System.out.println("      " + "Player " + (i+1) + "'s hand 2 got Blackjack!");
											System.out.println();
											break;
										}
										
										// Prints player's score (if < 21)
										System.out.println("      " + players[i].getName() + "'s current score for hand 2: " + sScoreTot2);
										
									}else { // if player decides to stand
										System.out.println();
										break;
									} // end of else statement (player decides to stand)
									
								} //end of while loop
								
							} //end of else statement
							
						} //end of 2nd if statement (q = 1)
						
					} // end of for loop (plays each hand)
					
				} // end of splitting = true loop
				
				if (splitting == false){
					isSplit = false;
					players[i].setSplitting(isSplit);
				}
				
			}else {
				isSplit = false;
				players[i].setSplitting(isSplit);	
			}
			
// END OF SPLITTING
			
// DOUBLING DOWN
			
			// Doubling down
			//		False -> if no money or player says no
			//		True -> if player says yes
			System.out.println();
			boolean doubleDown; 
			if((500 - players[i].getBet()) - players[i].getBet() - oInsurance < 0 || players[i].getScore() == 21 || players[i].getSplitting() == true){
				doubleDown = false;
			}else {
				System.out.println("Would you like to double down?");
				doubleDown = IO.readBoolean();
			}
			
			if(doubleDown == true && isSplit == false){
				
				double ddBet = 2 * players[i].getBet();
				players[i].setBet(ddBet);
				
				int ddScore2 = players[i].getScore();
				Card ddCard = deck.deal();
				int ddCardValue = ddCard.getValue();
				String fullCard = ddCard.toString();
				System.out.println("      " + "Drew a " + fullCard);
				
				if(ddCardValue == 1){
					if(ddScore2 + (ddCardValue + 10) <= 21){
						ddCardValue = 11;
					}else {
						ddCardValue = 1;
					}
				}
				
				score = ddCardValue + ddScore2;
				
				// Finds ace in hand -> changes value if necessary
				if(ddScore2 + ddCardValue > 21){
					for(int a = 0; a < players[i].getHand().length; a++){
						if(players[i].hand[a] == 11){
							players[i].hand[a] = 1;
							
							score = ((ddScore2 + ddCardValue) - 10);
							players[i].setScore(score);
						}
					}
				}
				
				// Adds card to player's hand
				players[i].setHand(pCardCount, ddCardValue);
				
				// Prints player's score if <= 21
				if(score <= 21){
					System.out.println("      " + players[i].getName() + "'s current score: " + score);
				}
				
				players[i].setScore(score); // sets player's score
				
				// Player busts -> sets score to 0
				if(score > 21){
					int bustScore = 0;
					players[i].setScore(bustScore);
					System.out.println("      " + "Player " + (i+1) + " has busted!");
					System.out.println();
				}
				
				// Player gets Blackjack (21)
				if(players[i].getScore() == 21){
					System.out.println("      " + "Player " + (i+1) + " got Blackjack!");
					System.out.println();
				}
				
			} //end of doubling down if statement

// END OF DOUBLING DOWN
			
			// Hit? (Y/N) 
			// 		-> Only if player does not double down
			// 		-> Ask each player if they want to hit or stay
			if(doubleDown == false && isSplit == false){
			
				while(players[i].getScore() < 21){
					
					System.out.println();
					System.out.println("Hit? (Y/N)");
					boolean hit = IO.readBoolean();
					
					if(hit == true){
						
						// Player is dealt a card
						int score2 = players[i].getScore();
						Card ccard = deck.deal();
						int cardValue = ccard.getValue();
						String fullCard = ccard.toString();
						System.out.println("      " + "Drew a " + fullCard);
						
						// Changes value of ace (if necessary) based on hand
						if(cardValue == 1){
							if(score2 + (cardValue + 10) <= 21){
								cardValue = 11;
							}else {
								cardValue = 1;
							}
						}
						
						// Adds card value to existing score
						score = cardValue + score2;
						
						// Finds ace in hand -> changes score if necessary
						if(score2 + cardValue > 21){
							for(int a = 0; a < players[i].getHand().length; a++){
								if(players[i].hand[a] == 11){
									players[i].hand[a] = 1;
									
									score = ((score2 + cardValue) - 10);
									players[i].setScore(score);
								}
							}
						}
						
						// Adds card to player's hand
						players[i].setHand(pCardCount, cardValue);
						pCardCount++;
						
						// Player busts -> sets score to 0
						if(score > 21){
							int bustScore = 0;
							players[i].setScore(bustScore);
							System.out.println("      " + "Player " + (i+1) + " has busted!");
							System.out.println();
							break;
						}
						
						// Sets player's score
						players[i].setScore(score);
						
						// Player gets Blackjack (21)
						if(players[i].getScore() == 21){
							System.out.println("      " + "Player " + (i+1) + " got Blackjack!");
							System.out.println();
							break;
						}
						
						// Prints player's score (if < 21)
						System.out.println("      " + players[i].getName() + "'s current score: " + score);
						
					}else { // if player decides to stand
						System.out.println();
						break;
					}
				} //end of while loop
				
			} //end of else statement
			
			} //end of for loop
		
		// Dealer's original 2 cards -> both are now face up
		// 							 -> current score is printed
		System.out.println();
		System.out.println("========================================");
		System.out.println();
		System.out.println(dealerName + ":");
		System.out.println();
		System.out.println("      " + fullCard1);
		System.out.println("      " + fullCard2);
		dealerScore = cardValue1 + cardValue2;
		dealer.setScore(dealerScore);
		System.out.println();
		System.out.println("Score: " + dealerScore);
		System.out.println();
		
		int dCardCount = 2;
		
		// Dealer hits until he reaches or goes over 17
		while(dealerScore < 17){
			int dealerScore2 = dealer.getScore();
			Card dcard3 = deck.deal();
			int cardValue3 = dcard3.getValue();
			String fullCard = dcard3.toString();
			System.out.println("      " + "Drew a " + fullCard);
			
			if(cardValue3 == 1){
				if(dealerScore2 + (cardValue3 + 10) <= 21){
					cardValue3 = 11;
				}else {
					cardValue3 = 1;
				}
			}
			
			dealer.setHand(dCardCount, cardValue2);
			dCardCount++;
			
			dealerScore = cardValue3 + dealerScore2;
			
			if(dealerScore2 + cardValue3 > 21){
				for(int a = 0; a < dealer.getHand().length; a++){
					if(dealer.hand[a] == 11){
						dealer.hand[a] = 1;
						
						dealerScore = ((dealerScore2 + cardValue3) - 10);
						dealer.setScore(dealerScore);
					}
				}
			}
			
			
			dealer.setHand(dCardCount, cardValue3);
			dCardCount++;
			
			
			// Dealer busts -> S.O.P message, set score to 0, & stop hitting
			if(dealerScore > 21){
				int bustScore = 0;
				dealer.setScore(bustScore);
				System.out.println("      " + dealerName + " has busted!");
				System.out.println();
				break;
			}
			
			// Sets dealer's new score (original 2 cards + the new cards)
			dealer.setScore(dealerScore);
			
			// Dealer gets Blackjack (21) -> stop hitting
			if(dealer.getScore() == 21){
				System.out.println("      " + dealerName + " got Blackjack!");
				System.out.println();
				break;
			}
			
			// Prints dealer's new score 
			System.out.println("      " + dealerName + "'s current score: " + dealerScore);
		
		}
		
		System.out.println();
		System.out.println("- - - - - - - - - - - - - - - - - - - -");
		System.out.println();
		System.out.println("               RESULTS                 ");
		System.out.println();
		
		// Results -> goes through all players' total score + dealer's total score
		for(int z = 0; z < players.length; z++){
			
			// Player(s) beats dealer -> player(s) win(s)
			if(players[z].getSplitting() == false && players[z].getScore() > dealer.getScore()){
				System.out.println("Player " + (z+1) + " (" + players[z].getName() + ") " + "has won!");
				double newBank = players[z].getBank() + players[z].getBet() + insuranceBet;
				if(insuranceBet > 0){
					System.out.println("-> You have won the insurance bet.");
				}
				System.out.println("      " + players[z].getName() + "'s current bank value: $" + newBank);
				System.out.println();
			}
			
			// Dealer beats player(s) -> player(s) lose(s)
			if(players[z].getSplitting() == false && dealer.getScore() > players[z].getScore()){
				System.out.println("Player " + (z+1) + " (" + players[z].getName() + ") " + "has lost!");
				double newBank = players[z].getBank() - players[z].getBet() + insuranceBet;
				if(insuranceBet > 0){
					System.out.println("-> You have won the insurance bet.");
				}
				System.out.println("      " + players[z].getName() + "'s current bank value: $" + newBank);
				System.out.println();
			}
			
			// Dealer ties player(s) & both do not bust -> player(s) tie(s)
			if(players[z].getSplitting() == false && dealer.getScore() == players[z].getScore() && dealer.getScore() != 0 && players[z].getScore() != 0){
				System.out.println("Player " + (z+1) + " (" + players[z].getName() + ") " + "has tied with the Dealer!");
				double newBank = players[z].getBank() + insuranceBet;
				if(insuranceBet > 0){
					System.out.println("-> You have won the insurance bet.");
				}
				System.out.println("      " + players[z].getName() + "'s current bank value: $" + newBank);
				System.out.println();
			}
			
			// Both player(s) and dealer bust -> player(s) lose(s)
			if(players[z].getSplitting() == false && dealer.getScore() == 0 && players[z].getScore() == 0){
				System.out.println("Player " + (z+1) + " (" + players[z].getName() + ") " + "has lost!");
				double newBank = players[z].getBank() - players[z].getBet() + insuranceBet;
				if(insuranceBet > 0){
					System.out.println("-> You have won the insurance bet.");
				}
				System.out.println("      " + players[z].getName() + "'s current bank value: $" + newBank);
				System.out.println();
			}
			
			// Results for player(s) who decided to split
			if(players[z].getSplitting() == true){
				
				// Players' information
				System.out.println();
				System.out.println("Player " + (z+1) + " (" + players[z].getName() + ") " + ": ");
				
				// Initializing variables that will store bet values for each hand
				double newBank1 = 0;
				double newBank2 = 0;
				
				// Hand 1 -> Loses (player's hand 1 busts before dealer busts)
				if(dealer.getScore() == 0 && players[z].getsScore1() == 0){
					System.out.println("- Hand 1 has lost!");
					if(players[z].getDDsHand1() == true){
						newBank1 = -(2*players[z].getOriginalBetResults());
					}else {
						newBank1 = -(players[z].getOriginalBetResults());
					}
				}
				
				// Hand 1 -> Loses (player's hand 1 score loses against dealer)
				if(dealer.getScore() > players[z].getsScore1()){
					System.out.println("- Hand 1 has lost!");
					if(players[z].getDDsHand1() == true){
						newBank1 = -(2*players[z].getOriginalBetResults());
					}else {
						newBank1 = -(players[z].getOriginalBetResults());
					}
				}
				
				// Hand 1 -> Ties (player's hand 1 score ties the dealer's)
				if(dealer.getScore() == players[z].getsScore1() && dealer.getScore() != 0 && players[z].getsScore1() != 0){
					System.out.println("- Hand 1 has tied!");
					if(players[z].getDDsHand1() == true){
						newBank1 = 0;
					}else {
						newBank1 = 0;
					}
					
				}
				
				// Hand 1 -> Wins (player's hand 1 score beats the dealer's)
				if(players[z].getsScore1() > dealer.getScore()){
					System.out.println("- Hand 1 has won!");
					if(players[z].getDDsHand1() == true){
						newBank1 = 3*players[z].getOriginalBetResults();
					}else {
						newBank1 = 2*players[z].getOriginalBetResults();
					}
					
				}
				
				// Hand 2 -> Loses (player's hand 2 score busts before the dealer)
				if(dealer.getScore() == 0 && players[z].getsScore2() == 0){
					System.out.println("- Hand 2 has lost!");
					if(players[z].getDDsHand2() == true){
						newBank2 = -(2*players[z].getOriginalBetResults());
					}else {
						newBank2 = -(players[z].getOriginalBetResults());
					}
					
				}
				
				// Hand 2 -> Ties (player's hand 2 score ties the dealer's)
				if(dealer.getScore() == players[z].getsScore2() && dealer.getScore() != 0 && players[z].getsScore2() != 0){
					System.out.println("- Hand 2 has tied!");
					if(players[z].getDDsHand2() == true){
						newBank2 = 0;
					}else {
						newBank2 = 0;
					}
				}
				
				// Hand 2 -> Wins (player's hand 2 score beats the dealer's)
				if(players[z].getsScore2() > dealer.getScore()){
					System.out.println("- Hand 2 has won!");
					if(players[z].getDDsHand2() == true){
						newBank2 = 3*players[z].getOriginalBetResults();
					}else {
						newBank2 = 2*players[z].getOriginalBetResults();
					}
				}
				
				// Hand 2 -> Loses (player's hand 2 score loses against the dealer's)
				if(dealer.getScore() > players[z].getsScore2()){
					System.out.println("- Hand 2 has lost!");
					if(players[z].getDDsHand2() == true){
						newBank2 = -(2*players[z].getOriginalBetResults());
					}else {
						newBank2 = -(players[z].getOriginalBetResults());
					}
				}
				
				System.out.println();
				
				// If player wins insurance bet -> prints out a message
				if(insuranceBet > 0){
					System.out.println("-> You have won the insurance bet.");
				}
				
				// final bet amount (is taken away from initial balance and also 
				// 					 calculated with the insurance bet)
				double finalBank = newBank1 + newBank2;
				
				// Player's ending/final bank value
				System.out.println("      " + players[z].getName() + "'s current bank value: $" + ((500 + finalBank) + insuranceBet));
				System.out.println();
			
			}
		}
		
	}
}