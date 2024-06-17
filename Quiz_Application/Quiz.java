import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz implements ActionListener{      //including actionListeners for handling button click events.

    String[] questions = {
                                "What is the size of Boolean Variable?",
                                "Which company created Java?",
                                "Which year was Java created?",
                                "Identify the return type of a method that doesn't return any value.",
                                "Select the valid statement.",
                                "What is the default value of Boolean Variable?",
                                "Arrays in java are-",
                                "Identify the modifier that cannot be used for constructors."
                        };


    String[][] options = {  
                            {"16 bit", "8 bit", "32 bit", "None of these"},
                            {"Sun Microsystems", "Google", "Microsoft", "Alphabet"},
                            {"1989", "1996", "1972", "1492"},
                            {"int", "double","void", "none"},
                            {"char[] ch = new char(6)","char[] ch = new char[6]","char[] ch = new char[]","char[] ch = new char()"},
                            {"True", "False", "Null", "Not Defined"},
                            {"Object references", "Objects", "Primitive Data Type", "None"},
                            {"public", "protected", "private", "static"}
                        
                        };

    char[] answers =        {
                                    'A',
                                    'A',
                                    'B',
                                    'C',
                                    'B',
                                    'B',
                                    'B',
                                    'D'
                            };

    // Declaring and Assigning some variables
    
    char user_input;    
    char answer;        
    int index;          
    int correct_guesses = 0;
    int total_questions = questions.length;   
    int result;   
    int seconds = 10;


    //initializing some GUI component

    JFrame frame = new JFrame();  
    JTextField textfield = new JTextField();  
    JTextArea textarea = new JTextArea();   
    JButton buttonA = new JButton();    
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    // create a few labels to hold all of the different answers
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();

    // create a label timer to display the word Time Left
    JLabel time_label = new JLabel();

    //used for the count down timer
    JLabel seconds_left = new JLabel();

    //appeared at the time of result
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();


    //it will move to next question automatically or when the assign time will over it will display the answer and move to next question.
    Timer timer = new Timer(1000, new ActionListener() {
            
        
        @Override
        public void actionPerformed(ActionEvent eve)
        {
           seconds--;
           seconds_left.setText(String.valueOf(seconds));
           if(seconds<=0)
           {
            displayAnswer();
           }

        }
    });

    //constructor
    public Quiz(){

        //designing GUI component

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,800);

        frame.getContentPane().setBackground(Color.BLUE); 
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0,0,1000,50);
        textfield.setBackground(new Color(50, 50, 50));
        textfield.setForeground(new Color(255,255,255));
        textfield.setFont(new Font("Roboto", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        
        textarea.setBounds(0,50,1000,50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25,25,25));
        textarea.setForeground(new Color(25,255,0));
        textarea.setFont(new Font("Roboto", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        //creating buttons
        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("Roboto", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("Roboto", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        
        
        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("Roboto", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        
        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("Roboto", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        //options
        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("Roboto", Font.PLAIN, 35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("Roboto", Font.PLAIN, 35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("Roboto", Font.PLAIN, 35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("Roboto", Font.PLAIN, 35));

        //countdown timer
        seconds_left.setBounds(885,660,100,100);
        seconds_left.setBackground(new Color(25,25,25));  //black color
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Roboto", Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        //The opaque flag is used by the Swing ComponentUI to test whether they should paint their background or whether they should not.
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));   //converting to string from int seconds.

        //for writing the text time left (optional)
        time_label.setBounds(885,625,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("Roboto", Font.BOLD,20));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("Time Left");

        //for number of question you corrected display at the end of quiz.
        number_right.setBounds(225, 325, 600, 100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25,255,0));
        number_right.setFont(new Font("Roboto", Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        //for displaying the score in percentage you scored at the end of quiz.
        percentage.setBounds(225,225,600,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Roboto", Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);


        //adding all components in the frame
        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);

        frame.add(textarea);
        frame.add(textfield);
        frame.setVisible(true);

        nextQuestion();    // calling next question method for starting the quiz.
    }

    //code for the working of quiz   (2nd phase)
    public void nextQuestion(){

        if(index>=total_questions)
        {
            results();
        }
        else {
            textfield.setText("Question "+(index+1));  //as index is initilized to 0
            textarea.setText(questions[index]);   //display question that we will ask
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);

            timer.start();  //timer will start
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //checking which button is clicked
        if(eve.getSource() == buttonA)
        {
            answer = 'A';
            if(answer == answers[index])
            {
                correct_guesses++;
            }
        }

        if(eve.getSource() == buttonB)
        {
            answer = 'B';
            if(answer == answers[index])
            {
                correct_guesses++;
            }
        }

        if(eve.getSource() == buttonC)
        {
            answer = 'C';
            if(answer == answers[index])
            {
                correct_guesses++;
            }
        }

        if(eve.getSource() == buttonD)
        {
            answer = 'D';
            if(answer == answers[index])
            {
                correct_guesses++;
            }
        }
        displayAnswer();

    }

    public void results()
    {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int)((correct_guesses/(double)total_questions)*100);

        textfield.setText("RESULTS!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        //display nummber of correct/total questions ratio.
        number_right.setText("Correct answer: ("+correct_guesses+ "/" +total_questions+ ")");
        //display the percentage
        percentage.setText("Score: ("+result+"%)");
        frame.add(percentage);
        frame.add(number_right);

    }

    public void displayAnswer(){

        timer.stop();    //timer will stop at the time answer will display
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);


        //if that option is not correct change that option color to red 
        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));


        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));


        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));

        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));

        // after the answer will display it will pause for some seconds like here it will pause for 1.5 seconds and then go to next question
        Timer pause = new Timer(1500, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent eve)
            {
                //changing to original color
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));
                
                answer = ' ';
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));  //changing to string 
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();

            }
        });
        pause.setRepeats(false);    //only excecute at once
        pause.start();   //start the timer
    }

    
}





