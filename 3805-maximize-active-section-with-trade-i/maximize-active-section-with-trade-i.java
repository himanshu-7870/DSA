class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int totalOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        String t = "1" + s + "1";
        java.util.List<Integer> zeroLengths = new java.util.ArrayList<>();

        int i = 0;
        int n = t.length();

        while (i < n) {
            if (t.charAt(i) == '1') {
                while (i < n && t.charAt(i) == '1') {
                    i++;
                }
            } else {
                int start = i;
                while (i < n && t.charAt(i) == '0') {
                    i++;
                }
                zeroLengths.add(i - start);
            }
        }

        int maxDelta = 0;
        for (int j = 0; j < zeroLengths.size() - 1; j++) {
            maxDelta = Math.max(maxDelta, zeroLengths.get(j) + zeroLengths.get(j + 1));
        }

        return totalOnes + maxDelta;
    }
}