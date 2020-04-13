#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{

    char input[30];

    gets(input);

    int sum = 0;

for (int i = 0; i < strlen(input); i++) {

  switch (input[i]) {

    case 'a':
        sum += 1;
     break;

    case 'e':
        sum += 2;
     break;

    case 'i':
        sum += 3;
     break;

     case 'o':
        sum += 4;
     break;

     case 'u':
        sum += 5;
     break;

  }
}
    printf("Vowels sum = %d\n", sum);

}
