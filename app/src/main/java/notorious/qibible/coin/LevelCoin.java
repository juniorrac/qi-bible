package notorious.qibible.coin;


public class LevelCoin {

    private String Title;
    private String Category ;
    private String Description ;
    private int Thumbnail ;

//    private int id;
//    private String question;
//    private String opta;
//    private String optb;
//    private String optc;
//    private String optd;
//    private String answer;

    public LevelCoin() {
//        id = 0;
//        question = "";
//        opta = "";
//        optb = "";
//        optc = "";
//        optd = "";
//        answer = "";
    }

    public LevelCoin(String title, String category, String description, int thumbnail) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;

//        question = q;
//        opta = oa;
//        optb = ob;
//        optc = oc;
//        optd = od;
//        answer = ans;
    }


    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }


//    public String getQuestion() {
//        return question;
//    }
//
//    public String getOptA() {
//        return opta;
//    }
//
//    public String getOptB() {
//        return optb;
//    }
//
//    public String getOptC() {
//        return optc;
//    }
//
//    public String getOptD() {
//        return optd;
//    }
//
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setId(int i) {
//        id = i;
//    }
//
//    public void setQuestion(String q1) {
//        question = q1;
//    }
//
//    public void setOptA(String o1) {
//        opta = o1;
//    }
//
//    public void setOptB(String o2) {
//        optb = o2;
//    }
//
//    public void setOptC(String o3) {
//        optc = o3;
//    }
//
//    public void setOptD(String o4) {
//        optd = o4;
//    }
//
//    public void setAnswer(String ans) {
//        answer = ans;
//    }
}
