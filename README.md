# [Amazing Numbers](https://hyperskill.org/projects/184)

## About

We use numbers every day. But do you know how many different properties they
have? Let's take a look at some of the amazing properties of numbers. After
completing this project, you will write a program that knows how to determine
whether a number is Palindromic or Gapful, or how to distinguish Spy numbers
from others. Numbers can be fascinating.

## Learning Outcomes

The project covers basic operations on integers, as well as with Java
collections. Make a program that indicates the properties of the numbers taken
from the input.

### Stage 1: [Buzz Numbers](https://hyperskill.org/projects/184/stages/931/implement)

#### _Learn about Buzz numbers and include them in your program._

Integers can be even or odd. All numbers that end with 0, 2, 4, 6, or 8 are
even; the rest are odd. However, numbers have many other properties, you will
learn about some of them in this project.

First, let's talk about **Buzz numbers**. They are numbers that are either
divisible by 7 or end with 7. For example, the number 14 is a buzz number,
since it is divisible by 7 without a remainder; the number 17 ends with 7, so
it is also a buzz number. However, the number 75 is not a buzz number, since
it is neither divisible by 7 nor does it end with 7. The number 7 is a buzz
number too.

The task at this stage is to write a program that prints the natural number
parity and determines whether this number is a buzz. The program should print
why this number is a buzz number. Natural numbers are whole numbers starting
from 1.

### Stage 2: [Duck numbers](https://hyperskill.org/projects/184/stages/932/implement)

#### _Learn about Duck numbers and include them in your program._

Your next task is to determine whether a number is a **Duck number**. A Duck
number is a positive number that contains zeroes. For example, 321**0**, 8**
0**5**0**896, 7**0**7**0**9 are Duck numbers. Note that a number with a
leading 0 is not a Duck number. So, numbers like 035 or 0212 are not Duck
numbers. Although, 012**0**3 is a Duck, since it has a trailing 0.

In this stage, we need to simplify the way we display the information. We
already have several properties that we need to show; we are going to add new
features to our program in each stage. From now on, the card should follow
this notation:

```
Properties of {number}
{property}: true/false
{property}: true/false
...
{property}: true/false
```

For this stage, the properties are `even`, `odd`, `buzz` and `duck`. The
property order, indentation, capitalization, and spaces are irrelevant. Format
how you wish.

### Stage 3: [Palindromic numbers](https://hyperskill.org/projects/184/stages/933/implement)

#### _Learn about Palindromic numbers and include them in your program._

In this stage, the program should check whether a number is a
**Palindromic** one. A Palindromic number is symmetrical; in other words, it
stays the same regardless of whether we read it from left or right. For
example, 17371 is a palindromic number. 5 is also a palindromic number. 1234
is not. If read it from right, it becomes 4321. Add this new property to our
program.

In previous stages, the program could process only one number. From now on,
the program should accept a request from a user, analyze and execute it. The
program should print `Enter a request` instead of asking for a natural number.
The program should also continue execution unless a user enters a terminate
command. Let's make it `0` (zero).

The program should welcome users and print the instructions. At this point,
make your program execute two commands. If a user enters a natural number, the
program should indicate the properties of that number. If a user enters zero,
then the program should exit. If a user enters a negative number by mistake,
print an error message.

### Stage 4: [Gapful numbers](https://hyperskill.org/projects/184/stages/934/implement)

#### _Learn about Gapful numbers and include them in your program._

In this stage, we are going to add one more property - **Gapful numbers**. It
is a number that contains at least 3 digits and is divisible by the
concatenation of its first and last digits **without a remainder**. 12 is not
a Gapful number, as it has only two digits. 132 is a Gapful number, as
**1**3**2** % 12 == 0. 7881 is another example of a Gapful number, as
**7**88**1** % 71 == 0.

Until this stage, the program could process only one number at a time. Now, a
user should be able to enter two numbers to obtain the properties of a list of
numbers. Separate the numbers with one space. Space separates the first number
in the list and the length of the list. For example, `100 2`
tells the program to process two numbers: `100` and `101`. `1 100` means that
the program is about to process 100 numbers, starting from `1`. If a user
enters one number, the program should work the same as in the previous stages.
The program should analyze a number and print its properties. As before, if a
user enters a single `0` (zero), terminate the program. Information about each
number should be printed in one line in the following format:

