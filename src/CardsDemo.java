import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

class CardsDemo extends JFrame {
    private Card cardBack, comp1;
    private static DeckOfCards deck;
    private JButton btnHit, btnCheck, btnShowCards, btnInstructions, btnShowComp, btnResults, btnPlayAgain, btnExit;
    private JLabel lblFirstUser, lblSecondUser, lblFirstComp, lblSecondComp, userTitleLbl, compTitleLbl;
    private int hit, userSum, compSum, compCount = 0;
    private JPanel userCards, panel, compCards, userTitle, compTitle;
    private ArrayList<Card> hitCards = new ArrayList<>(); //array list for the cards the user hits
    private ArrayList<JLabel> hitLabels = new ArrayList<>(); //array list for the labels that contain the hit cards
    private ArrayList<Card> compCard = new ArrayList<>(); //array list for the cards the computer obtains
    private ArrayList<JLabel> compLabels = new ArrayList<>(); //array list for the labels that contain the card
    ArrayList<Card> comp = new ArrayList<>(); //array list for all the user cards (used for score)
    ArrayList<Card> user = new ArrayList<>(); //array list for all the computer cards (used for score)

    public static void main(String[] args) {
        new CardsDemo();
    }

    public CardsDemo() {
        cardBack = new Card(); //creates a new card back object
        deck = new DeckOfCards(true); //creates a new shuffled deck object

        //Label for user's first card
        lblFirstUser = new JLabel();
        lblFirstUser.setIcon(cardBack.getImage()); //displays the back of the card
        lblFirstUser.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFirstUser.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblFirstUser.setPreferredSize(new Dimension(75, 100));

        //Label for user's second card
        lblSecondUser = new JLabel();
        lblSecondUser.setIcon(cardBack.getImage()); //displays the back of the card
        lblSecondUser.setHorizontalTextPosition(SwingConstants.CENTER);
        lblSecondUser.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblSecondUser.setPreferredSize(new Dimension(75, 100));

        //Label for computer's first card
        lblFirstComp = new JLabel();
        comp1 = deck.dealCard(); //deals a card
        lblFirstComp.setIcon(comp1.getImage()); //displays the card in the label
        lblFirstComp.setHorizontalTextPosition(SwingConstants.CENTER);
        lblFirstComp.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblFirstComp.setPreferredSize(new Dimension(75, 100));
        comp.add(comp1); //adds the card to the array list containing all the computer's cards
        compSum = getScore(comp); //gets the computer's score

        //Label for computer's second card
        lblSecondComp = new JLabel();
        lblSecondComp.setIcon(cardBack.getImage()); //displays the back of the card
        lblSecondComp.setHorizontalTextPosition(SwingConstants.CENTER);
        lblSecondComp.setVerticalTextPosition(SwingConstants.BOTTOM);
        lblSecondComp.setPreferredSize(new Dimension(75, 100));

        //main panel that contains all the components
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //panel that contains the title for user's cards
        userTitle = new JPanel();
        userTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        //title label for user's cards
        userTitleLbl = new JLabel("User's Cards:");
        userTitle.add(userTitleLbl);
        panel.add(userTitle);

        //panel that contains the user card labels
        userCards = new JPanel();
        userCards.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(userCards);
        userCards.add(lblFirstUser);
        userCards.add(lblSecondUser);

        //panel that contains computer's cards title
        compTitle = new JPanel();
        compTitle.setLayout(new FlowLayout(FlowLayout.LEFT));

        //title label for computer's cards
        compTitleLbl = new JLabel("Computer's Cards:\t\t\t\tScore = " + compSum);
        compTitle.add(compTitleLbl);
        panel.add(compTitle);

        //panel that contains the computer card labels
        compCards = new JPanel();
        compCards.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel.add(compCards);
        compCards.add(lblFirstComp);
        compCards.add(lblSecondComp);

        //button for user to get another card
        btnHit = new JButton("Hit");
        btnHit.setPreferredSize(new Dimension(75, 25));
        btnHit.setFocusable(false);
        btnHit.setVisible(false);
        //action listerner for when user clicks the hit button
        btnHit.addActionListener(a -> {
            hitCards.add(deck.dealCard()); //deals another card and adds it to arraylist
            hitLabels.add(new JLabel()); //creates a new JLabel in the arraylist containing card labels
            hitLabels.get(hit).setIcon(hitCards.get(hit).getImage()); //sets the label icon to the card image
            userCards.add(hitLabels.get(hit));
            user.add(hitCards.get(hit)); //adds the card to the arraylist containing all the user cards
            setPanel(panel); //revalidates the main panel
            userSum = getScore(user); //gets the score
            userTitleLbl.setText("User's Cards:\t\t\t\t\tScore = " + userSum); //displays the user score
            hit++; //increases the hitCards arraylist index by 1

            //if user's card sum goes over 21, the 'hit' and 'check' buttons disappears, and the button to show computer's cards appears
            if (userSum >= 21) {
                btnHit.setVisible(false);
                btnCheck.setVisible(false);
                btnShowComp.setVisible(true);
            }
        });

        //button to show the computer cards
        btnShowComp = new JButton("Show Computer Cards");
        btnShowComp.setPreferredSize(new Dimension(175, 25));
        btnShowComp.setFocusable(false);
        btnShowComp.setVisible(false);
        //action listener for when user clicks the button
        btnShowComp.addActionListener(a -> {
            Card comp2 = deck.dealCard(); //deals a new card
            lblSecondComp.setIcon(comp2.getImage()); //changes the icon of the computer's second card
            comp.add(comp2); //adds the card to the arraylist containing all the computer cards
            compSum = getScore(comp); //gets the computer score
            compTitleLbl.setText("Computer's Cards:\t\t\t\tScore = " + compSum); //displays the computer score

            //only runs is user has not busted
            if (userSum <= 21) {
                //loop runs if computer score is below user score, and computer sum is below 21
                while (compSum < userSum && compSum < 21) {
                    compCard.add(deck.dealCard()); //deals a new card in the computer cards arrayList
                    compLabels.add(new JLabel()); //adds the card to the arrayList containing the computer card labels
                    compLabels.get(compCount).setIcon(compCard.get(compCount).getImage()); //sets the label icon to the card image
                    comp.add(compCard.get(compCount)); //adds the card to the arraylist containing all the computer cards
                    compSum = getScore(comp); //gets the computer score
                    compTitleLbl.setText("Computer's Cards:\t\t\t\tScore = " + compSum); //displays the computer score
                    compCards.add(compLabels.get(compCount)); //adds the card label to its corresponding panel
                    setPanel(panel); //revalidates the main panel
                    compCount++;
                }
            }

            //once the computer cards have been dealt buttons disappear/appear
            btnShowComp.setVisible(false);
            btnResults.setVisible(true);
        });

        //button for the instructions
        btnInstructions = new JButton("Instructions");
        btnInstructions.setPreferredSize(new Dimension(115, 25));
        btnInstructions.setFocusable(false);
        //action listner for when user clicks the instructions button
        btnInstructions.addActionListener(a -> {
            //creates a new JFrame and initializes it for the instructions
            JFrame instructions = new JFrame();
            instructions.setTitle("Instructions");
            instructions.setSize(400, 150);
            instructions.setLocationRelativeTo(null);
            instructions.setResizable(false);
            instructions.setVisible(true);
            instructions.getContentPane().setLayout(new BoxLayout(instructions.getContentPane(), BoxLayout.Y_AXIS));

            //writes the instructions in labels and adds it to the JFrame
            instructions.getContentPane().add(new JLabel("1. Click 'Show Cards' to reveal user's cards"));
            instructions.getContentPane().add(new JLabel("2. Click 'Hit' to draw a card. Make sure to stay below 21."));
            instructions.getContentPane().add(new JLabel("3. Click 'Show Computer Cards' to reveal the computer's cards."));
            instructions.getContentPane().add(new JLabel("4. Click 'Results' to get the results of the game."));
            instructions.getContentPane().add(new JLabel("5. Highest score 21 or below wins!"));
        });

        //button to get the results of the game
        btnResults = new JButton("Results");
        btnResults.setPreferredSize(new Dimension(100, 25));
        btnResults.setFocusable(false);
        btnResults.setVisible(false);
        //action listener for when user clicks the results button
        btnResults.addActionListener(a -> {
            //based on user's score and computer's score, the corresponding results are displayed
            if (userSum == 21) {
                if (userSum != compSum) {
                    JOptionPane.showMessageDialog(null, "You got 21! Congratulations you win!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "You got the same score as the computer! It's a tie.", "Tie!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (userSum > 21) {
                JOptionPane.showMessageDialog(null, "You went over 21! Better luck next time.", "BUST!", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (compSum <= 21 && compSum > userSum){
                JOptionPane.showMessageDialog(null, "Computer wins! Better luck next time.", "You Lose!", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (userSum == compSum) {
                JOptionPane.showMessageDialog(null, "You got the same score as the computer! It's a tie.", "Tie!", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "You Win! Congratulations you win!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
            }

            btnResults.setVisible(false); //results button disappears
            btnPlayAgain.setVisible(true); //play again button appears
            btnExit.setVisible(true); //exit button appears
        });

        //button to play again
        btnPlayAgain = new JButton("Play Again");
        btnPlayAgain.setPreferredSize(new Dimension(100, 25));
        btnPlayAgain.setFocusable(false);
        btnPlayAgain.setVisible(false);
        //action listener for when user wants to play again
        btnPlayAgain.addActionListener(a -> {
            //reinitializes variables
            compSum = 0;
            userSum = 0;
            hit = 0;
            compCount = 0;

            //removes the labels from the panel
            for (int i = 0; i < hitLabels.size(); i++) {
                userCards.remove(hitLabels.get(i));
            }
            for (int i = 0; i < compLabels.size(); i++) {
                compCards.remove(compLabels.get(i));
            }

            //clears all the arraylists
            hitCards.clear();
            hitLabels.clear();
            compCard.clear();
            compLabels.clear();
            user.clear();
            comp.clear();

            //creates a new shuffled deck object
            deck = new DeckOfCards(true);

            //displays the back card images
            lblFirstUser.setIcon(cardBack.getImage());
            lblSecondUser.setIcon(cardBack.getImage());
            lblSecondComp.setIcon(cardBack.getImage());

            comp1 = deck.dealCard(); //deals computer's first card
            comp.add(comp1); //adds the card to the arraylist containing all the computer cards
            compSum = getScore(comp); //gets the computer score
            lblFirstComp.setIcon(comp1.getImage()); //adds the card image to the computer card label

            //titles
            userTitleLbl.setText("User's Cards:");
            compTitleLbl.setText("Computer's Cards:\t\t\t\tScore = " + compSum);

            btnInstructions.setVisible(true); //instructions button appears
            btnShowCards.setVisible(true); //show cards button appears
            btnPlayAgain.setVisible(false); //play again button disappears

            setPanel(panel); //revalidates the main panel
        });

        //exit button
        btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFocusable(false);
        btnExit.setVisible(true);
        //action listener for when user clicks exit
        btnExit.addActionListener(a -> {
            System.exit(0); //exits the application
        });

        //button to check
        btnCheck = new JButton("Check");
        btnCheck.setPreferredSize(new Dimension(75, 25));
        btnCheck.setFocusable(false);
        btnCheck.setVisible(false);
        //action listener for when user clicks check
        btnCheck.addActionListener(a -> {
            btnHit.setVisible(false); //hit button disappears
            btnCheck.setVisible(false); //check button disappears
            btnShowComp.setVisible(true); //show computer cards button appears
        });

        //button to show user cards
        btnShowCards = new JButton("Show Cards");
        btnShowCards.setPreferredSize(new Dimension(115, 25));
        btnShowCards.setFocusable(false);
        //action listener for when user clicks the button
        btnShowCards.addActionListener(a -> {
            Card userCard1 = deck.dealCard(); //deals card1
            Card userCard2 = deck.dealCard(); //deals card2
            Collections.addAll(user, userCard1, userCard2); //adds the cards to the arraylist containing all the user's cards
            userSum = getScore(user); //gets the user score
            userTitleLbl.setText("User's Cards:\t\t\t\t\tScore = " + userSum); //displays the user score

            //adds the card images to card labels
            lblFirstUser.setIcon(userCard1.getImage());
            lblSecondUser.setIcon(userCard2.getImage());

            //displays and removes buttons
            btnShowCards.setVisible(false);
            btnInstructions.setVisible(false);
            btnExit.setVisible(false);
            btnHit.setVisible(true);
            btnCheck.setVisible(true);
        });

        //panel that contains the buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

        //adds the individual buttons to the buttons panel
        buttons.add(btnInstructions);
        buttons.add(btnShowCards);
        buttons.add(btnHit);
        buttons.add(btnCheck);
        buttons.add(btnShowComp);
        buttons.add(btnResults);
        buttons.add(btnPlayAgain);
        buttons.add(btnExit);
        panel.add(buttons);

        //initializes the content pane
        setContentPane(panel);
        setTitle("Black Jack");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //method to revalidate the main panel
    public void setPanel(JPanel pnl) {
        setContentPane(pnl);
        getContentPane().validate();
    }

    //method to get score
    public int getScore (ArrayList<Card> crd) {
        ArrayList<Card> aces = new ArrayList<>(); //arraylist that contains all the aces
        int sum = 0; //variable that keeps track of score

        for (int i = 0; i < crd.size(); i++) {
            //if card is a face card, 10 is added to the score
            if (crd.get(i).getValue() >= 11 && crd.get(i).getValue() <= 13) {
                sum += 10;
            }
            //if the card is an ace, it is added to the ace arraylist
            else if (crd.get(i).getValue() == 14) {
                aces.add(crd.get(i));
            }
            //if the card is a number card, the value is added to the sum
            else {
                sum += crd.get(i).getValue();
            }
        }

        //for loop parses through the aces arraylist
        for (int i = 0; i < aces.size(); i++) {
            //if the score + 11 is greater than 21, ace value is 1
            if (sum + 11 > 21) {
                sum += 1;
            }
            //if the score + 11 is less than or equal to 21, ace value is 11
            else {
                sum += 11;
            }

        }

        return sum; //returns the sum
    }
}
