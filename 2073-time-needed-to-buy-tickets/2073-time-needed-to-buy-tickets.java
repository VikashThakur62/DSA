class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        int n=tickets.length;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++){
            queue.offer(i);
        }
        while(!queue.isEmpty()){
            time++;
            int front=queue.poll();
            tickets[front]--;
            if(tickets[k]==0){
                return time;
            }
                if(tickets[front]>0){
                    queue.offer(front);
                }
        }
        return time;
    }
}