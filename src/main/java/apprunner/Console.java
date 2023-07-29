package apprunner;

import java.io.IOException;

public class Console {

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
                System.out.println("=== Appium thread was interrupted ===");
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

    private static void executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start().waitFor();
    }

    public static void runApp(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "monkey", "-p", app.packageName, "-c", "android.intent.category.LAUNCHER 1");
    }

    public static void closeApp(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "am", "force-stop", app.packageName);
    }

    public static void clearCache(App app) throws IOException, InterruptedException {
        executeCommand("adb", "shell", "pm", "clear", app.packageName);
    }

}
