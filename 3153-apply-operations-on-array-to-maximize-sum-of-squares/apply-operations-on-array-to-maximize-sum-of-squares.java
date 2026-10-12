
import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        long[] bitCount = new long[32];

        // Count set bits at each position
        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if (((num >>> bit) & 1) == 1) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Construct k numbers with maximum possible values
        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    x |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + (x * x) % MOD) % MOD;
        }

        return (int) ans;
    }
}
