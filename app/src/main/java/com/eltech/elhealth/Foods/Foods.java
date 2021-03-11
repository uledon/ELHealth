package com.eltech.elhealth.Foods;

public class Foods{
    public static String[] getLoseFatFoods(){
        String[] loseFatFoods = new String[]{
                "Fatty Fish","MCT Oil","Coffee","Eggs",
                "Coconut Oil","Green Tea","Whey protein","Apple Cider Vinegar",
                "Chili peppers","Oolong Tea","Full-Fat Greek Yogurt","Olive Oil"};
        return loseFatFoods;
    }
    public static String getLoseFatFoodsWebSite(){
        return "https://www.healthline.com/nutrition/12-fat-burning-foods#TOC_TITLE_HDR_13";
    }
    public static String[] getBuildMuscleFoods(){
        String[] buildMusclesFoods = new String[]{
                "Eggs","Salmon","Chicken Breast","Greek Yogurt",
                "Tuna","Lean Beef","Shrimp","Soybeans", "Cottage Cheese","Turkey Breast",
                "Tilapia","Beans"
        };
        return buildMusclesFoods;
    }
    public static String getBuildMuscleFoodsWebSite(){
        return "https://www.healthline.com/nutrition/26-muscle-building-foods#TOC_TITLE_HDR_12/";
    }
    public static String[] getImproveEnduranceFoods(){
        String[] improveEnduranceFoods = new String[]{
                "Oatmeal","Cherries","Kale","Milk","Bananas","Chia Seeds", "Walnuts",
                "Sweet potatoes","Wild Salmon","Whey","Beetroot", "Avocado"
        };
        return improveEnduranceFoods;
    }
    public String getImproveEnduranceFoodsWebsite(){
        return "https://www.active.com/nutrition/articles/10-superfoods-for-endurance-athletes/slide-10";
    }
    public static String[] getImproveAthleticSkills(){
        String[] improveAthleticSkills = new String[]{
                "Rice","Oatmeal","Eggs","Chicken","Fish","Green Vegetables","Bananas",
                "Almonds","Raisins","Dark chocolate",
        };
        return improveAthleticSkills;
    }
    public static String getImproveAthleticSkillsWebSite(){
        return "https://www.sport-passion.fr/en/health/10-foods-to-improve-your-athletic-performance.php";
    }
}
