
public class interview {
    public static String entityParser(String text) {

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < text.length()) {
            char ch = text.charAt(i);
            if (ch == '&') {
                String str = "&";
                i++;
                while (i < text.length() && (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')) {
                    char c = text.charAt(i++);
                    str = str + c;
                }
                
                switch (str) {
                    case "&quot":
                        sb.append('"');
                        break;
                    case "&apos":
                        sb.append('.');
                        break;
                    case "&amp":
                        sb.append('&');
                        break;
                    case "&gt":
                        sb.append('>');
                        break;
                    case "&lt":
                        sb.append('<');
                        break;
                    case "&frasl":
                        sb.append('/');
                        break;
                    default:
                        sb.append(str);
                        
                }
                
            } else {
                sb.append(ch);
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String str = "&amp; is an HTML entity but &ambassador; is not.";
        String str = "and I quote: &quot;...&quot;";
        System.out.println(entityParser(str));
    }
}