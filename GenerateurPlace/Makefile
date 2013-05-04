CC=gcc
CFLAGS+=-Wall -g -std=gnu99
LDLIBS+= -lpthread

all: iterateur
place: iterateur.o

clean:
	-$(RM) place place.o iterateur iterateur.o

test: place
	./place
