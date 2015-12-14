import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayEightService)
class DayEightServiceSpecification extends Specification {


    def "test calculateActualCharactersInString"() {
        when:
        def result = service.calculateActualCharactersInString(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        6    | /abcdef/
        2    | /""/
        10   | /"aaa\"aaa"/
        6    | /"\x27"/
        14  | /"nxzo\"hf\\xp"/
    }

    def "test calculateInMemoryCharactersInString"() {
        when:
        def result = service.calculateInMemoryCharactersInString(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        6    | /"abcdef"/
        0    | /""/
        7   | /"aaa\"aaa"/
        1    | /"\x27"/
        10  | /"nxzo\"hf\\xp"/
    }

    def "test calculateCharactersInReEncodedString"() {
        when:
        def result = service.calculateCharactersInReEncodedString(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        12    | /"abcdef"/
        6     | /""/
        16   | /"aaa\"aaa"/
        11    | /"\x27"/
        22  | /"nxzo\"hf\\xp"/
    }



    def "test with actual part a"() {
        when:
        def result = service.calculateDifferenceInEscapedCharacters(service.PUZZLE_INPUT)

        then:
        result
        result == 1371
    }


    def "test with actual part b"() {
        when:
        def result = service.calculateReEncodedCharacters(service.PUZZLE_INPUT)

        then:
        result
        result == 2117
    }

}
