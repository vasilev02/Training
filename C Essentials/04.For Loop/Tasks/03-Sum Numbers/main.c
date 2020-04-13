#include <stdio.h>
#include <stdlib.h>

int main()
{

    int n;

    scanf("%d",&n);

    int sum = 0;

    for (int i = 1; i <= n; i++) {

    int current_value;

    scanf("%d", &current_value);

    sum = sum + current_value;

    }

    printf("%d",sum);

}
