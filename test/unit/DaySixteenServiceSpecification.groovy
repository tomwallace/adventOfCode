import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DaySixteenService)
class DaySixteenServiceSpecification extends Specification {

    def "test createMapOfData"() {
        when:
        def input = 'Sue 1: goldfish: 9, cars: 0, samoyeds: 9'
        def result = service.createMapOfData(input)
        then:
        result
        result == [[children: null, cats: null, samoyeds: 9, pomeranians: null, akitas: null, vizslas: null, goldfish: 9, trees: null, cars: 0, perfumes: null]]
    }

    def "test with actual part a"() {
        when:
        def result = service.findAuntNumber(service.PUZZLE_INPUT)

        then:
        result
        result == 40
    }

    def "test with actual part b"() {
        when:
        def result = service.findAuntNumberPartTwo(service.PUZZLE_INPUT)

        then:
        result
        result == 241
    }
}
