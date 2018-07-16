package model;

import org.json.simple.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    public Item(){}

    public Item(String title, Double price, String url){
        this.title = title;
        this.price = price;
        this.url = url;
    }

    public Item(JSONObject jsonObject){
        this(jsonObject.get("title").toString(),
             Double.parseDouble(formatNumer(jsonObject.get("price").toString())),
             jsonObject.get("url").toString());
    }

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Double price;

    @Column(unique = true)
    private String url;

    public String toString(){
        String string = "";
        string += "Título: "+title;
        string += "\nPreço: "+price;
        string += "\nUrl: "+url;
        return string;
    }

    public static String formatNumer(String number){
        return number.replaceAll("\\D", "").trim();
    }

    public String getUrl(){
        return url;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
