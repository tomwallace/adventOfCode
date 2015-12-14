import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayNineService)
class DayNineServiceSpecification extends Specification {

    def "test getUniqueCities"() {
        when:
        def result = service.getUniqueCities(service.PUZZLE_INPUT)

        then:
        result
        result.size() == 8
    }

    def "test createMapOfDistances"() {
        when:
        def cities = service.getUniqueCities(service.PUZZLE_INPUT)
        def result = service.createMapOfDistances(service.PUZZLE_INPUT, cities)

        def totalEntries = 0
        result.each {
            totalEntries += it.value.size()
        }

        then:
        result
        totalEntries == 56

    }


    def "test with actual part a"() {
        when:
        def result = service.calculateDistanceShortestRoute(service.PUZZLE_INPUT)

        then:
        result
        result == 207
    }


    def "test with actual part b"() {
        when:
        def result = service.calculateDistanceLongestRoute(service.PUZZLE_INPUT)

        then:
        result
        // TODO: Too low
        result == 764
    }

}
