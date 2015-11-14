package quiz.mobile.hiliti.com.hiltimobileapp.pojo;

/**
 * Created by Poorna on 13/11/2015.
 */
public class Question {




    private int qid;
    private String type;
    private String text;
    private int difficulty;
    private byte[] optionA;
    private byte[] optionB;
    private byte[] optionC;
    private byte[] optionD;
    private byte[] correctAns;
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

    public byte[] getOptionA() {
        return optionA;
    }

    public void setOptionA(byte[] optionA) {
        this.optionA = optionA;
    }

    public byte[] getOptionB() {
        return optionB;
    }

    public void setOptionB(byte[] optionB) {
        this.optionB = optionB;
    }

    public byte[] getOptionC() {
        return optionC;
    }

    public void setOptionC(byte[] optionC) {
        this.optionC = optionC;
    }

    public byte[] getOptionD() {
        return optionD;
    }

    public void setOptionD(byte[] optionD) {
        this.optionD = optionD;
    }

    public byte[] getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(byte[] correctAns) {
        this.correctAns = correctAns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Question(String type, String text, int difficulty, byte[] optionA, byte[] optionB, byte[] optionC,
                    byte[] optionD, byte[] correctAns, String status) {

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
        super();
        this.qid = qid;
    }

    public Question(int qid, String type, String text, int difficulty, byte[] optionA, byte[] optionB, byte[] optionC,
                    byte[] optionD, byte[] correctAns, String status) {
        super();
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
