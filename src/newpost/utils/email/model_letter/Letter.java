package newpost.utils.email.model_letter;

/**
 * Created by macaque on 24.07.2016.
 */
public class Letter {

    private String topic;
    private String fromName;
    private String toName;
    private String message;
    private String state;
    private String uid;

    public Letter(){

    }

    public Letter(String topic, String fromName, String toName, String message, String state, String uid) {
        this.topic = topic;
        this.fromName = fromName;
        this.toName = toName;
        this.message = message;
        this.state = state;
        this.uid = uid;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTopic() {
        return topic;
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    public String getMessage() {
        return message;
    }

    public String getState() {
        return state;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "From: " + fromName + "\n" +
                "Subject: " + topic + "\n" +
                "To: " + toName + "\n" +
                "Message: " + message + "\n" +
                "State: " + state + "\n" +
                 "uid: " + uid;
    }


}
