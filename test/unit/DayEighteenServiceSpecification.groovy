import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayEighteenService)
class DayEighteenServiceSpecification extends Specification {

    def "test AnimationShow methods"() {
        given:
        def input = """.#.#.#
#....#
.##.#.
......
######
#.#.#."""

        when:
        def animationShow = new AnimationShow(input)

        then:
        animationShow.countLit() == 17

        when:
        def litMiddle = animationShow.countNeighborLightsLit(2,1)
        def litEdge = animationShow.countNeighborLightsLit(0,0)

        def test = animationShow.countNeighborLightsLit(0,5)

        then:
        litMiddle == 2
        litEdge == 2
        test == 1

        when:
        AnimationShow updated = service.performUpdate(animationShow)

        then:
        updated.countLit() == 15
    }

    def "test with actual part a"() {
        when:
        AnimationShow animationShow = new AnimationShow(service.PUZZLE_INPUT)

        def x
        for (x = 0; x < 100; x++) {
            AnimationShow updated = service.performUpdate(animationShow)
            animationShow = updated
        }

        then:
        animationShow.countLit() == 768
    }

    def "test with actual part b"() {
        when:
        AnimationShow animationShow = new AnimationShow(service.PUZZLE_INPUT)
        animationShow.cornersAlwaysOn = true

        def x
        for (x = 0; x < 100; x++) {
            AnimationShow updated = service.performUpdate(animationShow)
            animationShow = updated
        }

        then:
        animationShow.countLit() == 781
    }

}
