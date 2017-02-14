#include <stdio.h>
#include <math.h>

double computeMedian(int* nums, int numsSize) {
    if (numsSize % 2 == 0)
        return (nums[numsSize / 2 - 1] + nums[numsSize / 2]) / 2.0;
    return nums[numsSize / 2];
}

double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    if (nums1Size == 0)
        return computeMedian(nums2, nums2Size);
    if (nums2Size == 0)
        return computeMedian(nums1, nums1Size);
    if (nums1Size == 1 && nums2Size == 1)
        return (nums1[0] + nums2[0]) / 2.0;
    
    // Make sure `nums1` is shorter
    if (nums2Size < nums1Size) {
        int temp = nums1Size;
        nums1Size = nums2Size;
        nums2Size = temp;
        int* temp_arr = nums1;
        nums1 = nums2;
        nums2 = temp_arr;
    }

    int i, j;
    int i_min = 0, i_max = nums1Size;
    int total_len = nums1Size + nums2Size;
    int half_len = (total_len + 1) / 2;
    while (1) {
        i = (i_min + i_max) / 2;
        j = half_len - i;
        if (i > 0 && nums1[i-1] > nums2[j]) {
            i_max = i - 1;
        }
        else if (i < nums1Size && nums2[j-1] > nums1[i]) {
            i_min = i + 1;
        }
        else { // Median found
            int max_left;
            if (i == 0)
                max_left = nums2[j-1];
            else if (j == 0)
                max_left = nums1[i-1];
            else
                max_left = nums1[i-1] > nums2[j-1] ? nums1[i-1] : nums2[j-1];
            
            if (total_len % 2 == 1) 
                return max_left; // max_left == min_right

            int min_right;
            if (i == nums1Size)
                min_right = nums2[j];
            else if (j == nums2Size)
                min_right = nums1[i];
            else
                min_right = nums1[i] < nums2[j] ? nums1[i] : nums2[j];
            return (max_left + min_right) / 2.0;
        }
    }
}

int main() {
    int a[2] = {1, 3}, b[1] = {2};
    printf("%.1f\n", findMedianSortedArrays(a, 2, b, 1)); // 2
    int c[2] = {1, 2}, d[2] = {3, 4};
    printf("%.1f\n", findMedianSortedArrays(c, 2, d, 2)); // 2.5
    int e[0] = {}, f[2] = {1, 2};
    printf("%.1f\n", findMedianSortedArrays(e, 0, f, 2)); // 1.5
    int g[1] = {1}, h[4] = {2, 3, 4, 5};
    printf("%.1f\n", findMedianSortedArrays(g, 1, h, 4)); // 3
}
