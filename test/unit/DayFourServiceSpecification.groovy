import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayFourService)
class DayFourServiceSpecification extends Specification {

    def "test with actual part a"() {
        when:
        def result = service.getAdventCoinNumberFiveZeros()

        then:
        result
        result == 254575
    }

    def "test with actual part b"() {
        when:
        def result = service.getAdventCoinNumberSixZeros()

        then:
        result
        result == 1038736
    }

}