```
140 is even, buzz, duck, gapful
141 is odd, palindromic
```

So, the format is `{number} is {property}, {property}, ... {property}`

### Stage 5: [Spy numbers](https://hyperskill.org/projects/184/stages/935/implement)

#### _Learn about Spy numbers and include them in your program._

In this stage, we will add another property that is called a **Spy number**. A
number is said to be Spy if the sum of all digits is equal to the product of
all digits.

Our program calculates the properties of numbers, and also knows how to
process a list of numbers. But what if we want to find numbers that have a
certain property? For example, in case we want to find ten Buzz numbers
starting from 1000. We need to add a new request feature for this. In
addition, add a new property — Spy numbers. These numbers hide well, so
beware. Your task is to modify the program so that it can search for these
numbers.

### Stage 6: [Sunny and Square numbers](https://hyperskill.org/projects/184/stages/936/implement)

#### _Learn about Sunny and Square numbers and include them in your program._

N is a **sunny** number if N+1 is a **perfect square** number. In mathematics,
a square number or a perfect square is an integer that is the square of an
integer; in other words, it is the product of an integer with itself. For
example, 9 is a square number, since it equals 3<sup>2</sup> and can be
written as 3x3.

Our program can search for Spy and Palindromic numbers. What if we want to
find all even Spy numbers? Or find all odd numbers among Palindromic numbers?
Are there any Palindromics that are also Spies? In this stage, you will add
the ability to search for several properties at once!

What if a user enters the same property twice by mistake? For example, they
want to find all even numbers that are even? Just ignore this duplicate
property. What about two mutually exclusive properties? For example, if a user
wants to find even numbers that are odd. In this case, the program will
initiate the search, but there are no such numbers. We need to make our
program foolproof. If a request contains mutually exclusive properties, the
program should abort this request and warn the user. There are three pairs of
mutually exclusive properties. A request cannot include **Even and Odd**,
**Duck and Spy**, **Sunny and Square** numbers.

### Stage 7: [Jumping numbers](https://hyperskill.org/projects/184/stages/937/implement)

#### _Learn about Jumping numbers and include them in your program._

A number is a **Jumping number** if the adjacent digits inside the number
differ by 1. The difference between 9 and 0 is not considered as 1.
Single-digit numbers are considered Jumping numbers. For example, 78987, and
4343456 are Jumping numbers, but 796 and 89098 are not.

In this stage, we will also remove the limitation on pending properties in a
request. The program knows how to calculate ten properties of numbers, and it
would be strange to limit the query to just two properties. Let's remove this
limitation. Let the program indicate all properties for all numbers in the
request.

### Stage 8: [Happy and Sad numbers](https://hyperskill.org/projects/184/stages/938/implement)

#### _Learn about Happy and Sad numbers and include them in your program._

In number theory, a **happy** number is a number that reaches 1 after a
sequence during which the number is replaced by the sum of each digit squares.
For example, 13 is a happy number, as 1<sup>2</sup> + 3<sup>2</sup>
= 10 which leads to 1<sup>2</sup> + 0<sup>2</sup> = 1. On the other hand, 4 is
not a happy number because the sequence starts with 4<sup>2</sup> = 16, 
1<sup>2</sup> + 6<sup>2</sup> = 37, and finally reaches 2<sup>2</sup> + 
0<sup>2</sup> = 4. This is the number that started the sequence, so the 
process goes on in an infinite cycle. A number that is not happy is called
**Sad** (or **Unhappy**).

Our program is finished. It can indicate many interesting properties of
numbers, it knows how to calculate them. Now, when prompted, a user can have a
list of number properties. To complete the program, let's add an ability to
exclude a property from the search query. If a user puts a **minus** (`-`)
before the property, exclude this property from the search query. For example,
if a user specifies `palindromic -duck`, it means that they are looking for
Palindromic numbers that are not Ducks.