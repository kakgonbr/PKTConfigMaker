package misc;

public class RouteEntry {
    private String routeTo;
    private String routeFrom;
    private String routeMask;

    public RouteEntry(){}

    public RouteEntry(String _from, String _to, String _mask){
        routeFrom = _from;
        routeTo = _to;
        routeMask = _mask;
    }

    public String getRouteFrom() {
        return routeFrom;
    }

    public String getRouteMask() {
        return routeMask;
    }

    public String getRouteTo() {
        return routeTo;
    }

    public boolean setRouteFrom(String _routeFrom) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(_routeFrom);
        return matcher.find() && misc.Utils.validateIP(routeFrom = matcher.group(0));
    }

    public boolean setRouteMask(String _routeMask) {
        int tempInt = 0;
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(_routeMask);
        if (matcher.find()) return misc.Utils.validateSubnet(routeMask = matcher.group(0));

        matcher = java.util.regex.Pattern.compile("\\d{1,2}").matcher(_routeMask);
        if (matcher.find() && (tempInt = Integer.parseInt(_routeMask)) < 31 && tempInt > -1)
            return !(routeMask = misc.Utils.getSubnet(tempInt)).isBlank();
        return false;
    }

    public boolean setRouteTo(String _routeTo) {
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(_routeTo);
        return matcher.find() && misc.Utils.validateIP(routeTo = matcher.group(0));
    }

    public boolean isValid(){
        return routeFrom != null && routeMask != null && routeTo != null;
    }

    @Override
    public String toString() {
        return "From: " + routeFrom + ", To: " + routeTo + ", Mask: " + routeMask; 
    }
}
