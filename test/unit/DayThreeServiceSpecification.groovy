import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayThreeService)
class DayThreeServiceSpecification extends Specification {


    def "test determineSantaFloor"() {
        when:
        def result = service.determineHousesOnePresent(input)

        then:
        expected == result

        where:
        expected | input
        2  | '>'
        4  | '^>v<'
        2  | '^v^v^v^v^v'
    }

    def "test determineRoboSantaHousesOnePresent"() {
        when:
        def result = service.determineRoboSantaHousesOnePresent(input)

        then:
        expected == result

        where:
        expected | input
        2  | '>'
        3  | '^v'
        3  | '^>v<'
        11  | '^v^v^v^v^v'
    }

    def "test with actual part a"() {
        when:
        def result = service.determineHousesOnePresent(service.PUZZLE_INPUT)

        then:
        result
        result == 2081
    }

    def "test with actual part b"() {
        when:
        def result = service.determineRoboSantaHousesOnePresent(service.PUZZLE_INPUT)

        then:
        result
        result == 2341
    }

}
