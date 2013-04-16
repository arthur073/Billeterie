#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
        int nbPlacesParRangees=10;
        int rangee=1;
//        int cpt=1;
        for (int zone=1; zone < 4 ; zone++){
                for (int i  = 1; i <= nbPlacesParRangees; i++) {
                        printf("insert into Place values (%u,%u,%u);\n",i,rangee,zone);
                }
                rangee++;
        }

}
