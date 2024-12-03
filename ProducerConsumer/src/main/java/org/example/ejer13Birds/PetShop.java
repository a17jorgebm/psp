package org.example.ejer13Birds;

/*
DU2 - Exercise 13 - Birds - wait() and notify()

One bird shop is having trouble keeping all their canaries happy.

The canaries share a cage in which there is a plate of birdseed and a swing for exercise.
All the canaries repeat the same routine over and over again: first they eat from the dish and then they swing.
But they are faced with the inconvenience that only three of them can eat from the dish at the same time and that
the swing can only hold one bird.

Write the code for the multithreaded application so that each of the canaries executes so that it is correctly
synchronised with all the others with respect to the use of the dish and the swing.

Assume that each bird takes between 500 and 1000 ms to eat.

Assume that each bird takes between 500 and 2000 ms to swing.

Show a message when a bird can start to eat.

Show a message when a bird stops to eat.

Show a message when a bird can start to swing.

Show a message when a bird stops to swing.

The solution must avoid deadlocks between the different threads.
 */

public class PetShop {
    public static void main(String[] args) {
        String[] birdNames = {"Sparrow", "Peacock", "Eagle", "Parrot", "Penguin", "Flamingo", "Kingfisher", "Hummingbird", "Owl", "Canary"};
        Cage cage=new Cage();

        for (String name : birdNames){
            Bird bird=new Bird(cage, name);
            Thread thread=new Thread(bird);
            thread.start();
        }
    }
}
