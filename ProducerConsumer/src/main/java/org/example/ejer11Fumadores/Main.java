package org.example.ejer11Fumadores;

/*
DU2 - Exercise 11 - Cigarette smokers - wait() and notify()

Consider a tobacco shop with three smokers and a tobacconist.

Each smoker smokes in an infinite loop.

Each smoker must wait for certain conditions (having supplies to smoke) before smoking.

The tobacconist produces supplies for smokers to smoke forever.

To ensure true concurrency, it is important to note that the solution must allow several smokers to smoke simultaneously.

The requirements for smokers and tobacconist are the following:

    Before smoking it is necessary to roll a cigarette, for this the smoker needs three ingredients:
    tobacco, paper and matches.
    Each smoker has only tobacco, paper or matches.
    The tobacconist randomly places two different ingredients out of the three needed to make a cigarette.  The smoker who
    has the third ingredient should remove the two items from the table, using them (along with their own supply) to make
    a cigarette, which they smoke for a while.
    Once the smoker has made his cigarette, the tobacconist places two new random items.

The solution must avoid deadlocks between the different threads. It also must produce messages on the standard output to
track the activity of the threads:

    The tobacconist must indicate when he produces ingredients and which supplies he produces.
    Each smoker must indicate when he waits, which product(s) he waits for, and when he starts and stops smoking.

 */

public class Main {
    
}
