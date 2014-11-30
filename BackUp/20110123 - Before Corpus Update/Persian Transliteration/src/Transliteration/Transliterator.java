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
    static Vector<String> resultsWithNull;
    static TokenizedResult tokenized;

    private static void genAllResults(Vector<String> english, int index, Vector<String> trans) {
        if (index >= english.size()) {
            String temp = "";
            String tempWithNull = "";
            for (int i = 0; i < trans.size(); i++) {
                if (trans.elementAt(i).compareTo("#") != 0) {
                    temp = temp + trans.elementAt(i);
                }
                tempWithNull = tempWithNull + trans.elementAt(i);
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

    public static Vector<String> genTransliteration(String input) {
        results = new Vector<String>();
        resultsWithNull = new Vector<String>();

        tokenized = Tokenizer.tokenizer(input);
        genAllResults(tokenized.english, 0, new Vector<String>());

        return results;
    }

    public static void updateCorpus(String input, int row) {



    }

}