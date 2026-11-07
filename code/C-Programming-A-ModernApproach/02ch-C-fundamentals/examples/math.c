#include <stdio.h>

int main(void) {
    int num;

    scanf("%d", &num);

    int operation = (num + 165) / 166;
    printf("(%d + 165) / 166: %d", num, operation);
}