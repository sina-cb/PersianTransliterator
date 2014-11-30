package Utility;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: sina
 * Date: 1/22/11
 * Time: 3:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tokenizer {

    public static Equivalents defaultData = new Equivalents();
    public TokenizedResult ts = new TokenizedResult();

    public TokenizedResult tokenizer(String input) {

        boolean ifContinue = false;

        for (int i = 0; i < input.length(); i++) {
            if (ifContinue) {
                ifContinue = false;
                continue;
            }

            String english = "" + input.charAt(i);
            String testEnglish = "";
            if ( i + 1 < input.length())
                testEnglish = english + input.charAt(i + 1);

            int englishPos = -1;
            if (defaultData.english.contains(testEnglish)) {
                ts.english.add(testEnglish);
                ifContinue = true;
                englishPos = defaultData.english.indexOf(testEnglish);
            }else {
                ts.english.add(english);
                ifContinue = false;
                englishPos = defaultData.english.indexOf(english);
            }

            Vector<String> persianEq = defaultData.persian.elementAt(englishPos);
            ts.persian.add(persianEq);
        }

        return ts;
    }

}
