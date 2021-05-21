package com.dogecoin.transcript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceCorrectorRunner {
    public static void jaroRunner(String predictedInvoice, List<String> openInvoiceList) {
        long startTime = System.nanoTime();

        double maxScore = 0;

        List<Double> maxScoreList = new ArrayList<>();

        for (String openInvoice: openInvoiceList) {
            double score = InvoiceCorrection.jaroDistanceCorrection(predictedInvoice, openInvoice);
            maxScoreList.add(score);
            if (score >= maxScore) {
                maxScore = score;
            }
        }

        int idx = 0;
        for(double score: maxScoreList) {
            if (maxScore == score) {
                System.out.println("Resultant Invoice = " + openInvoiceList.get(idx) + " with score = " + maxScore);
            }
            idx += 1;
        }


        long totalTime = System.nanoTime() - startTime;
        System.out.println("Total time =  " + (totalTime / 1_000_000_000.0));

    }

    public static void levenshteinRunner(String predictedInvoice, List<String> openInvoiceList) {
        int minCost = 999;

        List<Integer> minCostList = new ArrayList<>();

        for (String openInvoice: openInvoiceList) {
            int distance = InvoiceCorrection.levenshteinCorrection(predictedInvoice, openInvoice);
            minCostList.add(distance);
            if (distance <= minCost) {
                minCost = distance;
            }
        }


        for (int idx = 0; idx < minCostList.size(); idx++) {
            if (minCost == minCostList.get(idx)) {
                System.out.println("Resultant Invoice = " + openInvoiceList.get(idx) + " with operations = " + minCost);
            }
        }
    }

    public static void main(String[] args) {
        String predictedInvoice = "106474";
        List<String> placeHolderList = Arrays.asList("10474", "2356", "100346", "125908", "160604", "1938233", "1033472", "12630478");
        List<String> openInvoiceList = new ArrayList<>();
        int ll = placeHolderList.size();

        for (int idx = 0; idx < placeHolderList.size(); idx++) {
            int correctIdx = idx % ll;
            openInvoiceList.add(placeHolderList.get(correctIdx));
        }

        long startTime = System.nanoTime();

        jaroRunner(predictedInvoice, openInvoiceList);

//        levenshteinRunner(predictedInvoice, openInvoiceList);
        long totalTime = System.nanoTime() - startTime;
        System.out.println("Total time =  " + (totalTime / 1_000_000_000.0));
    }
}
