package com.surver.web.utils;

import com.surver.web.enums.FoodEnum;
import com.surver.web.enums.RateEnum;
import com.surver.web.models.SurveyDTO;
import com.surver.web.models.SurveyStats;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public interface Operations {

    static SurveyStats getSurveyStats(LinkedList<SurveyDTO> list) {

        SurveyStats stats = new SurveyStats();
        stats.setTotalSurveys(list.size());
        stats.setOldAge(calculateAge(getDate(list, true)) + "");
        stats.setAvarageAge(calculateAge(getAverageDateOfBirth(list)) + "");
        stats.setYoungAge(calculateAge(getDate(list, false)) + "");

        stats.setPizzaLovers(getPercentageOfFoodLovers(list, FoodEnum.PIZZA.name()));
        stats.setPastaLovers(getPercentageOfFoodLovers(list, FoodEnum.PASTA.name()));
        stats.setPapAndWorsLovers(getPercentageOfFoodLovers(list, FoodEnum.PAP_AND_WORS.name()));

        stats.setMovieLovers(getMoviePercentageOfStronglyAgreeAndAgree(list));
        stats.setRadioLovers(getRadioPercentageOfStronglyAgreeAndAgree(list));
        stats.setEatOutLovers(getEATOUTAverageMoviePercentage(list));
        stats.setTvLovers(getTVPercentageOfStronglyAgreeAndAgreeIn(list));

        return stats;
    }

    public static String getDate(LinkedList<SurveyDTO> list, boolean findMax) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        Optional<String> date = findMax ?
                list.stream().map(SurveyDTO::getDateOfBirth).max(Comparator.naturalOrder()) :
                list.stream().map(SurveyDTO::getDateOfBirth).min(Comparator.naturalOrder());

        String myDate = date.map(d -> formatter.format(LocalDate.parse(d)))
                .orElse("No dates found.");

        System.out.println("*");
        System.out.println("*");
        System.out.println(myDate);
        System.out.println("*");
        System.out.println("*");
        return myDate;
    }

    public static String getAverageDateOfBirth(LinkedList<SurveyDTO> list) {
        long totalDays = 0;

        // Calculate the total days since epoch for all dates of birth
        for (SurveyDTO survey : list) {
            LocalDate dateOfBirth = LocalDate.parse(survey.getDateOfBirth());
            totalDays += dateOfBirth.toEpochDay();
        }

        int totalEntries = list.size();
        if (totalEntries < 1) {
            return "No Average found";
        }

        // Calculate the average days since epoch
        long averageDays = totalDays / totalEntries;

        // Convert average days back to a LocalDate object
        LocalDate averageDate = LocalDate.ofEpochDay(averageDays);

        // Format the average date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return formatter.format(averageDate);
    }

    public static double getMoviePercentageOfStronglyAgreeAndAgree(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalStronglyAgreeAndAgree = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getLikeMovies();
            if (RateEnum.STRONGLY_AGREE.name().equalsIgnoreCase(response) || RateEnum.AGGRE.name().equalsIgnoreCase(response)) {
                totalStronglyAgreeAndAgree++;
            }
        }

        return (double) totalStronglyAgreeAndAgree / totalSurveyed * 100;
    }

    public static double getRadioPercentageOfStronglyAgreeAndAgree(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalStronglyAgreeAndAgree = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getListenToRadio();
            if (RateEnum.STRONGLY_AGREE.name().equalsIgnoreCase(response) || RateEnum.AGGRE.name().equalsIgnoreCase(response)) {
                totalStronglyAgreeAndAgree++;
            }
        }

        return (double) totalStronglyAgreeAndAgree / totalSurveyed * 100;
    }

    public static int calculateAge(String dateOfBirthString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(dateOfBirth, currentDate);

        return period.getYears();
    }

    public static double getEATOUTAverageMoviePercentage(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalStronglyAgreeAndAgree = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getEatOut();
            if ("STRONGLY_AGREE".equalsIgnoreCase(response) || "AGREE".equalsIgnoreCase(response)) {
                totalStronglyAgreeAndAgree++;
            }
        }

        return (double) totalStronglyAgreeAndAgree / totalSurveyed * 100;
    }

    public static double getTVPercentageOfStronglyAgreeAndAgreeIn(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalStronglyAgreeAndAgree = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getWatchTV();
            if ("STRONGLY_AGREE".equalsIgnoreCase(response) || "AGREE".equalsIgnoreCase(response)) {
                totalStronglyAgreeAndAgree++;
            }
        }

        return (double) totalStronglyAgreeAndAgree / totalSurveyed * 100;
    }


    public static double getPercentageOfFoodLovers(LinkedList<SurveyDTO> list, String type) {
        long pizzaLoversCount = list.stream()
                .filter(dto -> type.equalsIgnoreCase(dto.getFavouriteFood()))
                .count();

        // Calculate the total number of entries
        int totalEntries = list.size();

        // Calculate the percentage
        double percentage = (double) pizzaLoversCount / totalEntries * 100;

        return percentage;
    }

    //  ***********************************  other rates **********************
    public static double getPercentageOfNeutral(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalNeutral = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getLikeMovies();
            if (RateEnum.NEUTRAL.name().equalsIgnoreCase(response)) {
                totalNeutral++;
            }
        }

        return (double) totalNeutral / totalSurveyed * 100;
    }

    public static double getPercentageOfDisagreeAndStronglyDisagree(LinkedList<SurveyDTO> list) {
        int totalSurveyed = list.size();
        int totalDisagreeAndStronglyDisagree = 0;

        for (SurveyDTO survey : list) {
            String response = survey.getLikeMovies();
            if (RateEnum.DISAGREE.name().equalsIgnoreCase(response) || RateEnum.STRONGLY_DISAGREE.name().equalsIgnoreCase(response)) {
                totalDisagreeAndStronglyDisagree++;
            }
        }

        return (double) totalDisagreeAndStronglyDisagree / totalSurveyed * 100;
    }


}