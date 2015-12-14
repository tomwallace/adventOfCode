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

        when:
        def perms = []
        result.eachPermutation {
            perms << it
        }

        then:
        perms
    }

    def "test calculateAllDistances"() {
        when:
        def result = service.getUniqueCities(service.PUZZLE_INPUT)
        def perms = []
        result.eachPermutation {
            perms << it
        }

        def dists = service.calculateAllDistances(service.PUZZLE_INPUT)

        then:
        dists.size() == perms.size()
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
        def result = service.calculateAllDistances(service.PUZZLE_INPUT)
        def shortest = result.sort()[0]

        then:
        shortest
        shortest == 207
    }


    def "test with actual part b"() {
        when:
        def result = service.calculateAllDistances(service.PUZZLE_INPUT)
        def longest = result.sort { -it }[0]

        then:
        longest
        longest == 804
    }

}
