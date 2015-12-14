import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayOneService)
class DayOneServiceSpecification extends Specification {


    def "test determineSantaFloor"() {
        when:
        def result = service.determineSantaFloor(input)

        then:
        expected == result

        where:
        expected | input
        1   | '('
        -1 | ')'
        0 | '()'
        -3 | ')())())'
    }

    def "test determineFirstBasement"() {
        when:
        def result = service.determineFirstBasement(input)

        then:
        expected == result

        where:
        expected | input
        null   | '('
        1 | ')'
        null | '()'
        3 | '())()'
    }

    def "test with actual part a"() {
        when:
        def result = service.determineSantaFloor(service.PUZZLE_INPUT)

        then:
        result
        result == 280
    }

    def "test with actual part b"() {
        when:
        def result = service.determineFirstBasement(service.PUZZLE_INPUT)

        then:
        result
        result == 1797
    }
}
