import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTwentyTwoService)
class DayTwentyTwoServiceSpecification extends Specification {


    def "test with actual part a"() {
        when:
        def result = service.leastManaSpentToWin(71, false)

        then:
        result
        result == 1824
    }

    def "test with actual part b"() {
        when:
        def result = service.leastManaSpentToWin(71, true)

        then:
        result
        result == 1937
    }

}
