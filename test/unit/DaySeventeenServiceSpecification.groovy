import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DaySeventeenService)
class DaySeventeenServiceSpecification extends Specification {

    def "test findCombinationOfContainers"() {
        when:
        def input = """2\n
2\n
4\n
4\n
"""
        def result = service.findCombinationOfContainers(input, 8)

        then:
        result
        result == 3
    }

    def "test findCombinationOfContainers Two"() {
        when:
        def input = """20\n
15\n
10\n
5\n
5\n
"""
        def result = service.findCombinationOfContainers(input, 25)

        then:
        result
        result == 4
    }

    // 20, 15, 10, 5, and 5

    def "test with actual part a"() {
        when:
        def result = service.findCombinationOfContainers(service.PUZZLE_INPUT, 150)

        then:

        // 30183 is too high
        // 119 is too low
        result
        result == 40
    }
/*
    def "test with actual part b"() {
        when:
        def result = service.findAuntNumberPartTwo(service.PUZZLE_INPUT)

        then:
        result
        result == 241
    }
*/
}
