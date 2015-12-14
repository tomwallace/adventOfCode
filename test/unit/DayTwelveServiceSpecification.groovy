import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTwelveService)
class DayTwelveServiceSpecification extends Specification {


    def "test addAllNumbersInString"() {
        when:
        def result = service.addAllNumbersInString(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        6   | '[1,2,3]'
        6   | '{"a":2,"b":4}'
        3   | '[[[3]]]'
        3   | '{"a":{"b":4},"c":-1}'
        0   | '{"a":[-1,1]}'
        0   | '[-1,{"a":1}]'
        0   | '[]'
        0   | '{}'
    }

    def "test addAllNonRedNumbersInString"() {
        when:
        def result = service.addAllNonRedNumbersInString(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
//        6   | '[1,2,3]'
        4   | '[1,{"c":"red","b":2},3]'
        0   | '{"d":"red","e":[1,2,3,4],"f":5}'
        6   | '[1,"red",5]'
        0 | '{"f": {"g": 2, "h": "red"}}'
        18   | '{"d": [1,"red",5], "e": 5, "f": {"g": 2, "h": "red"}, "i": {"aa": [1,"red",3], "dd": {"bbb": 1, "ccc": 2}} }'
    }

    def "test with actual part a"() {
        when:
        def result = service.addAllNumbersInString(service.PUZZLE_INPUT)

        then:
        result
        result == 111754
    }


    def "test with actual part b"() {
        when:
        def result = service.addAllNonRedNumbersInString(service.PUZZLE_INPUT)

        then:
        result
        result == 500
    }


}
