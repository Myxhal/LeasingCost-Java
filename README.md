# LeasingCost (Java)

## 📝 Description  
A Java program that calculates and compares the total leasing costs of various gas and electric vehicles based on input from a dataset. The program reads vehicle data from a file, creates objects to represent each **Vehicle**, **Lease**, and **Fuel** type, and computes the overall cost of the lease considering factors such as down payment, monthly payments, mileage allowance, and charging costs. This project demonstrates **file I/O**, **object-oriented programming (OOP)**, and **conditional logic** for data analysis and cost comparison.

---

## 🛠️ Technologies  
- Java  
- StdIn / StdOut (for input/output handling)  

---

## 🚀 How to Run  

1. **Ensure you have all required files** in the same directory:  
   - `LeasingCost.java`  
   - `Fuel.java`  
   - `Lease.java`  
   - `Vehicle.java`   
   - `StdIn.java`  
   - `StdOut.java`  
   - `vehicles.txt`  

2. **Compile the program**:  
   ```bash  
   javac LeasingCost.java Fuel.java Lease.java Vehicle.java RecursiveAppend.java StdIn.java StdOut.java
3. **Run the program**:  
   ```bash  
   java LeasingCost vehicles.txt 3.85 11.0 
