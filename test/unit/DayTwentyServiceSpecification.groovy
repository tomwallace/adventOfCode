import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTwentyService)
class DayTwentyServiceSpecification extends Specification {


    def "test lowestHouseToGetPresents"() {
        when:
        def result = service.lowestHouseToGetPresents(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        1   | 10
        4   | 70
        8   | 150
    }

    def "test lowestHouseToGetPresents_DropOut"() {
        when:
        def result = service.lowestHouseToGetPresents_DropOut(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        1   | 11
        4   | 73
        8   | 154
    }

    def "test lowestHouseToGetPresents_DropOut2"() {
        when:
        def result = service.lowestHouseToGetPresents_DropOut(10000)

        then:
         result
    }
/*
    // These tests take hours to run, so commenting out
    def "test with actual part a"() {
        when:
        def result = service.lowestHouseToGetPresents(service.PUZZLE_INPUT)

        then:
        result
        result == 665280
    }


    def "test with actual part b"() {
        when:
        def result = service.lowestHouseToGetPresents_DropOut(service.PUZZLE_INPUT)

        then:
        result
        result == 705600
    }
*/

}
