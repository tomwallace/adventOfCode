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


    /*
           ORIGINAL
        def ccR
        ccR = { BigInteger tot, List<BigInteger> coins ->
            BigInteger n = coins.size()
            switch ([tot:tot, coins:coins]) {
                case { it.tot == 0 } :
                    return 1g
                case { it.tot < 0 || coins == [] } :
                    return 0g
                default:
                    return ccR(tot, coins[1..<n]) +
                            ccR(tot - coins[0], coins)
            }
        }
     */

//    def sumUpRecursive(List<Integer> numbers, int target, List<Integer> partial) {
//        int s = 0;
//        for (int x: partial) s += x;
//        if (s == target) {
//            System.out.println("sum(" + Arrays.toString(partial.toArray()) + ")=" + target);
//            return 1;
//        }
//        if (s >= target)
//            return 0;
//        for(int i=0;i<numbers.size();i++) {
//            List<Integer> remaining = []
//            int n = numbers.get(i);
//            for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
//            List<Integer> partialRec = partial
//            partialRec.add(n);
//            return sumUpRecursive(remaining,target,partialRec);
//        }
//    }

//    void printSum(List<Integer> candidates, List<Integer> index, Integer n) {
//        Set<Integer> noDups = new HashSet<Integer>(index)
//        if (index.size() == noDups.size()) {
//            for (int i = 1; i <= n; i++)
//                println("${index[i]} ${(i == n) ? "" : "+"}");
//            println(' end')
//        }
//    }
//
//    def solve(Integer target, Integer sum, List<Integer> candidates, Integer sz, List<Integer> index, Integer n) {
//        if (sum > target)
//            return
//        if (sum == target)
//            printSum(candidates, index, n)
//
//        for (int i = index[n]; i < sz; i++) {
//            // check current number and see if duplicate
//            if (i != index[n]) {
//                index[n+1] = i;
//                solve(target, sum + candidates[i], candidates, sz, index, n+1);
//            }
//
//        }
//    }

    // TODO: Change to make my own
    // TODO: Change to return an integer
    // TODO: Clean up!
    Integer recurseCombinations(List<Integer> value, List<Integer> retSet, Integer sz, Integer target, Integer preValue, Integer origSize){
        Integer result = 0
        if(target < 0)
            return 0
        if(target == 0){
            println('We found one')
            return 1
        }
        for(int i = preValue; i<= origSize && i <= target ; i++){
            if(value.contains(i)){
                retSet[sz] = i
                result += recurseCombinations(value, retSet, sz + 1, target - retSet[sz], i, origSize)
            }
        }
        return result
    }
