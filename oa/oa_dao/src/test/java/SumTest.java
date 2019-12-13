import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class SumTest {
    public int[] twoSum(int[] nums, int target) {
        //创建map集合储存键值对，键为数组元素与target的差，值为数组元素的索引
        Map<Integer, Integer> map = new HashMap<>();
        int[] nums1 = new int[2];
        //相加为target的两数的索引分别为index1,index2
        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int desc = target - nums[i];//当前元素的值与目标值的差
            if (map.containsKey(nums[i])) {
                index1 = map.get(nums[i]);
                index2 = i;
                break;
            } else {
                map.put(desc, i);
            }
        }
        nums1[0] = index1;
        nums1[1] = index2;
        return nums1;
    }

    public static void main(String[] args) {


    }

    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            }
            ans = Math.max(end - start + 1, ans);
            map.put(c, end + 1);
        }
        return ans;
    }

    public static int findTargetByBinary(int[] num, int target, int height, int low) {
        if (target > num[height] || target < num[low] || height < low) {
            return -1;
        }
        int mid = (height + low) / 2;
        if (num[mid] > target) {
            return findTargetByBinary(num, target, mid - 1, low);
        } else if (num[mid] < target) {
            return findTargetByBinary(num, target, height, mid + 1);
        } else {
            return mid;
        }
    }

    @Test
    public void testJedis() {
//        Jedis jedis = new Jedis();
//        jedis.set("username","zhangsan");
//        String username = jedis.get("username");
//        System.out.println(username);
//        jedis.close();
        //0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        //2.获取连接
        Jedis jedis = jedisPool.getResource();
        //3. 使用
        jedis.set("hehe", "heihei");


        //4. 关闭 归还到连接池中
        jedis.close();
    }

    public int reverse(int x) {
        int rever = 0;
        while (x != 0) {
            int y = x % 10;
            if (rever > Integer.MAX_VALUE / 10 || rever == Integer.MAX_VALUE / 10 && y > 7) {
                return 0;
            } else if (rever < Integer.MIN_VALUE / 10 || rever == Integer.MIN_VALUE / 10 && y < -8) {
                return 0;
            } else {
                x = x / 10;
                rever = rever * 10 + y;
            }
        }
        return rever;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x == 0) {
            return true;
        } else {
            String s = String.valueOf(x);
            int mid = s.length() / 2;
            for (int i = 0; i < mid; i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public int maxArea(int[] height) {
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return maxArea;
    }

    public int trap(int[] height) {
        int area = 0;//存水总面积
        for (int i = 1; i < height.length - 1; i++) {
            int l = i - 1, r = i + 1;//双指针，分别查询元素左右最高的柱子
            int lh = 0, rh = 0;
            while (l >= 0 && r < height.length) {
                //更新元素左右最高的柱子高度
                lh = Math.max(lh, height[l]);
                rh = Math.max(rh, height[r]);
                l--;
                r++;
            }
            //当左右最高柱子都高于当前柱子
            if (lh > height[i] && rh > height[i]) {
                area = area + Math.min(lh, rh) - height[i];
            }
        }
        return area;
    }

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        //去空
        String str1 = str.trim();
        if (str1.length() == 0) {
            return 0;
        }
        int index = 0;
        //判断数字的正负
        int flag = 1;
        char ch = str1.charAt(index);
        if (ch == '+') {
            index++;
        } else if (ch == '-') {
            flag = -1;
            index++;
        }
        //数字部分
        int res = 0;
        for (; index < str1.length(); index++) {
            ch = str1.charAt(index);
            if (ch > '9' || ch < '0') {
                break;
            }
            if (flag > 0 && res > Integer.MAX_VALUE / 10) {
                return Integer.MAX_VALUE;
            }
            if (flag > 0 && res == Integer.MAX_VALUE / 10 && ch - '0' > Integer.MAX_VALUE % 10) {
                return Integer.MAX_VALUE;
            }
            if (flag < 0 && -res < Integer.MIN_VALUE / 10) {
                return Integer.MIN_VALUE;
            }
            if (flag < 0 && -res == Integer.MIN_VALUE / 10 && -(ch - '0') < Integer.MIN_VALUE % 10) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + ch - '0';
        }
        return res * flag;
    }

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (num > 0) {
            num /= 10;
            count++;
        }
        int digit = (int) Math.pow(10, count - 1);
        while (num > 0) {
            String s = toRoman(num / digit, digit);
            sb.append(s);
            num /= 10;
        }
        return sb.toString();
    }

    public String toRoman(int num, int digit) {
        String roman = "";
        if (digit == 1) {
            switch (num) {
                case 0:
                    roman = "";
                    break;
                case 1:
                    roman = "I";
                    break;
                case 2:
                    roman = "II";
                    break;
                case 3:
                    roman = "III";
                    break;
                case 4:
                    roman = "IV";
                    break;
                case 5:
                    roman = "V";
                    break;
                case 6:
                    roman = "VI";
                    break;
                case 7:
                    roman = "VII";
                    break;
                case 8:
                    roman = "VIII";
                    break;
                case 9:
                    roman = "IX";
                    break;
            }
        }
        if (digit == 10) {
            switch (num) {
                case 0:
                    roman = "";
                    break;
                case 1:
                    roman = "X";
                    break;
                case 2:
                    roman = "XX";
                    break;
                case 3:
                    roman = "XXX";
                    break;
                case 4:
                    roman = "XL";
                    break;
                case 5:
                    roman = "L";
                    break;
                case 6:
                    roman = "LX";
                    break;
                case 7:
                    roman = "LXX";
                    break;
                case 8:
                    roman = "LXXX";
                    break;
                case 9:
                    roman = "XC";
                    break;
            }
        }
        if (digit == 100) {
            switch (num) {
                case 0:
                    roman = "";
                    break;
                case 1:
                    roman = "C";
                    break;
                case 2:
                    roman = "CC";
                    break;
                case 3:
                    roman = "CCC";
                    break;
                case 4:
                    roman = "CD";
                    break;
                case 5:
                    roman = "D";
                    break;
                case 6:
                    roman = "DC";
                    break;
                case 7:
                    roman = "DCC";
                    break;
                case 8:
                    roman = "DCCC";
                    break;
                case 9:
                    roman = "CM";
                    break;
            }
        }
        if (digit == 1000) {
            switch (num) {
                case 0:
                    roman = "";
                    break;
                case 1:
                    roman = "M";
                    break;
                case 2:
                    roman = "MM";
                    break;
                case 3:
                    roman = "MMM";
                    break;
            }
        }
        return roman;
    }

    public int romanToInt(String s) {
        int len = s.length();
        int result = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'V' || c == 'L' || c == 'D' || c == 'M') {
                result = result + charToInt(c);
            }
            if (c == 'I') {
                if (i != len - 1 && (s.charAt(i + 1) == 'X' || s.charAt(i + 1) == 'V')) {
                    result = result - charToInt(c);
                } else {
                    result = result + charToInt(c);
                }

            }
            if (c == 'X') {
                if (i != len - 1 && (s.charAt(i + 1) == 'C' || s.charAt(i + 1) == 'L')) {
                    result = result - charToInt(c);
                } else {
                    result = result + charToInt(c);
                }

            }
            if (c == 'C') {
                if (i != len - 1 && (s.charAt(i + 1) == 'M' || s.charAt(i + 1) == 'D')) {
                    result = result - charToInt(c);
                } else {
                    result = result + charToInt(c);
                }

            }
        }
        return result;
    }

    public int charToInt(char c) {
        int result = 0;
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
        switch (c) {
            case 'I':
                result = 1;
                break;
            case 'V':
                result = 5;
                break;
            case 'X':
                result = 10;
                break;
            case 'L':
                result = 50;
                break;
            case 'C':
                result = 100;
                break;
            case 'D':
                result = 500;
                break;
            case 'M':
                result = 1000;
                break;
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                }
            }
        }
        return list;
    }

    public String convert(String s, int numRows) {
        int size = 2 * (numRows - 1);
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder("");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = i % size;
            if (j < numRows) {
                sbs[j].append(c);
            } else {
                sbs[2 * numRows - 2 - j].append(c);
            }
        }
        for (StringBuilder sbb : sbs) {
            sb.append(sbb);
        }
        return sb.toString();
    }

}
