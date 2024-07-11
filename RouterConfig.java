import java.util.ArrayList;

public class RouterConfig {
    private static String hostName;
    private static String secret = "";
    private static String conPass = "";
    private static ArrayList<misc.InterfaceEntry> interfaces = new ArrayList<>();
    private static ArrayList<misc.RouteEntry> routes = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder("\nconf term\n");
    private static boolean saveReload;

    public static boolean setConPass(String _conPass) {
        return !(conPass = _conPass.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static boolean setHostName(String _hostName) {
        return !(hostName = _hostName.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static boolean setSecret(String _secret) {
        return !(secret = _secret.trim().replaceAll("^\\s*(\\S+).*", "$1")).isBlank();
    }

    public static void setSaveReload(boolean _saveReload) {
        saveReload = _saveReload;
    }

    public static boolean addInterface(String _name, String _IP, String _mask){
        misc.InterfaceEntry ie = new misc.InterfaceEntry();

        if (!ie.setInterName(_name)
            || !ie.setInterIP(_IP)
            || !ie.setInterMask(_mask)) return false;
        interfaces.add(ie);
        return true;
    }

    public static void addInterface(misc.InterfaceEntry ie){
        interfaces.add(ie);
    }

    public static ArrayList<misc.InterfaceEntry> getInterfaceEntries(){
        return interfaces;
    }

    public static void deleteInterface(int index){
        interfaces.remove(index);
    }

    public static void deleteInterface(java.util.function.Predicate<misc.InterfaceEntry> _pred){
        interfaces.removeIf(_pred);
    }

    public static void removeAllInterfaces(){
        interfaces.clear();
    }

    public static boolean addRoutes(String _from, String _to, String _mask){
        misc.RouteEntry route = new misc.RouteEntry();

        if (!route.setRouteFrom(_from)
            || !route.setRouteTo(_to)
            || !route.setRouteMask(_mask)) return false;
        routes.add(route);
        return true;
    }

    public static void addRoutes(misc.RouteEntry _route){
        routes.add(_route);
    }

    public static ArrayList<misc.RouteEntry> getRoutesEntries(){
        return routes;
    }

    public static void deleteRoutes(int index){
        routes.remove(index);
    }

    public static void deleteRoutes(java.util.function.Predicate<misc.RouteEntry> _pred){
        routes.removeIf(_pred);
    }

    public static void removeAllRoutess(){
        routes.clear();
    }

    
    public static String getConfig(){
        builder = new StringBuilder("\nconf term\n");
        for (final misc.InterfaceEntry ie : interfaces){
            builder.append("int " + ie.getInterName() + "\nip addr " + ie.getInterIP() + " " + ie.getInterMask() + "\nno shut\nexit\n");
        }

        for (final misc.RouteEntry route : routes){
            builder.append("ip route " + route.getRouteFrom() + " " + route.getRouteMask() + " " + route.getRouteTo() + "\n");
        }

        // for (final String key : routes.keySet()){
        //     builder.append("ip route " + key + " " + routes.get(key) + "\n");
        // }
        return String.format("\nen\nconf term\nno ip domain-lookup\nservice password\nhostname %s\nenable secret %s\nline con 0\npassword %s\nlogin\nexit\n", hostName, secret, conPass) + builder.toString()  + (saveReload ? "exit\ncopy runn startu\n\nreload\n": "");
    }
}
