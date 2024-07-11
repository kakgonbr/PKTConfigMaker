public class SwitchConfig {
    private static String hostName;
    private static String secret;
    private static String conPass;
    private static String vlanIP;
    private static String vlanMask;
    private static String vtyPass;
    private static String motd;
    private static boolean saveReload;
    private static int tempInt;
    private static java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    
    public static boolean setConPass(String _conPass) {
        return !(conPass = _conPass.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static boolean setHostName(String _hostName) {
        return !(hostName = _hostName.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static boolean setMotd(String _motd) {
        return !(motd = _motd.replaceAll("\n", " ").trim()).isBlank();
    }

    public static void setSaveReload(boolean _saveReload) {
        saveReload = _saveReload;
    }
    
    public static boolean setSecret(String _secret) {
        return !(secret = _secret.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static boolean setVlanIP(String _vlanIP) {
        java.util.regex.Matcher matcher = pattern.matcher(_vlanIP);

        return matcher.find() && misc.Utils.validateIP(vlanIP = matcher.group(0));
    }

    public static boolean setVlanMask(String _vlanMask) {
        java.util.regex.Matcher matcher = pattern.matcher(_vlanMask);
        if (matcher.find()) return misc.Utils.validateSubnet(vlanMask = matcher.group(0));

        matcher = java.util.regex.Pattern.compile("\\d{1,2}").matcher(_vlanMask);
        if (matcher.find() && (tempInt = Integer.parseInt(_vlanMask)) < 31 && tempInt > -1)
            return !(vlanMask = misc.Utils.getSubnet(tempInt)).isBlank();
        return false;
    }

    public static boolean setVtyPass(String _vtyPass) {
        return !(vtyPass = _vtyPass.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static String getConfig(){
        return String.format("\nen\nconf term\nno ip domain-lookup\nservice password\nhostname %s\nenable secret %s\nline con 0\npassword %s\nlogin\nexit\nint vlan1\nip address %s %s\nno shutdown\nexit\nline vty 0 4\npassword %s\nlogin\nexit\nbanner motd #%s#\n" + (saveReload ? "exit\ncopy runn startu\n\nreload\n": ""), hostName, secret, conPass, vlanIP, vlanMask, vtyPass, motd);
        }
}
