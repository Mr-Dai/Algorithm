var twoSum = function(nums, target) {
    var valueToIndex = {};
    for (var i = 0; i < nums.length; i++) {
        var another = valueToIndex[String(target - nums[i])]
        if (another != undefined)
            return [another, i];
        valueToIndex[nums[i]] = i;
    }
};

console.log(twoSum([2, 7, 11, 15], 9));
console.log(twoSum([2, 3, 4], 6));
console.log(twoSum([0, 4, 3, 0], 0));
