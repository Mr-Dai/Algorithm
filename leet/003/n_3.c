#include <stdio.h>
#include <string.h>

int lengthOfLongestSubstring(char* s) {
    unsigned int len = strlen(s);
    if (len == 0)
        return 0;
    if (len == 1)
        return 1;

    int result = 0, current_len = 0;
    int i, j, k;
    for (i = 0; i < len; i++) {
        for (j = i; j < len; j++) {
            for (k = i; k < j; k++)
                if (s[k] == s[j]) // Found duplicate
                    break;
            if (k >= j) { // No duplicate is found
                current_len = j - i + 1;
                result = current_len > result ? current_len : result;
            }
        }
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
