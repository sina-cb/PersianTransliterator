package Transliteration;

import Utility.TokenizedResult;
import Utility.Tokenizer;

import javax.swing.*;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: sina
 * Date: 1/23/11
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transliterator {

    static Vector<String> results;
    static Vector<Vector<String>> resultsWithNull;
    static TokenizedResult tokenized;
    Tokenizer tokenizer = new Tokenizer();

    private static void genAllResults(Vector<String> english, int index, Vector<String> trans) {
        if (index >= english.size()) {
            String temp = "";
            Vector<String> tempWithNull = new Vector<String>();
            for (int i = 0; i < trans.size(); i++) {
                if (trans.elementAt(i).compareTo("#") != 0) {
                    temp = temp + trans.elementAt(i);
                }
                tempWithNull.add(trans.elementAt(i));
            }
            results.add(temp);
            resultsWithNull.add(tempWithNull);

            return;
        }

        Vector<String> persianChars = tokenized.persian.elementAt(index);
        for (int i = 0; i < persianChars.size(); i++) {
            trans.add(persianChars.elementAt(i));
            genAllResults(english, index + 1, trans);
            trans.removeElementAt(trans.size() - 1);
        }
    }

    public Vector<String> genTransliteration(String input) {
        results = new Vector<String>();
        resultsWithNull = new Vector<Vector<String>>();
        tokenizer = new Tokenizer();

        tokenized = tokenizer.tokenizer(input);
        genAllResults(tokenized.english, 0, new Vector<String>());

        return results;
    }

    public void updateCorpus(String input, int row) {
        TokenizedResult tr = tokenizer.ts;

        Vector<String> english = tokenized.english;
        Vector<String> persian = resultsWithNull.elementAt(row);

        for (int i = 0; i < english.size(); i++) {
            //JOptionPane.showMessageDialog(null, "input: " + english.elementAt(i) + " persian: " + persian.elementAt(i));
            double newProb = 0;

            if (i == 0) {
                int repeats = tr.getNumberOfRepeats("#", english.elementAt(i));
                double prob = tr.getProbabilityFor("#", english.elementAt(i), persian.elementAt(i));

                newProb = ((prob * repeats) + 1) / (repeats + 1);

                tr.setProbabilityFor("#", english.elementAt(i), persian.elementAt(i), newProb);
            } else {
                int repeats = tr.getNumberOfRepeats(english.elementAt(i - 1), english.elementAt(i));
                double prob = tr.getProbabilityFor(english.elementAt(i - 1), english.elementAt(i), persian.elementAt(i));

                newProb = ((prob * repeats) + 1) / (repeats + 1);

                tr.setProbabilityFor( english.elementAt(i - 1), english.elementAt(i), persian.elementAt(i), newProb);
            }

        }

    }

}