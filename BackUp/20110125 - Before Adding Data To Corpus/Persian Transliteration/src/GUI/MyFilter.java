package GUI;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Created by IntelliJ IDEA.
 * User: sina
 * Date: 12/30/10
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyFilter extends PlainDocument {

    public static final String LOWERCASE  =
            "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE  =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA   =
            LOWERCASE + UPPERCASE;
    public static final String NUMERIC =
            "0123456789";
    public static final String DATE =
            NUMERIC + ":";
    public static final String PHONE =
            NUMERIC + "+";
    public static final String FLOAT =
            NUMERIC + ".";
    public static final String ALPHA_NUMERIC =
            ALPHA + NUMERIC;

    protected String acceptedChars = null;
    protected boolean negativeAccepted = false;

    public MyFilter() {
        this(ALPHA_NUMERIC);
    }
    public MyFilter(String acceptedchars) {
        acceptedChars = acceptedchars;
    }

    public void setNegativeAccepted(boolean negativeaccepted) {
        if (acceptedChars.equals(NUMERIC) ||
                acceptedChars.equals(FLOAT) ||
                acceptedChars.equals(ALPHA_NUMERIC)){
            negativeAccepted = negativeaccepted;
            acceptedChars += "-";
        }
    }

    public void insertString
            (int offset, String  str, AttributeSet attr)
            throws BadLocationException, BadLocationException {
        if (str == null) return;

        if (acceptedChars.equals(UPPERCASE))
            str = str.toUpperCase();
        else if (acceptedChars.equals(LOWERCASE))
            str = str.toLowerCase();

        for (int i=0; i < str.length(); i++) {
            if (acceptedChars.indexOf(str.valueOf(str.charAt(i))) == -1)
                return;
        }

        if (acceptedChars.equals(FLOAT) ||
                (acceptedChars.equals(FLOAT + "-") && negativeAccepted)) {
            if (str.indexOf(".") != -1) {
                if (getText(0, getLength()).indexOf(".") != -1) {
                    return;
                }
            }
        }

        if (negativeAccepted && str.indexOf("-") != -1) {
            if (str.indexOf("-") != 0 || offset != 0 ) {
                return;
            }
        }

        super.insertString(offset, str, (javax.swing.text.AttributeSet) attr);
    }
}