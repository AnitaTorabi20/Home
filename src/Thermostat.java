public class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String name, String protocol) {
        super(name, protocol);
        this.temperature = 20;
    }
    @Override
    public boolean setProperty(String property,String value) {
        switch (property) {
            case "status":
                if (value.equals("on")) turnOn();
                else if (value.equals("off")) turnOff();
                else return false;
                return true;
            case "temperature":
                try {
                    int t = Integer.parseInt(value);
                    if (t < 10 || t > 30) return false;
                    this.temperature = t;
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public String getStatusString() {
        return name + " " + (isOn ? "on" : "off") + " " + temperature + "C "+ protocol;
    }
}
