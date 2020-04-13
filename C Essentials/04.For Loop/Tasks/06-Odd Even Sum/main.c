#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main()
{

    int n;
    
    scanf("%d", &n);

    int odd_sum = 0;
    int even_sum = 0;

for (int i = 0; i < n; i++) {

  int current_value;
  
  scanf("%d", &current_value);


  if(i % 2 == 0){
        
    even_sum = even_sum + current_value;
  
  }else{
  
  odd_sum = odd_sum + current_value;
  
  }
  

}

  if(even_sum == odd_sum){
        
    printf("Yes\n");    
    printf("Sum = %d", even_sum);
  
  }else{
  
    printf("No\n");    
    printf("Diff = %d", abs(even_sum - odd_sum));
  
  }
    

}
