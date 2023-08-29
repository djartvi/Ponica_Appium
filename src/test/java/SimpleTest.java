import apprunner.App;
import com.google.firebase.remoteconfig.Template;
import firebase.RemoteConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SimpleTest {

    App app = new App();

    @Test
    public void simpleTest() throws IOException, InterruptedException, ExecutionException {

        RemoteConfig.initialize();
        Template template = RemoteConfig.getCurrentTemplate();
        RemoteConfig.setConditionAppVersion(template, "1.0");
        RemoteConfig.setTemplateValues(template, "number_ads_impressions", "16");
        RemoteConfig.publishConfig(template);
    }

}