//
//    void sumUpRecursive(List<Integer> numbers, int target, List<Integer> partial) {
//            int s = 0;
//            for (int x: partial) s += x;
//            if (s == target) {
//                System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
//            }
//            if (s > target)
//                return;
//            for(int i=0;i<numbers.size();i++) {
//                List<Integer> remaining = []
//                int n = numbers.get(i);
//                for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
//                List<Integer> partialRec = partial
//                partialRec.add(n);
//                sumUpRecursive(remaining,target,partialRec);
//            }
//        }

    Integer findCombinationOfContainers(String input, Integer total) {
        List<Integer> data = []
        input.tokenize("\n").each { String line ->
            def dataItem = line.find(/(\d*)/)
            data << dataItem.toInteger()
        }
//        List<Integer> index = []
//        index[0] = 0

        //solve(total, 0, data, data.size(), index, 0)
        //sumUpRecursive(data, total, [])
        return recurseCombinations(data, [], 0, total, 0, data.size())

//        def sumUpRecursive = {List<Integer> numbers, int target, List<Integer> partial ->
//            int s = 0;
//            for (int x: partial) s += x;
//            if (s == target) {
//                System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
//                return 1;
//            }
//            if (s >= target)
//                return 0;
//            for(int i=0;i<numbers.size();i++) {
//                List<Integer> remaining = []
//                int n = numbers.get(i);
//                for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
//                List<Integer> partialRec = partial
//                partialRec.add(n);
//                return sumUpRecursive(remaining,target,partialRec);
//            }
//        }

//        def results = sumUpRecursive(data, total, new ArrayList<Integer>())
//
//        return results

//        List<List> allPerms =  []
//        data.eachPermutation {
//            if (it.sum() == total) {
//                allPerms << it
//                println(allPerms.size())
//            }
//        }
//
//        return allPerms.size()
//
//


        /* ORIG
        def ccR
        ccR = { Integer tot, List<Integer> coins ->
            Integer n = coins.size()
            switch ([tot:tot, coins:coins]) {
                case { it.tot == 0 } :
                    return 1
                case { it.tot < 0 || coins == [] } :
                    return 0
                default:
                    return ccR(tot, coins[1..<n]) +
                            ccR(tot - coins[0], coins)
            }
        }

         */
//        def ccR
//        ccR = { Integer tot, List<Integer> coins ->
//            Integer n = coins.size()
//            switch ([tot:tot, coins:coins]) {
//                case { coins.sum() == tot } :
//                    return 1
//                case { coins.sum() > tot || coins == [] } :
//                    return 0
//                default:
//                    def trimmedCoins = coins.collect()
//                    trimmedCoins.remove(n-1)
//                    return ccR(tot, coins[1..<n]) +
//                            ccR(tot, trimmedCoins)
//            }
//        }
//
//        def ccI = { Integer tot, List<Integer> coins ->
//            List<Integer> ways = [0g] * (tot+1)
//            ways[0] = 1g
//            coins.each { Integer coin ->
//                (coin..tot).each { j ->
//                    ways[j] += ways[j-coin]
//                }
//            }
//            ways[tot]
//        }
//
//
//        return ccR(total, data)
    }

    /*
PART 2 - As you're about to send the thank you note, something in the MFCSAM's instructions catches your eye. Apparently, it has an outdated retroencabulator, and so the output from the machine isn't exact values - some of them indicate ranges.

In particular, the cats and trees readings indicates that there are greater than that many (due to the unpredictable nuclear decay of cat dander and tree pollen), while the pomeranians and goldfish readings indicate that there are fewer than that many (due to the modial interaction of magnetoreluctance).

What is the number of the real Aunt Sue?
     */
    Integer findAuntNumberPartTwo(String input) {
        List<Map> data = createMapOfData(input)
        def result = 0
        def i = 0
        data.each {
            ++i
            if ((it."children" == 3 || it."children" == null) &&
                    (it."cats" > 7 || it."cats" == null)  &&
                    (it."samoyeds" == 2 || it."samoyeds" == null)  &&
                    (it."pomeranians" < 3 || it."pomeranians" == null)  &&
                    (it."akitas" == 0 || it."akitas" == null)  &&
                    (it."vizslas" == 0 || it."vizslas" == null)  &&
                    (it."goldfish" < 5 || it."goldfish" == null)  &&
                    (it."trees" > 3 || it."trees" == null)  &&
                    (it."cars" == 2 || it."cars" == null)  &&
                    (it."perfumes" == 1 || it."perfumes" == null) ) {
                result = i
            }
        }

        return result
    }

    protected List<Map> createMapOfData(String input) {
        // Create our map of distances
        List<Map> data = []
        input.tokenize("\n").each { String line ->
            Map dataLine = [:]
            DATA_POINTS.each {dataPoint ->
                String value = line.find(/${dataPoint}\:\s(\d+)/)
                // TODO: Groovy returns more than the capture group, so we have to trim.  Fix that so works with only regex
                if (value) {
                    value = value - "${dataPoint}: "
                }
                dataLine["$dataPoint"] = value ? value.toInteger() : null
            }
            data << dataLine
        }

        return data
    }
}
