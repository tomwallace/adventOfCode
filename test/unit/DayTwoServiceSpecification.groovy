import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTwoService)
class DayTwoServiceSpecification extends Specification {


    def "test determineSantaFloor"() {
        when:
        def result = service.calcWrappingPaperSquareFeet(input)

        then:
        expected == result

        where:
        expected | input
        58   | '2x3x4'
        43   | '1x1x10'
        7     | '1x1x1'
    }

    def "test calcRibbonLength"() {
        when:
        def result = service.calcRibbonLength(input)

        then:
        expected == result

        where:
        expected | input
        34   | '2x3x4'
        14   | '1x1x10'
        5     | '1x1x1'
    }

    def "test with actual part a"() {
        when:
        def result = service.calcWrappingPaperSquareFeet(service.PUZZLE_INPUT)

        then:
        result
        result == 1588178
    }

    def "test with actual part b"() {
        when:
        def result = service.calcRibbonLength(service.PUZZLE_INPUT)

        then:
        result
        result == 3783758
    }

}
