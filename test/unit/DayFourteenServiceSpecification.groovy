import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayFourteenService)
class DayFourteenServiceSpecification extends Specification {

    def "test deerDistanceCovered"() {
        when:
        Map data = [deer: 'Comet', speed: 14, duration: 10, rest: 127]
        Integer time = 1000
        def result = service.deerDistanceCovered(data, time)

        then:
        result == 1120
    }

    def "test createMapOfSpeeds"() {
        when:
        def result = service.createMapOfSpeeds(service.PUZZLE_INPUT)

        then:
        result
        result.each {
            assert it.key
            assert it.value.speed
            assert it.value.duration
            assert it.value.rest
        }
    }

    def "test with actual part a"() {
        when:
        Map speedData = service.createMapOfSpeeds(service.PUZZLE_INPUT)
        def result = service.furthestDeerDistance(speedData, service.PUZZLE_TIME)

        then:
        result
        result.sort { -it }[0] == 2655
    }

    def "test with actual part b"() {
        when:
        def result = service.mostPoints(service.PUZZLE_INPUT, service.PUZZLE_TIME)

        then:
        result
        result == 1059
    }
}
