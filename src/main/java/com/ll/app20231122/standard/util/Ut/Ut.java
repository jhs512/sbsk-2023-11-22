package com.ll.app20231122.standard.util.Ut;

public class Ut {
    public static class cmd {

        public static void runAsync(String cmd) {
            new Thread(() -> {
                run(cmd);
            }).start();
        }

        public static void run(String cmd) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
                Process process = processBuilder.start();
                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
