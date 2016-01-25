/**
 * Created by tomwallace on 12/7/15.
 */
class DayFifteenService {


    List<Ingredient> INGREDIENTS = [
            new Ingredient(name: 'Sugar', capacity: 3, durability: 0, flavor: 0, texture: -3, calories: 2),
            new Ingredient(name: 'Sprinkles', capacity: -3, durability: 3, flavor: 0, texture: 0, calories: 9),
            new Ingredient(name: 'Candy', capacity: -1, durability: 0, flavor: 4, texture: 0, calories: 1),
            new Ingredient(name: 'Chocolate', capacity: 0, durability: 0, flavor: -2, texture: 2, calories: 8)
    ]

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

    protected BigInteger determineCookieScore(List<Ingredient> ingredients) {
        Integer capacity = ingredients.collect { it.capacity * it.amount }.sum()
        Integer durability = ingredients.collect { it.durability * it.amount }.sum()
        Integer flavor = ingredients.collect { it.flavor * it.amount }.sum()
        Integer texture = ingredients.collect { it.texture * it.amount }.sum()

        BigInteger total = (capacity > 0 ? capacity : 0) * (durability > 0 ? durability : 0) * (flavor > 0 ? flavor : 0) * (texture > 0 ? texture : 0)
        return total
    }

    protected BigInteger collectCookieScoresForIngredient(Integer calorieRequirement) {
        Integer su
        Integer sp
        Integer ca
        Integer ch

        def chocolate = INGREDIENTS.find { it.name == 'Chocolate'}
        def sugar = INGREDIENTS.find { it.name == 'Sugar'}
        def sprinkles = INGREDIENTS.find { it.name == 'Sprinkles'}
        def candy = INGREDIENTS.find { it.name == 'Candy'}

        BigInteger highest = 0
        for (su = 1; su <= 100; ++su) {
            for (sp = 1; sp <= 100; ++sp) {
                for (ca = 1; ca <= 100; ++ca) {
                    for (ch = 1; ch <= 100; ++ch) {
                        // Only count the totals that add up to 100
                        // If there is a calorie requirement, enforce it
                        boolean failsCalorieRequirement = (calorieRequirement && ((chocolate.calories * ch) + (sugar.calories * su) + (sprinkles.calories * sp) + (candy.calories * ca)) != calorieRequirement)
                        if (su+sp+ca+ch == 100 && !failsCalorieRequirement) {
                            chocolate.amount = ch
                            sugar.amount = su
                            sprinkles.amount = sp
                            candy.amount = ca
                            List<Ingredient> list = [
                                    chocolate, sugar, sprinkles, candy
                            ]
                            BigInteger result = determineCookieScore(list)
                            if (result > highest) {
                                highest = result
                            }
                        }
                    }
                }
            }
        }
        return highest
    }

}

class Ingredient {
    String name
    Integer capacity
    Integer durability
    Integer flavor
    Integer texture
    Integer calories

    Integer amount

}