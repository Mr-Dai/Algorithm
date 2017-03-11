# ZigZag Conversion

The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: `"PAHNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

```
string convert(string text, int nRows);
```

`convert("PAYPALISHIRING", 3)` should return `"PAHNAPLSIIGYIR"`.

Check it on [LeetCode](https://leetcode.com/problems/zigzag-conversion/)

## Solution

Not a very hard problem if you can deduce the index mapping between the result string and the input string.

```java
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
```

## Submission Run Time

| | n |
| --- | ---: |
| C | 26ms |
| Java | 54ms |
