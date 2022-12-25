import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllTasks {

    public static void main(String[] args) {

        // Task 1 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(1);

        int answer1 = bell(5);
        System.out.println(answer1);

        PrintHelper.blank();

        // Task 2 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(2);

        String answer2_1 = translateWord("Button");
        System.out.println(answer2_1);

        String answer2_2 = translateSentence("Do you think it is going to rain today?");
        System.out.println(answer2_2);

        PrintHelper.blank();

        // Task 3 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(3);

        boolean answer3 = validColor("rgba(0,0,0,0.123456789)");
        System.out.println(answer3);

        PrintHelper.blank();

        // Task 4 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(4);

        String answer4 = stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"});
        System.out.println(answer4);

        PrintHelper.blank();

        // Task 5 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(5);

        String[] answer5 = getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit");
        for (int i = 0; i < answer5.length; i++) {
            System.out.print(answer5[i] + " ");
        }

        PrintHelper.blank();

        // Task 6 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(6);

        int answer6 = ulam(206);
        System.out.println(answer6);

        PrintHelper.blank();

        // Task 7 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(7);

        String answer7 = longestNonrepeatingSubstring("abcda");
        System.out.println(answer7);

        PrintHelper.blank();

        // Task 8 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(8);

        String answer8 = convertToRoman(3999);
        System.out.println(answer8);

        PrintHelper.blank();

        // Task 9 ------------------------------------------------------------------------------------------------------

        PrintHelper.printTask(9);

        boolean answer9 = formula("16 * 10 = 160 = 14 + 120");
        System.out.println(answer9);

        PrintHelper.blank();

        // Task 10 -----------------------------------------------------------------------------------------------------

        PrintHelper.printTask(10);

        boolean answer10 = palindromeDescendant(23336014);
        System.out.println(answer10);

        PrintHelper.blank();

    }

    // Task 1 ----------------------------------------------------------------------------------------------------------

    public static int bell(int n) {

        if (n == 1) {
            return 1;
        }

        n = n - 1;

        int[] arr1 = new int[]{1};
        int[] arr2 = new int[]{};

        for (int i = 0; i < n; i++) {

            arr2 = new int[arr1.length+1];
            arr2[0] = arr1[arr1.length-1];

            for (int j = 1; j < arr2.length; j++) {
                arr2[j] = arr1[j-1] + arr2[j-1];
            }

            arr1 = Arrays.copyOf(arr2, arr2.length);

        }

        return arr2[arr2.length-1];
    }

    // Task 2 ----------------------------------------------------------------------------------------------------------

    public static String translateSentence(String str) {
        String[] words = str.split(" ");
        StringBuilder[] clean_words = new StringBuilder[words.length];
        StringBuilder[] punctuationMarks = new StringBuilder[words.length];

        for (int i = 0; i < words.length; i++) {

            clean_words[i] = new StringBuilder(words[i]);
            punctuationMarks[i] = new StringBuilder();

            for (int j = 0; j < clean_words[i].length(); j++) {

                if (isPunctuationMark(clean_words[i].charAt(j))) {
                    punctuationMarks[i].append(clean_words[i].charAt(j));
                    clean_words[i].deleteCharAt(j);
                }

            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < clean_words.length; i++) {
            answer.append(translateWord(String.valueOf(clean_words[i])));
            answer.append(punctuationMarks[i]);
            answer.append(" ");
        }

        return String.valueOf(answer);
    }

    public static String translateWord(String str) {

        StringBuilder answer = new StringBuilder();

        char[] char_arr = str.toCharArray();

        if (isVowel(char_arr[0])) {
            answer.append(char_arr);
            answer.append("yay");
        } else {
            int first_vowel_index = char_arr.length;
            for (int i = 1; i < char_arr.length; i++) {
                if (isVowel(char_arr[i])) {
                    first_vowel_index = i;
                    break;
                }
            }

            answer.append(str.substring(first_vowel_index));
            answer.append(str.substring(0, first_vowel_index));
            answer.append("ay");
        }

        return String.valueOf(answer);
    }

    public static boolean isVowel(char letter) {

        ArrayList<Character> vowels = new ArrayList<Character>();
        vowels.add('e');
        vowels.add('u');
        vowels.add('i');
        vowels.add('o');
        vowels.add('a');
        vowels.add('y');

        return vowels.contains(Character.toLowerCase(letter));
    }

    public static boolean isPunctuationMark(char character) {
        ArrayList<Character> punctuationMarks = new ArrayList<Character>();
        punctuationMarks.add('.');
        punctuationMarks.add(',');
        punctuationMarks.add('-');
        punctuationMarks.add(':');
        punctuationMarks.add(';');
        punctuationMarks.add('\'');
        punctuationMarks.add('\"');
        punctuationMarks.add('!');
        punctuationMarks.add('?');

        return punctuationMarks.contains(character);
    }

    // Task 3 ----------------------------------------------------------------------------------------------------------

    public static boolean validColor(String str) {
        str = str.toLowerCase();

        if (str.substring(0, 4).equals("rgba")) {

            str = str.substring(4);

            if ((str.charAt(0) == '(') & (str.charAt(str.length()-1) == ')')) {
                str = str.substring(1, str.length()-1);
            } else {
                return false;
            }

            String[] numbers_str = str.split(",");

            if (numbers_str.length != 4) {
                return false;
            }

            int[] numbers_int = new int[numbers_str.length-1];

            try {
                for (int i = 0; i < numbers_int.length; i++) {
                    numbers_int[i] = Integer.parseInt(numbers_str[i]);
                }
            } catch (Exception NumberFormatException) {
                return false;
            }

            double transparency = Double.parseDouble(numbers_str[numbers_str.length-1]);

            for (int i = 0; i < numbers_int.length; i++) {
                if ((numbers_int[i] < 0) | (numbers_int[i] > 255)) {
                    return false;
                }
            }

            if ((transparency < 0) | (transparency > 1)) {
                return false;
            }

        } else if (str.substring(0, 3).equals("rgb")){

            str = str.substring(3);

            if ((str.charAt(0) == '(') & (str.charAt(str.length()-1) == ')')) {
                str = str.substring(1, str.length()-1);
            } else {
                return false;
            }

            String[] numbers_str = str.split(",");

            if (numbers_str.length != 3) {
                return false;
            }

            int[] numbers_int = new int[numbers_str.length];

            try {
                for (int i = 0; i < numbers_int.length; i++) {
                    numbers_int[i] = Integer.parseInt(numbers_str[i]);
                }
            } catch (Exception NumberFormatException) {
                return false;
            }

            for (int i = 0; i < numbers_int.length; i++) {
                if ((numbers_int[i] < 0) | (numbers_int[i] > 255)) {
                    return false;
                }
            }

        } else {

            return false;

        }

        return true;
    }

    // Task 4 ----------------------------------------------------------------------------------------------------------

    public static String stripUrlParams(String str) {

        int question_mark_index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?') {
                question_mark_index = i;
                break;
            }
        }

        StringBuilder answer = new StringBuilder();

        if (question_mark_index == 0) {
            return str;
        } else {

            answer.append(str.substring(0, question_mark_index+1));

            String params_str = str.substring(question_mark_index+1);

            String[] params_arr = params_str.split("&");

            ArrayList<String> params = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            for (int i = 0; i < params_arr.length; i++) {
                String param = params_arr[i].split("=")[0];
                String value = params_arr[i].split("=")[1];

                if (params.contains(param)) {
                    values.set(params.indexOf(param), value);
                } else {
                    params.add(param);
                    values.add(value);
                }
            }

            for (int i = 0; i < params.size(); i++) {
                answer.append(params.get(i));
                answer.append("=");
                answer.append(values.get(i));
                answer.append("&");
            }

            answer.deleteCharAt(answer.length()-1);

        }

        return String.valueOf(answer);
    }

    public static String stripUrlParams(String str, String[] to_delete) {

        ArrayList<String> delete = new ArrayList<>();
        for (int i = 0; i < to_delete.length; i++) {
            delete.add(to_delete[i]);
        }

        int question_mark_index = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?') {
                question_mark_index = i;
                break;
            }
        }

        StringBuilder answer = new StringBuilder();

        if (question_mark_index == 0) {
            return str;
        } else {

            answer.append(str.substring(0, question_mark_index+1));

            String params_str = str.substring(question_mark_index+1);

            String[] params_arr = params_str.split("&");

            ArrayList<String> params = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();

            for (int i = 0; i < params_arr.length; i++) {
                String param = params_arr[i].split("=")[0];
                String value = params_arr[i].split("=")[1];

                if (params.contains(param)) {
                    values.set(params.indexOf(param), value);
                } else {
                    params.add(param);
                    values.add(value);
                }
            }

            for (int i = 0; i < params.size(); i++) {
                if (!delete.contains(params.get(i))) {
                    answer.append(params.get(i));
                    answer.append("=");
                    answer.append(values.get(i));
                    answer.append("&");
                }
            }

            answer.deleteCharAt(answer.length()-1);

        }

        return String.valueOf(answer);
    }

    // Task 5 ----------------------------------------------------------------------------------------------------------

    public static String[] getHashTags(String str) {

        str = str.toLowerCase();

        String[] str_arr = str.split(" ");

        for (int i = 0; i < str_arr.length; i++) {
            for (int j = 0; j < str_arr[i].length(); j++) {
                if (isPunctuationMark(str_arr[i].charAt(j))) {
                    str_arr[i] = str_arr[i].substring(0, j) + str_arr[i].substring(j+1);
                }
            }
        }

        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < str_arr.length; i++) {
            words.add(str_arr[i]);
        }

        int[] words_sizes = new int[words.size()];

        for (int i = 0; i < words_sizes.length; i++) {
            words_sizes[i] = words.get(i).length();
        }

        Arrays.sort(words_sizes);

        ArrayList<String> answer = new ArrayList<>();

        for (int i = words_sizes.length-1; i > -1; i--) {
            for (int j = 0; j < words.size(); j++) {
                if ((words.get(j).length() == words_sizes[i]) & (answer.size() < 3)) {
                    answer.add("#" + words.get(j));
                    words.set(j, " ");
                }
            }
        }

        return answer.toArray(new String[0]);
    }

    // Task 6 ----------------------------------------------------------------------------------------------------------

    public static int ulam(int n){

        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        n = n - 2;

        ArrayList<Integer> ulam = new ArrayList<>();
        ulam.add(1);
        ulam.add(2);

        for (int i = 0; i < n; i++){

            int add = 1;
            while (true) {
                int current_number = ulam.get(ulam.size()-1) + add;
                int ways_counter = 0;

                for (int j = 0; j < ulam.size(); j++) {
                    if ((ulam.contains(current_number-ulam.get(j))) & !(current_number-ulam.get(j) == ulam.get(j))) {
                        ways_counter += 1;
                        if (ways_counter > 2) {
                            break;
                        }
                    }
                }

                if (ways_counter == 2) {
                    ulam.add(current_number);
                    break;
                } else {
                    add += 1;
                }

            }

        }

        return ulam.get(ulam.size()-1);
    }

    // Task 7 ----------------------------------------------------------------------------------------------------------

    public static String longestNonrepeatingSubstring(String str) {

        char[] str_char = str.toCharArray();
        ArrayList<Character> current_substring = new ArrayList<>();
        String answer = "";

        for (int i = 0; i < str_char.length; i++) {
            if (!current_substring.contains(str_char[i])) {
                current_substring.add(str_char[i]);
            } else {
                StringBuilder helper = new StringBuilder();

                for (int j = 0; j < current_substring.size(); j++) {
                    helper.append(current_substring.get(j));
                }

                if (helper.length() > answer.length()) {
                    answer = String.valueOf(helper);
                }
                current_substring = new ArrayList<>();
            }
            StringBuilder helper = new StringBuilder();

            for (int j = 0; j < current_substring.size(); j++) {
                helper.append(current_substring.get(j));
            }

            if (helper.length() > answer.length()) {
                answer = String.valueOf(helper);
            }
        }

        return answer;
    }

    // Task 8 ----------------------------------------------------------------------------------------------------------

    public static String convertToRoman(int number) {

        if ((number > 3999) | (number < 1)) {
            return "Out of range";
        }

        String[] thousands_roman = new String[]{"-", "M", "MM", "MMM"};
        String[] hundreds_roman = new String[]{"-", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] dozens_roman = new String[]{"-", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units_roman = new String[]{"-", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        StringBuilder answer = new StringBuilder();

        int units = number % 10;
        int dozens = (number % 100 - units) / 10;
        int hundreds = (number % 1000 - dozens - units) / 100;
        int thousands = (number % 10_000 - hundreds - dozens - units) / 1000;

        if (thousands > 0) {
            answer.append(thousands_roman[thousands]);
        }

        if (hundreds > 0) {
            answer.append(hundreds_roman[hundreds]);
        }

        if (dozens > 0) {
            answer.append(dozens_roman[dozens]);
        }

        if (units > 0) {
            answer.append(units_roman[units]);
        }

        return String.valueOf(answer);
    }

    // Task 9 ----------------------------------------------------------------------------------------------------------

    public static boolean formula(String str) {

        String[] expressions = str.split("=");
        int[] values = new int[expressions.length];

        for (int i = 0; i < expressions.length; i++) {
            if (expressions[i].charAt(0) == ' ') {
                expressions[i] = expressions[i].substring(1);
            }
        }

        for (int i = 0; i < expressions.length; i++) {

            ArrayList<String> helper = new ArrayList<>(List.of(expressions[i].split(" ")));

            if (helper.size() == 3) {

                int number1 = Integer.parseInt(helper.get(0));
                int number2 = Integer.parseInt(helper.get(2));
                String sign = helper.get(1);

                if (sign.equals("+")) {
                    values[i] = number1 + number2;
                }
                if (sign.equals("-")) {
                    values[i] = number1 - number2;
                }
                if (sign.equals("/")) {
                    values[i] = number1 / number2;
                }
                if ((sign.equals("*")) | (sign.equals("x"))) {
                    values[i] = number1 * number2;
                }
            }

            if (helper.size() == 1) {
                values[i] = Integer.parseInt(helper.get(0));
            }

        }

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (!(values[i] == values[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    // Task 10 ---------------------------------------------------------------------------------------------------------

    public static boolean palindromeDescendant(long number) {

        String number_str = String.valueOf(number);

        if (isPalindrome(number_str)) {
            return true;
        }

        while (number_str.length() > 1) {

            StringBuilder new_number = new StringBuilder();

            for (int i = 0; i < number_str.length()/2; i++) {
                new_number.append(String.valueOf(Integer.parseInt(number_str.substring(i*2, i*2+1)) + Integer.parseInt(number_str.substring(i*2+1, i*2+2))));
            }
            number_str = String.valueOf(new_number);
            if (isPalindrome(number_str)) {
                return true;
            }

        }

        return false;
    }

    public static boolean isPalindrome(String str) {
        StringBuilder reverse = new StringBuilder();

        for (int i = str.length()-1; i > -1; i--) {
            reverse.append(str.charAt(i));
        }

        return str.equals(String.valueOf(reverse));
    }

}
