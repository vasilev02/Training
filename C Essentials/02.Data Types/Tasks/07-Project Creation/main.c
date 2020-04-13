#include <stdio.h>
#include <stdlib.h>

int main()
{

    char name[20];

    int buildings;

    scanf("%s",name);

    scanf("%d",&buildings);



    printf("The architect %s will need %d hours to complete %d project/s.",name,buildings*3,buildings);


    return 0;
}
