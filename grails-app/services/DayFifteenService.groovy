/**
 * Created by tomwallace on 12/7/15.
 */
class DayFifteenService {

    final static Integer PUZZLE_TIME = 2503
    final static String PUZZLE_INPUT = """Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2
Sprinkles: capacity -3, durability 3, flavor 0, texture 0, calories 9
Candy: capacity -1, durability 0, flavor 4, texture 0, calories 1
Chocolate: capacity 0, durability 0, flavor -2, texture 2, calories 8
"""

    // http://adventofcode.com/day/15

    /*
Today, you set out on the task of perfecting your milk-dunking cookie recipe. All you have to do is find the right balance of ingredients.

Your recipe leaves room for exactly 100 teaspoons of ingredients. You make a list of the remaining ingredients you could use to finish the recipe (your puzzle input) and their properties per teaspoon:

capacity (how well it helps the cookie absorb milk)
durability (how well it keeps the cookie intact when full of milk)
flavor (how tasty it makes the cookie)
texture (how it improves the feel of the cookie)
calories (how many calories it adds to the cookie)
You can only measure ingredients in whole-teaspoon amounts accurately, and you have to be accurate so you can reproduce your results in the future. The total score of a cookie can be found by adding up each of the properties (negative totals become 0) and then multiplying together everything except calories.

For instance, suppose you have these two ingredients:

Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3
Then, choosing to use 44 teaspoons of butterscotch and 56 teaspoons of cinnamon (because the amounts of each ingredient must add up to 100) would result in a cookie with the following properties:

A capacity of 44*-1 + 56*2 = 68
A durability of 44*-2 + 56*3 = 80
A flavor of 44*6 + 56*-2 = 152
A texture of 44*3 + 56*-1 = 76
Multiplying these together (68 * 80 * 152 * 76, ignoring calories for now) results in a total score of 62842880, which happens to be the best score possible given these ingredients. If any properties had produced a negative total, it would have instead become zero, causing the whole score to multiply to zero.

Given the ingredients in your kitchen and their properties, what is the total score of the highest-scoring cookie you can make?

     */

    // TODO: Takes too long to create all combinations.  Must  come back and review later
    // TODO: Process exceeds GC mem limit.  Must do a different way
   // TODO: Clean up!
    protected Map createMapOfData(String input) {
        // Create our map of ingredient data
        Map data = [:]
        input.tokenize("\n").each { String line ->
            String ingredient = line.find(/^(\w*)\b/)
            List<Integer> nums = line.findAll(/(\d+)/)
            Map ingredientData = [capacity: nums[0].toInteger(), durability: nums[1].toInteger(), flavor: nums[2].toInteger(), texture: nums[3].toInteger(), calories: nums[4].toInteger()]
            data["${ingredient}"] = ingredientData
        }

        return data
    }
/*
    protected List<List> determineCombinationsAddUpToValue(Integer countInputs, Integer total) {


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

        def ccR
        ccR = { Integer tot, List<Integer> tsps ->
            Integer n = tsps.size()
            switch ([tot:tot, coins:tsps]) {
                case { it.tot == tsps.sum() } :
                    return tsps
                case { it.tot < 0 || tsps == [] } :
                    return []
                default:
                    return ccR(tot, tsps[1..<n]) +
                            ccR(tot - tsps[0], tsps)
            }
        }

        def ways = ccR(100g, [1g, 1g, 1g, 1g])

        def i = 1

    }
*/

    static List<List> determineCombinationsAddUpToValue2(List<Integer> numbers, Integer target, List<Integer> partial) {
        List<List> output = []
        int s = 0;
        for (int x: partial) s += x;
        if (s == target) {
            System.out.println("sum("+Arrays.toString(partial.toArray())+")="+target);
            output << partial
        }

        if (s >= target)
            return;
        for(int i=0;i<numbers.size();i++) {
            ArrayList<Integer> remaining = new ArrayList<Integer>();
            int n = numbers.get(i);
            for (int j=i+1; j<numbers.size();j++) remaining.add(numbers.get(j));
            ArrayList<Integer> partial_rec = new ArrayList<Integer>(partial);
            partial_rec.add(n);
            determineCombinationsAddUpToValue2(remaining,target,partial_rec);
        }
    }


 // http://stackoverflow.com/questions/18051885/find-and-print-each-unique-combination-that-sums-to-100-and-return-a-count-of-al?lq=1

    protected List<List> determineCombinationsAddUpToValue(List<Integer> inputs, Integer total) {
        List<List> results = []
        List<List> iterations = revInputs(inputs, total)
        iterations.each { iteration ->
            if (iteration.sum() == total) {
                results << iteration
            }
        }
    }

    protected List<List> revInputs(List<Integer> input, Integer total) {
        def i
        def y
        List<List> output = []
        Integer length = input.size()
        for (;;) {  // infinite for
            for (i = length - 1; i >=0; --i) {
                ++input[i]
                if (i != 0 && input[i] >= total) {
                    input[i] = 1
                } else {
                    output << input.collect()
                    break
                }
            }
            if (input[0] == total) {
                break
            }
        }

        return output
    }
}
