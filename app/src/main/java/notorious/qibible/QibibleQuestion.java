package notorious.qibible;

import android.app.Activity;

public class QibibleQuestion extends Activity {
    private int id;

    private String nomBible;
    private String question;
    private String opta;
    private String optb;
    private String optc;
    private String optd;
    private String answer;

    public QibibleQuestion(String nbible,String q, String oa, String ob, String oc, String od, String ans) {
        nomBible = nbible;
        question = q;
        opta = oa;
        optb = ob;
        optc = oc;
        optd = od;
        answer = ans;
    }

    public QibibleQuestion() {
        id = 0;
        nomBible = "";
        question = "";
        opta = "";
        optb = "";
        optc = "";
        optd = "";
        answer = "";
    }

    public String getNomBible() {
        return nomBible;
    }

    public void setNomBible(String nomBible) {
        this.nomBible = nomBible;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptA() {
        return opta;
    }

    public String getOptB() {
        return optb;
    }

    public String getOptC() {
        return optc;
    }

    public String getOptD() {
        return optd;
    }

    public String getAnswer() {
        return answer;
    }

    public void setId(int i) {
        id = i;
    }

    public void setQuestion(String q1) {
        question = q1;
    }

    public void setOptA(String o1) {
        opta = o1;
    }

    public void setOptB(String o2) {
        optb = o2;
    }

    public void setOptC(String o3) {
        optc = o3;
    }

    public void setOptD(String o4) {
        optd = o4;
    }

    public void setAnswer(String ans) {
        answer = ans;
    }


}
