#include <stdio.h>
#include <string.h>

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

int main() {
    printf("%d\n", lengthOfLongestSubstring(""));
    printf("%d\n", lengthOfLongestSubstring("au"));
    printf("%d\n", lengthOfLongestSubstring("abcabcbb"));
    printf("%d\n", lengthOfLongestSubstring("bbbbb"));
    printf("%d\n", lengthOfLongestSubstring("pwwkew"));
}
