package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 7000;
    public static int bossDamage = 50;
    public static int[] heroesHealth = {1000, 150, 350, 170, 140};//0 - GOLEM 1___LUCKY__2 - BERSERK____3 --- THOR,___MEDIC- 4
    public static int[] heroesDamage = {18, 29, 26, 18, 0};//0 - GOLEM 1___LUCKY__2 - BERSERK____3 --- THOR,___MEDIC- 4
    public static String[] heroesAttackType = {"Golem", "lUCKY", "BERSERK", "THOR", "MEDIC"};

    public static Random randomDamage = new Random();
    public static int bossDamageOthers = 40;
    public static int number = 0;

    public static void main(String[] args) {
        while (isFinished()) {
            printStatus();
            round();
        }
    }

    public static void golemHit() {
        if (heroesHealth[0] > 0 && number != 2) {
            heroesHealth[0] -= bossDamage;
            System.out.println("Голем забирает 1/5 часть урона босса по всем героям и минусует у себя жизнь ");
            if (heroesHealth[0] < 0) {
                heroesHealth[0] = 0;
            }
        }
    }

    public static void bossHit() {
        number = (int) (Math.random() * 3);
        golemHit();
        medikHill();
        luckyHerouse();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[2]) {
                if (number == 2) {
                    System.out.println("босс пропускает один  раунд ");
                    break;
                }
                if (heroesHealth[0] > 0)
                    heroesHealth[i] = Math.max(heroesDamage[i] - bossDamageOthers, 0);
                else
                    heroesHealth[i] = Math.max(heroesDamage[i] - bossDamage, 0);
            }
        }
    }

    public static void luckyHerouse() {
        Random randomLucky = new Random();
        int randomMiss = randomLucky.nextInt(5);
        if (heroesHealth[1] > 0 && bossHealth > 0) {
            if (randomMiss == 1 && heroesHealth[0] > 0) {
                heroesHealth[1] = heroesHealth[1] + bossDamageOthers;
                System.out.println("lucky увернулься  от босса ");
            } else if (randomMiss == 1 && heroesHealth[0] < 0) {
                heroesHealth[1] = heroesHealth[1] + bossDamage;
            }
        }
    }

    public static void berserk() {
        int getRandomDamage = randomDamage.nextInt(bossDamageOthers);
        if (heroesHealth[2] > 0 && bossHealth > 0 && number != 2) {
            heroesHealth[2] = heroesHealth[2] - getRandomDamage;
            heroesDamage[2] += getRandomDamage;
            bossHealth -= getRandomDamage;
            System.out.println("berserk получил урон: " + getRandomDamage);
            System.out.println("berserk увеличил урон на: " + getRandomDamage);
            System.out.println("Данное время урон Berserka составляет: " + heroesDamage[2]);
        }
        if (heroesHealth[2] < 0) {
            heroesHealth[2] = 0;
        }
    }

    public static void medikHill() {
        for (int i = 0; i < heroesHealth.length; i++) {
            int randomKairat = (int) (Math.random() * 100);
            if (heroesHealth[4] < 0) heroesHealth[4] = 0;
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[4] > 0 && !heroesAttackType[i].equals(heroesAttackType[4])) {
                heroesHealth[i] = heroesHealth[i] + randomKairat;
                System.out.println("Медик лечить  :  " + heroesAttackType[i] + " на: " + randomKairat);
                break;

            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                bossHealth = bossHealth - heroesDamage[i];
                if (bossHealth < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                    if (bossHealth < 0) {
                        bossHealth = 0;
                    }
                }
            }
        }
    }

    public static void round() {
        heroesHit();
        bossHit();
        berserk();
    }

    public static void printStatus() {
        System.out.println("_______________________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Golem health: " + heroesHealth[0]);
        System.out.println("Lucky health" + heroesHealth[1]);
        System.out.println("Berserk health: " + heroesHealth[2]);
        System.out.println("Thor health " + heroesHealth[3]);
        System.out.println("Medic health" + heroesHealth[4]);
        System.out.println("");

    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("heroes won");
            return false;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <= 0 && heroesHealth[4] <= 0) {

            System.out.println("boss won");
            return false;
        }
        return true;
    }
}
