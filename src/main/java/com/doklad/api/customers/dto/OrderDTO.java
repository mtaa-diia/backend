package com.doklad.api.customers.dto;

public class OrderDTO {
    private String name;
    private String description;
    private String price;
    private String status;
    private String service;
    private String customer;
    private String employee;
    private String date;
    private String time;
    private String duration;
    private String note;

    public OrderDTO(String name, String description, String price, String status, String service, String customer,
                    String employee, String date, String time, String duration, String note) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.service = service;
        this.customer = customer;
        this.employee = employee;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
