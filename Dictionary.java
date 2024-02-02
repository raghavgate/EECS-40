package Dictionary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Dictionary {
    public ArrayList<WordProperties> wordDictionary = new ArrayList<>();
    public JPanel Dictionary;

    public Dictionary() {




        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextNewWord.setText(""); // Clear the field where new words are entered
                TextArea.setText(""); // Clear the text area for word meanings and error messages
                TextFreqWord1.setText(""); // Clear the frequent word display field 1
                TextFreqWord2.setText(""); // Clear the frequent word display field 2
                TextFreqWord3.setText(""); // Clear the frequent word display field 3
                TextOriginalWord.setText("");
                TextFilePath.setText("");
            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newWord = TextNewWord.getText(); // Get the new word from the text field
                String meaning = TextArea.getText(); // Get the meaning from the text area

                if (!newWord.matches("[a-zA-Z]+")) {
                    try {
                        throw new InvalidWordError("Invalid characters entered in the word: " + newWord);
                    } catch (InvalidWordError error) {
                        // Display an error message in the TextArea
                        TextArea.setText("Error: There is an invalid word entered");
                    }
                    return; // Exit the method if word is invalid
                }


                // Check for duplicate word before adding
                for (WordProperties wordProp : wordDictionary) {
                    if (wordProp.word.equals(newWord)) {
                        TextArea.setText("Error: Word '" + newWord + "' already exists in the dictionary.");
                        return; // Exit the method if word is already in the dictionary
                    }
                }

                // Attempt to add the word with its meaning to the dictionary
                try {
                    if (isWordDuplicated(newWord)) {
                        throw new WordDuplicatedError("Duplicate word found: " + newWord);
                    }
                    wordDictionary.add(new WordProperties(meaning, newWord, 0));

                    // Display a message or update UI elements after adding the word
                    TextArea.setText("Word '" + newWord + "' added to the dictionary with meaning.");
                    TextNewWord.setText("");
                } catch (WordDuplicatedError error) {
                    // Handle WordDuplicatedError if needed
                    TextArea.setText("Error: " + error.getMessage());
                }
            }
        });

        FINDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String keyword = TextNewWord.getText(); // Get the keyword to search
                if (keyword.isEmpty()) {
                    // Handle empty input

                    TextFreqWord1.setText("");
                    TextFreqWord2.setText("");
                    TextFreqWord3.setText("");
                    TextArea.setText("No word has been entered please try again");

                    return;
                }
                boolean wordFound = false;
                for (WordProperties wordProp : wordDictionary) {
                    if (wordProp.word.equals(keyword)) {
                        wordProp.frequency++; // Increment the frequency of the found word
                        wordFound = true;
                        break;
                    }
                    TextArea.setText(wordProp.meaning); // Display the meaning of the word
                }


                // If word is not found, throw WordNotFoundError
                if (!wordFound) {
                    try {
                        throw new WordNotFoundError("No Word Matched.");
                    } catch (WordNotFoundError error) {
                        // Display an error message in the TextArea or handle the error accordingly
                        TextArea.setText(error.getMessage());
                    }
                }


                ArrayList <WordProperties> tempArray = findfrequentwords(wordDictionary,keyword);
                JTextField[] freqWordFields = {TextFreqWord1, TextFreqWord2, TextFreqWord3}; // Assuming you have 3 text fields for frequent words

                for (WordProperties wordProp : tempArray) {
                    if (wordProp.word.equals(keyword)) {
                        TextArea.setText(wordProp.meaning);
                        freqWordFields[0].setText(wordProp.word);
                        return;
                    }
                }


                for (int i = 0; i < tempArray.size() && i < freqWordFields.length; i++) {
                    freqWordFields[i].setText(tempArray.get(i).word);
                }

                // Clear remaining text fields if there are fewer frequent words than available text fields
                for (int i = tempArray.size(); i < freqWordFields.length; i++) {
                    freqWordFields[i].setText("");
                }

            }
        });
        REMOVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wordToRemove = TextNewWord.getText(); // Get the word to remove from the text field

                boolean wordFound = false;
                for (int i = 0; i < wordDictionary.size(); i++) {
                    if (wordDictionary.get(i).word.equals(wordToRemove)) {
                        wordDictionary.remove(i); // Remove the word from the dictionary
                        TextArea.setText(""); // Clear the text area
                        TextArea.append("Word '" + wordToRemove + "' removed from the dictionary.\n");
                        TextNewWord.setText(""); // Clear the text field after removal
                        wordFound = true;
                        break;
                    }
                }

                if (!wordFound) {
                    throw new WordNotFoundError("Error: Word '" + wordToRemove + "' not found in the dictionary.\n");
                }
            }
        });
        MODIFYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String originalWord = TextOriginalWord.getText(); // Get the original word to modify
                String newWord = TextNewWord.getText(); // Get the new word from the text field

                String meaning = TextArea.getText(); // Get the meaning from the text area

                boolean wordFound = false;
                for (WordProperties wordProp : wordDictionary) {
                    if (wordProp.word.equalsIgnoreCase(originalWord)) {
                        // Modify the original word details
                        wordProp.word = newWord;
                        if (!meaning.isEmpty()) {
                            wordProp.meaning = meaning;
                        }

                        TextArea.setText(""); // Clear the text area
                        TextArea.append("Word '" + originalWord + "' modified to '" + newWord + "' in the dictionary.\n");
                        TextOriginalWord.setText(""); // Clear the original word field after modification
                        TextNewWord.setText(""); // Clear the new word field after modification
                        wordFound = true;
                        break;
                    }
                }

                if (!wordFound) {
                    TextArea.setText("Word '" + originalWord + "' not found in the dictionary.\n");
                }
            }
        });
        IMPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = TextFilePath.getText(); // Get the file path from the text field
                importFromFile(filePath); // Call the method to import from the file
                TextFilePath.setText("");
            }
        });
        EXPORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = TextFilePath.getText(); // Get the file path from the text field
                exportToFile(filePath); // Call the method to export to the file
            }
        });
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("Dictionary");
        frame.setContentPane(new Dictionary().Dictionary);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public JLabel Enterwordhere;
    public JButton FINDButton;
    public JButton ADDButton;
    public JTextField TextOriginalWord;
    public JTextField TextNewWord;
    public JTextField TextFreqWord1;
    public JTextField TextFreqWord2;
    public JTextField TextFreqWord3;
    public JButton IMPORTButton;
    public JButton EXPORTButton;
    public JTextField TextFilePath;
    public JTextArea TextArea;
    public JButton CLEARButton;
    public JButton MODIFYButton;
    public JButton REMOVEButton;

    public static ArrayList<WordProperties> findfrequentwords (ArrayList<WordProperties> tempList, String stringmatch) {
        ArrayList<WordProperties> temparray = new ArrayList<>();
        String searchlower = stringmatch.toLowerCase();
        int highestFreq = Integer.MIN_VALUE;

        for (WordProperties i : tempList) {
            if (i.word.toLowerCase().contains(searchlower)) {
                temparray.add(i);


                if (i.frequency > highestFreq) {
                    highestFreq = i.frequency;
                }
            }
        }
        int finalHighestFreq = highestFreq;
        temparray.removeIf(wordProp -> wordProp.frequency < finalHighestFreq);
        temparray.sort((a1,a2) -> a2.frequency - a1.frequency);

        return new ArrayList<>(temparray);

    }
    public void importFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String word = "";
            String meaning = "";
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                if (lineNumber % 3 == 1) {
                    // First line in a set of three: Word
                    word = line.trim();
                } else if (lineNumber % 3 == 2) {
                    // Second line in a set of three: Meaning
                    meaning = line.trim();
                } else {
                    // Third line in a set of three: Empty line, add to dictionary
                    wordDictionary.add(new WordProperties(meaning, word, 0)); // Add word and meaning to the dictionary
                }
                lineNumber++;
            }
            if (lineNumber % 3 != 1 && !word.isEmpty() && !meaning.isEmpty()) {
                wordDictionary.add(new WordProperties(meaning, word, 0));
            }


            // Display a message or update UI elements after importing
            TextArea.setText("Dictionary imported from file: " + filePath);
        } catch (FileNotFoundError e) {
            // Handle file not found or other exceptions
            TextArea.setText("Error: File not found");
        }
        catch (IOException e){
            TextArea.setText("Error: Unable to read File");
        }
    }
    public void exportToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Sort the wordDictionary by frequency in descending order
            wordDictionary.sort((a1, a2) -> Integer.compare(a2.frequency, a1.frequency));

            // Export words to the file in the specified format
            for (WordProperties wordProp : wordDictionary) {
                bw.write(wordProp.word);
                bw.newLine();
                bw.write(Integer.toString(wordProp.frequency));
                bw.newLine();
                bw.write(wordProp.meaning);
                bw.newLine();
                bw.newLine();
            }

            // Display a message or update UI elements after exporting
            TextArea.setText("Dictionary exported to file: " + filePath);
        }  catch (FileNotFoundError e) {
            // Handle file not found or other exceptions
            TextArea.setText("Error: File not found");
        }
        catch (IOException e) {
            // Handle exceptions if unable to write to the file
            TextArea.setText("Error: Unable to export dictionary to the file.");
        }
    }

    public boolean isWordDuplicated(String word) {
        for (WordProperties wordProp : wordDictionary) {
            if (wordProp.word.equals(word)) {
                return true; // Word is a duplicate
            }
        }
        return false; // Word is not a duplicate
    }





}
