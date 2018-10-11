package rs.mvd.palindrome;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static Pattern lettersAndNumbersPattern = Pattern.compile("[^a-zA-Z0-9]");

    private static Set<String> makePalindromes(String word) {
        Matcher matcher = lettersAndNumbersPattern.matcher(word);
        while (matcher.find()) {
            String s = matcher.group();
            word = word.replaceAll("\\" + s, "");
        }
        Map<Character, Integer> characterMap = checkIfWordCanBePalindrome(word);
        String partOne = "";
        String partTwo = "";
        for (Character character : characterMap.keySet()) {
            Integer integer = characterMap.get(character);
            if (integer % 2 == 0) {
                integer = integer / 2;
                while (integer > 0) {
                    partOne = partOne + character;
                    integer--;
                }
            } else {
                while (integer > 0){
                    partTwo = partTwo + character;
                    integer--;
                }
            }
        }
        Set<String> palindromes = new HashSet<>();
        HashSet<String> permute = permute("", partOne, new HashSet<>());
        for (String s : permute) {
            String partThree = new StringBuilder(s).reverse().toString();
            palindromes.add(s + partTwo + partThree);
        }
        return palindromes;
    }

    private static HashSet<String> permute(String prefix, String partOne, HashSet<String> combinations) {
        int n = partOne.length();
        if (n == 0) {
            combinations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + partOne.charAt(i), partOne.substring(0, i) + partOne.substring(i + 1, n), combinations);
            }
        }
        return combinations;
    }

    private static Map<Character, Integer> checkIfWordCanBePalindrome(String word) {
        Map<Character, Integer> characterMap = new HashMap<>();
        int brojNeparnihVecihOdJedan = 0;
        int brojKarakteraKojiSeJavljajuTacnoJednom = 0;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            Integer integer = characterMap.get(c);
            if (integer != null) {
                integer++;
            } else {
                integer = 1;
            }
            characterMap.put(c, integer);
        }
        for (Character character : characterMap.keySet()) {
            Integer integer = characterMap.get(character);
            if (integer % 2 != 0 && integer > 1) {
                brojNeparnihVecihOdJedan++;
            }
            if (integer == 1) {
                brojKarakteraKojiSeJavljajuTacnoJednom++;
            }
        }

        if (brojKarakteraKojiSeJavljajuTacnoJednom > 1 || brojNeparnihVecihOdJedan > 1) {
            throw new RuntimeException("Word: " + word + " is not candidat for makePalindromes.");
        }
        return characterMap;
    }

    public static void main(String[] args) {
        Set<String> palindromes = makePalindromes("aaaabbcc111..........");
        System.out.println("Number of palindromes: " + palindromes.size());
        for (String palindrome : palindromes) {
            System.out.println(palindrome);
        }
    }
}
