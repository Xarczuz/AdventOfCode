package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day10 {

    public long part1(ArrayList<String> strings) {
        ArrayList<Character> errors = findError(strings);
        return errorPoints(errors);
    }

    public long part2(ArrayList<String> strings) {
        ArrayList<LinkedList<Character>> errors = autocomplete(strings);
        ArrayList<Long> points = errorPoints2(errors);
        return findMid(points);
    }

    private long findMid(ArrayList<Long> points) {
        points.sort(Long::compareTo);
        return points.get(points.size() / 2);
    }

    public ArrayList<LinkedList<Character>> autocomplete(ArrayList<String> strings) {
        ArrayList<LinkedList<Character>> errors = new ArrayList<>();
        for (String string : strings) {
            LinkedList<Character> stack = new LinkedList<>();
            addCompleteLines(errors, string, stack);
        }
        return errors;
    }

    private void addCompleteLines(ArrayList<LinkedList<Character>> errors, String string, LinkedList<Character> stack) {
        for (char c : string.toCharArray()) {
            if (c == '{' || c == '[' || c == '<' || c == '(') {
                stack.push(c);
            } else {
                Character match = stack.pop();
                if (isCorrupted(c, match)) {
                    stack.clear();
                    break;
                }
            }
        }
        if (!stack.isEmpty()) {
            errors.add(stack);
        }
    }

    private boolean isCorrupted(char c, Character match) {
        return match == '{' && c != '}'
                || match == '(' && c != ')'
                || match == '[' && c != ']'
                || match == '<' && c != '>';
    }

    private ArrayList<Long> errorPoints2(ArrayList<LinkedList<Character>> errors) {
        ArrayList<Long> pointArr = new ArrayList<>();
        HashMap<Character, Integer> characterHashMap = new HashMap<>();
        characterHashMap.put('(', 1);
        characterHashMap.put('[', 2);
        characterHashMap.put('{', 3);
        characterHashMap.put('<', 4);
        for (LinkedList<Character> error : errors) {
            long points = 0;
            for (Character character : error) {
                points *= 5;
                points += characterHashMap.get(character);
            }
            pointArr.add(points);

        }
        return pointArr;
    }

    private long errorPoints(ArrayList<Character> errors) {
        HashMap<Character, Integer> characterHashMap = new HashMap<>();
        characterHashMap.put(')', 3);
        characterHashMap.put(']', 57);
        characterHashMap.put('}', 1197);
        characterHashMap.put('>', 25137);
        long sum = 0;
        for (Character error : errors) {
            sum += characterHashMap.get(error);
        }
        return sum;
    }

    private ArrayList<Character> findError(ArrayList<String> strings) {
        ArrayList<Character> errors = new ArrayList<>();
        addErrors(strings, errors);
        return errors;
    }

    private static void addErrors(ArrayList<String> strings, ArrayList<Character> errors) {
        for (String string : strings) {
            LinkedList<Character> stack = new LinkedList<>();
            addErrors(errors, string, stack);
        }

    }

    private static void addErrors(ArrayList<Character> errors, String string, LinkedList<Character> stack) {
        for (char c : string.toCharArray()) {
            if (c == '{' || c == '[' || c == '<' || c == '(') {
                stack.push(c);
            } else {
                Character match = stack.pop();
                if (match == '{' && c != '}'
                        || match == '(' && c != ')'
                        || match == '[' && c != ']'
                        || match == '<' && c != '>') {
                    errors.add(c);
                    break;
                }
            }
        }
    }
}

