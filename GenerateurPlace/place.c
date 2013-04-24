#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
   for (int rang = 15; rang >= 1; rang-- ) { 
      if (rang > 13) { 
         for (int place = 1; place <= 43; place++) {
            // balcons
            printf("insert into Place values (%u,%u,3);\n", place, rang);
         }
      }
      if (rang == 13|| rang == 8 || rang == 4) { 
         for (int place = 1; place <= 43; place++) {
            if (place < 5 || place > 39) { 
               printf("insert into Place values (%u,%u,3);\n", place, rang);
            }
         }
      }
      if (rang < 13 && rang > 8) { 
         for (int place = 1; place <= 41; place++) {
            if (place < 5 || place > 37) { 
               printf("insert into Place values (%u,%u,3);\n", place, rang);
            } else { 
               printf("insert into Place values (%u,%u,1);\n", place, rang);
            }
         }
      }
      if (rang < 8 && rang > 4) { 
         for (int place = 1; place <= 41; place++) {
            if (place < 5 || place > 37) {
               printf("insert into Place values (%u,%u,3);\n", place, rang);
            } else { 
               printf("insert into Place values (%u,%u,4);\n", place, rang);
            }
         }
      }
      if (rang < 4 && rang > 0) { 
         for (int place = 1; place <= 41; place++) {
            if (place < 5 || place > 37) { 
               printf("insert into Place values (%u,%u,3);\n", place, rang);
            } else { 
               printf("insert into Place values (%u,%u,2);\n", place, rang);
            }
         }
      }
   }
}
