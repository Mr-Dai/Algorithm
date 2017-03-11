public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        int i, j, next;
        StringBuilder builder = new StringBuilder();
        // Compute first row
        for (i = 0; i < s.length(); i += 2 * numRows - 2)
            builder.append(s.charAt(i));
        // Compute intermediate rows
        for (i = 1; i < numRows - 1; i++) {
            for (j = i; j < s.length(); j += 2 * numRows - 2) {
                builder.append(s.charAt(j));
                next = j + 2 * numRows - 2 - 2 * i;
                if (next < s.length())
                    builder.append(s.charAt(next));
            }
        }
        // Compute last row
        for (i = numRows - 1; i < s.length(); i += 2 * numRows - 2)
            builder.append(s.charAt(i));
        return builder.toString();
    }
}
