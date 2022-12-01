package adventOfCode.Year2021;

import classes.Energy;

import java.util.ArrayList;

public class Day11 {

    public Integer part1(ArrayList<String> strings) {
        ArrayList<Energy[]> energy = parse(strings);
        AmountOfFlashes flashes = new AmountOfFlashes();
        for (int i = 0; i < 100; i++) {
            increaseByOne(energy);
            boolean flash = flash(energy, flashes);
            while (flash) {
                flash = flash(energy, flashes);
            }
            resetFlash(energy);
        }
        print(energy);
        System.out.println(flashes.f);
        return flashes.f;
    }

    private boolean flash(ArrayList<Energy[]> energy, AmountOfFlashes flashes) {
        for (int i = 0, energySize = energy.size(); i < energySize; i++) {
            Energy[] e = energy.get(i);
            for (int j = 0, eLength = e.length; j < eLength; j++) {
                Energy l = e[j];
                if (l.lightLevel > 9) {
                    l.lightLevel = 0;
                    l.flashed = true;
                    flashes.f++;
                    if (i - 1 >= 0 && !energy.get(i - 1)[j].flashed) {
                        energy.get(i - 1)[j].lightLevel++;
                    }
                    if (i + 1 < e.length && !energy.get(i + 1)[j].flashed) {
                        energy.get(i + 1)[j].lightLevel++;
                    }
                    if (j - 1 >= 0 && !energy.get(i)[j - 1].flashed) {
                        energy.get(i)[j - 1].lightLevel++;
                    }
                    if (j + 1 < e.length && !energy.get(i)[j + 1].flashed) {
                        energy.get(i)[j + 1].lightLevel++;
                    }
                    if (j - 1 >= 0 && i - 1 >= 0 && !energy.get(i - 1)[j - 1].flashed) {
                        energy.get(i - 1)[j - 1].lightLevel++;
                    }
                    if (j + 1 < e.length && i + 1 < e.length && !energy.get(i + 1)[j + 1].flashed) {
                        energy.get(i + 1)[j + 1].lightLevel++;
                    }
                    if (j - 1 >= 0 && i + 1 < e.length && !energy.get(i + 1)[j - 1].flashed) {
                        energy.get(i + 1)[j - 1].lightLevel++;
                    }
                    if (j + 1 < e.length && i - 1 >= 0 && !energy.get(i - 1)[j + 1].flashed) {
                        energy.get(i - 1)[j + 1].lightLevel++;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void increaseByOne(ArrayList<Energy[]> energy) {
        for (Energy[] e : energy) {
            for (Energy l : e) {
                l.lightLevel++;
            }
        }
    }

    private void resetFlash(ArrayList<Energy[]> energy) {
        for (Energy[] e : energy) {
            for (Energy l : e) {
                l.flashed = false;
            }
        }
    }

    private void print(ArrayList<Energy[]> energy) {
        for (Energy[] e : energy) {
            for (Energy l : e) {
                System.out.print(l);
            }
            System.out.println();
        }
        System.out.println();
    }

    private ArrayList<Energy[]> parse(ArrayList<String> strings) {
        ArrayList<Energy[]> arr = new ArrayList<>(10);
        for (String s : strings) {
            Energy[] energy = new Energy[10];
            char[] charArray = s.toCharArray();
            for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
                char c = charArray[i];
                energy[i] = new Energy(c - 48);
            }
            arr.add(energy);
        }

        return arr;
    }

    public Integer part2(ArrayList<String> readFromFile) {
        ArrayList<Energy[]> energy = parse(readFromFile);
        AmountOfFlashes flashes = new AmountOfFlashes();
        int i = 0;
        boolean allFlash = false;
        while (!allFlash) {
            increaseByOne(energy);
            boolean flash = flash(energy, flashes);
            while (flash) {
                flash = flash(energy, flashes);
            }
            allFlash = allFlash(energy);
            resetFlash(energy);
            i++;
        }

        return i ;
    }

    private boolean allFlash(ArrayList<Energy[]> energy) {
        boolean b = true;
        for (Energy[] e : energy) {
            for (Energy l : e) {
                b = b && l.flashed;
            }
        }
        return b;
    }

    private class AmountOfFlashes {
        int f;
    }
}
