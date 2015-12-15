import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayThirteenService)
class DayThirteenServiceSpecification extends Specification {

    def "test getUniqueCities"() {
        when:
        def result = service.getUniquePeople(service.PUZZLE_INPUT)

        then:
        result
        result.size() == 8
    }

    def "test calculateAllHappiness"() {
        when:
        def result = service.getUniquePeople(service.PUZZLE_INPUT)
        def perms = []
        result.eachPermutation {
            perms << it
        }

        def haps = service.calculateAllHappiness(service.PUZZLE_INPUT)

        then:
        haps.size() == perms.size()
    }

    def "test calculateAllHappiness Me"() {
        when:
        def result = service.getUniquePeople(service.PUZZLE_INPUT_ME)
        def perms = []
        result.eachPermutation {
            perms << it
        }

        def haps = service.calculateAllHappiness(service.PUZZLE_INPUT_ME)

        then:
        haps.size() == perms.size()
    }

    def "test createMapOfHappiness"() {
        when:
        def people = service.getUniquePeople(service.PUZZLE_INPUT)
        def result = service.createMapOfHappiness(service.PUZZLE_INPUT, people)

        def totalEntries = 0
        result.each {
            totalEntries += it.value.size()
        }

        then:
        result
        totalEntries == 56

    }

    def "test createMapOfHappiness Me"() {
        when:
        def people = service.getUniquePeople(service.PUZZLE_INPUT_ME)
        def result = service.createMapOfHappiness(service.PUZZLE_INPUT_ME, people)

        def totalEntries = 0
        result.each {
            totalEntries += it.value.size()
        }

        then:
        result
        totalEntries == 72

    }


    def "test with actual part a"() {
        when:
        def result = service.calculateAllHappiness(service.PUZZLE_INPUT)
        def mostHappy = result.sort { -it }[0]

        then:
        mostHappy
        mostHappy == 664
    }

    def "test with actual part b"() {
        when:
        def me = service.calculateAllHappiness(service.PUZZLE_INPUT_ME)
        def meHappy = me.sort { -it }[0]

        then:
        meHappy
        meHappy == 640
    }
}
