import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayFifteenService)
class DayFifteeenServiceSpecification extends Specification {

    def "test determineCookieScore"() {
        when:
        def ingredients = [
                new Ingredient(name: 'Butterscotch', capacity: -1, durability: -2, flavor: 6, calories: 8, texture: 3,  amount: 44),
                new Ingredient(name: 'Cinnamon', capacity: 2, durability: 3, flavor: -2, calories: 3, texture: -1,  amount: 56)
        ]

        def result = service.determineCookieScore(ingredients)

        then:
        result == 62842880
    }

    def "test with actual part a"() {
        when:
        def result = service.collectCookieScoresForIngredient(null)

        then:
        result
        result == 222870
    }

    def "test with actual part b"() {
        when:
        def result = service.collectCookieScoresForIngredient(500)

        then:
        result
        result == 117936
    }
}
