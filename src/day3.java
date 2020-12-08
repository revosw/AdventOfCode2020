import java.util.ArrayList;
import java.util.function.Predicate;

public class day3 {
    private static final String DATA =
            ".#....#..##.#..####....#......." +
                    "......#..#....#....###......#.#" +
                    "#..#.....#..............##.#.#." +
                    "#.#...#....#...#......##..#..#." +
                    "...#..#.##..#..#........###.#.#" +
                    "...#.#..........#.........##..." +
                    "...#.#....#.#....#..#......#..." +
                    "..##.#.....#.......#.###..#..##" +
                    "..#.......#.......#....##......" +
                    "....##........##.##...#.###.##." +
                    "#.......#.......##..#......#..." +
                    "..##.............##.#......#..." +
                    "...#.####....#.....#...##......" +
                    ".............##.#......#......." +
                    "..#...#....#......#....#......." +
                    "..#....#..#............#......." +
                    "##...#..#........##..#......#.." +
                    "##........##........#.#.......#" +
                    "#.......#........#.#..#....###." +
                    ".....#..#.#..........##...#...." +
                    "..##...#......#.#...#..#...#..." +
                    "##.##...#......#....#....#...#." +
                    "#.......#..#.#..#....#..###.#.#" +
                    "#.............#.#....#..#.#...." +
                    "...#.......###.#.##.##.#...#..#" +
                    ".##.......##..##...#..###......" +
                    ".......#.#.#.#..####..#..#..#.." +
                    "...##......#.#.##.###....#.###." +
                    "###......###......#.#####..#..." +
                    "..#........##..#..##.##..#...#." +
                    ".....##..#...#..#.##.....#.#..." +
                    "#......#.##....#..##.#....#.#.." +
                    "##.#.##..#................#...." +
                    "......#.#....#......##.....#..." +
                    "..#...##..#..#...#..#.#..#....." +
                    "........#.#.#.##...#.#.....#.#." +
                    "#.#......#.....##..#...#......." +
                    "..#.#......#...........###....." +
                    "......##....#....##..#..#.#.#.#" +
                    "##....#.###...#......#..#...#.." +
                    "#.#.##....###...####.......#..#" +
                    "##...........#.....#........#.#" +
                    ".##.#..#.....#......#.......#.." +
                    "##..##..###....#.........##...." +
                    "..#..#..#.##...#.#...#........#" +
                    "#.##.###...#.......#..........." +
                    ".........#.................#..." +
                    "#.##...#.....#..##........#...." +
                    "....#.#...##...#...........#..." +
                    ".#.....#.#..#...##..##.....#..." +
                    ".#.....####....#..##..#........" +
                    "...#..#......##.#..##.#.#.#..#." +
                    ".##.#.....#.....#...#.......##." +
                    "......#..#..#......#...####...." +
                    ".......#......##..#..##.....#.." +
                    "......#.#..#...#..#.#.........." +
                    "....##.........#..............." +
                    ".#....#..##.....#....##.##....." +
                    "#.#.....#...#....####....#.#..." +
                    "#.....#....#.#...#............." +
                    "...#..#.....#....##..#..#......" +
                    "...#.#............#...........#" +
                    "###.#..#.#......#.....##.....#." +
                    "####....#....###.....#..#.#####" +
                    ".###..#...#.#..#......##.#.#.#." +
                    ".....#.##.#....#..##....#..#..#" +
                    "...#....#...##.....#......#.#.." +
                    "....#...#....#...#....#.....#.#" +
                    ".#.....#.....#.#..#......#..#.." +
                    "..#..##....##.##....#.....##..." +
                    "#..##...#.##...#..#.#.#.....#.." +
                    "...#..##.#..#....#.#....######." +
                    "..........#..#.....#....#...##." +
                    "#.#####.#.###..#.....#........." +
                    "#....#......#..#.#.##.##..###.." +
                    "..#...###.#.#....##.##...##...." +
                    ".......#....#..#...##......#..." +
                    "...#.#...#..#.....#..##.#......" +
                    "###..##...........#............" +
                    "..#....#.##....#.#..##...#....." +
                    "##....#...#....#.....#.#..##..." +
                    "..............#.##.#..#..##.###" +
                    "......#..#..#..#.#....###...##." +
                    ".#...#..#.#.#....#..........#.." +
                    "..#.#.....#..#...........#.##.." +
                    "...#.#......#......#..#..#.#..." +
                    "...#....#.#.#.....#...#.##..#.." +
                    ".#.#..#...#........##.......#.." +
                    "##..........#..#...#....###.#.." +
                    "#.....###......#..#.#.#....#.#." +
                    "..###.......#.#...............#" +
                    "#....#.....#.#......#..##.##..." +
                    "#.##.#.#....#..#.#...#.#...#..#" +
                    "#....#..#...........#.......#.." +
                    "...#.####.....#.........###.##." +
                    "......#..#.....#..........#..#." +
                    "#...#.#..####...#...#.#.##...##" +
                    ".##.........#......#.#.#......." +
                    ".......##...##.##....###...##.." +
                    "...#..#....#..#.#.#.....#.#...." +
                    "#....#.#.#.......##..###..##..." +
                    "......#............#.#...#..#.." +
                    "#.#.....#......#...#.......###." +
                    "...#.#................#...#...." +
                    ".....#......#.#..#...##.#.#...#" +
                    "#....#.#..#..#..##..#.##..#...." +
                    "#.................#..#....#...." +
                    "..#....#.......####....###....." +
                    ".#..#.#.#...###..#...#..###...." +
                    "..#..#.#......#.###..........#." +
                    ".....#......#.......##....##.#." +
                    ".#....#........#.#.##.#........" +
                    "#.#..##..#..#.#...####....##..." +
                    "...#....#.#..#...#..........#.." +
                    ".#.....#.##....#...##.........." +
                    "....##....#.....#.....#...#.###" +
                    ".#...##.#.#..##..#...#.#..#..#." +
                    "..#.......#.##.....#.#........#" +
                    "...#...#.....##..#.#.#....#...." +
                    "...#.....#.......##.........#.#" +
                    ".##.....#..#.#...#.#...#.#...#." +
                    "...........#...#.###..#...#..#." +
                    "#.##........#..###.##...####..." +
                    ".#.....#.#...##...#..#..#...##." +
                    "..#....#..#...#.....#.....##..." +
                    "..###..#.#.....##........#.##.." +
                    ".#.#..##........#.##....#..#.##" +
                    ".####.#..##..#.#..#....##....#." +
                    ".##....##...#.#........#......." +
                    "....#..#..#...#..#..#..#.#....." +
                    "...#......................#...." +
                    "#.....#.#....#..#..#.#..#....#." +
                    "##.....#.....##..........###..." +
                    ".#..#..............#...##.#.#.#" +
                    "...#...#.#.............#.#..#.#" +
                    "..#.....#.......#......#.#....." +
                    ".###.#..#..#..#.#..#.....#....." +
                    ".....##..##...##.......#....###" +
                    ".#........###...##..#....##...." +
                    "#....#.#......##..#....#.##..#." +
                    "#....#.#...#........##...###..." +
                    ".#.....#...#.###....#.##.#.####" +
                    "###......#....#...#....##..#..#" +
                    "##....#..###......#...#.#.#...." +
                    "..........#......##.#..#......." +
                    ".#..#......###.........##...#.." +
                    "....#......#....#.........#.#.#" +
                    "##.#.#...#.#...#...#..#......#." +
                    "....#.##.........#..#.....##.#." +
                    "........#...#..#.#.#.#.....##.." +
                    "..#......#.#.#..#.....##......." +
                    "..............#....#....##.#..#" +
                    "....#.#.....#....#.#.###.#....#" +
                    "..#..........#..#......#.##..#." +
                    "...#...#.#.............#..#...." +
                    "#.......#..#..##.........##..#." +
                    "..##..#............#.....#....." +
                    "....#.#..##...#.#..#.........#." +
                    "........#.......#.##....#....#." +
                    "...#.....#.#.....#.#....#......" +
                    "..#......##.#.............#.#.#" +
                    "#.#.............#.#.....#......" +
                    ".##....##.#.....#....#...##...." +
                    ".#.#....##....#.....##........." +
                    "...#.....#.....#.....#..#.###.." +
                    ".......#....#...##.#...#...#..#" +
                    "..#.#.......#...###.#...#....#." +
                    ".....###..##....###.#.##......." +
                    "....#..................##.#.##." +
                    ".#.......###.##...#.#.....#...." +
                    "....#....##...##.....#.#...#..#" +
                    "#..#.....#......##...#....#...." +
                    "#..##.........#.....#...#......" +
                    "...#..##.......##......#..#####" +
                    ".#..###.###.#.##........#......" +
                    ".#...#....#....#.#....#...##..." +
                    "##........#....#.........##..#." +
                    ".#.....##............#.#......." +
                    "....#....#...........###.....##" +
                    ".#......#.#.#..#....#.#.....##." +
                    "......#.##.#..##....#.#.#..#..." +
                    "#....#......#...#..####........" +
                    "......#..#..##..#.......#.#..#." +
                    "##....##.###.#...#.##.#..#.###." +
                    ".#.........#...##...#.#......#." +
                    "..#.#...........####.#....##.##" +
                    ".....#.#..##.#...###...#..#.#.." +
                    "...#..#..##.#...#.....#.##...##" +
                    "..##......##..........#..###..." +
                    ".#......##.....#.##....#.#.##.#" +
                    "...#.......##..##.....#....#..." +
                    ".##...#...#....#..#............" +
                    "#..#....#...........#.........." +
                    "......#...#.#.......#...#.##..#" +
                    "..#.###..#.....#.....#..#.....#" +
                    "....#....#..........##....#..#." +
                    ".......##.#.#.#......#....#...#" +
                    "####..#.###........#..#......#." +
                    "#........##.#.#.#.............#" +
                    ".#......#......#..#.##.....#..." +
                    ".....##.##......##.#.....#.#.#." +
                    ".......##.#.....##.......#.#.#." +
                    ".#.#..#.#..#.##...#.#....#.#..#" +
                    ".#..##....#..#...##.......#..#." +
                    ".#.#..#.......#................" +
                    "#........#.#.#......#.#.#.#...." +
                    "##......#...#..##.#...##.##...." +
                    "##.#..#...........##...#..###.." +
                    "......#.####...#........#.#.#.." +
                    "...#........##..###.#.#...#...#" +
                    ".#.....##..#......##......###.." +
                    "..#.#...#......#..#..##.#.....#" +
                    "#....#..#.#..........#...#....." +
                    ".#......#.##..###..#.#....#..##" +
                    ".......#.......#..#..#......#.." +
                    "..##.....##.#..#..#.....##....." +
                    "........#.##...#.#.#..#..#..#.." +
                    "...#.######.........#.....#..##" +
                    ".#.#............#....#........." +
                    "#...#....###.#......#.#........" +
                    "#.........#....#...##.........." +
                    "....#...........#.###.#...###.." +
                    ".........#........#.#.#..#...#." +
                    ".#.......#.#.....#.#.....#.##.." +
                    ".....#.......#.....#.#.#......." +
                    "#.##..#..##.......#...#......#." +
                    ".###.....##...##.#...##.##.#.#." +
                    "...#......##..##............#.#" +
                    "...#......................#..##" +
                    "#..#..#................#...#..." +
                    "#..#....#.#.#...##.......#..#.." +
                    "....#.#..###.##...#..#.###..#.." +
                    "..#...#....####.#............#." +
                    "......#....#.#...#.#.#........." +
                    "#...#........#.....##..###.#..#" +
                    "#....#...#...##...#..#....##..." +
                    "#..#...#..#.......#.#..##.#..#." +
                    "#.#..........#...........##...." +
                    ".#...###...#......#.......#.#.#" +
                    ".........#.........#...#...##.." +
                    "##.#..###......##..#.....#..#.." +
                    "....##...............#.....#..." +
                    ".....#.....###.#.....#.#......." +
                    "....#..#......###..#..##..#...." +
                    "......................#.....#.." +
                    "..#..#...##....##....#........#" +
                    "..#....#...#...#.......#.....#." +
                    "...##.#.#.##......#.#.#.#.####." +
                    ".###...#..#......#.#..#........" +
                    "#..#...##.#..#..##..##....#...#" +
                    "...#...#..#..#..#........#..##." +
                    ".##....#.##.#....#...#.#.#....#" +
                    "#..#....#..#....#.......##..#.#" +
                    "...#.#....####...........#...#." +
                    "#...#####...#.#..#......#...#.#" +
                    ".##....#.#.#..#......#..##....." +
                    "..........#..#.#.#.....##......" +
                    ".....#....#..................#." +
                    ".........#...#...#....#..###..." +
                    ".#.#.#....#...................." +
                    "......##............##.###..#.." +
                    "#.#...#........####.##..#.#.##." +
                    "#........#.#.#.#..#.##.....#..." +
                    "......####..#.##.......#....#.." +
                    ".........#...#...#.....#......." +
                    "..##.....#...#...#.....##.....#" +
                    "....#...##....#.....#..#..##.##" +
                    "..#.........##...##..###..#...." +
                    "#....#.#.........##.###.#...##." +
                    ".##...#....#..#..#.#....##....." +
                    "##..#..#..#...........#.##....#" +
                    "....#..........#...#..#.....#.." +
                    "........###..#..#.#.#.....##..." +
                    "#...#...#..###............###.." +
                    "..#.....#.#.#..#..#.#..#......#" +
                    "..#...##..#....#...#......#...." +
                    "#....#........##.....#..##....#" +
                    "#.....#.#.#..#.......##.#.#.##." +
                    "..##...#...#.....#..........#.." +
                    "##.....#....#......#..........#" +
                    "......#..#..........#.#..####.." +
                    "......#...#............##...##." +
                    "..#.......##.......#...###.###." +
                    ".#..#.#.#...#..##.#......#.#..." +
                    ".##.....##.#.#...#.##.........#" +
                    "#.#.######...........#.#####.#." +
                    "........#.##...##....##.#.##.#." +
                    "....#......#.....#.....###...##" +
                    "#..............#.#....#.#....#." +
                    "....#..###.#.........##.#.#...." +
                    "..#.#.#..##....####..........#." +
                    "...#..#.......#................" +
                    "...#....#..............#....#.." +
                    ".....#...#...#....#.#.#..#...#." +
                    "......##.............###.##..##" +
                    ".#...#.#..#......#..#.##......." +
                    "##.....#.....#.##...#....#....." +
                    "..#..#.#.#.#.#..........#..###." +
                    "##..........#........#....#.#.." +
                    ".....#...#........#.#..###....#" +
                    ".###.#........#.##......#.#...#" +
                    "#...##....#....#....##.#.#....." +
                    ".....#.#............#.........." +
                    "..#.##....................#...." +
                    ".....#..#..#.#..#.##.......#..." +
                    ".....###......#......##......##" +
                    "#.....#.#.......##.......#...#." +
                    ".#.#...#......#..###...#.....#." +
                    "#.#..#...#..##.....#...#.#..#.." +
                    ".....#.#..........#..#........." +
                    ".###..##..##.....#...#...#..##." +
                    "#...#.#....#.......##..#......." +
                    "###...#.#.#..#.......#......#.." +
                    "....##........#..........##...." +
                    "............#....#...........#." +
                    "#..#.#....##..#.#..#......##..." +
                    ".###....##...#....##..........#" +
                    ".###........#........###.....#." +
                    "...#...#.#......#...#....#....." +
                    ".###.......#.........#........." +
                    "....##.#......#...###......##.#" +
                    ".###...#..##.....##.......#...." +
                    ".#.#...#..#.##....#........#...";
    private static final int ROW_LENGTH = 31;

    public static void main(String[] args) {
        var result1 = findTreeNumToBottom(1, 1);
        var result2 = findTreeNumToBottom(3, 1);
        var result3 = findTreeNumToBottom(5, 1);
        var result4 = findTreeNumToBottom(7, 1);
        var result5 = findTreeNumToBottom(1, 2);
        System.out.println(result1 * result2 * result3 * result4 * result5);
    }

    private static long findTreeNumToBottom(int rightInterval, int downInterval) {
        var indices = getIndices(rightInterval, downInterval);

        return indices.stream()
                .filter(isTree)
                .count();
    }

    public static Predicate<Integer> isTree = currentChar -> DATA.charAt(currentChar) == '#';

    private static ArrayList<Integer> getIndices(int rightInterval, int downInterval) {
        ArrayList<Integer> indices = new ArrayList<>();

        var right = rightInterval;
        var down = downInterval;
        var currentChar = down * ROW_LENGTH + right;
        while (currentChar < DATA.length()) {
            indices.add(currentChar);

            right = (right + rightInterval) % ROW_LENGTH;
            down += downInterval;
            currentChar = down * ROW_LENGTH + right;
        }

        return indices;
    }
}
