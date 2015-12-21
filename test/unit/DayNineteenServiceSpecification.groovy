import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Created by tomwallace on 12/7/15.
 */
@TestFor(DayNineteenService)
class DayNineteenServiceSpecification extends Specification {


    def "test getReplacementRules"() {
        when:
        def result = service.getReplacementRules(input)
        println(input)

        then:
        expected == result

        where:
        expected | input
        [['Al', 'ThF'], ['e', 'NAl']]    | "Al => ThF\ne => NAl\n"
        [['Al', 'ThF'], ['e', 'NAl'], ['ee', 'AbC']]    | "Al => ThF\ne => NAl\nee => AbC"
    }

    def "test countDistinctMolecules"() {
        when:
        def rules = "H => HO\nH => OH\nO => HH\n"
        def inputOne = "HOH"
        def resultOne = service.countDistinctMolecules(inputOne, rules)

        def inputTwo = "HOHOHO"
        def resultTwo = service.countDistinctMolecules(inputTwo, rules)

        then:
        resultOne == 4
        resultTwo == 7
    }

    def "test countMinFabricateMolecule"() {
        when:
        def rules = "e => H\ne =>O\nH => HO\nH => OH\nO => HH\n"
        def desiredMoleculeOne = "HOH"
        def resultOne = service.countMinFabricateMolecule(desiredMoleculeOne, rules)

        def desiredMoleculeTwo = "HOHOHO"
        def resultTwo = service.countMinFabricateMolecule(desiredMoleculeTwo, rules)

        then:
        resultOne == 3
        resultTwo == 6
    }

    def "test with actual part a"() {
        when:
        def result = service.countDistinctMolecules(service.PUZZLE_INPUT, service.PUZZLE_RULES)

        then:
        result
        result == 576
    }

    // TODO: Maybe try overnight.  Needed to increase the thread count as getting a stack overflow -       -Xss512M
    // TODO: Indicating recusion problem might not work for this case

    def "test with actual part b"() {
        when:
        def result = service.countMinFabricateMolecule(service.PUZZLE_INPUT, service.PUZZLE_RULES)

        then:
        result
        result == 2117
    }

}
