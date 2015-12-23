/**
 * Created by tomwallace on 12/7/15.
 */
class DayTwentyService {

    final static Integer PUZZLE_INPUT = 29000000

    // http://adventofcode.com/day/20

    /*
To keep the Elves busy, Santa has them deliver some presents by hand, door-to-door. He sends them down a street with infinite houses numbered sequentially: 1, 2, 3, 4, 5, and so on.

Each Elf is assigned a number, too, and delivers presents to houses based on that number:

The first Elf (number 1) delivers presents to every house: 1, 2, 3, 4, 5, ....
The second Elf (number 2) delivers presents to every second house: 2, 4, 6, 8, 10, ....
Elf number 3 delivers presents to every third house: 3, 6, 9, 12, 15, ....
There are infinitely many Elves, numbered starting with 1. Each Elf delivers presents equal to ten times his or her number at each house.

So, the first nine houses on the street end up like this:

House 1 got 10 presents.
House 2 got 30 presents.
House 3 got 40 presents.
House 4 got 70 presents.
House 5 got 60 presents.
House 6 got 120 presents.
House 7 got 80 presents.
House 8 got 150 presents.
House 9 got 130 presents.
The first house gets 10 presents: it is visited only by Elf 1, which delivers 1 * 10 = 10 presents. The fourth house gets 70 presents, because it is visited by Elves 1, 2, and 4, for a total of 10 + 20 + 40 = 70 presents.

What is the lowest house number of the house to get at least as many presents as the number in your puzzle input?

Your puzzle input is 29000000.

     */
    Integer lowestHouseToGetPresents(Integer input) {
        Integer elfCounter = 1
        for(;;) { // infinite for
            Integer housePresents = 0
            def i
            for (i = elfCounter; i > 0; i--) {
                if ((elfCounter % i) == 0) {
                    housePresents += (i * 10)
                }
            }

            if (housePresents >= input) {
                break
            }
            println("$elfCounter: $housePresents")
            ++elfCounter
        }
        return elfCounter
    }

    Integer lowestHouseToGetPresents_DropOut(Integer input) {
        Integer elfCounter = 1
        Map elfVisits = [:]
        for(;;) { // infinite for
            Integer housePresents = 0
            def i
            for (i = 1; i <= elfCounter; i++) {
                if ((elfCounter % i) == 0 && (elfVisits["$i"] ?: 0) < 50) {
                    housePresents += (i * 11)
                    elfVisits["$i"] = 1 + (elfVisits["$i"] ?: 0)
                }
            }

            if (housePresents >= input) {
                break
            }
            // Print every 1000 cases
            if ((elfCounter % 1000) == 0) {
                println("$elfCounter: $housePresents")
            }

            ++elfCounter
        }
        return elfCounter
    }

}
