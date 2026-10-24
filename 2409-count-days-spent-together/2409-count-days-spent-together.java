class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;

        if (start.compareTo(end) > 0) {
            return 0;
        }

        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        return convert(end, days) - convert(start, days) + 1;
    }

    private int convert(String date, int[] days) {
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }

        return total;
    }
}