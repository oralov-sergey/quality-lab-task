package api;

public class HolidayItem {

    private String date;
    private String type;
    private String type_id;
    private int count;

    public String getDate(){
        return date;
    }

    public void setData(String date){
        this.date = date;
    }

    public String getType(){
        return type;
    }

    public void setType(){
        this.type = type;
    }

    public String getType_id(){
        return type_id;
    }

    public void setType_id(){
        this.type_id = type_id;
    }

    public int getCount(){
        return count;
    }

    public void setCount(){
        this.count = count;
    }

    }



