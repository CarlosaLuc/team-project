package data_access;

import java.util.ArrayList;
import java.util.List;

/**
 * Reads and writes one CSV line. Values that contain a comma or a quote are
 * wrapped in quotes, so names like "Max, Jr." are stored safely.
 */
final class CsvUtils {

    private CsvUtils() {
    }

    /** Joins values into one CSV line. */
    static String join(String... values) {
        final StringBuilder line = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                line.append(',');
            }
            line.append(escape(values[i]));
        }
        return line.toString();
    }

    /** Splits one CSV line into its values. */
    static List<String> split(String line) {
        final List<String> values = new ArrayList<>();
        final StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            final char c = line.charAt(i);
            if (inQuotes) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                }
                else if (c == '"') {
                    inQuotes = false;
                }
                else {
                    current.append(c);
                }
            }
            else if (c == '"') {
                inQuotes = true;
            }
            else if (c == ',') {
                values.add(current.toString());
                current.setLength(0);
            }
            else {
                current.append(c);
            }
        }
        values.add(current.toString());
        return values;
    }

    private static String escape(String value) {
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
