10.10.2023:
- Implemented printing available seats and rows
- Calculating total possible profit based on the number of total room seats

11.10.2023:
- generate 2D array containing cinema room rows and seats
- pretty print all cinema rows and seats that are available
- added method to calculate ticket price
- Get input from user on the row and seat, calculate price and book the seat.

12.10.2023
- Added menu with options for:
    - buying tickets
    - printing existing bookings
    - exiting the program

13.10.2023
- Added a few input validations:
    - check if row and/or seat is in available cinema room range
    - check if seat already booked
    - added new option in menu for showing existing cinema statistics regarding:
        - How many tickets were bought as a number
        - How many tickets were bought as a percentage
        - What is the total possible profit based on the number of seats
        - Current amount of profit based on the number of tickets bought

  Completed project

~~TODO: Once I get more familiar with OOP in Java I will refactor this project to make proper use of classes and objects as the current solution is mostly procedural, even if it's wrapped up in a class :).~~

29.12.2023
- Refactored and simplified methods
- Split functionality into different classes.

30.12.2023
- moved fetching desired row/seat from booking method to userInterface class
- switched project to use maven 
- added some unit testing with Junit5 and AssertJ library