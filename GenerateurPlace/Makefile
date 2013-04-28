CC=gcc
CFLAGS+=-Wall -g -std=gnu99
LDLIBS+= -lpthread

all: place
place: place.o 

clean:
	-$(RM) place place.o

test: place
	./place
