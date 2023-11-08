package firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import com.google.firebase.remoteconfig.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static firebase.ServiceAccountPaths.DATA_RECOVERY_SERVICE_FILE;

public class RemoteConfig {

    public static final String QA_AUTO_CONDITION_NAME = "QA AUTO test";

    public RemoteConfig() throws IOException {
        initialize();
    }
    private void initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(DATA_RECOVERY_SERVICE_FILE);
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        FirebaseApp.initializeApp(options);
    }

    public Template getCurrentTemplate() throws ExecutionException, InterruptedException {
        System.out.println("ETag from server: " + FirebaseRemoteConfig.getInstance().getTemplateAsync().get().toJSON());
        return FirebaseRemoteConfig.getInstance().getTemplateAsync().get();
    // See the ETag of the fetched template.
    }

    public void printTemplate(Template template) {
        System.out.println(template.toJSON());
    }

    // устанавливаем значение для параетра в Firebase, например parameter = "number_ads_impressions", value = "15"
    public RemoteConfig setTemplateValues(Template template, String parameter, String value) {
        Map<String, ParameterValue> map = Map.of(QA_AUTO_CONDITION_NAME, ParameterValue.of(value));

        template.getParameters().get(parameter).setConditionalValues(map);
        return this;
    }

    public RemoteConfig setConditionAppVersion(Template template, String appVersion) {
        List<Condition> conditionList = template.getConditions();

        conditionList.stream()
                .filter(condition -> condition.getName().equals(QA_AUTO_CONDITION_NAME))
                .findFirst()
                .ifPresentOrElse(condition -> condition
                                .setExpression(String.format("app.version.exactlyMatches(['%s'])", appVersion)),
                        () -> System.err.println("Firebase. Имя условия не найдено: " + QA_AUTO_CONDITION_NAME)
                );

        template.setConditions(conditionList);
        return this;
    }


        public void publishConfig(Template template) {
        try {
            Template publishedTemplate = FirebaseRemoteConfig.getInstance()
                    .publishTemplateAsync(template).get();
            System.out.println("Опубликована новая конфигурация Remote Config");
            // See the ETag of the published template.
            System.out.println("ETag from server: " + publishedTemplate.getETag());
        } catch (ExecutionException | InterruptedException e) {
            if (e.getCause() instanceof FirebaseRemoteConfigException) {
                FirebaseRemoteConfigException rcError = (FirebaseRemoteConfigException) e.getCause();
                System.out.println("Не удалось опубликовать конфигурацию Remote Config");
                System.out.println(rcError.getMessage());
            }
        }
    }

}
