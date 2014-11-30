package Utility;

import javax.swing.*;
import java.io.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: sina
 * Date: 1/23/11
 * Time: 3:09 AM
 * To change this template use File | Settings | File Templates.
 */

//It's a Custom Data Structure For Returning The Tokenized Word
public class TokenizedResult {

    public Vector<String> english = new Vector<String>();
    public Vector<Vector<String>> persian = new Vector<Vector<String>>();

    public static Vector<Vector<String>> probabilities;
    public static Vector<String> numberOfRepeats;

    int probSize = 0;
    int numberOfPrevCurrentChars = 0;

    public TokenizedResult() {
        readFromFile();

        probSize = (Tokenizer.defaultData.english.size() + 1) * Tokenizer.defaultData.english.size();
        numberOfPrevCurrentChars = Tokenizer.defaultData.english.size();

        if (probabilities == null && numberOfRepeats == null) {
            probabilities = new Vector<Vector<String>>(probSize);
            numberOfRepeats = new Vector<String>(probSize);

            //Initiating NumberOfRepeats And Probabilities
            for (int i = 0; i < probSize; i++) {
                numberOfRepeats.add("0");

                int indexForPersian = i % Tokenizer.defaultData.persian.size();
                int nextVectorSize = Tokenizer.defaultData.persian.elementAt(indexForPersian).size();
                probabilities.add(new Vector<String>(nextVectorSize));
                for (int j = 0; j < nextVectorSize; j++) {
                    probabilities.elementAt(i).add("0");
                }

            }
        }

    }

    public double getProbabilityFor(String prevChar, String currentChar, String equivalent) {
        int prevCharIndex = Tokenizer.defaultData.english.indexOf(prevChar);
        int currentCharIndex = Tokenizer.defaultData.english.indexOf(currentChar);
        int equIndex = Tokenizer.defaultData.persian.elementAt(currentCharIndex).indexOf(equivalent);

        int atIndex = prevCharIndex * numberOfPrevCurrentChars + currentCharIndex;

        String probString = probabilities.elementAt(atIndex).elementAt(equIndex);
        return (Double.parseDouble(probString));
    }

    public int getNumberOfRepeats(String prevChar, String currentChar) {
        int repeats = 0;

        int prevCharIndex = Tokenizer.defaultData.english.indexOf(prevChar);
        int currentCharIndex = Tokenizer.defaultData.english.indexOf(currentChar);

        String repeatsString = numberOfRepeats.elementAt(prevCharIndex * numberOfPrevCurrentChars + currentCharIndex);
        repeats = Integer.parseInt(repeatsString);
        return repeats;
    }

    public void setProbabilityFor(String prevChar, String currentChar, String equivalent, double prob) {
        int prevCharIndex = Tokenizer.defaultData.english.indexOf(prevChar);
        int currentCharIndex = Tokenizer.defaultData.english.indexOf(currentChar);
        int equIndex = Tokenizer.defaultData.persian.elementAt(currentCharIndex).indexOf(equivalent);

        int nextVectorIndex = prevCharIndex * numberOfPrevCurrentChars + currentCharIndex;

        String prevRepeatString = numberOfRepeats.elementAt(prevCharIndex * numberOfPrevCurrentChars + currentCharIndex);
        int prevRepeat = Integer.parseInt(prevRepeatString);
        prevRepeat++;
        numberOfRepeats.setElementAt("" + prevRepeat, nextVectorIndex);

        for (int i = 0; i < probabilities.elementAt(nextVectorIndex).size(); i++) {
            double newProb = 0;
            double oldProb = Double.parseDouble(probabilities.elementAt(nextVectorIndex).elementAt(i));

            if (i == equIndex) {
                probabilities.elementAt(nextVectorIndex).setElementAt("" + prob, equIndex);
                //JOptionPane.showMessageDialog(null, prob + " for " + currentChar + " as " + equivalent);
            }else {
                newProb = (oldProb * (prevRepeat - 1)) / prevRepeat;
                probabilities.elementAt(nextVectorIndex).setElementAt("" + newProb, i);
                //JOptionPane.showMessageDialog(null, newProb + " for " + currentChar + " as " + equivalent);
            }
        }
    }

    public static void writeToFile() {
        File probFile = new File("src/Files/Probabilities.AI");
        File numberFile = new File("src/Files/NumberOfRepeats.AI");

        try {
            JOptionPane.showMessageDialog(null, "Infos Are Writing To The Files...");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(probFile));
            oos.writeObject(probabilities);

            oos = new ObjectOutputStream(new FileOutputStream(numberFile));
            oos.writeObject(numberOfRepeats);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        File probFile = new File("src/Files/Probabilities.AI");
        File numberFile = new File("src/Files/NumberOfRepeats.AI");

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(probFile));
            probabilities = (Vector<Vector<String>>) ois.readObject();

            ois = new ObjectInputStream(new FileInputStream(numberFile));
            numberOfRepeats = (Vector<String>) ois.readObject();

            /*for (int i = 0; i < probabilities.size(); i++) {
                for (int j = 0; j < probabilities.elementAt(i).size(); j++) {
                    System.out.print(probabilities.elementAt(i).elementAt(j));
                }
            }

            System.out.println();

            for (int i = 0; i < numberOfRepeats.size(); i++) {
                if (!numberOfRepeats.elementAt(i).equals("0")) {
                    JOptionPane.showMessageDialog(null, numberOfRepeats.elementAt(i));
                }
                System.out.print(numberOfRepeats.elementAt(i));
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cleanFiles() {
        File probFile = new File("src/Files/Probabilities.AI");
        File numberFile = new File("src/Files/NumberOfRepeats.AI");
        File temp = new File("src/Files/Temp.AI");

        try {
            probFile.renameTo(temp);
            probFile.createNewFile();

            numberFile.renameTo(temp);
            numberFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
