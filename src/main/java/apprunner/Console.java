package apprunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static Thread appiumThread;
    private static Process process;
    private static final String ADB_COMMAND = "adb";
    private static final String SHELL_COMMAND = "shell";

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
                System.out.println("=== Поток с Appium был прерван ===");
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

    private static void adbShellCommand(String... command) throws IOException, InterruptedException {
        String[] adbShellCommand = new String[command.length + 2];
        adbShellCommand[0] = ADB_COMMAND;
        adbShellCommand[1] = SHELL_COMMAND;
        System.arraycopy(command, 0, adbShellCommand, 2, command.length);

        new ProcessBuilder(adbShellCommand).start().waitFor();
    }

    public static void runApp(App app) throws IOException, InterruptedException {
        adbShellCommand("monkey", "-p", app.getPackageName(), "-c", "android.intent.category.LAUNCHER 1");
    }

    public static void closeApp(App app) throws IOException, InterruptedException {
        adbShellCommand("am", "force-stop", app.getPackageName());
    }

    public static void clearCache(App app) throws IOException, InterruptedException {
        adbShellCommand("pm", "clear", app.getPackageName());
    }

    public static void setFirebaseDebugView(App app, boolean enable) throws IOException, InterruptedException {
        String command = enable ? app.getPackageName() : ".none.";
        adbShellCommand("setprop", "debug.firebase.analytics.app", command);
    }

    public static String getVersionName(App app) throws IOException, InterruptedException {
        String[] command = {ADB_COMMAND, SHELL_COMMAND, "dumpsys", "package", app.getPackageName()};
        Process process = new ProcessBuilder(command).start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            process.waitFor();
            return reader.lines()
                    .filter(line -> line.contains("versionName"))
                    .map(line -> line.split("=")[1])
                    .findFirst()
                    .orElse(null);
        }
    }

}
