package Utility;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: sina
 * Date: 1/23/11
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Equivalents {

    public Vector<String> english = new Vector<String>();
    public Vector<Vector<String>> persian = new Vector<Vector<String>>();

    public Equivalents() {

        //#
        Vector<String> equivalent = new Vector<String>();
        equivalent.add("#");
        setEquivalentForEnglishCharacter("#", equivalent);

        //A
        equivalent = new Vector<String>();
        equivalent.add("#");
        equivalent.add("ا");
        equivalent.add("آ");
        equivalent.add("ع");
        setEquivalentForEnglishCharacter("a", equivalent);

        //B
        equivalent = new Vector<String>();
        equivalent.add("ب");
        setEquivalentForEnglishCharacter("b", equivalent);

        //C
        equivalent = new Vector<String>();
        equivalent.add("ک");
        setEquivalentForEnglishCharacter("c", equivalent);

        //D
        equivalent = new Vector<String>();
        equivalent.add("د");
        setEquivalentForEnglishCharacter("d", equivalent);

        //E
        equivalent = new Vector<String>();
        equivalent.add("#");
        equivalent.add("ا");
        equivalent.add("ع");
        equivalent.add("ه");
        setEquivalentForEnglishCharacter("e", equivalent);

        //F
        equivalent = new Vector<String>();
        equivalent.add("ف");
        setEquivalentForEnglishCharacter("f", equivalent);

        //G
        equivalent = new Vector<String>();
        equivalent.add("گ");
        equivalent.add("ق");
        setEquivalentForEnglishCharacter("g", equivalent);

        //H
        equivalent = new Vector<String>();
        equivalent.add("ه");
        equivalent.add("ح");
        setEquivalentForEnglishCharacter("h", equivalent);

        //I
        equivalent = new Vector<String>();
        equivalent.add("ی");
        equivalent.add("ای");
        setEquivalentForEnglishCharacter("i", equivalent);

        //J
        equivalent = new Vector<String>();
        equivalent.add("ج");
        equivalent.add("ژ");
        setEquivalentForEnglishCharacter("j", equivalent);

        //K
        equivalent = new Vector<String>();
        equivalent.add("ک");
        setEquivalentForEnglishCharacter("k", equivalent);

        //L
        equivalent = new Vector<String>();
        equivalent.add("ل");
        setEquivalentForEnglishCharacter("l", equivalent);

        //M
        equivalent = new Vector<String>();
        equivalent.add("م");
        setEquivalentForEnglishCharacter("m", equivalent);

        //N
        equivalent = new Vector<String>();
        equivalent.add("ن");
        setEquivalentForEnglishCharacter("n", equivalent);

        //O
        equivalent = new Vector<String>();
        equivalent.add("#");
        equivalent.add("ا");
        equivalent.add("‍‍‍‍‍‍‍‍و");
        setEquivalentForEnglishCharacter("o", equivalent);

        //P
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍پ");
        setEquivalentForEnglishCharacter("p", equivalent);

        //Q
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ک");
        setEquivalentForEnglishCharacter("q", equivalent);

        //R
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ر");
        setEquivalentForEnglishCharacter("r", equivalent);

        //S
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍س");
        equivalent.add("‍‍‍‍‍‍‍‍ص");
        equivalent.add("‍‍‍‍‍‍‍‍ث");
        setEquivalentForEnglishCharacter("s", equivalent);

        //T
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ت");
        equivalent.add("‍‍‍‍‍‍‍‍ط");
        setEquivalentForEnglishCharacter("t", equivalent);

        //U
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍و");
        setEquivalentForEnglishCharacter("u", equivalent);

        //V
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍و");
        setEquivalentForEnglishCharacter("v", equivalent);

        //W
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍و");
        setEquivalentForEnglishCharacter("w", equivalent);

        //X
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍خ");
        equivalent.add("‍‍‍‍‍‍‍‍ز");
        equivalent.add("‍‍‍‍‍‍‍‍کس");
        setEquivalentForEnglishCharacter("x", equivalent);

        //Y
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ی");
        setEquivalentForEnglishCharacter("y", equivalent);

        //Z
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ض");
        equivalent.add("‍‍‍‍‍‍‍‍ز");
        equivalent.add("‍‍‍‍‍‍ظ");
        equivalent.add("‍‍‍‍‍‍ذ");
        setEquivalentForEnglishCharacter("z", equivalent);

        //SPACE
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ ");
        setEquivalentForEnglishCharacter(" ", equivalent);

        //KH
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍خ");
        setEquivalentForEnglishCharacter("kh", equivalent);

        //GH
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ق");
        setEquivalentForEnglishCharacter("gh", equivalent);

        //CH
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍چ");
        setEquivalentForEnglishCharacter("ch", equivalent);

        //AI
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ی");
        equivalent.add("‍‍‍‍‍‍‍‍ای");
        setEquivalentForEnglishCharacter("ai", equivalent);

        //II
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍یی");
        equivalent.add("‍‍‍‍‍‍‍‍ای");
        setEquivalentForEnglishCharacter("ii", equivalent);

        //SH
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ش");
        setEquivalentForEnglishCharacter("sh", equivalent);

        //OO
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍و");
        equivalent.add("‍‍‍‍‍‍‍‍او");
        setEquivalentForEnglishCharacter("oo", equivalent);

        //OU
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍و");
        equivalent.add("‍‍‍‍‍‍‍‍او");
        setEquivalentForEnglishCharacter("ou", equivalent);

        //AA
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍آ");
        setEquivalentForEnglishCharacter("aa", equivalent);

        //LL
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ل");
        setEquivalentForEnglishCharacter("ll", equivalent);

        //EH
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍ه");
        setEquivalentForEnglishCharacter("eh", equivalent);

        //MM
        equivalent = new Vector<String>();
        equivalent.add("‍‍‍‍‍‍‍‍م");
        setEquivalentForEnglishCharacter("mm", equivalent);

        //HH
        equivalent = new Vector<String>();
        equivalent.add("ه");
        equivalent.add("ح");
        setEquivalentForEnglishCharacter("hh", equivalent);

        //////////////////////////////////
        //AH

        //////////////////////////////////
    }

    public void setEquivalentForEnglishCharacter(String englishChar, Vector<String> persianChar) {
        english.add(englishChar);
        persian.add(persianChar);
    }

}
