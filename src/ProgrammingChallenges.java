/**
 * Name: Jingyu Zhang
 * PID: A15509346
 */

/**
 * Programming challenges for DSC30 PA1.
 *
 * <p>Covers array manipulation, string processing, geometry, encoding,
 * and polynomial calculus through five independent practice problems.</p>
 *
 * @author Jingyu Zhang
 * @since 01/04/2022
 */
public class ProgrammingChallenges {

    public static void main(String[] args) {
        // Entry point — individual methods can be called here for manual testing
    }

    // ─────────────────────────── Practice 1 ───────────────────────────

    /**
     * Reverses the order of even numbers in {@code arr} while keeping odd
     * numbers in their original positions.
     *
     * <p>Example: {@code [3, 2, 1, 4]} → {@code [3, 4, 1, 2]}</p>
     *
     * @param arr the input array (not modified)
     * @return a new array with even numbers reversed and odd numbers unchanged
     */
    public static int[] reverseEvenNumber(int[] arr) {
        // Work on a defensive copy so the caller's array is not mutated
        int[] result = arr.clone();

        // Collect even numbers in order
        int[] evens = new int[0];
        for (int num : result) {
            if (num % 2 == 0) {
                evens = addX(evens.length, evens, num);
            }
        }

        // Place evens back in reverse order at even-number positions
        int evenIdx = 0;
        int evensLen = evens.length;
        for (int i = 0; i < result.length; i++) {
            if (result[i] % 2 == 0) {
                result[i] = evens[evensLen - evenIdx - 1];
                evenIdx++;
            }
        }

        return result;
    }

    /**
     * Helper: appends {@code x} to {@code arr} and returns the extended array.
     *
     * @param n   current length of {@code arr}
     * @param arr the source array
     * @param x   the integer to append
     * @return a new array of length {@code n + 1} with {@code x} at the end
     */
    public static int[] addX(int n, int[] arr, int x) {
        int[] extended = new int[n + 1];
        for (int i = 0; i < n; i++) {
            extended[i] = arr[i];
        }
        extended[n] = x;
        return extended;
    }

    // ─────────────────────────── Practice 2 ───────────────────────────

    /**
     * Rates the strength of a password based on four criteria:
     * <ol>
     *   <li>Contains at least one letter (upper or lower case)</li>
     *   <li>Contains at least one digit</li>
     *   <li>Is at least 8 characters long</li>
     *   <li>Contains at least one special character (non-letter, non-digit)</li>
     * </ol>
     *
     * @param input the password candidate
     * @return {@code "Strong"} (≥ 3 criteria), {@code "Weak"} (1–2 criteria),
     *         or {@code "Not Acceptable"} (0 criteria)
     */
    public static String checkPasswordStrength(String input) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasMinLength = input.length() >= 8;
        boolean hasSpecial = false;

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        int score = (hasLetter ? 1 : 0)
                  + (hasDigit ? 1 : 0)
                  + (hasMinLength ? 1 : 0)
                  + (hasSpecial ? 1 : 0);

