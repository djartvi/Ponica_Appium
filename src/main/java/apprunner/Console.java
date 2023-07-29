package apprunner;

import java.io.IOException;

public class Console {
    //поправить создание объекта класса App
    private String packageName;
    private static Thread appiumThread;
    private static Process process;

    public static void runAppium() {
        ProcessBuilder runAppium = new ProcessBuilder("appium");
        runAppium.inheritIO();

        appiumThread = new Thread(() -> {
            try {
                process = runAppium.start();
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.err.println("Appium thread was interrupted: " + e.getMessage());
            }
        });

        appiumThread.start();
    }

    public static void stopAppium() {
        if (process != null) {
            process.destroy();
        }

        if (appiumThread != null && appiumThread.isAlive()) {
            appiumThread.interrupt();
        }
    }

    private void executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start().waitFor();
    }

    public void runApp(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "monkey", "-p", app.packageName, "-c", "android.intent.category.LAUNCHER 1");
    }

    public void closeApp(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "am", "force-stop", app.packageName);
    }

    public void clearCache(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "pm", "clear", app.packageName);
    }

}
