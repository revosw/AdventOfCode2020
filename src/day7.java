import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day7 {
    private static final Map<String, List<String>> allBagRules = new HashMap<>();
    private static final Map<String, List<String>> shinyGoldenBagRules = new HashMap<>();

    private record BagRule(String bagType, List<String> canBePutIn) {}

    public static void main(String[] args) {
        DATA.lines().forEach(insertBagRule);

        part1();
        part2();
    }

    private static void part1() {
        var startingRules = DATA.lines()
                .filter(takesShinyGoldBag)
                .map(parseBagRule)
                .collect(Collectors.toMap(BagRule::bagType, BagRule::canBePutIn));

        startingRules.forEach(shinyGoldenBagRules::put);
        startingRules.keySet().forEach(day7::eulerTourBags);

        System.out.println(shinyGoldenBagRules.size());
    }

    private static void part2() {}

    private static void eulerTourBags(String bag) {
        allBagRules.get(bag).forEach(day7::eulerTourBags);

        shinyGoldenBagRules.put(bag, allBagRules.get(bag));
    }

    private static final Predicate<String> takesShinyGoldBag = bagRule -> {
        return !bagRule.startsWith("shiny gold") && bagRule.contains("shiny gold");
    };

    private static final Function<String, BagRule> parseBagRule = bagRule -> {
        var bags = Pattern.compile("\\w+ \\w+ bag")
                .matcher(bagRule)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        var bagType = bags.get(0);
        var canBePutInto = bags.subList(1, bags.size());

        if (canBePutInto.contains("no other bag")) {
            return new BagRule(bagType, List.of());
        }

        return new BagRule(bagType, canBePutInto);
    };

    private static final Consumer<String> insertBagRule = bagRule -> {
        var bag = parseBagRule.apply(bagRule);
        allBagRules.put(bag.bagType(), bag.canBePutIn());
    };


    private static final String DATA = "dark maroon bags contain 2 striped silver bags, 4 mirrored maroon bags, 5 shiny gold bags, 1 dotted gold bag.\n" +
            "dark coral bags contain 4 pale blue bags, 3 wavy yellow bags, 4 vibrant tan bags, 3 striped purple bags.\n" +
            "striped aqua bags contain 1 pale aqua bag, 2 muted yellow bags, 4 pale maroon bags, 2 shiny coral bags.\n" +
            "wavy coral bags contain 4 pale purple bags, 2 bright olive bags.\n" +
            "bright aqua bags contain 5 mirrored purple bags, 1 dull maroon bag.\n" +
            "muted plum bags contain 1 dark beige bag.\n" +
            "pale cyan bags contain 5 dull gray bags, 3 posh olive bags, 2 striped silver bags.\n" +
            "muted aqua bags contain 3 muted black bags, 2 posh cyan bags.\n" +
            "wavy fuchsia bags contain 5 light gray bags, 3 wavy beige bags.\n" +
            "plaid orange bags contain 2 vibrant bronze bags, 3 pale silver bags, 1 shiny blue bag, 3 plaid maroon bags.\n" +
            "dotted salmon bags contain 2 clear lavender bags.\n" +
            "dark gray bags contain 3 plaid gray bags, 2 clear yellow bags, 5 posh gray bags.\n" +
            "vibrant aqua bags contain 4 posh salmon bags.\n" +
            "mirrored yellow bags contain 1 drab turquoise bag, 5 drab teal bags, 3 light cyan bags, 5 wavy gray bags.\n" +
            "muted indigo bags contain 2 pale cyan bags, 5 striped brown bags, 3 striped red bags.\n" +
            "striped white bags contain 4 pale maroon bags, 4 dull yellow bags, 3 mirrored white bags.\n" +
            "light beige bags contain 3 clear cyan bags, 5 dull gold bags, 4 dark olive bags.\n" +
            "mirrored turquoise bags contain 3 dull gray bags, 4 muted turquoise bags, 4 dull indigo bags.\n" +
            "clear lime bags contain 2 dotted salmon bags.\n" +
            "dull tomato bags contain 2 dim beige bags, 1 dotted brown bag, 3 faded magenta bags, 4 faded gray bags.\n" +
            "muted red bags contain 4 dark orange bags, 3 light black bags.\n" +
            "bright olive bags contain 4 pale green bags, 2 wavy red bags.\n" +
            "muted cyan bags contain 1 mirrored lavender bag, 1 shiny blue bag.\n" +
            "faded red bags contain 5 bright plum bags, 1 dull violet bag, 4 pale yellow bags, 3 pale coral bags.\n" +
            "shiny white bags contain 2 dark beige bags, 4 clear aqua bags.\n" +
            "dark tomato bags contain 3 pale yellow bags, 2 bright red bags.\n" +
            "clear bronze bags contain 1 dull salmon bag.\n" +
            "dark violet bags contain 4 dark purple bags.\n" +
            "dim indigo bags contain 5 plaid gray bags, 4 pale maroon bags, 2 bright maroon bags, 3 dark fuchsia bags.\n" +
            "striped beige bags contain 1 pale olive bag, 3 shiny purple bags, 1 dull salmon bag.\n" +
            "bright yellow bags contain 1 pale gold bag, 5 shiny maroon bags.\n" +
            "vibrant plum bags contain 5 dotted yellow bags, 2 plaid white bags, 5 drab red bags, 4 clear lavender bags.\n" +
            "clear olive bags contain 3 mirrored magenta bags.\n" +
            "bright lime bags contain 5 mirrored turquoise bags, 1 clear plum bag, 3 dull aqua bags, 1 drab green bag.\n" +
            "bright tan bags contain 1 vibrant lavender bag, 1 vibrant salmon bag, 1 dim green bag.\n" +
            "dull gold bags contain 5 dotted lavender bags.\n" +
            "dull crimson bags contain 5 shiny tomato bags.\n" +
            "dotted tomato bags contain 1 dark coral bag, 5 pale plum bags.\n" +
            "shiny gold bags contain 5 muted orange bags, 2 faded tan bags, 3 faded orange bags, 1 dull brown bag.\n" +
            "bright blue bags contain 1 plaid cyan bag, 4 dim magenta bags, 2 drab magenta bags.\n" +
            "shiny tomato bags contain 5 vibrant aqua bags, 3 clear tan bags.\n" +
            "pale silver bags contain 4 wavy orange bags, 4 dotted green bags, 3 drab silver bags.\n" +
            "faded indigo bags contain 5 striped gold bags, 3 clear olive bags.\n" +
            "faded gray bags contain 2 drab lime bags, 4 clear maroon bags, 1 mirrored silver bag, 1 shiny brown bag.\n" +
            "faded magenta bags contain 3 dim gold bags, 5 wavy lavender bags, 3 posh brown bags.\n" +
            "bright coral bags contain 3 drab tan bags, 4 pale beige bags, 4 clear turquoise bags, 1 faded white bag.\n" +
            "plaid salmon bags contain 1 striped tan bag, 3 pale blue bags.\n" +
            "drab aqua bags contain 3 wavy turquoise bags.\n" +
            "mirrored silver bags contain 1 dull brown bag, 4 pale black bags, 2 mirrored plum bags, 1 dotted red bag.\n" +
            "light salmon bags contain 4 dotted chartreuse bags.\n" +
            "wavy crimson bags contain 2 dim olive bags, 4 mirrored black bags, 4 faded salmon bags.\n" +
            "posh maroon bags contain 2 dark teal bags, 3 dim violet bags, 5 mirrored tan bags.\n" +
            "light lavender bags contain 3 vibrant turquoise bags, 1 pale orange bag, 4 shiny orange bags, 3 drab turquoise bags.\n" +
            "dark lavender bags contain 1 shiny salmon bag, 4 wavy cyan bags, 5 dim lavender bags.\n" +
            "shiny green bags contain 3 shiny brown bags, 5 dim coral bags.\n" +
            "pale violet bags contain 1 faded white bag.\n" +
            "dotted silver bags contain 2 pale blue bags.\n" +
            "posh olive bags contain 1 vibrant chartreuse bag, 4 posh salmon bags, 5 plaid coral bags.\n" +
            "vibrant gray bags contain 5 wavy yellow bags.\n" +
            "wavy brown bags contain 4 vibrant indigo bags, 2 vibrant green bags, 5 wavy blue bags, 3 dim magenta bags.\n" +
            "wavy teal bags contain 4 vibrant indigo bags, 2 vibrant turquoise bags.\n" +
            "mirrored black bags contain 5 vibrant turquoise bags, 1 drab salmon bag, 5 pale tan bags.\n" +
            "faded maroon bags contain 2 wavy crimson bags, 3 faded white bags.\n" +
            "mirrored salmon bags contain 4 striped purple bags, 1 vibrant black bag, 4 drab green bags.\n" +
            "faded brown bags contain 3 wavy turquoise bags.\n" +
            "drab tomato bags contain 3 pale brown bags.\n" +
            "dotted violet bags contain 5 plaid chartreuse bags, 5 shiny beige bags, 2 clear tan bags.\n" +
            "light orange bags contain 1 dull aqua bag, 2 clear silver bags, 3 bright tomato bags, 2 bright teal bags.\n" +
            "drab salmon bags contain 3 dark coral bags, 2 wavy brown bags, 1 striped purple bag, 4 dull gray bags.\n" +
            "dotted maroon bags contain 1 dark orange bag, 1 wavy brown bag.\n" +
            "striped chartreuse bags contain 2 striped yellow bags, 1 shiny orange bag, 1 dotted turquoise bag, 2 pale violet bags.\n" +
            "shiny tan bags contain 4 dim beige bags, 2 pale tomato bags.\n" +
            "plaid purple bags contain 4 light coral bags, 4 faded salmon bags.\n" +
            "wavy tan bags contain 2 vibrant tan bags.\n" +
            "pale lavender bags contain 3 dotted red bags, 1 bright green bag, 1 bright violet bag.\n" +
            "posh crimson bags contain 2 light white bags.\n" +
            "dark lime bags contain 2 plaid white bags, 2 bright gray bags.\n" +
            "light green bags contain 5 bright magenta bags, 1 light chartreuse bag.\n" +
            "light coral bags contain 3 pale black bags, 1 vibrant indigo bag, 1 wavy olive bag.\n" +
            "dark bronze bags contain 2 dim teal bags, 4 dark maroon bags.\n" +
            "light brown bags contain 5 dim white bags, 3 muted green bags, 1 dull white bag.\n" +
            "dim chartreuse bags contain 1 drab bronze bag, 2 mirrored maroon bags, 3 dark salmon bags, 5 light coral bags.\n" +
            "posh tan bags contain 2 plaid tan bags, 5 drab red bags, 5 vibrant salmon bags, 1 plaid salmon bag.\n" +
            "vibrant blue bags contain 2 dim turquoise bags, 4 plaid lime bags, 2 faded turquoise bags, 4 faded gold bags.\n" +
            "dark indigo bags contain 3 pale gray bags, 4 mirrored orange bags, 1 vibrant gray bag.\n" +
            "bright fuchsia bags contain 2 light cyan bags, 3 vibrant chartreuse bags, 4 wavy yellow bags, 2 vibrant olive bags.\n" +
            "posh white bags contain 4 light lime bags, 1 muted teal bag, 1 dull aqua bag.\n" +
            "clear blue bags contain 1 faded tan bag.\n" +
            "drab gold bags contain 5 mirrored beige bags.\n" +
            "posh blue bags contain 2 vibrant brown bags, 3 vibrant salmon bags.\n" +
            "shiny magenta bags contain 2 dotted fuchsia bags.\n" +
            "clear brown bags contain 3 posh olive bags, 1 drab silver bag, 5 dark purple bags.\n" +
            "muted brown bags contain 5 wavy yellow bags.\n" +
            "dim maroon bags contain 1 wavy crimson bag, 2 faded tan bags.\n" +
            "clear gold bags contain 5 dark aqua bags, 1 shiny coral bag, 2 dim fuchsia bags, 1 mirrored blue bag.\n" +
            "dull coral bags contain 4 faded salmon bags, 2 pale aqua bags, 5 dull aqua bags, 4 dull silver bags.\n" +
            "vibrant chartreuse bags contain 5 dull gray bags, 2 bright purple bags.\n" +
            "posh beige bags contain 2 dotted turquoise bags, 3 vibrant tan bags, 4 pale orange bags, 4 bright salmon bags.\n" +
            "bright white bags contain 3 dotted purple bags, 5 bright violet bags.\n" +
            "posh red bags contain 4 vibrant bronze bags, 4 wavy beige bags.\n" +
            "drab tan bags contain 2 dull indigo bags, 3 faded tan bags.\n" +
            "dull blue bags contain 5 dark maroon bags.\n" +
            "wavy plum bags contain 1 clear gray bag, 4 dark teal bags, 5 faded gray bags.\n" +
            "light tomato bags contain 4 muted red bags, 3 muted maroon bags, 1 dim teal bag.\n" +
            "posh purple bags contain 2 wavy black bags, 4 vibrant salmon bags.\n" +
            "dull lavender bags contain 5 posh tomato bags, 4 faded tan bags.\n" +
            "light silver bags contain 3 mirrored salmon bags, 4 pale yellow bags, 4 clear silver bags, 2 dim olive bags.\n" +
            "light black bags contain 2 wavy crimson bags, 1 dull salmon bag, 4 muted black bags.\n" +
            "light purple bags contain 2 vibrant purple bags, 2 vibrant aqua bags.\n" +
            "striped orange bags contain 3 clear aqua bags.\n" +
            "mirrored plum bags contain 3 vibrant aqua bags, 3 vibrant turquoise bags, 5 faded orange bags, 1 bright violet bag.\n" +
            "posh coral bags contain 5 drab yellow bags, 2 faded white bags.\n" +
            "clear tomato bags contain 3 shiny turquoise bags, 1 wavy cyan bag, 1 bright bronze bag, 5 light red bags.\n" +
            "posh bronze bags contain 3 dull orange bags, 4 dull salmon bags, 5 clear blue bags.\n" +
            "shiny crimson bags contain 4 drab green bags, 3 posh lime bags, 2 striped magenta bags, 2 bright teal bags.\n" +
            "drab chartreuse bags contain 1 wavy yellow bag, 4 mirrored beige bags, 4 dull gold bags, 5 dull plum bags.\n" +
            "striped tomato bags contain 3 plaid tan bags, 3 pale bronze bags, 4 dull yellow bags, 3 drab magenta bags.\n" +
            "wavy chartreuse bags contain 3 dim coral bags, 4 bright chartreuse bags, 5 pale plum bags.\n" +
            "posh chartreuse bags contain 4 dark purple bags, 3 muted magenta bags, 2 faded black bags.\n" +
            "dotted aqua bags contain 2 wavy green bags, 5 vibrant tomato bags.\n" +
            "dotted red bags contain 5 bright purple bags, 1 dotted gold bag, 5 mirrored magenta bags, 2 plaid indigo bags.\n" +
            "faded salmon bags contain 1 wavy yellow bag.\n" +
            "vibrant fuchsia bags contain 3 mirrored coral bags.\n" +
            "drab coral bags contain 5 mirrored brown bags, 5 dotted lavender bags, 1 drab bronze bag, 3 dim chartreuse bags.\n" +
            "posh aqua bags contain 3 bright olive bags.\n" +
            "dark chartreuse bags contain 5 plaid silver bags.\n" +
            "vibrant maroon bags contain 5 shiny brown bags, 2 faded black bags, 1 drab silver bag.\n" +
            "striped salmon bags contain 5 dark gray bags, 1 muted tomato bag.\n" +
            "posh gray bags contain 2 clear plum bags.\n" +
            "dotted gray bags contain 3 drab salmon bags, 1 dim coral bag.\n" +
            "clear aqua bags contain 4 dotted lavender bags, 1 striped purple bag, 3 light purple bags.\n" +
            "dark aqua bags contain 2 dark indigo bags, 5 bright bronze bags.\n" +
            "bright brown bags contain 2 plaid salmon bags, 4 faded indigo bags.\n" +
            "clear violet bags contain 3 pale cyan bags, 3 dull aqua bags, 4 pale brown bags, 5 dim plum bags.\n" +
            "wavy salmon bags contain 4 clear tomato bags, 4 bright bronze bags, 5 posh purple bags, 5 faded black bags.\n" +
            "drab green bags contain 4 mirrored maroon bags, 2 vibrant aqua bags, 4 dim olive bags.\n" +
            "posh silver bags contain 4 muted black bags, 5 pale cyan bags, 2 dark plum bags, 1 shiny bronze bag.\n" +
            "dull indigo bags contain 4 striped purple bags.\n" +
            "pale magenta bags contain 1 drab coral bag, 5 plaid blue bags, 1 wavy yellow bag, 5 dark salmon bags.\n" +
            "light olive bags contain 2 pale gold bags.\n" +
            "muted crimson bags contain 1 striped cyan bag, 1 vibrant bronze bag, 1 dull coral bag, 4 vibrant black bags.\n" +
            "plaid yellow bags contain 3 bright silver bags.\n" +
            "vibrant gold bags contain 5 vibrant tan bags.\n" +
            "bright beige bags contain 2 pale beige bags, 2 dark lavender bags, 1 dull teal bag.\n" +
            "faded white bags contain 2 pale brown bags, 3 muted orange bags, 3 dull indigo bags.\n" +
            "striped gray bags contain 5 pale chartreuse bags.\n" +
            "plaid brown bags contain 4 dull teal bags, 2 wavy beige bags.\n" +
            "wavy yellow bags contain no other bags.\n" +
            "dotted green bags contain 5 posh gray bags.\n" +
            "dull turquoise bags contain 3 faded coral bags, 2 bright green bags.\n" +
            "faded plum bags contain 1 dull fuchsia bag.\n" +
            "clear maroon bags contain 2 light purple bags, 2 dim crimson bags, 5 vibrant bronze bags.\n" +
            "posh green bags contain 1 plaid silver bag.\n" +
            "vibrant teal bags contain 5 dotted gold bags.\n" +
            "faded coral bags contain 3 dull aqua bags, 3 wavy orange bags, 3 drab chartreuse bags, 4 muted olive bags.\n" +
            "wavy beige bags contain 1 dark plum bag, 2 dull lavender bags, 2 drab green bags.\n" +
            "dim magenta bags contain 2 plaid coral bags, 2 faded orange bags.\n" +
            "striped teal bags contain 2 bright tan bags.\n" +
            "drab crimson bags contain 2 clear salmon bags, 2 clear orange bags, 1 striped yellow bag.\n" +
            "dotted yellow bags contain 4 dull gray bags.\n" +
            "mirrored tan bags contain 1 dotted fuchsia bag, 2 vibrant lavender bags.\n" +
            "wavy aqua bags contain 4 drab gray bags, 3 muted yellow bags, 5 shiny lavender bags, 2 plaid turquoise bags.\n" +
            "wavy gray bags contain 5 dim green bags, 1 plaid blue bag.\n" +
            "dull teal bags contain 3 mirrored black bags, 4 dark plum bags, 2 drab silver bags, 4 mirrored gray bags.\n" +
            "striped indigo bags contain 4 clear violet bags, 5 dotted yellow bags, 4 clear tomato bags.\n" +
            "dim brown bags contain 1 drab bronze bag, 5 shiny yellow bags, 1 pale olive bag, 2 drab silver bags.\n" +
            "mirrored teal bags contain 3 faded orange bags.\n" +
            "dull salmon bags contain 3 dark indigo bags, 2 striped gray bags.\n" +
            "dark tan bags contain 5 striped lime bags, 1 dark purple bag, 5 faded gold bags.\n" +
            "clear teal bags contain 3 dark crimson bags, 1 bright tomato bag.\n" +
            "dark olive bags contain 4 striped gold bags, 1 striped gray bag, 1 bright tomato bag.\n" +
            "posh indigo bags contain 1 mirrored maroon bag, 4 striped indigo bags.\n" +
            "drab yellow bags contain 5 dull teal bags, 2 plaid turquoise bags.\n" +
            "faded bronze bags contain 3 dull orange bags.\n" +
            "dull chartreuse bags contain 2 dull bronze bags, 3 light gold bags, 2 striped white bags.\n" +
            "posh orange bags contain 5 pale crimson bags, 1 bright blue bag, 5 clear green bags.\n" +
            "striped coral bags contain 4 pale gray bags.\n" +
            "clear tan bags contain 4 dark plum bags, 1 posh olive bag.\n" +
            "dotted tan bags contain 1 faded aqua bag, 3 pale brown bags, 5 dim chartreuse bags.\n" +
            "striped brown bags contain 5 dark black bags.\n" +
            "clear red bags contain 4 faded lime bags, 3 striped blue bags, 3 light fuchsia bags, 4 drab turquoise bags.\n" +
            "plaid gold bags contain 5 dim chartreuse bags.\n" +
            "plaid cyan bags contain no other bags.\n" +
            "muted tomato bags contain 2 pale blue bags, 2 shiny bronze bags, 1 light teal bag.\n" +
            "dotted crimson bags contain 2 dark maroon bags.\n" +
            "light turquoise bags contain 3 wavy orange bags, 5 drab red bags, 5 plaid yellow bags.\n" +
            "wavy cyan bags contain 1 dim magenta bag, 2 shiny blue bags.\n" +
            "dim coral bags contain 3 vibrant indigo bags, 4 vibrant turquoise bags.\n" +
            "dark blue bags contain 3 clear teal bags, 1 dim bronze bag, 4 light tan bags, 4 wavy indigo bags.\n" +
            "faded gold bags contain 2 posh salmon bags, 4 plaid white bags, 2 dull cyan bags.\n" +
            "vibrant turquoise bags contain no other bags.\n" +
            "dim fuchsia bags contain 3 shiny blue bags, 1 dark bronze bag, 2 wavy yellow bags.\n" +
            "muted blue bags contain 4 shiny violet bags, 5 muted tomato bags, 5 light salmon bags.\n" +
            "plaid bronze bags contain 4 dull green bags.\n" +
            "mirrored maroon bags contain 3 dark coral bags, 4 clear plum bags.\n" +
            "muted olive bags contain 2 dark brown bags, 5 bright white bags.\n" +
            "pale crimson bags contain 1 pale blue bag, 4 clear plum bags, 5 wavy yellow bags, 1 dotted purple bag.\n" +
            "striped crimson bags contain 4 muted teal bags.\n" +
            "dark orange bags contain 5 dark maroon bags, 2 drab cyan bags, 1 dotted chartreuse bag, 5 shiny green bags.\n" +
            "light white bags contain 5 faded gray bags, 1 dim beige bag, 5 bright violet bags.\n" +
            "plaid white bags contain 1 pale brown bag.\n" +
            "dim orange bags contain 3 faded blue bags.\n" +
            "vibrant green bags contain 4 posh salmon bags, 4 plaid cyan bags, 3 clear plum bags.\n" +
            "dotted beige bags contain 5 posh black bags, 2 mirrored yellow bags, 3 mirrored salmon bags.\n" +
            "dim black bags contain 2 vibrant cyan bags, 3 shiny turquoise bags, 2 dotted turquoise bags, 1 muted orange bag.\n" +
            "mirrored tomato bags contain 4 dotted plum bags.\n" +
            "dim violet bags contain 1 dotted chartreuse bag.\n" +
            "pale coral bags contain 5 dim silver bags, 3 clear gray bags.\n" +
            "vibrant lavender bags contain 3 dim bronze bags.\n" +
            "dark crimson bags contain 1 shiny brown bag.\n" +
            "bright orange bags contain 3 muted tomato bags, 2 pale black bags.\n" +
            "clear yellow bags contain 4 striped purple bags, 5 mirrored black bags, 4 posh gray bags.\n" +
            "posh violet bags contain 3 clear red bags.\n" +
            "plaid tan bags contain 1 pale cyan bag.\n" +
            "pale gold bags contain 5 striped olive bags.\n" +
            "muted lavender bags contain 1 clear crimson bag, 5 light white bags.\n" +
            "faded crimson bags contain 3 clear coral bags.\n" +
            "wavy blue bags contain no other bags.\n" +
            "faded purple bags contain 5 dark crimson bags.\n" +
            "dim silver bags contain 5 mirrored silver bags, 1 vibrant turquoise bag, 5 wavy turquoise bags.\n" +
            "pale gray bags contain 4 wavy yellow bags, 2 vibrant tan bags, 2 pale tan bags, 1 dark coral bag.\n" +
            "dim purple bags contain 2 striped silver bags, 1 plaid blue bag, 5 pale cyan bags.\n" +
            "striped purple bags contain no other bags.\n" +
            "plaid red bags contain 4 faded coral bags.\n" +
            "dull bronze bags contain 5 bright coral bags.\n" +
            "light teal bags contain 2 dotted green bags, 4 pale purple bags.\n" +
            "mirrored blue bags contain 2 striped silver bags.\n" +
            "muted green bags contain 2 drab red bags, 1 drab tan bag.\n" +
            "muted gray bags contain 2 muted coral bags.\n" +
            "light fuchsia bags contain 2 dim indigo bags.\n" +
            "shiny orange bags contain 2 bright green bags, 4 shiny lime bags, 1 striped red bag, 5 dull turquoise bags.\n" +
            "bright purple bags contain 1 striped purple bag, 3 shiny brown bags, 4 vibrant indigo bags.\n" +
            "dotted brown bags contain 2 faded blue bags, 5 shiny lavender bags, 4 clear lavender bags, 1 bright bronze bag.\n" +
            "light maroon bags contain 4 muted brown bags, 4 striped orange bags, 1 clear plum bag, 3 dull lavender bags.\n" +
            "wavy green bags contain 1 dull brown bag, 1 clear plum bag.\n" +
            "dull fuchsia bags contain 2 dark coral bags, 3 light coral bags.\n" +
            "dotted purple bags contain no other bags.\n" +
            "light plum bags contain 4 muted gold bags, 1 muted crimson bag.\n" +
            "light red bags contain 5 dull magenta bags, 2 wavy olive bags, 3 drab silver bags, 1 dim magenta bag.\n" +
            "faded green bags contain 3 dotted turquoise bags, 2 dull bronze bags, 2 muted green bags.\n" +
            "posh lime bags contain 3 faded brown bags, 4 wavy brown bags.\n" +
            "shiny black bags contain 2 plaid blue bags, 5 drab salmon bags.\n" +
            "muted teal bags contain 5 bright violet bags, 4 pale cyan bags.\n" +
            "light cyan bags contain 2 muted silver bags, 3 faded salmon bags, 5 shiny brown bags, 5 drab magenta bags.\n" +
            "faded beige bags contain 2 muted red bags.\n" +
            "mirrored aqua bags contain 4 faded silver bags, 4 dull maroon bags, 3 dull olive bags, 4 dim bronze bags.\n" +
            "muted silver bags contain 3 dotted cyan bags, 3 muted fuchsia bags, 5 plaid purple bags.\n" +
            "plaid teal bags contain 4 mirrored magenta bags, 3 dotted red bags.\n" +
            "bright teal bags contain 1 shiny brown bag, 4 vibrant tan bags.\n" +
            "dark red bags contain 3 wavy red bags, 4 dotted violet bags, 5 pale tomato bags.\n" +
            "muted maroon bags contain 1 plaid indigo bag.\n" +
            "dark fuchsia bags contain 4 bright teal bags, 4 dim blue bags, 5 vibrant lavender bags, 4 striped yellow bags.\n" +
            "dark white bags contain 1 drab green bag, 4 dotted chartreuse bags.\n" +
            "drab maroon bags contain 1 faded salmon bag, 1 dotted orange bag, 1 faded fuchsia bag.\n" +
            "dull violet bags contain 2 dotted tan bags, 4 pale cyan bags.\n" +
            "shiny violet bags contain 1 dull red bag, 4 wavy indigo bags, 1 clear violet bag, 2 dotted white bags.\n" +
            "posh teal bags contain 5 muted green bags, 1 dotted brown bag.\n" +
            "dotted teal bags contain 4 pale olive bags, 5 mirrored crimson bags.\n" +
            "dim olive bags contain 3 pale white bags, 1 posh olive bag, 3 striped orange bags, 1 striped silver bag.\n" +
            "shiny purple bags contain 3 shiny salmon bags, 5 posh plum bags, 1 striped white bag, 5 shiny black bags.\n" +
            "mirrored white bags contain 3 muted turquoise bags, 5 striped gray bags, 1 faded lime bag.\n" +
            "mirrored indigo bags contain 1 dim teal bag, 3 drab lavender bags, 1 plaid coral bag, 5 posh orange bags.\n" +
            "wavy orange bags contain 2 mirrored maroon bags, 1 striped silver bag, 2 wavy yellow bags, 2 dim crimson bags.\n" +
            "light indigo bags contain 2 vibrant cyan bags, 2 vibrant gray bags.\n" +
            "dark cyan bags contain 3 clear tomato bags.\n" +
            "dim lime bags contain 5 dull indigo bags, 1 wavy olive bag.\n" +
            "pale beige bags contain 2 muted red bags, 2 clear tomato bags, 3 posh olive bags.\n" +
            "plaid silver bags contain 4 dim plum bags, 1 dim beige bag, 3 plaid salmon bags.\n" +
            "wavy silver bags contain 1 plaid indigo bag.\n" +
            "wavy violet bags contain 4 posh chartreuse bags, 2 mirrored tomato bags.\n" +
            "clear beige bags contain 4 dark maroon bags.\n" +
            "vibrant cyan bags contain 1 striped purple bag, 4 muted black bags.\n" +
            "faded teal bags contain 4 dark blue bags, 1 pale brown bag.\n" +
            "drab gray bags contain 3 striped crimson bags, 2 shiny gold bags, 2 shiny magenta bags.\n" +
            "clear turquoise bags contain 5 light crimson bags, 4 dotted cyan bags.\n" +
            "plaid lime bags contain 4 dotted chartreuse bags, 2 plaid orange bags, 4 shiny fuchsia bags.\n" +
            "faded tan bags contain 5 vibrant green bags.\n" +
            "dotted indigo bags contain 3 light violet bags, 4 dull fuchsia bags.\n" +
            "muted turquoise bags contain 5 pale tan bags, 4 dim olive bags, 2 plaid indigo bags.\n" +
            "vibrant orange bags contain 4 dotted yellow bags, 4 plaid cyan bags.\n" +
            "drab fuchsia bags contain 1 dim plum bag, 1 striped red bag, 2 vibrant cyan bags.\n" +
            "clear green bags contain 1 dull magenta bag, 5 wavy blue bags, 1 vibrant olive bag, 1 vibrant tan bag.\n" +
            "light magenta bags contain 3 posh bronze bags.\n" +
            "dull black bags contain 4 drab bronze bags.\n" +
            "dull beige bags contain 3 dull lime bags, 2 wavy coral bags, 1 plaid yellow bag.\n" +
            "dotted plum bags contain 4 posh salmon bags, 3 vibrant chartreuse bags, 5 dim green bags.\n" +
            "vibrant salmon bags contain 4 dim purple bags.\n" +
            "mirrored crimson bags contain 2 faded tomato bags, 1 striped blue bag, 3 vibrant green bags, 3 striped turquoise bags.\n" +
            "dotted fuchsia bags contain 2 plaid coral bags, 5 dull brown bags, 1 dark maroon bag.\n" +
            "posh salmon bags contain no other bags.\n" +
            "dotted cyan bags contain 4 clear aqua bags, 5 plaid gray bags, 3 bright bronze bags.\n" +
            "mirrored bronze bags contain 3 vibrant black bags, 3 faded maroon bags, 1 drab coral bag, 1 muted black bag.\n" +
            "bright magenta bags contain 2 dim magenta bags, 3 dotted gold bags, 4 posh gold bags.\n" +
            "faded violet bags contain 1 light red bag.\n" +
            "dim yellow bags contain 1 dull orange bag, 1 muted purple bag, 2 faded indigo bags, 5 dotted plum bags.\n" +
            "wavy lime bags contain 1 clear blue bag.\n" +
            "mirrored coral bags contain 3 dull maroon bags, 1 striped orange bag.\n" +
            "wavy maroon bags contain 4 dark gold bags.\n" +
            "bright gold bags contain 3 dim orange bags, 5 dark gray bags, 2 clear fuchsia bags, 5 clear maroon bags.\n" +
            "dotted turquoise bags contain 3 dim olive bags, 3 light red bags, 3 dull fuchsia bags.\n" +
            "striped plum bags contain 4 dark tan bags, 2 mirrored teal bags, 1 striped brown bag.\n" +
            "shiny fuchsia bags contain 5 clear green bags, 5 muted tomato bags, 1 dark coral bag, 5 wavy blue bags.\n" +
            "vibrant white bags contain 1 dim olive bag, 2 bright silver bags, 2 muted blue bags.\n" +
            "dull cyan bags contain 2 clear teal bags, 4 plaid blue bags.\n" +
            "drab teal bags contain 4 clear crimson bags, 5 wavy lime bags, 4 plaid turquoise bags.\n" +
            "posh turquoise bags contain 5 plaid fuchsia bags, 5 pale blue bags, 3 vibrant green bags.\n" +
            "bright red bags contain 1 clear magenta bag, 4 striped aqua bags, 2 posh cyan bags, 1 dull salmon bag.\n" +
            "dark teal bags contain 3 dull yellow bags, 3 plaid white bags, 3 clear cyan bags, 4 striped red bags.\n" +
            "vibrant beige bags contain 3 drab silver bags, 4 wavy orange bags, 3 pale brown bags, 2 faded orange bags.\n" +
            "clear purple bags contain 5 faded indigo bags, 3 striped maroon bags, 4 vibrant plum bags, 3 light red bags.\n" +
            "pale lime bags contain 2 plaid blue bags, 1 bright violet bag, 5 faded bronze bags, 1 dotted plum bag.\n" +
            "shiny lavender bags contain 5 mirrored plum bags.\n" +
            "posh brown bags contain 1 drab yellow bag.\n" +
            "dark black bags contain 5 dull salmon bags, 3 pale silver bags, 2 vibrant turquoise bags.\n" +
            "shiny turquoise bags contain 3 plaid blue bags, 3 dim salmon bags, 5 dull gold bags.\n" +
            "dim green bags contain 3 vibrant turquoise bags, 5 faded tan bags.\n" +
            "pale maroon bags contain 3 dull fuchsia bags.\n" +
            "dull purple bags contain 2 vibrant aqua bags, 2 bright blue bags.\n" +
            "dull magenta bags contain 4 pale blue bags, 2 shiny bronze bags, 2 pale lavender bags, 3 pale chartreuse bags.\n" +
            "light gray bags contain 4 bright orange bags, 5 dull gray bags.\n" +
            "faded black bags contain 2 clear crimson bags, 4 posh gray bags.\n" +
            "plaid tomato bags contain 1 light bronze bag, 2 posh plum bags.\n" +
            "faded aqua bags contain 4 shiny gold bags, 5 pale tomato bags, 1 plaid blue bag, 1 faded coral bag.\n" +
            "muted tan bags contain 2 muted tomato bags, 2 light white bags, 4 wavy lavender bags, 2 dim turquoise bags.\n" +
            "dark turquoise bags contain 4 dotted silver bags, 3 vibrant chartreuse bags, 4 striped green bags, 2 dotted black bags.\n" +
            "dim lavender bags contain 3 dark indigo bags, 3 wavy orange bags, 5 faded orange bags, 2 striped orange bags.\n" +
            "plaid black bags contain 3 light violet bags, 3 mirrored bronze bags, 1 clear blue bag, 5 clear olive bags.\n" +
            "clear lavender bags contain 1 dim bronze bag.\n" +
            "pale blue bags contain no other bags.\n" +
            "mirrored magenta bags contain 1 vibrant tan bag, 1 wavy turquoise bag, 4 clear aqua bags.\n" +
            "shiny gray bags contain 3 wavy indigo bags, 5 striped gold bags, 1 drab cyan bag.\n" +
            "light bronze bags contain 3 dark aqua bags, 4 striped gold bags.\n" +
            "muted black bags contain 3 faded white bags, 5 pale crimson bags, 3 muted brown bags, 1 vibrant tan bag.\n" +
            "wavy purple bags contain 3 pale bronze bags, 5 shiny maroon bags, 4 dotted teal bags, 4 pale indigo bags.\n" +
            "muted orange bags contain 1 dotted chartreuse bag, 2 pale blue bags, 3 muted brown bags, 1 posh tomato bag.\n" +
            "wavy turquoise bags contain 2 drab silver bags, 2 vibrant purple bags, 3 faded tan bags.\n" +
            "dull plum bags contain 3 striped silver bags, 4 clear violet bags.\n" +
            "mirrored olive bags contain 4 shiny violet bags.\n" +
            "shiny teal bags contain 5 dim purple bags, 3 faded violet bags, 5 plaid crimson bags.\n" +
            "dotted coral bags contain 4 dull indigo bags, 5 muted silver bags, 1 faded white bag.\n" +
            "striped olive bags contain 5 posh tomato bags, 1 pale purple bag.\n" +
            "shiny beige bags contain 2 wavy red bags.\n" +
            "plaid aqua bags contain 2 muted teal bags.\n" +
            "shiny silver bags contain 1 dotted gold bag, 4 plaid tomato bags.\n" +
            "muted chartreuse bags contain 2 pale crimson bags, 2 mirrored black bags, 5 dark purple bags, 3 wavy tan bags.\n" +
            "shiny lime bags contain 1 light tan bag.\n" +
            "vibrant coral bags contain 1 dotted violet bag, 3 faded gray bags, 3 pale magenta bags.\n" +
            "clear orange bags contain 4 mirrored brown bags, 1 posh turquoise bag.\n" +
            "pale white bags contain 2 dotted gold bags, 3 dotted lavender bags.\n" +
            "dark green bags contain 5 clear tan bags.\n" +
            "pale olive bags contain 4 mirrored black bags, 1 dull magenta bag.\n" +
            "faded blue bags contain 5 dull magenta bags.\n" +
            "dull gray bags contain 4 vibrant indigo bags, 2 pale crimson bags, 2 clear plum bags.\n" +
            "dull lime bags contain 4 dim salmon bags.\n" +
            "bright green bags contain 1 drab silver bag, 5 bright bronze bags.\n" +
            "dim salmon bags contain 3 dotted silver bags.\n" +
            "dim red bags contain 1 vibrant green bag, 2 drab tan bags, 1 mirrored brown bag.\n" +
            "clear magenta bags contain 1 clear cyan bag, 3 light bronze bags.\n" +
            "dotted bronze bags contain 2 striped yellow bags, 3 dark maroon bags.\n" +
            "dotted blue bags contain 3 dotted tan bags, 4 muted violet bags.\n" +
            "bright cyan bags contain 4 pale turquoise bags, 4 dull yellow bags.\n" +
            "drab lavender bags contain 1 shiny green bag.\n" +
            "bright black bags contain 3 mirrored lime bags.\n" +
            "wavy black bags contain 1 faded indigo bag, 4 dim salmon bags, 1 drab aqua bag, 5 dull maroon bags.\n" +
            "clear gray bags contain 2 muted fuchsia bags, 4 clear yellow bags.\n" +
            "bright chartreuse bags contain 2 bright tomato bags.\n" +
            "posh black bags contain 5 dotted tan bags, 3 muted orange bags, 2 dim purple bags, 1 shiny cyan bag.\n" +
            "dull green bags contain 1 striped tan bag, 1 dull magenta bag.\n" +
            "mirrored green bags contain 3 posh salmon bags, 5 mirrored purple bags, 4 vibrant magenta bags.\n" +
            "dull olive bags contain 1 dim maroon bag, 4 striped gold bags, 2 shiny white bags, 3 clear tomato bags.\n" +
            "pale purple bags contain 3 dull salmon bags, 1 drab cyan bag, 5 bright green bags, 5 drab salmon bags.\n" +
            "mirrored lime bags contain 2 posh white bags.\n" +
            "drab blue bags contain 3 pale tomato bags, 1 shiny plum bag.\n" +
            "dim plum bags contain 2 vibrant indigo bags, 4 faded orange bags, 3 wavy cyan bags.\n" +
            "wavy magenta bags contain 1 muted fuchsia bag.\n" +
            "wavy lavender bags contain 3 shiny lime bags.\n" +
            "plaid beige bags contain 5 bright beige bags, 4 pale black bags.\n" +
            "mirrored gold bags contain 4 muted salmon bags, 3 striped black bags, 3 dotted red bags, 3 dim orange bags.\n" +
            "clear salmon bags contain 1 striped turquoise bag, 1 muted silver bag.\n" +
            "vibrant purple bags contain 5 vibrant aqua bags.\n" +
            "pale turquoise bags contain 4 wavy maroon bags.\n" +
            "dull brown bags contain 5 dotted purple bags, 5 vibrant turquoise bags.\n" +
            "drab lime bags contain 3 dim magenta bags.\n" +
            "vibrant black bags contain 1 drab magenta bag.\n" +
            "striped silver bags contain 2 shiny bronze bags, 5 striped purple bags.\n" +
            "dim turquoise bags contain 2 pale brown bags, 4 bright green bags, 1 drab salmon bag.\n" +
            "shiny red bags contain 4 light purple bags, 2 dull orange bags, 4 striped magenta bags, 3 dull red bags.\n" +
            "dotted magenta bags contain 3 faded silver bags, 3 dull purple bags, 5 dotted tan bags.\n" +
            "dotted lavender bags contain 2 vibrant indigo bags, 1 clear plum bag.\n" +
            "shiny indigo bags contain 1 dull lavender bag, 3 vibrant tan bags, 4 faded lime bags, 5 drab tan bags.\n" +
            "striped violet bags contain 1 muted tomato bag.\n" +
            "muted salmon bags contain 2 light cyan bags, 4 dull tomato bags, 2 pale chartreuse bags, 1 dotted gray bag.\n" +
            "mirrored fuchsia bags contain 4 faded silver bags, 4 plaid gray bags, 4 dotted purple bags.\n" +
            "light aqua bags contain 4 striped gold bags, 1 dark blue bag, 3 dim red bags, 2 wavy gold bags.\n" +
            "dark yellow bags contain 2 pale gray bags, 1 shiny blue bag, 4 faded turquoise bags.\n" +
            "striped fuchsia bags contain 4 muted magenta bags.\n" +
            "clear silver bags contain 2 plaid purple bags, 2 plaid indigo bags, 1 muted orange bag, 3 clear plum bags.\n" +
            "posh yellow bags contain 1 posh silver bag, 3 dotted gold bags.\n" +
            "dark magenta bags contain 1 drab violet bag, 2 bright purple bags, 4 shiny coral bags, 5 striped tan bags.\n" +
            "bright tomato bags contain 1 dotted purple bag, 2 dotted chartreuse bags, 2 wavy yellow bags.\n" +
            "bright plum bags contain 1 bright blue bag, 2 faded orange bags, 2 dim coral bags, 3 dotted cyan bags.\n" +
            "shiny maroon bags contain 3 dotted crimson bags, 5 bright teal bags, 5 dotted fuchsia bags, 4 dull green bags.\n" +
            "pale yellow bags contain 4 muted silver bags, 4 dull teal bags, 4 muted purple bags.\n" +
            "plaid fuchsia bags contain 3 dull lavender bags.\n" +
            "clear white bags contain 5 light turquoise bags.\n" +
            "striped lavender bags contain 2 plaid lime bags, 4 dark white bags.\n" +
            "posh cyan bags contain 3 faded coral bags, 5 light maroon bags, 3 faded indigo bags.\n" +
            "faded lime bags contain 2 wavy yellow bags.\n" +
            "striped red bags contain 1 plaid indigo bag.\n" +
            "light tan bags contain 4 wavy blue bags.\n" +
            "vibrant violet bags contain 4 striped purple bags, 4 light cyan bags, 5 faded tan bags, 4 light yellow bags.\n" +
            "dotted gold bags contain 4 vibrant indigo bags, 2 bright bronze bags, 2 drab silver bags, 5 muted brown bags.\n" +
            "shiny plum bags contain 1 faded lime bag, 4 faded orange bags, 2 vibrant beige bags, 2 striped orange bags.\n" +
            "striped cyan bags contain 5 clear beige bags.\n" +
            "striped yellow bags contain 3 dim teal bags, 5 clear maroon bags, 1 light cyan bag.\n" +
            "muted yellow bags contain 3 drab bronze bags, 2 pale gray bags.\n" +
            "clear indigo bags contain 1 clear beige bag, 2 bright silver bags, 1 dim salmon bag.\n" +
            "striped green bags contain 1 mirrored red bag, 3 shiny blue bags.\n" +
            "plaid plum bags contain 1 drab cyan bag, 5 light lime bags, 4 muted lavender bags.\n" +
            "pale green bags contain 2 dotted chartreuse bags, 3 plaid magenta bags.\n" +
            "wavy bronze bags contain 2 pale purple bags, 1 pale teal bag, 1 muted bronze bag, 1 mirrored beige bag.\n" +
            "clear black bags contain 4 vibrant brown bags, 5 pale tomato bags, 4 bright teal bags, 3 wavy brown bags.\n" +
            "pale fuchsia bags contain 3 pale beige bags, 5 plaid blue bags, 3 light violet bags, 3 striped blue bags.\n" +
            "vibrant magenta bags contain 5 posh plum bags, 2 mirrored turquoise bags, 2 light red bags, 2 pale plum bags.\n" +
            "drab olive bags contain 5 vibrant green bags, 4 dull turquoise bags, 4 muted fuchsia bags.\n" +
            "bright salmon bags contain 1 muted silver bag, 1 drab purple bag, 5 pale cyan bags.\n" +
            "shiny coral bags contain 4 wavy magenta bags, 5 dark olive bags.\n" +
            "mirrored cyan bags contain 3 dim chartreuse bags, 4 pale aqua bags.\n" +
            "plaid blue bags contain 5 bright purple bags.\n" +
            "vibrant tomato bags contain 5 faded tomato bags, 3 muted silver bags, 5 dotted turquoise bags.\n" +
            "mirrored brown bags contain 2 pale black bags.\n" +
            "shiny olive bags contain 3 plaid teal bags, 3 vibrant fuchsia bags.\n" +
            "shiny blue bags contain 3 dark coral bags, 4 bright purple bags, 2 pale cyan bags, 5 plaid indigo bags.\n" +
            "plaid crimson bags contain 5 wavy crimson bags, 5 clear crimson bags, 4 vibrant red bags.\n" +
            "striped black bags contain 1 faded chartreuse bag, 2 dull brown bags, 4 clear cyan bags, 1 light teal bag.\n" +
            "wavy gold bags contain 2 dull gray bags, 1 dark lavender bag, 3 pale gray bags.\n" +
            "faded turquoise bags contain 3 bright white bags, 4 pale bronze bags, 5 pale lavender bags.\n" +
            "dull maroon bags contain 2 bright orange bags, 5 vibrant bronze bags, 4 drab chartreuse bags.\n" +
            "striped bronze bags contain 2 pale lavender bags, 1 dull brown bag.\n" +
            "plaid violet bags contain 5 mirrored plum bags.\n" +
            "light violet bags contain 4 drab blue bags, 2 plaid beige bags, 3 pale crimson bags.\n" +
            "posh lavender bags contain 2 faded tomato bags, 4 dim crimson bags.\n" +
            "dark salmon bags contain 1 pale crimson bag.\n" +
            "dotted black bags contain 4 wavy lime bags, 1 faded salmon bag, 3 pale blue bags, 5 wavy blue bags.\n" +
            "shiny aqua bags contain 1 bright blue bag, 4 drab green bags, 2 light teal bags, 2 dotted green bags.\n" +
            "shiny salmon bags contain 3 dim magenta bags.\n" +
            "dark silver bags contain 1 shiny tan bag, 3 wavy yellow bags, 4 drab violet bags.\n" +
            "pale red bags contain 4 mirrored cyan bags, 4 clear gray bags, 2 dotted tan bags.\n" +
            "dark brown bags contain 1 plaid indigo bag.\n" +
            "drab cyan bags contain 2 wavy green bags, 2 pale gray bags, 2 dotted purple bags, 3 dark beige bags.\n" +
            "dim gray bags contain 2 bright salmon bags, 2 clear silver bags, 2 light turquoise bags, 4 drab plum bags.\n" +
            "pale black bags contain 1 plaid coral bag, 4 vibrant turquoise bags, 1 pale brown bag.\n" +
            "dotted olive bags contain 2 faded blue bags, 2 dull maroon bags, 3 dim fuchsia bags, 5 light fuchsia bags.\n" +
            "posh fuchsia bags contain 5 dark lavender bags.\n" +
            "pale orange bags contain 2 vibrant aqua bags, 2 vibrant green bags, 1 bright coral bag.\n" +
            "faded cyan bags contain 4 clear orange bags, 5 dull orange bags, 4 dotted bronze bags.\n" +
            "dim tan bags contain 2 dim chartreuse bags, 3 vibrant turquoise bags, 2 faded coral bags, 2 dim coral bags.\n" +
            "dim gold bags contain 2 wavy magenta bags.\n" +
            "dull aqua bags contain 2 wavy yellow bags.\n" +
            "posh tomato bags contain 1 muted brown bag.\n" +
            "dark gold bags contain 4 muted fuchsia bags, 4 light salmon bags.\n" +
            "clear chartreuse bags contain 3 light coral bags.\n" +
            "plaid chartreuse bags contain 1 light silver bag, 5 vibrant green bags, 3 wavy lavender bags, 4 vibrant turquoise bags.\n" +
            "mirrored red bags contain 5 plaid aqua bags, 4 clear cyan bags, 1 mirrored magenta bag.\n" +
            "dim beige bags contain 4 dull green bags, 2 dim green bags.\n" +
            "mirrored beige bags contain 1 drab magenta bag, 4 clear tan bags, 5 mirrored turquoise bags, 4 drab tan bags.\n" +
            "bright maroon bags contain 3 vibrant green bags, 5 shiny gold bags.\n" +
            "dim aqua bags contain 4 faded coral bags.\n" +
            "dim blue bags contain 3 posh silver bags.\n" +
            "drab silver bags contain 1 vibrant aqua bag, 5 vibrant tan bags.\n" +
            "drab turquoise bags contain 3 light gold bags, 1 striped blue bag, 2 dark maroon bags.\n" +
            "posh gold bags contain 5 dotted red bags.\n" +
            "muted violet bags contain 3 bright fuchsia bags, 1 muted tomato bag, 4 drab lime bags, 2 dim olive bags.\n" +
            "drab orange bags contain 5 vibrant brown bags, 5 striped bronze bags.\n" +
            "faded orange bags contain 4 bright bronze bags.\n" +
            "clear plum bags contain no other bags.\n" +
            "drab black bags contain 3 mirrored bronze bags, 1 dotted bronze bag, 5 light gray bags.\n" +
            "pale salmon bags contain 4 plaid coral bags, 5 wavy yellow bags, 2 light coral bags.\n" +
            "dim teal bags contain 5 dark brown bags, 5 dark crimson bags, 5 dull aqua bags.\n" +
            "bright silver bags contain 3 pale beige bags, 4 bright beige bags, 3 wavy maroon bags, 1 drab chartreuse bag.\n" +
            "dull white bags contain 4 dim chartreuse bags, 5 vibrant coral bags, 2 muted indigo bags.\n" +
            "wavy tomato bags contain 3 bright fuchsia bags, 2 dull fuchsia bags, 4 pale brown bags.\n" +
            "striped magenta bags contain 2 shiny white bags, 1 shiny brown bag, 2 bright tomato bags.\n" +
            "shiny chartreuse bags contain 3 mirrored green bags, 3 dark salmon bags.\n" +
            "vibrant silver bags contain 2 faded gray bags, 3 mirrored orange bags, 4 clear black bags, 2 pale lavender bags.\n" +
            "dull tan bags contain 3 light black bags, 4 faded brown bags, 2 pale bronze bags, 3 shiny lavender bags.\n" +
            "light gold bags contain 2 wavy beige bags, 5 plaid salmon bags, 3 shiny cyan bags.\n" +
            "posh plum bags contain 3 dim beige bags, 1 wavy magenta bag, 3 dull fuchsia bags, 4 dull gold bags.\n" +
            "dull red bags contain 1 striped teal bag, 2 dark violet bags, 1 shiny tomato bag, 1 striped yellow bag.\n" +
            "plaid lavender bags contain 2 light purple bags.\n" +
            "faded chartreuse bags contain 4 vibrant yellow bags.\n" +
            "shiny brown bags contain no other bags.\n" +
            "light crimson bags contain 3 wavy orange bags, 3 pale white bags, 4 vibrant turquoise bags.\n" +
            "mirrored chartreuse bags contain 2 muted maroon bags.\n" +
            "wavy white bags contain 3 striped maroon bags, 5 striped aqua bags, 4 dull fuchsia bags, 1 wavy orange bag.\n" +
            "plaid gray bags contain 1 muted brown bag, 3 shiny brown bags.\n" +
            "drab white bags contain 5 muted yellow bags.\n" +
            "mirrored gray bags contain 3 light tan bags.\n" +
            "bright lavender bags contain 1 drab lime bag, 4 plaid olive bags, 3 pale blue bags.\n" +
            "shiny yellow bags contain 5 muted orange bags.\n" +
            "pale bronze bags contain 4 mirrored coral bags, 2 muted red bags.\n" +
            "drab beige bags contain 2 plaid purple bags, 5 clear magenta bags, 3 faded coral bags, 2 clear beige bags.\n" +
            "vibrant yellow bags contain 2 mirrored lavender bags, 1 light orange bag.\n" +
            "vibrant red bags contain 3 vibrant salmon bags, 2 plaid teal bags, 2 dull gray bags, 2 vibrant gray bags.\n" +
            "wavy olive bags contain 1 shiny bronze bag, 5 plaid coral bags, 3 dotted gold bags, 1 pale brown bag.\n" +
            "muted white bags contain 3 plaid violet bags, 1 dim black bag.\n" +
            "faded olive bags contain 4 mirrored chartreuse bags.\n" +
            "muted gold bags contain 1 dim orange bag.\n" +
            "mirrored orange bags contain 3 light purple bags, 4 dull lavender bags, 1 bright bronze bag, 4 pale black bags.\n" +
            "light chartreuse bags contain 3 muted red bags, 3 bright violet bags.\n" +
            "pale teal bags contain 4 dim lime bags, 4 pale crimson bags, 2 clear lavender bags.\n" +
            "muted coral bags contain 3 dark green bags.\n" +
            "shiny bronze bags contain 1 pale blue bag.\n" +
            "dim crimson bags contain 1 vibrant indigo bag, 4 pale crimson bags.\n" +
            "striped gold bags contain 3 dotted gray bags.\n" +
            "light lime bags contain 4 mirrored teal bags, 4 dotted cyan bags.\n" +
            "posh magenta bags contain 3 posh salmon bags, 5 bright bronze bags, 5 mirrored maroon bags, 2 vibrant purple bags.\n" +
            "pale chartreuse bags contain 4 bright purple bags, 1 posh salmon bag.\n" +
            "mirrored violet bags contain 2 mirrored magenta bags, 1 dotted cyan bag, 2 dark beige bags, 1 mirrored plum bag.\n" +
            "shiny cyan bags contain 4 bright violet bags.\n" +
            "bright indigo bags contain 1 muted teal bag, 4 faded silver bags, 3 dim indigo bags.\n" +
            "striped maroon bags contain 5 shiny magenta bags.\n" +
            "muted bronze bags contain 2 drab salmon bags, 1 mirrored orange bag, 2 light coral bags, 4 clear plum bags.\n" +
            "dotted white bags contain 2 bright plum bags, 3 pale lavender bags, 2 muted red bags.\n" +
            "pale indigo bags contain 1 dull green bag, 5 drab olive bags, 5 dull salmon bags, 5 dark cyan bags.\n" +
            "dark plum bags contain 2 pale white bags, 5 dim plum bags.\n" +
            "clear crimson bags contain 4 wavy blue bags, 5 dim chartreuse bags, 1 plaid maroon bag, 4 dull cyan bags.\n" +
            "striped lime bags contain 2 bright chartreuse bags, 2 striped orange bags.\n" +
            "dark beige bags contain 5 mirrored black bags, 3 dim purple bags, 5 light purple bags, 5 dark brown bags.\n" +
            "clear cyan bags contain 4 mirrored plum bags, 3 dotted purple bags, 1 dull fuchsia bag, 5 bright teal bags.\n" +
            "plaid olive bags contain 5 posh tomato bags, 3 faded bronze bags, 4 pale white bags.\n" +
            "plaid indigo bags contain 4 drab silver bags, 2 dotted purple bags, 3 vibrant green bags, 2 vibrant aqua bags.\n" +
            "drab purple bags contain 1 shiny green bag, 5 wavy gray bags.\n" +
            "bright violet bags contain 4 posh salmon bags, 1 shiny bronze bag, 1 vibrant indigo bag.\n" +
            "dark purple bags contain 4 dotted purple bags, 3 striped yellow bags, 5 vibrant purple bags, 3 mirrored lavender bags.\n" +
            "dull silver bags contain 3 clear turquoise bags, 3 dim salmon bags.\n" +
            "drab red bags contain 2 muted tomato bags, 3 dim bronze bags, 2 mirrored black bags, 4 faded salmon bags.\n" +
            "light yellow bags contain 2 mirrored orange bags, 4 vibrant fuchsia bags, 5 drab purple bags.\n" +
            "dim white bags contain 5 mirrored white bags.\n" +
            "dotted lime bags contain 5 dotted magenta bags, 5 dark maroon bags.\n" +
            "striped blue bags contain 3 pale white bags.\n" +
            "pale tomato bags contain 3 dull teal bags, 4 vibrant green bags, 1 bright crimson bag.\n" +
            "mirrored lavender bags contain 1 wavy cyan bag, 2 drab cyan bags, 1 striped olive bag.\n" +
            "dull orange bags contain 2 pale salmon bags, 2 posh olive bags, 2 dark coral bags, 1 plaid coral bag.\n" +
            "pale tan bags contain 1 shiny bronze bag, 4 dim coral bags, 4 vibrant green bags, 4 dotted gold bags.\n" +
            "drab brown bags contain 3 striped teal bags.\n" +
            "dim bronze bags contain 1 drab magenta bag, 1 mirrored gray bag, 2 striped purple bags.\n" +
            "clear fuchsia bags contain 4 plaid brown bags, 4 dim bronze bags, 1 posh lime bag.\n" +
            "faded lavender bags contain 2 posh gray bags, 3 dark cyan bags, 4 muted tan bags.\n" +
            "bright turquoise bags contain 3 pale white bags.\n" +
            "muted lime bags contain 5 plaid olive bags, 4 muted orange bags.\n" +
            "vibrant bronze bags contain 5 dim silver bags, 4 dotted fuchsia bags, 1 vibrant gray bag, 1 muted brown bag.\n" +
            "drab violet bags contain 3 bright turquoise bags, 1 posh olive bag, 2 wavy olive bags.\n" +
            "drab plum bags contain 2 clear turquoise bags, 5 striped violet bags, 5 muted black bags.\n" +
            "vibrant tan bags contain 2 dull brown bags, 1 vibrant indigo bag, 1 dim crimson bag.\n" +
            "bright gray bags contain 2 wavy gray bags, 4 clear maroon bags.\n" +
            "faded fuchsia bags contain 4 faded tan bags, 1 clear silver bag, 1 faded tomato bag.\n" +
            "muted magenta bags contain 3 shiny green bags, 4 pale aqua bags.\n" +
            "plaid green bags contain 2 wavy red bags, 1 pale yellow bag, 5 posh black bags.\n" +
            "dotted orange bags contain 5 clear crimson bags, 2 bright gold bags, 2 dim violet bags, 3 faded gray bags.\n" +
            "faded tomato bags contain 4 drab coral bags, 5 bright lime bags, 2 light black bags, 2 muted olive bags.\n" +
            "pale aqua bags contain 3 light bronze bags.\n" +
            "wavy indigo bags contain 4 bright crimson bags.\n" +
            "drab magenta bags contain 1 plaid coral bag.\n" +
            "dim tomato bags contain 4 clear gold bags, 4 bright crimson bags, 4 light beige bags, 4 striped white bags.\n" +
            "striped turquoise bags contain 5 bright green bags, 5 dark bronze bags, 3 pale crimson bags.\n" +
            "vibrant lime bags contain 4 shiny maroon bags, 2 shiny plum bags, 3 dull green bags.\n" +
            "muted fuchsia bags contain 3 dim purple bags, 2 pale cyan bags, 4 pale gray bags, 3 drab salmon bags.\n" +
            "dim cyan bags contain 4 shiny salmon bags, 3 striped white bags, 3 plaid chartreuse bags.\n" +
            "mirrored purple bags contain 5 posh plum bags, 1 light purple bag, 2 plaid aqua bags, 5 striped gold bags.\n" +
            "plaid coral bags contain 4 vibrant indigo bags, 3 bright bronze bags, 1 dotted purple bag.\n" +
            "plaid maroon bags contain 3 wavy olive bags, 5 pale plum bags, 2 dark beige bags, 4 striped white bags.\n" +
            "plaid turquoise bags contain 2 pale tomato bags, 4 faded white bags, 4 bright teal bags.\n" +
            "drab bronze bags contain 1 mirrored brown bag.\n" +
            "clear coral bags contain 5 plaid maroon bags.\n" +
            "vibrant indigo bags contain 2 striped purple bags, 4 vibrant green bags, 3 dotted purple bags, 1 vibrant turquoise bag.\n" +
            "bright bronze bags contain 5 dotted purple bags, 4 shiny brown bags.\n" +
            "striped tan bags contain 2 light purple bags, 3 mirrored blue bags, 5 dim magenta bags.\n" +
            "pale brown bags contain 4 wavy green bags, 1 wavy yellow bag.\n" +
            "faded yellow bags contain 4 dotted red bags.\n" +
            "plaid magenta bags contain 5 striped magenta bags.\n" +
            "bright crimson bags contain 4 vibrant tan bags, 2 dotted gold bags, 5 striped purple bags.\n" +
            "dull yellow bags contain 3 clear cyan bags.\n" +
            "dotted chartreuse bags contain 5 dim coral bags, 1 dull gray bag, 2 posh tomato bags.\n" +
            "vibrant crimson bags contain 2 pale black bags, 5 mirrored silver bags, 1 clear beige bag, 5 drab yellow bags.\n" +
            "wavy red bags contain 2 drab silver bags, 3 shiny salmon bags.\n" +
            "light blue bags contain 4 posh black bags, 2 clear magenta bags.\n" +
            "vibrant brown bags contain 4 dim red bags.\n" +
            "drab indigo bags contain 3 shiny aqua bags, 4 dotted lavender bags, 4 dim brown bags, 2 faded purple bags.\n" +
            "pale plum bags contain 4 wavy blue bags, 3 posh bronze bags, 4 pale lime bags.\n" +
            "muted beige bags contain 5 bright bronze bags, 3 dull olive bags.\n" +
            "muted purple bags contain 4 striped purple bags.\n" +
            "faded silver bags contain 1 light purple bag, 3 bright tomato bags, 1 mirrored magenta bag.\n" +
            "vibrant olive bags contain 3 muted turquoise bags, 5 wavy blue bags, 1 dotted silver bag, 5 striped tan bags.";
}
