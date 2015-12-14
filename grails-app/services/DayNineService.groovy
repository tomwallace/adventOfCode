
/**
 * Created by tomwallace on 12/7/15.
 */
class DayNineService {

    // http://adventofcode.com/day/9

    final String PUZZLE_INPUT = """Faerun to Norrath = 129
Faerun to Tristram = 58
Faerun to AlphaCentauri = 13
Faerun to Arbre = 24
Faerun to Snowdin = 60
Faerun to Tambi = 71
Faerun to Straylight = 67
Norrath to Tristram = 142
Norrath to AlphaCentauri = 15
Norrath to Arbre = 135
Norrath to Snowdin = 75
Norrath to Tambi = 82
Norrath to Straylight = 54
Tristram to AlphaCentauri = 118
Tristram to Arbre = 122
Tristram to Snowdin = 103
Tristram to Tambi = 49
Tristram to Straylight = 97
AlphaCentauri to Arbre = 116
AlphaCentauri to Snowdin = 12
AlphaCentauri to Tambi = 18
AlphaCentauri to Straylight = 91
Arbre to Snowdin = 129
Arbre to Tambi = 53
Arbre to Straylight = 40
Snowdin to Tambi = 15
Snowdin to Straylight = 99
Tambi to Straylight = 70"""

    // TODO: Come back and try - a bit stumped

    /*
Every year, Santa manages to deliver all of his presents in a single night.

This year, however, he has some new locations to visit; his elves have provided him the distances between every pair of locations. He can start and end at any two (different) locations he wants, but he must visit each location exactly once. What is the shortest distance he can travel to achieve this?

For example, given the following distances:

London to Dublin = 464
London to Belfast = 518
Dublin to Belfast = 141
The possible routes are therefore:

Dublin -> London -> Belfast = 982
London -> Dublin -> Belfast = 605
London -> Belfast -> Dublin = 659
Dublin -> Belfast -> London = 659
Belfast -> Dublin -> London = 605
Belfast -> London -> Dublin = 982
The shortest of these is London -> Dublin -> Belfast = 605, and so the answer is 605 in this example.

What is the distance of the shortest route?
     */
    // 8 locations in the input

    Integer calculateDistanceShortestRoute(String input) {
        List<String> cities = getUniqueCities(input)
        Map startingCityDistances = [:]

        // Create our map of distances
        Map distances = createMapOfDistances(input, cities)

        // Loop through each of the cities as a starting city
        cities.each { startingCity ->
            Integer totalDistance = 0
            List<String> citiesAlreadyVisited = []
            String city = startingCity

            def i = 0
            // Recurse through the list
            for (i = 0; i <= distances.size() - 1; i++) {
                // If we don't have a distance below, then we have to break out as that starting city was invalid
                List<Integer> dists = distances."${city}"?.values()?.toList()?.collect()
                // Drop all values for cities already visited, as we don't revisit
                citiesAlreadyVisited.each {
                    Integer distToRemove = distances."${city}"."${it}"?.value
                    dists.remove(dists.indexOf(distToRemove))
                }
                // Break on last point of route
                if (dists.size() == 0) {
                    break
                }
                Integer leastDistance = dists.sort()[0]
                String linkingCity = distances."${city}".find { it.value == leastDistance}.key

                totalDistance += leastDistance
                citiesAlreadyVisited << city
                city = linkingCity
            }

            // Create map entry for that starting city's distance
            startingCityDistances["${startingCity}"] = totalDistance
        }

        // Return the smallest value
        return startingCityDistances.values().sort()[0]
    }

    Integer calculateDistanceLongestRoute(String input) {
        List<String> cities = getUniqueCities(input)
        Map startingCityDistances = [:]

        // Create our map of distances
        Map distances = createMapOfDistances(input, cities)

        // Loop through each of the cities as a starting city
        cities.each { startingCity ->
            Integer totalDistance = 0
            List<String> citiesAlreadyVisited = []
            String city = startingCity

            def i = 0
            // Recurse through the list
            for (i = 0; i <= distances.size() - 1; i++) {
                List<Integer> dists = distances."${city}"?.values()?.toList()?.collect()
                // Drop all values for cities already visited, as we don't revisit
                citiesAlreadyVisited.each {
                    Integer distToRemove = distances."${city}"."${it}"?.value
                    dists.remove(dists.indexOf(distToRemove))
                }
                // Break on last point of route
                if (dists.size() == 0) {
                    break
                }
                Integer mostDistance = dists.sort{ -it }[0]
                String linkingCity = distances."${city}".find { it.value ==  mostDistance}.key

                totalDistance +=  mostDistance
                citiesAlreadyVisited << city
                city = linkingCity
            }

            // Create map entry for that starting city's distance
            startingCityDistances["${startingCity}"] = totalDistance
        }

        // Return the smallest value
        return startingCityDistances.values().sort{ -it }[0]
    }


    protected List<String> getUniqueCities(String input) {
        List<String> cities = input.findAll(/(?m)^(\w*)\b/).unique()
        // TODO: Missing Straylight and can't figure out how to get
        cities << "Straylight"
        return cities
    }

    protected Map createMapOfDistances(String input, List<String> cities) {
        // Create our map of distances
        Map distances = [:]
        cities.each { city ->
            Map linksToOtherCities = [:]
            List<String> data = input.findAll(/(?m)^(.*${city}.*)$/)
            data.each { d ->
                Integer distance = d.find(/(\d+)/).toInteger()
                List<String> words = d.findAll(/(\w*)/)
                String otherCityName = words[0] == city ? words[4] : words [0]
                linksToOtherCities["${otherCityName}"] = distance
            }
            distances["${city}"] = linksToOtherCities
        }
        return distances
    }
}
