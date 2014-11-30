package Utility;

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
    public Vector<Vector<String>> probabilities;
    public Vector<String> numberOfRepeats;

    int probSize = 0;
    int numberOfPrevCurrentChars = 0;

    public TokenizedResult() {
        probSize = (english.size() + 1) * english.size();
        numberOfPrevCurrentChars = english.size() + 1;

        probabilities = new Vector<Vector<String>>(probSize);
        numberOfRepeats = new Vector<String>(probSize);

        //Initiating NumberOfRepeats And Probabilities
        for (int i = 0; i < probSize; i++) {
            numberOfRepeats.setElementAt("0", i);
            for (int j = 0; j < probabilities.elementAt(i).size(); j++) {
                probabilities.elementAt(i).setElementAt("0", j);
            }
        }
    }

    public double getProbabilityFor(String prevChar, String currentChar, String equivalent) {
        int prevCharIndex = english.indexOf(prevChar);
        int currentCharIndex = english.indexOf(currentChar);
        int equIndex = persian.elementAt(currentCharIndex).indexOf(equivalent);

        String probString = probabilities.elementAt(prevCharIndex * numberOfPrevCurrentChars + currentCharIndex).elementAt(equIndex);
        return (Double.parseDouble(probString));
    }

    public void setProbabilityFor(String prevChar, String currentChar, String equivalent, double prob) {
        int prevCharIndex = english.indexOf(prevChar);
        int currentCharIndex = english.indexOf(currentChar);
        int equIndex = persian.elementAt(currentCharIndex).indexOf(equivalent);

        probabilities.elementAt(prevCharIndex * english.size() + currentCharIndex).setElementAt("" + prob, equIndex);

        String prevRepeatString = numberOfRepeats.elementAt(prevCharIndex * english.size() + currentCharIndex);
        int prevRepeat = Integer.parseInt(prevRepeatString);
        prevRepeat++;
        numberOfRepeats.setElementAt("" + prevRepeat, prevCharIndex * english.size() + currentCharIndex);
    }

}
