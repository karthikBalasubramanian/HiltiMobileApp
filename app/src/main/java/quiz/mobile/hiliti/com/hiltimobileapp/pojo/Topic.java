package quiz.mobile.hiliti.com.hiltimobileapp.pojo;

/**
 * Created by Poorna on 18/11/2015.
 */
public class Topic {

    private int topicid;

    private String topicName;


    public int getTopicid() {
        return topicid;
    }
    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }
    public String getTopicName() {
        return topicName;
    }
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
    public Topic(String topicName) {

        this.topicName = topicName;
    }
    public Topic() {

    }


    public Topic(int topicid, String topicName) {
        super();
        this.topicid = topicid;
        this.topicName = topicName;
    }
}
