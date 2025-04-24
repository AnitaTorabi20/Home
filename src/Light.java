public class Light  extends SmartDevice {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    @Override
    public boolean setProperty(String property, String value) {
        switch (property) {
            case "status":
                if (value.equals("on")) turnOn();
                else if (value.equals("off")) turnOff();
                else return false;
                return true;
            case "brightness":
                try {
                    int b = Integer.parseInt(value);
                    if (b < 0 || b > 100) return false;
                    this.brightness = b;
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
        return name + " " + (isOn ? "on" : "off") + " " + brightness + "% " + protocol;
    }
}
