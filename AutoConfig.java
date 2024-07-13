public class AutoConfig extends view.Menu<String>{
    private static int tempInt;
    private static String inp;
    private static String tempStr;

    private static view.Menu<String> menuSwitch = new view.Menu<String>(new String[]{"Create new config", "Show current config", "Edit Config", "Return"}, "Switch Config") {
        @Override
        protected boolean execute(int choice) {
            switch (choice) {
                case 1:
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Host Name: ")).isBlank() || !SwitchConfig.setHostName(inp)) System.out.println("Invalid Host Name, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Console Password: ")).isBlank() || !SwitchConfig.setConPass(inp)) System.out.println("Invalid Console Password, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Secret Password: ")).isBlank() || !SwitchConfig.setSecret(inp)) System.out.println("Invalid Secret Password, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("VTY Password: ")).isBlank() || !SwitchConfig.setVtyPass(inp)) System.out.println("Invalid VTY Password, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("MOTD: ")).isBlank() || !SwitchConfig.setMotd(inp)) System.out.println("Invalid MOTD, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Vlan IP: ")).isBlank() || !SwitchConfig.setVlanIP(inp)) System.out.println("Invalid Vlan IP, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0;
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Vlan Subnet Mask: ")).isBlank() || !SwitchConfig.setVlanMask(inp)) System.out.println("Invalid Vlan Subnet Mask, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Default Gateway: ")).isBlank() || !SwitchConfig.setDefGate(inp)) System.out.println("Invalid IP, " + (3 - tempInt++) + " attempts left.");

                    SwitchConfig.setSaveReload(misc.Utils.getLine("Save and reload config (y/n)?: ").contains("y"));
                    break;
                case 2:
                    System.out.println(tempStr = SwitchConfig.getConfig());
                    if (misc.Utils.getLine("Copy to clipboard (y/n)?: ").contains("y")) copyToClipBoard(tempStr);
                    break;
                case 3:
                    submenuSwitch.display();
                    break;
                case 4:
                    System.out.println("Returning.");
                    return false;
                default:
                    System.out.println("Enter 4 to return to main menu.");
                    break;
            }
            return true;
        }
        
    };
    private static view.Menu<String> submenuSwitch = new view.Menu<String>(new String[]{"Host Name", "Console Password", "Secret Password", "VTY Password", "MOTD", "Vlan IP", "Vlan Subnet Mask", "Save and reload", "Default Gateway", "Return"}, "Modify") {

        @Override
        protected boolean execute(int choice) {
            switch (choice) {
                case 1: // hn
                    if ((inp = misc.Utils.getLine("Host Name: ")).isBlank() || !SwitchConfig.setHostName(inp)) System.out.println("Invalid Host Name.");
                    break;
                case 2: // cp
                    if ((inp = misc.Utils.getLine("Console Password: ")).isBlank() || !SwitchConfig.setConPass(inp)) System.out.println("Invalid Console Password.");
                    break;
                case 3: // sp
                    if ((inp = misc.Utils.getLine("Secret Password: ")).isBlank() || !SwitchConfig.setSecret(inp)) System.out.println("Invalid Secret Password");
                    break;
                case 4: // vtyp
                    if ((inp = misc.Utils.getLine("VTY Password: ")).isBlank() || !SwitchConfig.setVtyPass(inp)) System.out.println("Invalid VTY Password");
                    break;
                case 5: // mo
                    if ((inp = misc.Utils.getLine("MOTD: ")).isBlank() || !SwitchConfig.setMotd(inp)) System.out.println("Invalid MOTD");
                    break;
                case 6: // vip
                    if ((inp = misc.Utils.getLine("Vlan IP: ")).isBlank() || !SwitchConfig.setVlanIP(inp)) System.out.println("Invalid Vlan IP");
                    break;
                case 7: // vsn
                    if ((inp = misc.Utils.getLine("Vlan Subnet Mask: ")).isBlank() || !SwitchConfig.setVlanMask(inp)) System.out.println("Invalid Vlan Subnet Mask");
                    break;
                case 8: // sar
                    SwitchConfig.setSaveReload(misc.Utils.getLine("Save and reload config (y/n)?: ").contains("y"));
                    break;
                case 9:
                    if ((inp = misc.Utils.getLine("Default Gateway: ")).isBlank() || !SwitchConfig.setDefGate(inp)) System.out.println("Invalid IP");
                    break;
                case 10: // re
                    System.out.println("Returning.");
                    return false;
                default:
                    System.out.println("Enter 10 to return.");
                    break;
            }
            return true;
        }
        
    };

    private static view.Menu<String> menuRouter = new view.Menu<String>(new String[]{"Create new config", "Show current config", "Edit config", "Return"}, "Router config") {

        @Override
        protected boolean execute(int choice) {
            switch (choice) {
                case 1:
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Host Name: ")).isBlank() || !RouterConfig.setHostName(inp)) System.out.println("Invalid Host Name, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Console Password: ")).isBlank() || !RouterConfig.setConPass(inp)) System.out.println("Invalid Console Password, " + (3 - tempInt++) + " attempts left.");
                    tempInt = 0; 
                    while (tempInt < 4 && (inp = misc.Utils.getLine("Secret Password: ")).isBlank() || !RouterConfig.setSecret(inp)) System.out.println("Invalid Secret Password, " + (3 - tempInt++) + " attempts left.");

                    while ((inp = misc.Utils.getLine("Add an interface config (y/n)?: ")).contains("y")) {
                        misc.InterfaceEntry ie = new misc.InterfaceEntry();
                        tempInt = 0;
                        while (tempInt < 4 && !ie.setInterName(misc.Utils.getLine("Interface name (ex giga 0/0/0): "))) System.out.println("Invalid Interface name, " + (3 - tempInt++) + " attempts left.");
                        while (tempInt < 4 && !ie.setInterIP(misc.Utils.getLine("Interface IP: "))) System.out.println("Invalid Interface IP, " + (3 - tempInt++) + " attempts left.");
                        while (tempInt < 4 && !ie.setInterMask(misc.Utils.getLine("Interface Subnet Mask: "))) System.out.println("Invalid Subnet Mask, " + (3 - tempInt++) + " attempts left.");
                        if (ie.isValid()) RouterConfig.addInterface(ie);
                    }

                    while ((inp = misc.Utils.getLine("Add an ipv4 route (y/n)?: ")).contains("y")) {
                        misc.RouteEntry route = new misc.RouteEntry();
                        tempInt = 0;
                        while (tempInt < 4 && !route.setRouteFrom(misc.Utils.getLine("From (IP): "))) System.out.println("Invalid IP, " + (3 - tempInt++) + " attempts left.");
                        while (tempInt < 4 && !route.setRouteMask(misc.Utils.getLine("Subnet Mask: "))) System.out.println("Invalid Subnet Mask, " + (3 - tempInt++) + " attempts left.");
                        while (tempInt < 4 && !route.setRouteTo(misc.Utils.getLine("To (IP): "))) System.out.println("Invalid IP, " + (3 - tempInt++) + " attempts left.");
                        if (route.isValid()) RouterConfig.addRoutes(route);
                    }
                    RouterConfig.setSaveReload(misc.Utils.getLine("Save and reload config (y/n)?: ").contains("y"));
                    break;
                case 2:
                    System.out.println(tempStr = RouterConfig.getConfig());
                    if (misc.Utils.getLine("Copy to clipboard (y/n)?: ").contains("y")) copyToClipBoard(tempStr);
                    break;
                case 3:
                    submenuRouter.display();
                    break;
                case 4:
                    System.out.println("Returning.");
                    return false;                    
                default:
                    System.out.println("Enter 4 to return.");
                    break;
            }
            return true;
        }
        
    };

    private static view.Menu<String> submenuRouter = new view.Menu<String>(new String[]{"Host Name", "Console Password", "Secret Password", "Add an Interface", "Delete an Interface", "Add a Route", "Delete a Route", "Save and reload", "Return"}, "Modify") {

        @Override
        protected boolean execute(int choice) {
            switch (choice) {
                case 1: // hn
                    if ((inp = misc.Utils.getLine("Host Name: ")).isBlank() || !RouterConfig.setHostName(inp)) System.out.println("Invalid Host Name.");
                    break;
                case 2: // cp
                    if ((inp = misc.Utils.getLine("Console Passowrd: ")).isBlank() || !RouterConfig.setConPass(inp)) System.out.println("Invalid Console Password.");
                    break;
                case 3: // sp
                    if ((inp = misc.Utils.getLine("Secret Password: ")).isBlank() || !RouterConfig.setSecret(inp)) System.out.println("Invalid Secret Password.");
                    break;
                case 4: // ai
                    misc.InterfaceEntry ie = new misc.InterfaceEntry();
                        if (!ie.setInterName(misc.Utils.getLine("Interface name (ex giga 0/0/0): ")) 
                        || !ie.setInterIP(misc.Utils.getLine("Interface IP: ")) 
                        || !ie.setInterMask(misc.Utils.getLine("Interface Subnet Mask: ")) ) System.out.println("Invalid Input.");
                        if (ie.isValid()) RouterConfig.addInterface(ie);
                    break;
                case 5: // di
                    var entries = RouterConfig.getInterfaceEntries();
                    tempInt = 0;
                    for (final misc.InterfaceEntry entry : entries)
                        System.out.println((++tempInt) + ". " + entry);

                    if (!(inp = misc.Utils.getLine("Delete: ", misc.Utils.validations.vInt)).isBlank() && (tempInt = Integer.parseInt(inp)) > 0 && tempInt <= entries.size()) entries.remove(tempInt - 1);
                    break;
                case 6: // ar
                        misc.RouteEntry route = new misc.RouteEntry();
                        if (!route.setRouteFrom(misc.Utils.getLine("From (IP): ")) 
                        || !route.setRouteMask(misc.Utils.getLine("Mask: ")) 
                        || !route.setRouteTo(misc.Utils.getLine("To (IP): ")) ) System.out.println("Invalid Input.");
                        if (route.isValid()) RouterConfig.addRoutes(route);
                    break;
                case 7: // dr
                    var routes = RouterConfig.getRoutesEntries();
                    tempInt = 0;
                    for (final misc.RouteEntry routeEntry : routes)
                        System.out.println((++tempInt) + ". " + routeEntry);

                    if (!(inp = misc.Utils.getLine("Delete: ", misc.Utils.validations.vInt)).isBlank() && (tempInt = Integer.parseInt(inp)) > 0 && tempInt <= routes.size()) routes.remove(tempInt - 1);
                    break;
                case 8: // sar
                    RouterConfig.setSaveReload(misc.Utils.getLine("Save and reload config (y/n)?: ").contains("y"));
                    break;
                case 9: // ret
                    System.out.println("Returning.");
                    return false;
                default:
                    System.out.println("Enter 9 to return.");
                    break;
            }
            return true;
        }

    };

    public AutoConfig() {
        super(new String[]{"Switch config", "Router config", "Subnet mask calculator", "Exit"}, "Packet tracer utilities");
    }

    protected boolean execute(int choice) {
        switch (choice) {
            case 1: // Switch
                menuSwitch.display();
                break;
            case 2: // Router
                menuRouter.display();
                break;
            case 3:
                if ((inp = misc.Utils.getLine("Enter number of network bits: ", misc.Utils.validations.vInt)).isBlank() || (tempInt = Integer.parseInt(inp)) < 0)
                    {System.out.println("Invalid subnet number"); break;}
                System.out.println("Subnet mask for " + tempInt + " number of network bits: \033[33m" + (tempStr = misc.Utils.getSubnet(tempInt)) + "\033[0m");
                if ((inp = misc.Utils.getLine("Copy to clipboard (y/n)? ")).contains("y")) copyToClipBoard(tempStr);
                break;
            case 4:
                System.out.println("Exiting.");
                return false;
            default:
                System.out.println("Enter 4 to exit.");
                break;
        }
        return true;
    }
    private static void copyToClipBoard(String _tobCopied){
        java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new java.awt.datatransfer.StringSelection(_tobCopied), null);
        System.out.println("Copied to clipboard.");
    }

    public static void main(String[] args) {
        (new AutoConfig()).display();
    }
}