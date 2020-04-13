#include <stdio.h>
#include <stdlib.h>

int main()
{

    int min = INT_MAX;
    
    int max = INT_MIN;
    
    int n;
    
    scanf("%d", &n);
    
 for (int i = 0; i < n; i++) {
        
     int num;
 
     scanf("%d", &num);
     
     if (num < min) min = num;
     
     if (num > max) max = num;
 }
     printf("Max number: %d\nMin number: %d", max, min);


}
