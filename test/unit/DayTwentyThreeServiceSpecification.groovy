import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 1/8/16.
 */
@TestFor(DayTwentyThreeService)
class DayTwentyThreeServiceSpecification extends Specification {

    def "test openTuringLock"() {
        when:
        Map regs = [a: 0, b: 0]
        def input = """inc a
tpl a
inc a
"""
        def result = service.openTuringLock(input, regs)

        then:
        result
        result.a == 4
        result.b == 0
    }

    def "test openTuringLock jump"() {
        when:
        Map regs = [a: 0, b: 0]
        def input = """inc a
jie a, +2
jmp -2
inc b
"""
        def result = service.openTuringLock(input, regs)

        then:
        result
        result.a == 2
        result.b == 1
    }

    def "test with actual part a"() {
        when:
        Map regs = [a: 0, b: 0]
        def result = service.openTuringLock(service.PUZZLE_INPUT, regs)

        then:

        result
        result.b == 184
    }

    def "test with actual part b"() {
        when:
        Map regs = [a: 1, b: 0]
        def result = service.openTuringLock(service.PUZZLE_INPUT, regs)

        then:

        result
        // 155 is too low
        result.b == 231
    }
}
