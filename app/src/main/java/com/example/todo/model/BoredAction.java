package com.example.todo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "bored_action")
public class BoredAction {

    @SerializedName("key")
    @ColumnInfo(name = "uuid")
    @PrimaryKey
    @NonNull
    private String key;

    @SerializedName("activity")
    @ColumnInfo(name = "activity")
    private String activity;

    @SerializedName("type")
    @ColumnInfo(name = "type")
    private String type;

    @SerializedName("link")
    @ColumnInfo(name = "link")
    private String link;

    @SerializedName("participants")
    @ColumnInfo(name = "participants")
    private Integer participants;

    @SerializedName("price")
    @ColumnInfo(name = "key")
    private Float price;

    @SerializedName("accessibility")
    @ColumnInfo(name = "accessibility")
    private Float accessibility;


    public String getActivity() {
        return activity; }

    public void setActivity(String activity) {
        this.activity = activity; }

    public String getType() {
        return type; }

    public void setType(String type) {
        this.type = type; }

    public String getLink() {
        return link; }

    public void setLink(String link) {
        this.link = link; }

    public String getKey() {
        return key; }

    public void setKey(String key) {
        this.key = key; }

    public Integer getParticipants() {
        return participants; }

    public void setParticipants(Integer participants) {
        this.participants = participants; }

    public Float getPrice() {
        return price; }

    public void setPrice(Float price) {
        this.price = price; }

    public Float getAccessibility() {
        return accessibility; }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility; }

    public BoredAction(String key, String activity, String type, String link, Integer participants, Float price, Float accessibility) {
        this.key = key;
        this.activity = activity;
        this.type = type;
        this.link = link;
        this.participants = participants;
        this.price = price;
        this.accessibility = accessibility;
    }

    @Override
    public String toString() {
        return "BoredAction{" +
                "uuid='" + key + '\'' +
                ", activity='" + activity + '\'' +
                ", type='" + type + '\'' +
                ", participants=" + participants +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", accessibility=" + accessibility +
                '}';
    }
}
