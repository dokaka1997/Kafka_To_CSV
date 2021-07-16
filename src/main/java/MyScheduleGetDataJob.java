import java.util.Timer;
import java.util.TimerTask;

public class MyScheduleGetDataJob extends TimerTask {


    @Override
    public void run() {
//        String jsonMessage = MyKafkaConsumer.getMessage();
        String topicName = MyKafkaConsumer.TOPIC_NAME;
        CSVWriter csvWriter = new CSVWriter();
//        csvWriter.insertCSV(topicName, jsonMessage);
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        MyScheduleGetDataJob task = new MyScheduleGetDataJob();
        timer.schedule(task, 0);

    }
}