        if (score >= 3) {
            return "Strong";
        } else if (score >= 1) {
            return "Weak";
        } else {
            return "Not Acceptable";
        }
    }

    // ─────────────────────────── Practice 3 ───────────────────────────

    /**
     * For each point {@code (x[i], y[i])}, computes the Euclidean distance to
     * its nearest neighbor among all other points in the set.
     *
     * @param x array of x-coordinates
     * @param y array of y-coordinates (same length as {@code x})
     * @return array where {@code result[i]} is the shortest distance from point
     *         {@code i} to any other point
     */
    public static double[] findShortestDistance(double[] x, double[] y) {
        int size = x.length;
        double[] distances = new double[size];

        for (int i = 0; i < size; i++) {
            double minDist = Double.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                double dist = Math.sqrt(Math.pow(x[i] - x[j], 2)
                                      + Math.pow(y[i] - y[j], 2));
                if (dist < minDist) {
                    minDist = dist;
                }
            }
            distances[i] = minDist;
        }

        return distances;
    }

    // ─────────────────────────── Practice 4.1 ─────────────────────────

    /**
     * Returns the unique elements of {@code arr} in first-occurrence order.
     * Positions after the last unique element are filled with {@code null}.
     *
     * <p>Example: {@code ["a","b","a","c"]} → {@code ["a","b","c",null]}</p>
     *
     * @param arr the input string array
     * @return an array of the same length containing unique values followed by nulls
     */
    public static String[] getUnique(String[] arr) {
        String[] unique = new String[arr.length];
        int count = 0;

        outer:
        for (String candidate : arr) {
            for (int j = 0; j < count; j++) {
                if (candidate.equals(unique[j])) {
                    continue outer;         // already seen — skip
                }
            }
            unique[count++] = candidate;   // first occurrence
        }

        // Remaining slots are already null (Java default for Object arrays)
        return unique;
    }

    /**
     * Counts the number of unique (non-null) elements produced by
     * {@link #getUnique(String[])}.
     *
     * @param arr the input string array
     * @return the number of distinct values in {@code arr}
     */
    public static int getNumOfUniqueElements(String[] arr) {
        int count = 0;
        for (String s : getUnique(arr)) {
            if (s != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    // ─────────────────────────── Practice 4.2 ─────────────────────────

    /**
     * Produces a one-hot encoded feature matrix for {@code arr}.
     *
     * <p>Each row corresponds to a unique category (in first-occurrence order)
     * and each column corresponds to one element of {@code arr}.
     * A cell is {@code 1} when the element matches that row's category,
     * {@code 0} otherwise.</p>
     *
     * @param arr the input string array
     * @return a {@code uniqueCount × arr.length} binary matrix
     */
    public static int[][] oneHotEncode(String[] arr) {
        String[] uniqueArr = getUnique(arr);
        int rows = getNumOfUniqueElements(uniqueArr);
        int cols = arr.length;
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[j].equals(uniqueArr[i])) {   // .equals() for value comparison
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    // ─────────────────────────── Practice 5.1 ─────────────────────────

    /**
     * Computes the derivative of a polynomial represented as a coefficient array.
     *
     * <p>Convention: {@code polyArr[i]} is the coefficient of x<sup>i</sup>,
     * so {@code [3, 2, 1]} represents 3 + 2x + x².</p>
     *
     * @param polyArr coefficient array of the polynomial f(x); length ≥ 1
     * @return coefficient array of f′(x), or {@code [0]} for a constant polynomial
     */
    public static double[] calculateDerivative(double[] polyArr) {
        if (polyArr.length == 1) {
            return new double[]{0};
        }
        double[] derivative = new double[polyArr.length - 1];
        for (int i = 1; i < polyArr.length; i++) {
            derivative[i - 1] = i * polyArr[i];
        }
        return derivative;
    }

    // ─────────────────────────── Practice 5.2 ─────────────────────────

    /**
     * Finds the date at which two quadratic stock-price models have equal
     * growth rates (i.e. equal first derivatives).
     *
     * <p>Assumes both polynomials are degree-2 (coefficient arrays of length 3)
     * so their derivatives are linear and have at most one intersection.</p>
     *
     * @param poly1 coefficients of the first stock-price polynomial
     * @param poly2 coefficients of the second stock-price polynomial
     * @return the date (x-value) where f₁′(x) = f₂′(x);
     *         {@code Double.POSITIVE_INFINITY} / {@code Double.NEGATIVE_INFINITY}
     *         when the derivatives are parallel but offset, or {@code -1.0} if
     *         the intersection falls before time zero
     */
    public static double getIntersection(double[] poly1, double[] poly2) {
        double[] d1 = calculateDerivative(poly1);
        double[] d2 = calculateDerivative(poly2);

        // Solve d1[0] + d1[1]*x = d2[0] + d2[1]*x  →  x = (d2[0]-d1[0]) / (d1[1]-d2[1])
        double denominator = d1[1] - d2[1];
        double numerator   = d2[0] - d1[0];

        if (denominator == 0) {
            // Parallel derivatives — never intersect or always equal
            return numerator < 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }

        double date = numerator / denominator;
        return date < 0 ? -1.0 : date;
    }
}
