import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DaySevenService)
class DaySevenServiceSpecification extends Specification {


    def "test updateCircuitValues"() {
        when:
        String input = """123 -> xx
xx AND yy -> d
xx OR yy -> e
xx LSHIFT 2 -> f
yy RSHIFT 2 -> g
NOT xx -> h
NOT yy -> i
456 -> yy
"""
        def result = service.updateCircuitValues(input)

        then:
        result
        result.size() == 8
        result."d" == 72
        result."e" == 507
        result."f" == 492
        result."g" == 114
        result."h" == 65412
        result."i" == 65079
        result."xx" == 123
        result."yy" == 456
    }

    def "test with actual part a"() {
        when:
        def result = service.updateCircuitValues(service.PUZZLE_INPUT)

        then:
        result
        result.each {
            assert it.value >= 0
        }
        result."a" == 16076
    }

    def "test with actual part b"() {
        when:
        def circuitBRemoved = service.PUZZLE_INPUT - "19138 -> b\n"
        def newInput = "16076 -> b\n" + circuitBRemoved
        def result = service.updateCircuitValues(newInput)

        then:
        result
        result."a" == 2797
    }
}
