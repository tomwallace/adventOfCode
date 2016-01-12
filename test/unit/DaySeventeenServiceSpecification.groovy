import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DaySeventeenService)
class DaySeventeenServiceSpecification extends Specification {

    def "test fetchCombinationsOfContainers"() {
        when:
        def input = """2\n
2\n
4\n
4\n
"""
        def result = service.fetchCombinationsOfContainers(8, input)

        then:
        result
        result.size() == 3
    }

    def "test fetchCombinationsOfContainers Two"() {
        when:
        def input = """20\n
15\n
10\n
5\n
5\n
"""
        def result = service.fetchCombinationsOfContainers(25, input)

        then:
        result
        result.size() == 4
    }

    def "test with actual part a"() {
        when:
        def result = service.fetchCombinationsOfContainers(150, service.PUZZLE_INPUT)

        then:

        result
        result.size() == 4372
    }

    def "test with actual part b"() {
        when:
        def result = service.fetchCombinationsOfContainers(150, service.PUZZLE_INPUT)
        def smallestSize = result.sort { it.size() }[0].size()
        def count = result.findAll { it.size() == smallestSize }.size()

        then:
        count == 4
    }

}
