import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayElevenService)
class DayElevenServiceSpecification extends Specification {


    def "test hasBlacklistedCharacter"() {
        when:
        def goodString = 'abcdefg'
        def badString = 'aiblcog'

        then:
        service.hasBlacklistedCharacter(badString)
        !service.hasBlacklistedCharacter(goodString)
    }

    def "test containsDuplicatePairs"() {
        when:
        def result = service.containsDuplicatePairs(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        true   | 'abbceffgxyz'
        true   | 'abbcdeegjk'
        false   | 'abcdefe'
        false   | 'qaaadfbea'
    }

    def "test isValidPassword"() {
        when:
        def result = service.isValidPassword(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        false   | 'hijklmmn'
        true   | 'habcmmnn'
        false   | 'abbceffg'
        true   | 'abbceffgxyz'
        false   | 'abbbcdegjk'
        true   | 'abbcdeegjk'
    }

    def "test revChar"() {
        when:
        def result = service.revChar(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        'b'   | 'a'
        'd'   | 'c'
        'a'   | 'z'
    }

    def "test revPassword"() {
        when:
        def result = service.revPassword(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        'bb'   | 'ba'
        'abce'   | 'abcd'
        'abdaaa'   | 'abczzz'
        'baaaaa'   | 'azzzzz'
    }

    def "test with actual part a"() {
        when:
        def result = service.createNewPassword(service.PUZZLE_INPUT)

        then:
        result
        result == 'cqjxxyzz'
    }


    def "test with actual part b"() {
        when:
        def firstPassword = service.createNewPassword(service.PUZZLE_INPUT)
        def result = service.createNewPassword(firstPassword)

        then:
        result
        result == 'cqkaabcc'
    }


}
