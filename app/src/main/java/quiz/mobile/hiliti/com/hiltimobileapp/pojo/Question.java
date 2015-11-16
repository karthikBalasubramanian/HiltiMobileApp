package quiz.mobile.hiliti.com.hiltimobileapp.pojo;

/**
 * Created by Poorna on 13/11/2015.
 */
public class Question {




    private int qid;
    private String type;
    private String text;
    private int difficulty;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAns;
    private String status;


    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Question(String type, String text, int difficulty, String optionA, String optionB, String optionC,
                    String optionD, String correctAns, String status) {

        this.type = type;
        this.text = text;
        this.difficulty = difficulty;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
        this.status = status;
    }


    public Question(int qid) {
        this.qid = qid;
    }

    public Question(){

    }

    public Question(int qid, String type, String text, int difficulty, String optionA, String optionB, String optionC,
                    String optionD, String correctAns, String status) {
        this.qid = qid;
        this.type = type;
        this.text = text;
        this.difficulty = difficulty;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAns = correctAns;
        this.status = status;
    }


}
