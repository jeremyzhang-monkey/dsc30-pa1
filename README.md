# DSC30 — Programming Assignment 1

**Author:** Jingyu Zhang (PID: A15509346)
**Course:** DSC30 — Data Structures and Algorithms for Data Science
**Date:** January 4, 2022

---

## Overview

Five independent Java programming challenges covering array manipulation, string processing, computational geometry, categorical encoding, and polynomial calculus.

---

## Files

```
src/
├── HelloWorld.java            # Warm-up: print "Hello World"
└── ProgrammingChallenges.java # Five practice problems
```

---

## Practice Problems

### Practice 1 — Reverse Even Numbers (`reverseEvenNumber`)

Reverses the order of **even numbers only** in an array while leaving odd numbers in their original positions.

```
Input:  [3, 2, 1, 4, 6]
Output: [3, 6, 1, 4, 2]
        ↑        ↑  ↑   ← evens reversed; odds unchanged
```

---

### Practice 2 — Password Strength Checker (`checkPasswordStrength`)

Rates a password against four criteria:

| Criterion | Description |
|-----------|-------------|
| Letters | Contains at least one letter |
| Digits | Contains at least one digit |
| Length | At least 8 characters |
| Special chars | Contains at least one non-letter, non-digit character |

| Score | Rating |
|-------|--------|
| ≥ 3 criteria | `"Strong"` |
| 1 – 2 criteria | `"Weak"` |
| 0 criteria | `"Not Acceptable"` |

---

### Practice 3 — Nearest Neighbor Distance (`findShortestDistance`)

For each point `(x[i], y[i])`, computes the Euclidean distance to its **closest neighbor** in the set.

```
distance(A, B) = √((xA - xB)² + (yA - yB)²)
```

---

### Practice 4 — One-Hot Encoding (`getUnique`, `oneHotEncode`)

**4.1 `getUnique`** — Extracts distinct elements in first-occurrence order, padding remaining slots with `null`.

```
Input:  ["cat", "dog", "cat", "fish"]
Output: ["cat", "dog", "fish", null]
```

**4.2 `oneHotEncode`** — Builds a binary feature matrix where each row is a unique category and each column is one element of the input.

```
Input:  ["cat", "dog", "cat"]

        cat  dog  cat
cat  [  1,   0,   1  ]
dog  [  0,   1,   0  ]
```

---

### Practice 5 — Polynomial Derivative & Stock Intersection (`calculateDerivative`, `getIntersection`)

**5.1 `calculateDerivative`** — Differentiates a polynomial stored as a coefficient array.

```
Convention: polyArr[i] = coefficient of xⁱ
[3, 2, 1]  →  3 + 2x + x²
Derivative  →  [2, 2]  (2 + 2x)
```

**5.2 `getIntersection`** — Finds the date (x-value) at which two quadratic stock-price models have **equal growth rates** (equal first derivatives). Returns `-1.0` if the intersection is before time zero, or `±Infinity` if the derivatives are parallel.

---

## How to Run

### With IntelliJ IDEA
1. Open the project folder in IntelliJ.
2. Right-click `HelloWorld.java` or `ProgrammingChallenges.java` → **Run**.

### With `javac` / `java`
```bash
cd src
javac HelloWorld.java ProgrammingChallenges.java
java HelloWorld
java ProgrammingChallenges
```

---

## Bug Fixes & Improvements (vs. original submission)

| Issue | Fix |
|-------|-----|
| `getUnique` used `==` for String comparison | Changed to `.equals()` |
| `oneHotEncode` used `==` for String comparison | Changed to `.equals()` |
| `reverseEvenNumber` mutated the input array | Now operates on a defensive copy |
| Debug `System.out.println` left in `findShortestDistance` | Removed |
| Debug `System.out.println` left in `getIntersection` | Removed |
| Incomplete / inaccurate Javadoc | Rewritten throughout |
| No `.gitignore` (tracked `.idea/`, `.DS_Store`, `out/`) | Added `.gitignore` |
