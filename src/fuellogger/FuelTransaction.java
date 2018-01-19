package fuellogger;

import becker.util.DateTime;

public class FuelTransaction
{
    public final DateTime date;
    public final int carMileage;
    public final double litresBought;
    public final double costPerLitre;
	
    public FuelTransaction(DateTime date, int carMileage, double litresBought, double costPerLitre) 
    {
        this.date = date;
        this.carMileage = carMileage;
        this.litresBought = litresBought;
        this.costPerLitre = costPerLitre;
    }

    public String getRecord()
    {
        String year = String.valueOf(this.date.getYear());
        String month = String.valueOf(this.date.getMonth());
        String day = String.valueOf(this.date.getDay());
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        return year + month + day + " " + this.carMileage + " " + this.litresBought + " " + this.costPerLitre;
    }
    
    @Override
    public String toString() 
    {
        String year = String.valueOf(this.date.getYear());
        String month = String.valueOf(this.date.getMonth());
        String day = String.valueOf(this.date.getDay());
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }
        return day + "/" + month + "/" + year + " " + this.carMileage + " " + this.litresBought + " " + this.costPerLitre;
    }
}


