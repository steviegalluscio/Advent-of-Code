# [Day 2](http://adventofcode.com/2017/day/2)
## Part One
I used split with regex to find new lines and split the input into rows. I then looped through the rows and split the values by tabs so that I could find the min and max of each row. You can use apply to get the min and max values from not just two values but an array as seen in the code below. 
```javascript
max = Math.max.apply(null, values);
min = Math.min.apply(null, values);
```

## Part Two
This is not a very clean solution. It uses three layers deep of nested for loops to to check every value against every value(except itself) in each row with modulus. It does break out of the row once it finds the divisble numbers which makes it a little better.
```javascript
loop2:
for (j = 0; j < items.length; j++){
```
...
```javascript
break loop2;
```


## Additional Notes
Since you can not have multiline strings in JavaScript without modifying the original input, which I consider cheating, I put the input in a HTML element. This is a simple workaround. I later found that [template literals](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Template_literals) work too. 
