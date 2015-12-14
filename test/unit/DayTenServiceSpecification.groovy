import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayTenService)
class DayTenServiceSpecification extends Specification {


    def "test determineLookSay"() {
        when:
        def result = service.determineLookSay(input)
        println(input)

        then:
        //result == '312221'
        expected == result

        where:
        expected | input
        '312221'    | '1112211'
        '11'    | '1'
        '1211'    | '21'
        '111221'    | '1211'
        '312211'    | '111221'
    }

    // Takes long time to run, so commenting out for test-app
    /*
    def "test with actual part a"() {
        when:
        println("Start: ${new Date().toString()}")
        def result = service.getLookSaySize(40)
        println("End: ${new Date().toString()}")

        then:
        result
        result == 329356
    }
    */

    // Took HOURs to run.  So commenting out in case accidentaly run via test-app
    /*
    def "test with actual part b"() {
        when:
        println("Start: ${new Date().toString()}")
        def result = service.getLookSaySize(50)
        println("End: ${new Date().toString()}")

        then:
        result
        result == 4666278
    }
    */
}
