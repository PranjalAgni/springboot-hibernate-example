package com.dogecoin.transcript;

public class InvoiceCorrection {
    public static int getMinimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int levenshteinCorrection(String predictedInvoice, String currentInvoice) {
        int predictedLen = predictedInvoice.length();
        int currentLen = currentInvoice.length();

        if (predictedLen == 0) return currentLen;
        if (currentLen == 0) return predictedLen;

        int[][] wordDistanceDP = new int[predictedLen + 1][currentLen + 1];

        for (int row = 0; row <= predictedLen; row++) wordDistanceDP[row][0] = row;

        for (int col = 0; col <= currentLen; col++) wordDistanceDP[0][col] = col;

        for (int row = 1; row <= predictedLen; row++) {
            char predictedChar = predictedInvoice.charAt(row - 1);
            for (int col = 1; col <= currentLen; col++) {
                char currentChar = currentInvoice.charAt(col - 1);
                int currentCost = (predictedChar == currentChar) ? 0 : 1;
                int insertionCost = wordDistanceDP[row][col - 1] + 1;
                int deletionCost = wordDistanceDP[row - 1][col] + 1;
                int replaceCost = wordDistanceDP[row - 1][col - 1] + currentCost;
                wordDistanceDP[row][col] = getMinimum(insertionCost, deletionCost, replaceCost);
            }
        }

        return wordDistanceDP[predictedLen][currentLen];

    }

    public static double jaroDistanceCorrection(String predictedInvoice, String correctInvoice) {
        int predictedLen = predictedInvoice.length();
        int correctLen = correctInvoice.length();

        if (predictedLen == 0 || correctLen == 0) return 0;

        int maxDistance = (int) (Math.floor(Math.max(predictedLen, correctLen) / 2) - 1);
        int match = 0;
        int[] predictedInvoiceHashMap = new int[predictedLen];
        int[] correctInvoiceHashMap = new int[correctLen];

        for (int idx = 0; idx < predictedLen; idx++) {
            for (int jdx = Math.max(0, idx - maxDistance); jdx < Math.min(correctLen, idx + 1 + maxDistance); jdx++) {
                if (predictedInvoice.charAt(idx) == correctInvoice.charAt(jdx) && correctInvoiceHashMap[jdx] == 0) {
                    predictedInvoiceHashMap[idx] = 1;
                    correctInvoiceHashMap[jdx] = 1;
                    match += 1;
                    break;
                }
            }
        }

        // check if nothing is matched
        if (match == 0) return 0;

        int numTransposition = 0;
        int correctIdx = 0;
        for (int idx = 0; idx < predictedLen; idx++) {
            if (predictedInvoiceHashMap[idx] == 1) {
                while (correctInvoiceHashMap[correctIdx] == 0) {
                    correctIdx += 1;
                }

                if (predictedInvoice.charAt(idx) != correctInvoice.charAt(correctIdx)) {
                    numTransposition += 1;
                }

                correctIdx += 1;
            }
        }

        double transposition = numTransposition / 2;

        double jaroScore = ((match / predictedLen) + (match / correctLen) + ((match - transposition) / match)) / 3;
        return jaroScore;

    }

}
