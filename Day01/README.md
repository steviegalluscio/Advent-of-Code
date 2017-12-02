# [Day 1](http://adventofcode.com/2017/day/1)
######--- Part One ---
I just used a for loop with an if statement after to check the last digit with the first digit.

#####--- Part Two ---
Because the digits we are comparing are half of the list length apart and not always 1 step ahead I had to treat it like a "circular list". To find the index of a digit halfway around the list from another digit: I took the index of the digit and added half of the list's length then used the modulus operator to get the remainder when divided by the full list length ().
```javascript
(i+halfLength)%length)
```

Part one can also be solved with this code if you switch out half of the list's length for 1.
```javascript
(i+1)%length)
```
