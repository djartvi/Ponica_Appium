package apprunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class Console {

    private static Thread appiumThread;
    private static Process AppiumProcess;
    private static final String ADB_COMMAND = "adb";
    private static final String SHELL_COMMAND = "shell";
    private static final String SELECT_DEVICE_PREFIX = "-s";
    private static final String APP_PATH = "src/main/resources/1.apk";
    private static String DEVICE;
    private static boolean shown;

    public static void runAppium() {
        ProcessBuilder runAppium = new ProcessBuilder("appium");

        if (System.getProperty("appiumLog").equals("true")) {
        runAppium.inheritIO();
        }

        appiumThread = new Thread(() -> {
            try {
                AppiumProcess = runAppium.start();
                AppiumProcess.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ignored) {
            }
        });

        appiumThread.start();
    }

    public static void stopAppium() {
        if (AppiumProcess != null) {
            AppiumProcess.destroy();
            try {
                AppiumProcess.waitFor(); // Wait for the process to terminate
                System.out.println("Процесс Appium остановлен");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (appiumThread != null && appiumThread.isAlive()) {
            appiumThread.interrupt();
            try {
                appiumThread.join(); // Wait for the thread to finish
                System.out.println("Поток Appium остановлен");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String selectDevice() {
        switch (System.getProperty("device")) {
            case "samsung":
                DEVICE = Devices.SAMSUNG_S10;
                break;
            case "xiaomi":
                DEVICE = Devices.XIAOMI_REDMI_9A;
                break;
            default:
                if (!shown) {
                    System.err.println("Такого устройства нет в списке: " + System.getProperty("device"));
                    shown = true;
                }
        }

        return DEVICE;
    }

    private static Process adbShellCommand(String... command) throws IOException {
        String[] adbShellCommand;

        if (selectDevice() != null) {
            adbShellCommand = new String[command.length + 4];
            adbShellCommand[0] = ADB_COMMAND;
            adbShellCommand[1] = SELECT_DEVICE_PREFIX;
            adbShellCommand[2] = DEVICE;
            adbShellCommand[3] = SHELL_COMMAND;
            System.arraycopy(command, 0, adbShellCommand, 4, command.length);
        } else {
            adbShellCommand = new String[command.length + 2];
            adbShellCommand[0] = ADB_COMMAND;
            adbShellCommand[1] = SHELL_COMMAND;
            System.arraycopy(command, 0, adbShellCommand, 2, command.length);
        }

        return new ProcessBuilder(adbShellCommand).start();
    }

    private static Process adbNotShellCommand(String... command) throws IOException {
        String[] adbShellCommand;

        if (selectDevice() != null) {
            adbShellCommand = new String[command.length + 3];
            adbShellCommand[0] = ADB_COMMAND;
            adbShellCommand[1] = SELECT_DEVICE_PREFIX;
            adbShellCommand[2] = DEVICE;
            System.arraycopy(command, 0, adbShellCommand, 3, command.length);
        } else {
            adbShellCommand = new String[command.length + 1];
            adbShellCommand[0] = ADB_COMMAND;
            System.arraycopy(command, 0, adbShellCommand, 1, command.length);
        }

        return new ProcessBuilder(adbShellCommand).start();
    }

    private static String getConsoleError(Process process) throws IOException {
        StringJoiner errorOutput = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                errorOutput.add(line);
            }
        }

        return errorOutput.toString();

    }

    private static boolean isAppAlreadyInstalled(App app) throws IOException, InterruptedException {
        Process process = adbShellCommand("pm", "list", "packages");
        process.waitFor();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            return reader.lines()
                    .anyMatch(line -> line.contains(app.getPackageName()));
        }

    }

    public static void installApp(App app) throws IOException, InterruptedException {
        if (app.getPackageName() != null && isAppAlreadyInstalled(app)) {
            System.err.println("Приложение было установлено ранее");
            uninstallApp(app);
        }

        Process process = adbNotShellCommand("install", APP_PATH);
        System.out.println("Установка приложения...");
        process.waitFor();

        if (process.exitValue() == 0) {
            System.out.println("APK успешно установлен.");
        } else {
            System.err.println("Не удалось установить приложение:\n" + getConsoleError(process));
        }
    }

    public static void uninstallApp(App app) throws IOException, InterruptedException {
        Process process = adbShellCommand("pm", "uninstall", app.getPackageName());
        System.out.println("Приложение удаляется...");
        process.waitFor();

        if (process.exitValue() == 0) {
            System.out.println("Приложение и все его данные удалены");
        } else {
            System.err.println("Не удалось удалить приложение:\n" + getConsoleError(process));
        }
    }

    public static void runApp(App app) throws IOException, InterruptedException {
        adbShellCommand("monkey", "-p", app.getPackageName(), "-c", "android.intent.category.LAUNCHER 1").waitFor();
    }

    public static void closeApp(App app) throws IOException, InterruptedException {
        adbShellCommand("am", "force-stop", app.getPackageName()).waitFor();
    }

    public static void clearCache(App app) throws IOException, InterruptedException {
        adbShellCommand("pm", "clear", app.getPackageName()).waitFor();
    }

    public static void setFirebaseDebugView(App app, boolean enable) throws IOException, InterruptedException {
        String command = enable ? app.getPackageName() : ".none.";
        adbShellCommand("setprop", "debug.firebase.analytics.app", command).waitFor();
    }

    public static Process getCurrentActivity() throws IOException {
        return adbShellCommand("dumpsys activity | grep top-activity");
    }

    public static String getVersionName(App app) throws IOException, InterruptedException {
        Process process = adbShellCommand("dumpsys", "package", app.getPackageName());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            process.waitFor();
            String errorMessage = "\u001B[31mНе удалось получить версию приложения\u001B[0m";

            return reader.lines()
                    .filter(line -> line.contains("versionName"))
                    .map(line -> line.split("=")[1])
                    .findFirst()
                    .orElse(errorMessage);
        }
    }

}
