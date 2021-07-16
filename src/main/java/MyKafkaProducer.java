import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MyKafkaProducer {

    private static final String TOPIC_NAME = "test";
    private static final String FIN_MESSAGE = "exit";

    public static void run(String message) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        while (true) {

            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
            try {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        // some exception
                    }
                });

            } catch (Exception e) {
                // exception
            } finally {
                producer.flush();
            }

            if (StringUtils.equals(message, FIN_MESSAGE)) {
                producer.close();
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String json = "{\n" +
                "  \"configGlossary\": \"Philadelphia, PA\",\n" +
                "  \"configGlossary\": \"ksm@pobox.com\",\n" +
                "  \"configGlossary\": \"Cofax\",\n" +
                "  \"configGlossary\": \"/images/cofax.gif\",\n" +
                "  \"configGlossary\": \"/content/static\",\n" +
                "  \"templateProcessorClass\": \"org.cofax.WysiwygTemplate\",\n" +
                "  \"templateLoaderClass\": \"org.cofax.FilesTemplateLoader\",\n" +
                "  \"templatePath\": \"templates\",\n" +
                "  \"templateOverridePath\": \"\",\n" +
                "  \"defaultListTemplate\": \"listTemplate.htm\",\n" +
                "  \"defaultFileTemplate\": \"articleTemplate.htm\",\n" +
                "  \"useJSP\": false,\n" +
                "  \"jspListTemplate\": \"listTemplate.jsp\",\n" +
                "  \"jspFileTemplate\": \"articleTemplate.jsp\",\n" +
                "  \"cachePackageTagsTrack\": 200,\n" +
                "  \"cachePackageTagsStore\": 200,\n" +
                "  \"cachePackageTagsRefresh\": 60,\n" +
                "  \"cacheTemplatesTrack\": 100,\n" +
                "  \"cacheTemplatesStore\": 50,\n" +
                "  \"cacheTemplatesRefresh\": 15,\n" +
                "  \"cachePagesTrack\": 200,\n" +
                "  \"cachePagesStore\": 100,\n" +
                "  \"cachePagesRefresh\": 10,\n" +
                "  \"cachePagesDirtyRead\": 10,\n" +
                "  \"searchEngineListTemplate\": \"forSearchEnginesList.htm\",\n" +
                "  \"searchEngineFileTemplate\": \"forSearchEngines.htm\",\n" +
                "  \"searchEngineRobotsDb\": \"WEB-INF/robots.db\",\n" +
                "  \"useDataStore\": true,\n" +
                "  \"dataStoreClass\": \"org.cofax.SqlDataStore\",\n" +
                "  \"redirectionClass\": \"org.cofax.SqlRedirection\",\n" +
                "  \"dataStoreName\": \"cofax\",\n" +
                "  \"dataStoreDriver\": \"com.microsoft.jdbc.sqlserver.SQLServerDriver\",\n" +
                "  \"dataStoreUrl\": \"jdbc:microsoft:sqlserver://LOCALHOST:1433;DatabaseName=goon\",\n" +
                "  \"dataStoreUser\": \"sa\",\n" +
                "  \"dataStorePassword\": \"dataStoreTestQuery\",\n" +
                "  \"dataStoreTestQuery\": \"SET NOCOUNT ON;select test='test';\",\n" +
                "  \"dataStoreLogFile\": \"/usr/local/tomcat/logs/datastore.log\",\n" +
                "  \"dataStoreInitConns\": 10,\n" +
                "  \"dataStoreMaxConns\": 100,\n" +
                "  \"dataStoreConnUsageLimit\": 100,\n" +
                "  \"dataStoreLogLevel\": \"debug\",\n" +
                "  \"maxUrlLength\": 500\n" +
                "}";
        while (true) {
            run(json);
            Thread.sleep(5000);
        }
    }

}
