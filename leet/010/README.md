# Regular Expression Matching

Implement regular expression matching with support for `'.'` and `'*'`.

```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
```

Check it on [LeetCode](https://leetcode.com/problems/regular-expression-matching/)

## Solutions

### Intuitive Solution

It's not difficult to implement this matching algorithm directly, if you can think of all the corner cases:

```c
/* Test if the first character matches */
bool matchFirst(char* s, char* p) {
    return (*s == *p) || (*p == '.');
}

/* The answer */
bool isMatch(char* s, char* p) {
    if (*p == '\0')        // End of Regex
        return *s == '\0'; // True if the input is also empty
    if (*(p+1) == '\0')    // Last char of Regex
        // True if there is also one char left for the input
        return *(s+1) == '\0' && matchFirst(s, p);
    
    if (*(p+1) != '*')  // The next char of Regex is not asterisk
        // Consumes the first char and test the remainder
        return *s != '\0' && matchFirst(s, p) && isMatch(s+1, p+1);
    
    return isMatch(s, p+2) // No occurance
        || *s != '\0' && matchFirst(s, p) && isMatch(s+1, p); // Occurs at least once
}
```

This solution runs for `29ms` and beats only `22.67%` of C submissions. There is indeed a faster solution.

### Dynamic Programming

Most problems that can be solved using recursion can also be solved using dynamic programming, which essentially
inlines and memorizes all those recursive invocations.

Say `matches[i][j]` is `true` when `s[:i]` matches `p[:j]`, then the value of
`matches[i][j]` actually depends on other computed values within the matrix of `(i-1)X(j-1)` in many cases.

```c
bool isMatch(char* s, char* p) {
    int i, j;
    int len_s = strlen(s), len_p = strlen(p);
    // Allocate memory
    bool** matches = (bool**) malloc((len_s+1) * sizeof(bool*));
    for (i = 0; i <= len_s; i++) {
        matches[i] = (bool*) malloc((len_p+1) * sizeof(bool));
        memset(matches[i], false, (len_p+1) * sizeof(bool));
    }
    matches[0][0] = true; // Empty Regex matches empty input

    // Calculate the result set
    for (i = 0; i <= len_s; i++) {
        for (j = 1; j <= len_p; j++) {
            if (p[j - 1] == '*')
                matches[i][j] = matches[i][j-2] // No occurance
                    || // Occur at least once
                        i > 0 // Input should not be empty
                        && matches[i-1][j] // All the previous input matches
                        && (p[j-2] == '.' || s[i-1] == p[j-2]); // And the latest char can be consumed
            else
                matches[i][j] =
                    i > 0 // Input should not be empty
                    && matches[i-1][j-1] // All the previous input matches
                    && (p[j-1] == '.' || s[i-1] == p[j-1]); // And the latest char matches
        }
    }

    bool result = matches[len_s][len_p];
    // Release memory
    for (i = 0; i <= len_s; i++)
        free(matches[i]);
    free(matches);
    return result;
}
```

This solution runs for `6ms` and beats `92%` of C submissions.
