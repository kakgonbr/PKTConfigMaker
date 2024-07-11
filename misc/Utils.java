
// JDK 17.0.10

package misc;

public class Utils {
    private static final java.util.HashMap<Integer, String> map = new java.util.HashMap<>(){{
        put(0, "0.0.0.0");
        put(1, "128.0.0.0");
        put(2, "192.0.0.0");
        put(3, "224.0.0.0");
        put(4, "240.0.0.0");
        put(5, "248.0.0.0");
        put(6, "252.0.0.0");
        put(7, "254.0.0.0");
        put(8, "255.0.0.0");
        put(9, "255.128.0.0");
        put(10, "255.192.0.0");
        put(11, "255.224.0.0");
        put(12, "255.240.0.0");
        put(13, "255.248.0.0");
        put(14, "255.252.0.0");
        put(15, "255.254.0.0");
        put(16, "255.255.0.0");
        put(17, "255.255.128.0");
        put(18, "255.255.192.0");
        put(19, "255.255.224.0");
        put(20, "255.255.240.0");
        put(21, "255.255.248.0");
        put(22, "255.255.252.0");
        put(23, "255.255.254.0");
        put(24, "255.255.255.0");
        put(25, "255.255.255.128");
        put(26, "255.255.255.192");
        put(27, "255.255.255.224");
        put(28, "255.255.255.240");
        put(29, "255.255.255.248");
        put(30, "255.255.255.252");
    }};
    public enum validations {
        vInt,
        vLong,
        vFloat,
        vDouble,
        vBool
    }
    
    public static String getLine(String message, validations val){
        System.out.print(message);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        switch(val){
            case vInt:
                if (scanner.hasNextInt()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vLong:
                if (scanner.hasNextLong()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vFloat:
                if (scanner.hasNextFloat()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vDouble:
                if (scanner.hasNextDouble()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            case vBool:
                if (scanner.hasNextBoolean()) return scanner.nextLine().split("\\s+")[0].trim();
                return "";
            default:
                return scanner.nextLine();
        }
    }
    
    public static String getLine(String message){
        System.out.print(message);
        return (new java.util.Scanner(System.in)).nextLine();
    }

    public static String getSubnet(int bits){
        return map.get(bits);
    }

    public static boolean validateSubnet(String _subnetMask){
        if (_subnetMask == null ||_subnetMask.isBlank()) return false;

        int[] octets = java.util.Arrays.stream(_subnetMask.split("\\.")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < 4; i++){
            if (octets[i] < 0 || octets[i] > 255) return false;
        }

        int binarySequence = (octets[0] << 24 | octets[1] << 16 | octets[2] << 8 | octets[3]);

        return binarySequence == 0 || Integer.toBinaryString(binarySequence).matches("1+0+");
    }

    public static boolean validateIP(String _IP){
        if (_IP == null ||_IP.isBlank()) return false;

        int[] octets = java.util.Arrays.stream(_IP.split("\\.")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < 4; i++){
            if (octets[i] < 0 || octets[i] > 255) return false;
        }
        return true;
    }
}
