package notorious.qibible;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class QibibleHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Nom Bible
    private static final String NOMBIBLE = "NOMBIBLE";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + NOMBIBLE + " VARCHAR(255)," + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public QibibleHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void allQuestion() {
        ArrayList<QibibleQuestion> arraylist = new ArrayList<>();

        arraylist.add(new QibibleQuestion("CG","Que signifie le nom J??r??mie ?", "Dieu ??l??ve", "Le Seigneur est mon Dieu", "Tristesse", "Beni de Dieu", "Dieu ??l??ve"));

        arraylist.add(new QibibleQuestion("CG","O?? est-ce que Mo??se va mourir ?", "Au mont Sina?? ", "Dans le Desert", "Au mont N??bo", "En terre de Canaan", "Au mont N??bo"));

        arraylist.add(new QibibleQuestion("CG","Qui est la m??re de Joseph fils de Jacob?", "Lea", "Rachel", "Ruth", "Sarah", "Rachel"));

        arraylist.add(new QibibleQuestion("CG","Le dernier repas du Christ qu'il prend avec ses disciples se nomme ?", "La c??ne", "l'Eucharistie", "Le repas", "La Scene", "La c??ne"));

        arraylist.add(new QibibleQuestion("CG","Quel miracle J??sus accomplit-il sur la barque de Simon ?", "la p??che miraculeuse", "la marche sur l???eau", "la Cene", "la temp??te calm??e", "la p??che miraculeuse"));

        arraylist.add(new QibibleQuestion("CG","Quelle est la signification du nom No?? ?", "l'homme fort", "Sauve des eaux", "Repos ou r??confort", "Dieu parmis nous", "Repos ou r??confort"));

        arraylist.add(new QibibleQuestion("CG","Quel est le signe de l???alliance entre No?? et Dieu?", "Un arc-en-ciel", "Les 10 commandements", "La fin du Deluge", "Le soleil", "Un arc-en-ciel"));

        arraylist.add(new QibibleQuestion("CG","Dieu n'a pas permis au roi David de construire son temple parce que ...", "David allait bient??t mourir", "David ??tait pas bon ?? ??a", "David n'??tait pas bon ?? ??a", "David a vers?? trop de sang", "David n'??tait pas bon ?? ??a"));

        arraylist.add(new QibibleQuestion("CG","Notre R??dempteur nous supplie de pardonner ?? notre prochain ...", "77 ?? 7", "70 ?? 7", "17 ?? 7", "7 ?? 7", "70 ?? 7"));

        arraylist.add(new QibibleQuestion("Genese","Selon la bible, dans quel jour Dieu cr??a-t-il l'??tre humain?", "5", "6", "7", "3", "6"));

        arraylist.add(new QibibleQuestion("Genese","Comment s'appelle la partie de la Bible o?? est racont??e la cr??ation du Monde?", "La g??nisse", "La jeunesse", "La Gen??se", "La Cr??ation", "La Gen??se"));

        arraylist.add(new QibibleQuestion("Genese","Dans le po??me de la cr??ation, lors de quel jour Dieu cr??e la terre, la Nature?", "Le premier", "Le deuxi??me", "Le troisi??me", "Le quatri??me", "Le quatri??me"));

        arraylist.add(new QibibleQuestion("Genese","Quelle signification existe pour le lieu o?? Dieu pla??a Adam et ??ve apr??s leur cr??ation?", "Jardin de Babylone, les fleuves", "Jardin d'Eden, notre Belle Nature", "Jardin d'Eden, la jardin paradisiaque", "Jardin de Golgotha, jardin de la trahison", "Jardin d'Eden, notre Belle Nature"));

        arraylist.add(new QibibleQuestion("CG","O?? furent appel??s pour la premi??re fois \"chr??tiens\"les disciples de J??sus ?", "?? Rome", "?? Antioche", "?? Jerusalem", "?? Damas", "?? Antioche"));

        arraylist.add(new QibibleQuestion("CG","Pourquoi Abraham fut-il appel?? l'ami de Dieu ?", "il etait un Patriarche.", "il faisait le bien", "il priait beaucoup", "il eut confiance dans les promesses de Dieu", "Parce qu'il eut confiance dans les promesses de Dieu"));

        arraylist.add(new QibibleQuestion("CG","Qui fut le premier roi d'Isra??l ?", "Jeremi", "Sa??l", "Isaac", "David", "Sa??l"));

        arraylist.add(new QibibleQuestion("CG","Au temps d'Ester, que voulait entreprendre Haman, premier ministre du roi de Babylone?", "De combattre les ennemis d'Isra??l.", "exterminer les juifs", "Devenir Roi", "De conqu??rir Jerusalem", "exterminer les juifs"));

        arraylist.add(new QibibleQuestion("CG","Quel est le plus grand commandement Dieu ? ", "Tu aimeras ton prochain comme toi m??me", "Tu ne tueras point", "Tu aimeras le Seigneur ton Dieu de tout ton coeur", "Aimer vous les un et les autres", "Tu aimeras le Seigneur ton Dieu de tout ton coeur"));

        arraylist.add(new QibibleQuestion("CG","Pour quelle somme Judas Iscariot livra-t-il J??sus aux principaux sacrificateurs ?", "30 pi??ces d'or", "30 pi??ces d'argent", "30 deniers", "30 euro", "30 pi??ces d'argent"));

        arraylist.add(new QibibleQuestion("Genese","Signification de ??ve", "Femme toute belle", "Femme de Dieu", "Femme et M??re", "M??re de toute l'humanit??", "Femme et M??re"));
        arraylist.add(new QibibleQuestion("Genese","Le nom du troisi??me fils d'Adam et ??ve", "Benjamin", "Abel", "Set", "Cain", "Set"));
        arraylist.add(new QibibleQuestion("Genese","Jacob a douze fils. Lequel est l???a??n???", "Benjamin", "Juda", "Ruben", "Levi", "Ruben"));
        arraylist.add(new QibibleQuestion("Genese","Quel est le nom de la femme d???Isaac?", "Sarah", "R??becca", "Dina", "Zilpa", "R??becca"));
        arraylist.add(new QibibleQuestion("Genese","Joseph, fils de Jacob, avait combien de fr??res ? ", "11", "12", "10", "13", "11"));

        this.addAllQuestions(arraylist);

    }

    private void addAllQuestions(ArrayList<QibibleQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (QibibleQuestion question : allQuestions) {
                values.put(NOMBIBLE, question.getNomBible());
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public List<QibibleQuestion> getAllOfTheQuestions() {

        List<QibibleQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, NOMBIBLE, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
//        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);
        Cursor cursor =  db.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + NOMBIBLE + "= 'CG'",null);

        while (cursor.moveToNext()) {
            QibibleQuestion question = new QibibleQuestion();
            question.setId(cursor.getInt(0));
            question.setNomBible(cursor.getString(2));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(3));
            question.setOptB(cursor.getString(4));
            question.setOptC(cursor.getString(5));
            question.setOptD(cursor.getString(6));
            question.setAnswer(cursor.getString(7));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }

    public List<QibibleQuestion> getAllTheQuestionsOfGenese() {

        List<QibibleQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, NOMBIBLE, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
//        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);
        Cursor cursor =  db.rawQuery("select * FROM " + TABLE_NAME + " WHERE " + NOMBIBLE + "= 'Genese'",null);

        while (cursor.moveToNext()) {
            QibibleQuestion question = new QibibleQuestion();
            question.setId(cursor.getInt(0));
            question.setNomBible(cursor.getString(2));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(3));
            question.setOptB(cursor.getString(4));
            question.setOptC(cursor.getString(5));
            question.setOptD(cursor.getString(6));
            question.setAnswer(cursor.getString(7));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
