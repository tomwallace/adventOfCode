import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayFifteenService)
class DayFifteeenServiceSpecification extends Specification {

    def "test createMapOfData"() {
        when:
        def result = service.createMapOfData(service.PUZZLE_INPUT)

        then:
        result
        result.each {
            assert it.key
            assert it.value.capacity instanceof Integer
            assert it.value.durability instanceof Integer
            assert it.value.flavor instanceof Integer
            assert it.value.texture instanceof Integer
            assert it.value.calories instanceof Integer
        }
    }

    def "test determineCombinationsAddUpToValue"() {
        when:
        def total = 100
        List<Integer> countInputs = [1,1,1,1]
        println("Start: ${new Date().toString()}")
        def result = service.determineCombinationsAddUpToValue(countInputs, total)
        println("End: ${new Date().toString()}")

        then:
        result
        result.each {
            assert it.sum() == total
        }
    }

    def "test revInputs"() {
        when:
        println("Start: ${new Date().toString()}")
        def result = service.revInputs([1,1,1,1], 100)
        println("End: ${new Date().toString()}")

        then:
        result
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
