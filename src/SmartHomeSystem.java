import java.util.*;

public class SmartHomeSystem {
    private Map<String, SmartDevice> devices = new LinkedHashMap<>();
    private List<AutomationRule> rules = new ArrayList<>();
    private List<AutomationRule> remove = new ArrayList<>();

    public String addDevice(String type, String name, String protocol) {
        if (devices.containsKey(name))
            return "duplicate device name";

        if (!protocol.equals("WiFi") && !protocol.equals("Bluetooth"))
            return "invalid input";

        SmartDevice device;
        switch (type) {
            case "light":
                device = new Light(name, protocol);
                break;
            case "thermostat":
                device = new Thermostat(name, protocol);
                break;
            default:
                return "invalid input";
        }

        devices.put(name, device);
        return "device added successfully";
    }

    public String setDevice(String name, String property, String value) {
        SmartDevice device = devices.get(name);
        if (device == null)
            return "device not found";

        boolean result = device.setProperty(property, value);
        return result ? "device updated successfully" : "invalid " + (property.equals("status") ? "value" : "property");
    }

    public String removeDevice(String name) {
        if (!devices.containsKey(name))
            return "device not found";

        devices.remove(name);
        for (AutomationRule rule : rules) {
            if (rule.getDeviceName().equals(name)) {
                remove.add(rule);
            }

        }
        rules.removeAll(remove);
        return "device removed successfully";
    }

    public String listDevices() {
        if (devices.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (SmartDevice device : devices.values()) {
            sb.append(device.getStatusString()).append("\n");
        }
        return sb.toString().trim();
    }

    public String addRule(String name, String time, String action) {
        if (!devices.containsKey(name)) return "device not found";
        if (!time.matches("\\d{2}:\\d{2}"))
            return "invalid time";

        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
            return "invalid time";

        if (!action.equals("on") && !action.equals("off")) return "invalid action";

        for (AutomationRule rule : rules) {
            if (rule.getDeviceName().equals(name) && rule.getTime().equals(time))
                return "duplicate rule";
        }

        rules.add(new AutomationRule(name, time, action));
        return "rule added successfully";
    }

    public String checkRules(String time) {
        if (!time.matches("\\d{2}:\\d{2}"))
            return "invalid time";

        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
            return "invalid time";

        for (AutomationRule rule : rules) {
            if (rule.getTime().equals(time)) {
                SmartDevice device = devices.get(rule.getDeviceName());
                if (device != null) {
                    if (rule.getAction().equals("on")) device.turnOn();
                    else device.turnOff();
                }
            }
        }
        return "rules checked";
    }

    public String listRules() {
        if (rules.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (AutomationRule rule : rules) {
            sb.append(rule.getRuleString()).append("\n");
        }
        return sb.toString().trim();
    }
}
