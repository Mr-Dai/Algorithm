var twoSum = function(nums, target) {
    for (var i = 0; i < nums.length; i++)
        for (var j = i + 1; j < nums.length; j++)
            if (nums[i] + nums[j] == target)
                return [i, j];
};

console.log(twoSum([2, 7, 11, 15], 9));
console.log(twoSum([2, 3, 4], 6));
console.log(twoSum([0, 4, 3, 0], 0));
