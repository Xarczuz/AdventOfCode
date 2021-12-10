package days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Day10 {
    public long part1(ArrayList<String> strings) {

        ArrayList<Character> errors = findError(strings);

        long points = errorPoints(errors);
        return points;
    }

    public long part2(ArrayList<String> strings) {
        ArrayList<LinkedList<Character>> errors = autocomplete(strings);

        ArrayList<Long> points = errorPoints2(errors);

        long middleScore = findMid(points);

        return middleScore;
    }

    private long findMid(ArrayList<Long> points) {
        points.sort(Long::compareTo);
        return points.get(points.size() / 2);
    }

    public ArrayList<LinkedList<Character>> autocomplete(ArrayList<String> strings) {
        ArrayList<LinkedList<Character>> errors = new ArrayList<>();
        for (String string : strings) {
            LinkedList<Character> stack = new LinkedList<>();
            for (char c : string.toCharArray()) {
                if (c == '{' || c == '[' || c == '<' || c == '(') {
                    stack.push(c);
                } else {
                    Character match = stack.pop();
                    if (match == '{' && c != '}') {
                        stack.clear();
                        break;
                    } else if (match == '(' && c != ')') {
                        stack.clear();
                        break;
                    } else if (match == '[' && c != ']') {
                        stack.clear();

                        break;
                    } else if (match == '<' && c != '>') {
                        stack.clear();
                        break;
                    }
                }
            }
            if (stack.size() != 0) {

                errors.add(stack);
            }
        }
        return errors;
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

    private ArrayList<Character> findError2(ArrayList<String> strings) {
        return null;
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
            Stack<Character> stack = new Stack<>();
            for (char c : string.toCharArray()) {
                if (c == '{' || c == '[' || c == '<' || c == '(') {
                    stack.push(c);
                } else {
                    Character match = stack.pop();
                    if (match == '{' && c != '}') {
                        errors.add(c);
                        break;
                    } else if (match == '(' && c != ')') {
                        errors.add(c);
                        break;
                    } else if (match == '[' && c != ']') {
                        errors.add(c);
                        break;
                    } else if (match == '<' && c != '>') {
                        errors.add(c);
                        break;
                    }
                }
            }
        }

    }
}

