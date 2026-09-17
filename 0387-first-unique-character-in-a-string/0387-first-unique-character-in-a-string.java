class Solution {
    public int firstUniqChar(String s) {
       int[] freq = new int[26];
       Queue<Integer> q = new LinkedList<>();

       for(int i = 0; i< s.length();i++){
        freq[s.charAt(i) - 'a']++;
        q.offer(i);
       }
       while(!q.isEmpty()){
        int index = q.poll();

        if (freq[s.charAt(index) - 'a'] == 1) {
            return index;
        }
       }
       return -1;
    }
}