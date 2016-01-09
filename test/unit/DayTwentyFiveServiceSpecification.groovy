import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 1/8/16.
 */
@TestFor(DayTwentyFiveService)
class DayTwentyFiveServiceSpecification extends Specification {

    def "test createCodeMatrix"() {
        when:
        def result = service.createCodeMatrix(row, col)

        then:
        result == expected

        where:
        row | col | expected
        5 | 3 | 24659492
    }

    def "test with actual part a"() {
        when:
        def result = service.createCodeMatrix(3009, 3018)

        then:

        result
        result == 8997277
    }

}
