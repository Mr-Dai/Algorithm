# Longest Substring Without Repeating Characters

Given a string, find the length of the __longest substring__ without repeating characters.

__Examples:__

Given `"abcabcbb"`, the answer is `"abc"`, which the length is 3.

Given `"bbbbb"`, the answer is `"b"`, with the length of 1.

Given `"pwwkew"`, the answer is `"wke"`, with the length of 3. Note that the answer must be a __substring__, `"pwke"` is a subsequence and not a substring.

## Solution

```c
int lengthOfLongestSubstring(char* s) {
    unsigned int len = strlen(s);
    int i, ascii_table[256];
    for (i = 0; i < 256; i++)
        ascii_table[i] = -1;
    int result = 0;
    int j, prev_loc, current_len = 0;
    for (i = 0, j = 0; j < len; j++) {
        prev_loc = ascii_table[s[j]];
        if (prev_loc != -1) { // Found duplicate
            // Record current length
            current_len = j - i;
            result = current_len > result ? current_len : result;
            // Reset lookup table
            for (; i <= prev_loc; i++)
                ascii_table[s[i]] = -1;
        }
        ascii_table[s[j]] = j;
    }
    current_len = j - i;
    return current_len > result ? current_len : result;
}
```
