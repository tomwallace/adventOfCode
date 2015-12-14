import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DaySixService)
class DaySixServiceSpecification extends Specification {


    def "test LightShow workOn and toggle"() {
        given:
        LightShow lightShow = new LightShow()

        when:
        def lit = lightShow.countLit()
        def brightness = lightShow.countBrightness()

        then:
        lightShow
        lit == 0
        brightness == 0

        when:
        lightShow.workOnLight(0, 0, true)
        lightShow.workOnLight(1, 2, true)
        lightShow.workOnLight(3, 1, false)  // already off

        then:
        lightShow.countLit() == 2
        lightShow.countBrightness() == 2

        when:
        lightShow.toggleLight(0, 0)
        lightShow.toggleLight(5, 5)
        lightShow.toggleLight(999, 999)

        then:
        lightShow.countLit() == 3
        lightShow.countBrightness() == 8

    }

    def "test workOnRange"() {
        given:
        LightShow lightShow = new LightShow()

        when:
        lightShow.workOnRange(topLeft, bottomRight, true)
        Integer lit = lightShow.countLit()

        then:
        expected == lit

        when:
        lightShow.workOnRange(topLeft, bottomRight, false)

        then:
        lightShow.countLit() == 0

        where:
        topLeft | bottomRight | expected
        [0,0]    | [999,999]     | 1000000
        [1,1]    | [3,3]              | 9
    }

    def "test workOnRange and toggleRange"() {
        given:
        LightShow lightShow = new LightShow()

        when:
        lightShow.workOnRange([0,0], [999,999], true)

        then:
        lightShow.countLit() == 1000000

        when:
        lightShow.toggleRange([0,0], [999,0])

        then:
        lightShow.countLit() == 999000

        when:
        lightShow.workOnRange([499,499], [500,500], false)

        then:
        lightShow.countLit() == 998996
    }

    def "test parseInstructionLine"() {
        when:
        def result = service.parseInstructionLine(input)

        then:
        result
        result.cmd == cmd
        result.topLeft == topLeft
        result.bottomRight == bottomRight

        where:
        cmd              | topLeft    | bottomRight | input
        service.TURN_ON  | [294, 132] | [460, 338]  | 'turn on 294,132 through 460,338'
        service.TOGGLE   | [717, 493] | [930, 875]  | 'toggle 717,493 through 930,875'
        service.TURN_OFF | [225, 603] | [483, 920]  | 'turn off 225,603 through 483,920'

    }

    def "test with actual part a"() {
        when:
        LightShow result = service.performLightShowAdjustments(service.PUZZLE_INPUT)

        then:
        result
        result.countLit() == 377891
    }

    def "test with actual part b"() {
        when:
        LightShow result = service.performLightShowAdjustments(service.PUZZLE_INPUT)

        then:
        result
        result.countBrightness() == 14110788
    }

}
