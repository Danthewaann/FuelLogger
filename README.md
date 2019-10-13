# FuelLogger
Command line interface program that manages fuel transactions for a single vehicle

## Java version
openjdk version "1.8.0_222"  
OpenJDK Runtime Environment (build1.8.0_222-8u222-b10-1ubuntu1~18.04.1-b10)  
OpenJDK 64-Bit Server VM (build 25.222-b10, mixed mode)  

## How to build
Run the following from the `FuelLogger/` directroy:
1. Run `javac src/fuellogger/*.java -d classes/`
2. Run `jar cvfm obj/FuelLogger.jar META-INF/MANIFEST.MF -C classes/ .`
3. The `FuelLogger.jar` should now be available under `obj/`
4. Run the app with `java -jar obj/FuelLogger.jar`

The program allows you to add a new fill-up, where you provide info like:
1. Date of fill-up
2. Current car mileage
3. Amount of fuel purchased and;
4. Cost per litre of fuel

The program also generates different types of reports of fuel transactions made between two supplied dates:
1. Reports fuel quantity bought between two dates
2. Reports total cost of fuel bought between two dates and;
3. Reports a list of transactions made between two dates

This was a project I did for an assignment at university, and I thought I might as well share it here
