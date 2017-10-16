

/**
 * Created by toan on 15/03/17.
 */
public class ItemFlight extends Item {


    public ItemFlight() {
        super();

    }

    public String setTimeFormat(String s) {
        String r = s.substring(0, s.length() - 2) + "." + s.substring(s.length() - 2);
        return r;
    }

//year,Month,DayofMonth,DayOfWeek,DepTime,CRSDepTime,ArrTime,CRSArrTime,UniqueCarrier,FlightNum,
//TailNum,ActualElapsedTime,CRSElapsedTime,AirTime,ArrDelay,DepDelay,Origin,Dest,Distance,TaxiIn,
//TaxiOut,Cancelled,CancellationCode,Diverted,CarrierDelay,WeatherDelay,NASDelay,SecurityDelay,LateAircraftDelay

    public void cutRawDescription(String partOfDescription[]) throws Exception {
        try {
            this.rawDescription.put("DayOfWeek", partOfDescription[3]);
        } catch (Exception e) {
            this.rawDescription.put("DayOfWeek", "null");
        }
        try {
            this.rawDescription.put("DepTime", this.setTimeFormat(partOfDescription[4]));//Change to a time
        } catch (Exception e) {
            this.rawDescription.put("DepTime", "null");
        }
        try {
            this.rawDescription.put("ArrTime", this.setTimeFormat(partOfDescription[6]));//Change to a time
        } catch (Exception e) {
            this.rawDescription.put("ArrTime", "null");
        }
        try {
            this.rawDescription.put("AirTime", partOfDescription[13]);
        } catch (Exception e) {
            this.rawDescription.put("AirTime", "null");
        }
        try {
            this.rawDescription.put("ArrDelay", partOfDescription[14]);
        } catch (Exception e) {
            this.rawDescription.put("ArrDelay", "null");
        }
        try {
            this.rawDescription.put("DepDelay", partOfDescription[15]);
        } catch (Exception e) {
            this.rawDescription.put("DepDelay", "null");
        }
        try {
            this.rawDescription.put("Distance", partOfDescription[18]);
        } catch (Exception e) {
            this.rawDescription.put("Distance", "null");
        }
        try {
            this.rawDescription.put("Month", partOfDescription[1]);
        } catch (Exception e) {
            this.rawDescription.put("Month", "null");
        }
        try {
            this.rawDescription.put("DayOfMonth", partOfDescription[2]);
        } catch (Exception e) {
            this.rawDescription.put("DayOfMonth", "null");
        }
        try {
            this.rawDescription.put("TaxiIn", partOfDescription[19]);
        } catch (Exception e) {
            this.rawDescription.put("TaxiIn", "null");
        }
        try {
            this.rawDescription.put("TaxiOut", partOfDescription[20]);
        } catch (Exception e) {
            this.rawDescription.put("TaxiOut", "null");
        }
        try {
            this.rawDescription.put("CarrierDelay", partOfDescription[24]);
        } catch (Exception e) {
            this.rawDescription.put("CarrierDelay", "null");
        }
        try {
            this.rawDescription.put("WeatherDelay", partOfDescription[25]);
        } catch (Exception e) {
            this.rawDescription.put("WeatherDelay", "null");
        }
        try {
            this.rawDescription.put("SecurityDelay", partOfDescription[27]);
        } catch (Exception e) {
            this.rawDescription.put("SecurityDelay", "null");
        }
        try {
            this.rawDescription.put("LateAirCraftDelay", partOfDescription[28]);
        } catch (Exception e) {
            this.rawDescription.put("LateAirCraftDelay", "null");
        }
        try {
            this.rawDescription.put("Origin", partOfDescription[16]);
        } catch (Exception e) {
            this.rawDescription.put("Origin", "null");
        }
        try {
            this.rawDescription.put("Dest", partOfDescription[17]);
        } catch (Exception e) {
            this.rawDescription.put("Dest", "null");
        }
    }

    /*private void getValueForAttribute(String[] partOfDescription) {
    }*/

}