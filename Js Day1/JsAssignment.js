//1. print in console
// console.log("Hello World")

//2. print name 
// let name="kirthi"
// console.log("My name is",name)

//3.Arithmetic operation
// let n1=10;
// let n2=5;
// console.log("Sum:",n1+n2)
// console.log("Difference:",n1-n2)
// console.log("Product:",n1*n2)
// console.log("Quotient:",n1/n2)

//4.Simple Calculator
// const operator = '+';
// const number1 = 10;
// const number2 = 5;
// let result;
// if (operator=== '+') {
//     result= number1 + number2;
// } else if (operator=== '-') {
//     result= number1 - number2;
// } else if (operator=== '*') {
//     result= number1 * number2;
// } else if (operator === '/') {
//     result = number1 / number2;
// } else {
//     console.log('Invalid operator');
// }
// console.log(`${number1} ${operator} ${number2} = ${result}`);


//5.Odd or Even
// const number = 8;
// if (number % 2 === 0) {
//     console.log(`${number} is even.`);
// } else {
//     console.log(`${number} is odd.`);
// }

//6.Maximum of Three Numbers
// let num1 = 12;
// let num2 = 45;
// let num3 = 30;
// let max;
// if (num1 >= num2 && num1 >= num3) {
//     max= num1;
// } else if (num2 >= num1 && num2 >= num3) {
//     max= num2;
// } else {
//     max= num3;
// }
// console.log(`The maximum of ${num1}, ${num2}, and ${num3} is ${max}`);


//7. Multiplication Table
// const num= 5;
// console.log(`Multiplication Table of ${num}:`);
// for (let i = 1; i <= 10; i++) {
//     console.log(`${num} x ${i} = ${num * i}`);
// }

//8. Factorial
// function factorial(n) {
//     let result = 1;
//     for (let i = 2; i <= n; i++) {
//         result *= i;
//     }
//     return result;
// }
// const number = 5;   
// console.log(`Factorial of ${number} is ${factorial(number)}`);

//9. Fibonacci Series
// function generateFibonacci(n) {
//     const fib=[0, 1]
//     for (let i=2; i < n; i++) {
//         fib[i]=fib[i - 1] + fib[i - 2]
//     }
//     return fib
// }
// const n = 10;
// console.log(`First ${n} numbers in Fibonacci series:`)
// console.log(generateFibonacci(n).join(', '))

//10. Check Prime Number
// function isPrime(n) {
//     if (n<= 1) return false;
//     for (let i= 2; i <= Math.sqrt(n); i++) {
//         if (n % i === 0) return false;
//     }
//     return true;
// }
// const checkNum = 17;
// console.log(`${checkNum} is ${isPrime(checkNum) ? 'a prime' : 'not a prime'} number.`);

//11.  Sum of Digits
// function sumOfDigits(num) {
//     let sum= 0;
//     while (num > 0) {
//         sum +=num % 10;
//         num=Math.floor(num / 10);
//     }
//     return sum;
// }
// const digitNum=12345;
// console.log(`Sum of digits of ${digitNum} is ${sumOfDigits(digitNum)}`);


//12.  Reverse a String
// const str = "hello"
// const reversed = str.split('').reverse().join('')
// console.log(`Reversed string: ${reversed}`)

//13. Palindrome Checker
// function isPalindrome(str) {
//     const reversed= str.split('').reverse().join('');
//     return str=== reversed;
// }
// const word ="madam";
// console.log(`${word} is ${isPalindrome(word) ? "" : "not "}a palindrome.`);


//14.  Find Largest Number in Array
// function findLargest(arr) {
//     return Math.max(...arr);
// }
// const numbers = [10, 45, 23, 89, 5];
// console.log(`Largest number is: ${findLargest(numbers)}`);

//15.  Count Vowels in a String
// function countVowels(str) {
//     const vowels='aeiouAEIOU';
//     let count= 0;
//     for (let char of str) {
//         if (vowels.includes(char)) {
//             count++;
//         }
//     }
//     return count;
// }
// const inputStr = "Hello World";
// console.log(`Number of vowels in "${inputStr}": ${countVowels(inputStr)}`);

//16.  Remove Duplicates from Array
// function removeDuplicates(arr) {
//     return [...new Set(arr)];
// }
// const items = [1, 2, 2, 3, 4, 4, 5];
// console.log(`Array after removing duplicates: ${removeDuplicates(items)}`);
