public class LeasingCost {
    
    /* 
     * Description:
     *      This method creates an array of Vehicles objects from the given file name
     *      ******IMPORTANT******
     *      This method calls buildVehicle() method, which is STUDENT'S task to complete.
     *
     * Parameters:
     *      filename: the file name containing the vehicles description
     *
     * File format:
     *      the first line of the file containing an integer representing how many vehicles will be described 
     *      in the following lines.
     *      Each line other than the first line describes one vehicle; 
     *      7 or 8 fragments of data in randomly placed order describing the vehicle (7 for gas car, 8 for electric car) in each line. 
     *      Each fragment end with the ';' character
     * 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *      type:gas; name:civic; due:1000; length:3; monthly:295; mile/unit:34; 
     *
     * Returns:
     *      this method returns an array of Vehicle objects 
     */
	public static Vehicle[] createAllVehicles(String filename) {
        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt( StdIn.readLine() );
        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for ( int i = 0; i < numberOfCars; i++ ) {
        	String line = StdIn.readLine();
            vehicles[i] = createVehicle(line);
        }
        return vehicles;
    }

    /* 
     * Description:
     *      This method calculates the CO2 emission given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		co2PerUnit:
     *			kg of CO2 released with the combustion of each gallon of gasoline, or
     *			kg of CO2 are emitted to generate 1 kWh on average
     * Returns:
     *      this method returns a double representing the CO2 emission produced by the car during the lease.
     */
	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){
		double miles = numberOfMonth * mileageAllowance ;
		return miles/usage*co2PerUnit;
    }

    /* 
     * Description:
     *      This method calculates the cost the fuel during the lease given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		fuelPrice: 
     *			price of 1 kWh in cents of a dollar,  if fuelType is GAS, or
     *			price of one gallon of gas in dollars if fuelType is ELECTRIC
     * Returns:
     *      this method returns a double representing the fuel cost during the lease
     */
	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){
    	double miles = numberOfMonth * mileageAllowance;
        double cost = miles/usage * fuelPrice;
    	return cost;
    }

    /* 
     * Description:
     *      This method calculates the cost of lease
     * Parameters:
     *      dueAtSigning: 
     *          the dollar amount due at signing the lease
     *      numberOfMonths: 
     *          lease length in months
     *      monthlyCost: 
     *			cost of the lease per month
     * Returns:
     *      this method returns a double representing the lease cost during the entire lease
     */
	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){
    	
        return dueAtSigning + numberOfMonths*monthlyCost;
    }

    /* 
     * Description:
     *      This method creates and returns an Vehicle object with name, Lease, and Fuel properly populated based on the given string
     *      
     * Parameters:
     *      one string containing 7~8 fragments descrbing the 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *          type:gas.name:civic.due:1000.length:3.monthly:295.mile/unit:34.mileageAllowance:1200.
     *          monthly:238.name:Bolt.due:1000.mileageAllowance:20000.length:15.mile/unit:50.type:electric.charger:500.
     * Returns:
     *      this method creates and returns an Vehicle object with name, Lease, and Fuel properly populated
     *
     * Hint: 
     *      to extract the information of each fragments, we can use 
     *          s.substring(int startIndex, int endIndex) 
     *          s.indexOf(String target)
     *          s.indexOf(char target)
     *
     *      for example, assume we have: 
     *          String s = "description1:ABCD;  description2:EFGH;  description3:IJKL;"
     *      if we want to find the data for description 2, we can first take the substring of the entire string from the letter 'E'
     *      but first we need to find the index of 'E', we can do it by find the index of the string "description2:" and add 13("description2" is 13 chars long)to it
     *      and then we can take the substring from 'E' until the end of the entire string
     *      now use s.substring to exract:
     *          "EFGH;  description3:IJKL;" and let's call it newString for now. 
     *      to extract "EFGH" (the data we want) from newString. we need to find the index of the first ';' which we can simply use newString.indexOf(';')
     *      then we can take the substring of the newString knowing the index of ;
     *      we now have extracted "EFGH" from the String s
     *      the last step is just to convert the datatype based on what type of data we are extracting
     */
	public static Vehicle createVehicle(String description){
        //this creates an object

        //cuts the line to name
        String temp = description.substring(description.indexOf("name:")+5,description.length());
        //Gives name of the car 
        String name = temp.substring(0,temp.indexOf(";")); 
 
        temp = description.substring(description.indexOf("type:")+5,description.length());
        //Gives name of either gas or electric
        String fuelType = temp.substring(0,temp.indexOf(";"));

        Fuel fuel;
        temp = description.substring(description.indexOf("mile/unit:")+10,description.length());
        // System.out.println(temp.substring(description.indexOf(":")+1,description.indexOf(";")));
        double usage = Double.parseDouble(temp.substring(0,temp.indexOf(";"))); 

        if(fuelType.equals("gas")){
            fuel = new Fuel(usage);
        }
        else{
            temp = description.substring(description.indexOf("charger:")+8,description.length());
            double charger = Double.parseDouble(temp.substring(0,temp.indexOf(";"))); 
            fuel = new Fuel(usage, charger);
        }


        //gets due at signing amount
        temp = description.substring(description.indexOf("due:")+4,description.length());
        double due = Double.parseDouble(temp.substring(0,temp.indexOf(";")));
        //gets lease length
        temp = description.substring(description.indexOf("length:")+7,description.length());
        int leaseLength = Integer.parseInt(temp.substring(0,temp.indexOf(";")));
        //gets monthly cost
        temp = description.substring(description.indexOf("monthly:")+8,description.length());
        double monthCost = Double.parseDouble(temp.substring(0,temp.indexOf(";")));
        //gets mileage allowance
        temp = description.substring(description.indexOf("allowance:")+10,description.length());
        int mileageAllowance = Integer.parseInt(temp.substring(0,temp.indexOf(";")));
        Lease lease;
        lease = new Lease(due, leaseLength, monthCost, mileageAllowance);

        //this creates an object
        Vehicle car = new Vehicle(name, fuel, lease);



        
        // COMPLETE THIS METHOD
	    return car;
	}

    /* 
     * Description:
     *      The method calculates and assign CO2Emission, FuelCost, leastCost, of each vehicle.
     *      
     * Parameters:
     *      vehicles: 
     *          an array of Vehicle objects, initilized by getVehicles() method
     *      gasPrice: 
     *          a double representing the price of gas in dollar/gallon
     *      electricityPrice: 
     *			a double representing the price of gas in dollar/kWh
     * Hint:
     *      **********REMEMBER charger cost for electric cars***************
     *      feel free to use:
     *          computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit )
     *          computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice)
     *          computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost)
     */
	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice ){
        // COMPLETE THIS METHOD

           // double co2PerUnit;
        for(int i = 0; i < vehicles.length; i++){
            if (vehicles[i].getFuel().getType() == 1){
                //co2PerUnit = 8.887;
                //vehicles[i].setCO2Emission(co2PerUnit);
                //vehicles[i].setFuelCost(gasPrice);

            vehicles[i].setCO2Emission(computeCO2(vehicles[i].getLease().getLeaseLength(),vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), 8.887 ));
            vehicles[i].setFuelCost(computeFuelCost(vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), gasPrice));
            vehicles[i].setTotalCost(computeLeaseCost(vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(), vehicles[i].getLease().getMonthlyCost()) + vehicles[i].getFuelCost());
            }
            else{
                //co2PerUnit = 0.453;
                //vehicles[i].setCO2Emission(co2PerUnit);
                //vehicles[i].setFuelCost(electricityPrice);

            vehicles[i].setCO2Emission(computeCO2(vehicles[i].getLease().getLeaseLength(),vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), 0.453));
            vehicles[i].setFuelCost(computeFuelCost(vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), electricityPrice));
            vehicles[i].setTotalCost(computeLeaseCost(vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(), vehicles[i].getLease().getMonthlyCost()) + vehicles[i].getFuelCost()+ vehicles[i].getFuel().getCharger());
            }

            //vehicles[i].setCO2Emission(computeCO2(vehicles[i].getLease().getLeaseLength(),vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), vehicles[i].getCO2Emission()));
           //vehicles[i].setFuelCost(computeFuelCost(vehicles[i].getLease().getLeaseLength(), vehicles[i].getFuel().getUsage(), vehicles[i].getLease().getMileageAllowance(), vehicles[i].getFuelCost()));
           // vehicles[i].setTotalCost(computeLeaseCost(vehicles[i].getLease().getDueAtSigning(), vehicles[i].getLease().getLeaseLength(), vehicles[i].getLease().getMonthlyCost()+ vehicles[i].getFuelCost()));

        }

        
    	}


    /**
     * How to compile:
     *     javac LeasingCost.java
     * How to run:         
     *     java LeasingCost vehicles.txt 3.85 11.0
     **/
	public static void main (String[] args) {
        String filename         = args[0];
        double gasPrice 		= Double.parseDouble( args[1] );
		double electricityPrice = Double.parseDouble( args[2] );

		Vehicle[] vehicles = createAllVehicles(filename); 
		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for ( Vehicle v : vehicles ) 
            System.out.println(v.toString());
    }
}
