package entity;

import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private HashSet<Integer> num6;
    private int grade;

    public Lotto() {
        this.num6 = new HashSet<>();
        this.makeNums();
    }

    public Lotto(Set<Integer> num6) {
        this.num6 = (HashSet<Integer>) num6;
    }

    static int getRandomNumbers() {
        return (int)(Math.random() * 45) + 1;
    }

    private void makeNums() {
        while (this.num6.size() < 6) {
            this.num6.add(getRandomNumbers());
        }
    }

    public void diffOtherAndThenGetGrade(Lotto other) {
        HashSet<Integer> temp_set = (HashSet<Integer>) this.getNum6().clone();
        temp_set.removeAll(other.getNum6());
        switch (temp_set.size()){
            case 3: this.grade = 5;
                break;
            case 2: this.grade = 4;
                break;
            case 1: this.grade = 3;
                break;
            case 0: this.grade = 1;
                break;
            default: this.grade = 0;
        }
    }

    public HashSet<Integer> getNum6() {
        return num6;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "{" + num6 + ", 당첨 = " + grade + " 등}";
    }
}
