/**
 * Created by tomwallace on 12/7/15.
 */
class DaySeventeenService {

    final static String PUZZLE_INPUT = """11
30
47
31
32
36
3
1
5
3
32
36
15
11
46
26
28
1
19
3
"""

    // http://adventofcode.com/day/17

    /*
The elves bought too much eggnog again - 150 liters this time. To fit it all into your refrigerator, you'll need to move it into smaller containers. You take an inventory of the capacities of the available containers.

For example, suppose you have containers of size 20, 15, 10, 5, and 5 liters. If you need to store 25 liters, there are four ways to do it:

15 and 10
20 and 5 (the first 5)
20 and 5 (the second 5)
15, 5, and 5
Filling all containers entirely, how many different combinations of containers can exactly fit all 150 liters of eggnog?

     */

    List<List> fetchCombinationsOfContainers(Integer total, String input) {
        List<Integer> data = parseInput(input)

        def recursiveClosure
        recursiveClosure = { Integer tot, List<Integer> containers, List<Integer> usedCont ->
            Integer n = containers.size()
            switch ([tot: tot, containers: containers, usedCont: usedCont]) {
                case { it.tot == 0 } :
                    //results << usedCont.collect()
                    return [usedCont.collect()]
                case { it.tot < 0 || containers == [] } :
                    return []
                default:
                    List<Integer> newList = usedCont.collect()
                    newList.add(containers[0])
                    return recursiveClosure(tot, containers[1..<n], usedCont) +
                            recursiveClosure(tot - containers[0], containers[1..<n], newList)
            }
        }
        return recursiveClosure(total, data, [])
    }

    protected List<Integer> parseInput(String input) {
        List<Integer> data = []
        input.tokenize("\n").each { String line ->
            def dataItem = line.find(/(\d*)/)
            data << dataItem.toInteger()
        }
        return data
    }
}
