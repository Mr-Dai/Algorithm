#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* convert(char* s, int numRows) {
    int length = strlen(s);
    char* result = (char*) malloc((length + 1) * sizeof(char));
    if (numRows == 1) {
        strcpy(result, s);
        return result;
    }
    int i, j, next, c = 0;
    // Compute first row
    for (i = 0; i < length; i += 2 * numRows - 2)
        result[c++] = s[i];
    // Compute intermediate rows
    for (i = 1; i < numRows - 1; i++) {
        for (j = i; j < length; j += 2 * numRows - 2) {
            result[c++] = s[j];
            next = j + 2 * numRows - 2 - 2 * i;
            if (next < length)
                result[c++] = s[next];
        }
    }
    // Compute last row
    for (i = numRows - 1; i < length; i += 2 * numRows - 2)
        result[c++] = s[i];
    result[c++] = '\0';
    return result;
}

int main() {
    char* result = convert("", 1);
    printf("%d\n", strcmp(result, ""));
    free(result);

    result = convert("A", 1);
    printf("%d\n", strcmp(result, "A"));
    free(result);

    result = convert("knqaqhgviudkwfmpxhjvusqdpjcadaanpsnfzwch", 1);
    printf("%d\n", strcmp(result, "knqaqhgviudkwfmpxhjvusqdpjcadaanpsnfzwch"));
    free(result);

    result = convert("A", 3);
    printf("%d\n", strcmp(result, "A"));
    free(result);

    result = convert("Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.", 2);
    printf("%d\n", strcmp(result,
        "Aaidoeswr,haenme,rtesqecouishtabrateaeaietedrcinwtgnrlloacsoajsmnsoucutoadodiiesplnrmiaodprs,ubroohreunefnttacneedhsmwynihrieto,iheeaalwnefrdutettpntainnwrdvdr."));
    free(result);

    result = convert("PAYPALISHIRING", 3);
    printf("%d\n", strcmp(result, "PAHNAPLSIIGYIR"));
    free(result);
}
