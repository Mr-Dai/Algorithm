#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool matchFirst(char* s, char* p) {
    return (*s == *p) || (*p == '.');
}

bool isMatch(char* s, char* p) {
    if (*p == '\0') // End of Regex
        return *s == '\0';
    if (*(p+1) == '\0') // Last char of Regex
        return *(s+1) == '\0' && matchFirst(s, p);
    
    if (*(p+1) != '*')
        return *s != '\0' && matchFirst(s, p) && isMatch(s+1, p+1);
    
    return isMatch(s, p+2) || *s != '\0' && matchFirst(s, p) && isMatch(s+1, p);
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
