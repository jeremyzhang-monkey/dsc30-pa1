/**
 * Name: Jingyu Zhang
 * PID: A15509346
 */


/**
 * The Java class for the first programming assignment of DSC30
 * @author Jingyu Zhang
 * @since 01/04/2022
 */

public class ProgrammingChallenges {
    /**

     * Here is the main method

     */
    public static void main(String[] args) {
    }

    ///////// Practice 1 /////////
    /**
     * reverseEvenNumber is going to reverse the order of only the even numbers in a given
     * array while the odd number stays in the same position
     *
     * @param arr the array we are given
     *
     * @return the array with even number reversed
     */
    public static int[] reverseEvenNumber(int[] arr) {
        int[] evenArray = new int[0];
        for (int num : arr) {
            if (num % 2 == 0) {
                evenArray = addX(evenArray.length, evenArray, num);
            }
        }

        int evenNum = 0;
        int evenLeng = evenArray.length;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                arr[i] = evenArray[evenLeng - evenNum - 1];
                evenNum++;
            }
        }

        return arr;
    }


    /**
     * addX is a Helper Method used to add an integer to an int array
     *
     * @param n the position we are going to add the integer
     * @param arr the array we are going to add the integer to
     * @param x the integer we are going to add
     *
     * @return a new int array with the integer added
     */
    public static int[] addX(int n, int arr[], int x) {
        int newarr[] = new int[n + 1];

        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }

        newarr[n] = x;

        return newarr;
    }


    ///////// Practice 2 /////////
    /**
     * checkPasswordStrength is used to check the strength of a potential
     * password combination
     *
     * @param input the password combination we are going to test
     *
     * @return the strength of the password
     */
    public static String checkPasswordStrength(String input) {
        boolean upLow = false;
        boolean num = false;
        boolean eightCha = false;
        boolean specialCha = false;
        boolean[] fulfill = new boolean[]{upLow, num, eightCha, specialCha};
        char[] chars = input.toCharArray();

        for (char c : chars) {
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                fulfill[0] = true;
            }

            if (Character.isDigit(c)) {
                fulfill[1] = true;
            }

            if (chars.length >= 8) {
                fulfill[2] = true;
            }

            if (!Character.isUpperCase(c) && !Character.isLowerCase(c) && !Character.isDigit(c)) {
                fulfill[3] = true;
            }
        }

        int ful = 0;
        String re = "";
        for (boolean fill : fulfill) {
            if (fill == true) {
                ful++;
            }
        }

        if (ful >= 3) {
            re = "Strong";
        }

        if (ful == 1 || ful == 2) {
            re = "Weak";
        }

        if (ful == 0) {
            re = "Not Acceptable";
        }

        return re;
    }

    ///////// Practice 3 /////////
    /**
     * findShortestDistance is going to find the nearest neighbor of a point
     * (x[i], y[i]) taht we are given by their coordinates array
     *
     * @param x the array of x-coordinates we are given
     * @param y the array of y-coordinates we are given
     *
     * @return a new int array with the integer added
     */
    public static double[] findShortestDistance(double[] x, double[] y) {
        int size = x.length;
        double[] dis = new double[size];

        for (int i = 0; i < size; i++) {
            double minDis = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                } else {
                    double newDis = Math.pow(Math.pow(x[i] - x[j], 2)
                            + Math.pow(y[i] - y[j], 2), 0.5);
                    System.out.println(newDis);
                    if (newDis < minDis) {
                        minDis = newDis;
                    }
                }
            }
            dis[i] = minDis;
        }
        return dis;
    }

    ///////// Practice 4.1 /////////
    /**
     * getUnique is going to find out all the unique elements in the given array and
     * out put them in the order of their occurrence in the array
     *
     * @param arr the array of strings we are given
     *
     * @return the array with the unique categories but with the same length
     * Repeated values are replaced by null
     */
    public static String[] getUnique(String[] arr) {
        String[] outString = new String[arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            boolean inString = false;
            for (int j = 0; j <= index; j++) {
                if (arr[i] == outString[j]) {
                    inString = true;

                }
            }
            if (inString == false) {
                outString[index] = arr[i];
                index++;
            }
        }

        for (int k = index; k < outString.length; k++) {
            outString[index] = null;
            index++;
        }
        return outString;
    }

    /**
     * Find the number of unique Element in an array with only unique element
     * (PROVIDED)
     * @param arr the array we are given
     * @return the number of unique element.
     */
    public static int getNumOfUniqueElements(String[] arr) {
        int counter = 0;
        arr = getUnique(arr);
        for (String s : arr) {
            if (s != null) {
                counter++;
            } else {
                break;
            }
        }
        return counter;
    }


    ///////// Practice 4.2 /////////
    /**
     * oneHotEncode is used to generate a feature matrix of the given array
     * (PROVIDED)
     * @param arr the array we are given
     * @return the feature matrix of the given array
     * The output sequence should have the same sequence with the unique array.
     */
    public static int[][] oneHotEncode(String[] arr) {
        int raw = getNumOfUniqueElements(getUnique(arr));
        String[] uniqueArray = getUnique(arr);
        int column = arr.length;
        int[][] matrix = new int[raw][column];
        for (int i = 0; i < raw; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[j] == uniqueArray[i]) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    ///////// Practice 5.1 /////////
    /**
     * calculateDerivative performs the derivative operation for the input polyArr
     *
     * @param polyArr the array that represents a polynomial f(x)
     * @return an array that represents the polynomial function f’(x)
     */
    public static double[] calculateDerivative(double[] polyArr) {
        //Performing the derivative operation for the input polyArr, and returning an array
        // that represents the polynomial function f’(x).
        double[] derivative = new double[polyArr.length - 1];
        double[] noPoly = new double[]{0};
        if (polyArr.length == 1) {
            return noPoly;
        }
        for (int i = 1; i < polyArr.length; i++) {
            derivative[i - 1] = i * polyArr[i];
        }
        return derivative;
    }

    /**
     * getIntersection finds out the date the two stock price models we are
     * given will intersect and have equal growth rate
     *
     * @param poly1 the array that represents the polynomial of the first stock price model
     * @param poly2 the array that represents the polynomial of the second stock price model
     * @return the date where the growth rate of the two stock price model will be equal
     */
    public static double getIntersection(double[] poly1, double[] poly2) {
        double[] poly1De = calculateDerivative(poly1);
        double[] poly2De = calculateDerivative(poly2);

        double denominator = poly1De[1] - poly2De[1];
        double numerator = poly2De[0] - poly1De[0];

        System.out.println(denominator);
        System.out.println(numerator);

        if (denominator == 0 && numerator < 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (denominator == 0 && numerator >= 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            double date = numerator / denominator;

            if (date < 0) {
                return -1.0;
            } else {
                return date;
            }
        }
    }
}
