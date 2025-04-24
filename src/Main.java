import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();
        List<String> outputs = new ArrayList<>();

        int q = Integer.parseInt(scanner.nextLine());
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            commands.add(scanner.nextLine());
        }

        for (String line : commands) {
            String[] parts = line.split(" ");
            String command = parts[0];

            switch (command) {
                case "add_device":
                    outputs.add(system.addDevice(parts[1], parts[2], parts[3]));
                    break;
                case "set_device":
                    outputs.add(system.setDevice(parts[1], parts[2], parts[3]));
                    break;
                case "remove_device":
                    outputs.add(system.removeDevice(parts[1]));
                    break;
                case "list_devices":
                    outputs.add(system.listDevices());
                    break;
                case "add_rule":
                    outputs.add(system.addRule(parts[1], parts[2], parts[3]));
                    break;
                case "check_rules":
                    outputs.add(system.checkRules(parts[1]));
                    break;
                case "list_rules":
                    outputs.add(system.listRules());
                    break;
            }
        }

        // در نهایت همه خروجی‌ها را نمایش بده
        for (String output : outputs) {
            System.out.println(output);
        }

        scanner.close();
    }
}