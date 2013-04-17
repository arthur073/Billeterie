#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(){
        int nbPlacesParRangees=10;
        int rangee=1;
        //        int cpt=1;
        for (int rangee = 1; rangee <= 12 ; rangee++){
                for (int i  = 1; i <= nbPlacesParRangees; i++) {
                        printf("insert into Place values (%u,%u,%u);\n",i,rangee,1);
                }
        }
        for (int rangee = 13; rangee <= 21 ; rangee++){
                for (int i  = 1; i <= nbPlacesParRangees; i++) {
                        printf("insert into Place values (%u,%u,%u);\n",i,rangee,2);
                }
        }
        for (int rangee = 22; rangee <= 27 ; rangee++){
                for (int i  = 1; i <= nbPlacesParRangees; i++) {
                        printf("insert into Place values (%u,%u,%u);\n",i,rangee,4);
                }
        }

}
