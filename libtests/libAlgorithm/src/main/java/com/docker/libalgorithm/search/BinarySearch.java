package com.docker.libalgorithm.search;

/*
 * 二分法查找（Binary Search）也称折半查找，是指当每次查询时，将数据分为前后两部分，再用中值和待搜索的值进行比较，如果搜索的值大于中值，
 * 则使用同样的方式（二分法）向后搜索，反之则向前搜索，直到搜索结束为止。
 *二分法使用的时候需要注意：二分法只适用于有序的数据，也就是说，数据必须是从小到大，或是从大到小排序的。
 */
public class BinarySearch {
    public static void main(String[] args) {
        // 二分法查找
        int[] binaryNums = {1, 6, 15, 18, 27, 50};
        int findValue = 27;
        int binaryResult = binarySearch(binaryNums, 0, binaryNums.length - 1, findValue);
    }

    /**
     * 二分查找，返回该值第一次出现的位置（下标从 0 开始）
     *
     * @param nums      查询数组
     * @param start     开始下标
     * @param end       结束下标
     * @param findValue 要查找的值
     * @return int
     */
    // 个人理解 递归调用有 1.链式递归调用（中间链路中一个return 就结束） 2.依赖递归调用(使用递归函数的返回值)
     private static int binarySearch(int[] nums, int start, int end, int findValue) {
        if (start <= end) {
            // 中间位置
            int middle = (start + end) / 2;
            // 中间的值
            int middleValue = nums[middle];
            if (findValue == middleValue) {
                // 等于中值直接返回
                return middle;
            } else if (findValue < middleValue) {
                // 小于中值，在中值之前的数据中查找
                return binarySearch(nums, start, middle - 1, findValue); //递归进去了
            } else {
                // 大于中值，在中值之后的数据中查找
                return binarySearch(nums, middle + 1, end, findValue);//递归进去了，不是需要每次递归的返回值的运算的那种，是个链式的递归调用。
            }
        }
        return -1;
    }
}
