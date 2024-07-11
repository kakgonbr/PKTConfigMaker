package misc;

public class InterfaceEntry {
    private String interName;
    private String interIP;
    private String interMask;

    public InterfaceEntry(){}

    public InterfaceEntry(String _name, String _IP, String _mask){
        interName = _name;
        interIP = _IP;
        interMask = _mask;
    }

    public String getInterIP() {
        return interIP;
    }

    public String getInterMask() {
        return interMask;
    }

    public String getInterName() {
        return interName;
    }

    public boolean setInterIP(String _interIP) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(_interIP);
        return matcher.find() && misc.Utils.validateIP(interIP = matcher.group(0));
    }

    public boolean setInterMask(String _interMask) {
        int tempInt = 0;
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(_interMask);
        if (matcher.find()) return misc.Utils.validateSubnet(interMask = matcher.group(0));

        matcher = java.util.regex.Pattern.compile("\\d{1,2}").matcher(_interMask);
        if (matcher.find() && (tempInt = Integer.parseInt(_interMask)) < 31 && tempInt > -1)
            return !(interMask = misc.Utils.getSubnet(tempInt)).isBlank();
        return false;
    }

    public boolean setInterName(String _interName) {
        return !(interName = _interName.trim()).isBlank();
    }

    public boolean isValid(){
        return interIP != null && interMask != null && interName != null;
    }

    @Override
    public String toString() {
        return "Name: " + interName + ", IP: " + interIP + ", Mask: " + interMask; 
    }
}
