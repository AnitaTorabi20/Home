public abstract class SmartDevice {
    protected String name;
    protected String protocol;
    protected boolean isOn;

    public SmartDevice(String name, String protocol) {
        this.name = name;
        this.protocol = protocol;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getProtocol() {
        return protocol;
    }

    public abstract String getStatusString();
    public abstract boolean setProperty(String property, String value);

}
