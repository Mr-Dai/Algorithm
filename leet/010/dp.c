#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isMatch(char* s, char* p) {
    int i, j;
    int len_s = strlen(s), len_p = strlen(p);
    // Allocate memory
    bool** matches = (bool**) malloc((len_s+1) * sizeof(bool*));
    for (i = 0; i <= len_s; i++) {
        matches[i] = (bool*) malloc((len_p+1) * sizeof(bool));
        memset(matches[i], false, (len_p+1) * sizeof(bool));
    }
    matches[0][0] = true; // Empty input and empty Regex

    // Calculate the result set
    for (i = 0; i <= len_s; i++) {
        for (j = 1; j <= len_p; j++) {
            if (p[j - 1] == '*')
                matches[i][j] = matches[i][j-2] || i > 0 && matches[i-1][j] && (p[j-2] == '.' || s[i-1] == p[j-2]);
            else
                matches[i][j] = i > 0 && matches[i-1][j-1] && (p[j-1] == '.' || s[i-1] == p[j-1]);
        }
    }

    bool result = matches[len_s][len_p];
    // Release memory
    for (i = 0; i <= len_s; i++)
        free(matches[i]);
    free(matches);
    return result;
}

void print(bool b) {
    printf("%s\n", b ? "True" : "False");
}

int main() {
    print(isMatch("aa", "a")); // False
    print(isMatch("aa", "aa")); // True
    print(isMatch("aaa", "aa")); // False
    print(isMatch("aa", "a*")); // True
    print(isMatch("aa", ".*")); // True
    print(isMatch("ab", ".*")); // True
    print(isMatch("aab", "c*a*b")); // True
    print(isMatch("ab", ".*c")); // False
    print(isMatch("abcd", "d*")); // False
    print(isMatch("a", ".*..a*")); // False
    print(isMatch("bbaa", "a...")); // False
}
