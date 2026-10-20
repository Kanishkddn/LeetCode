class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            int prevMax = Integer.MIN_VALUE;
            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int drops = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int i = start; i < start + k; i++) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);

                    int next = (i + 1 == start + k)
                            ? start : i + 1;

                    if (nums[i] > nums[next]) {
                        drops++;
                    }
                }

                if (drops > 1 || prevMax > min) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}