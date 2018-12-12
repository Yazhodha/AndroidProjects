package com.demo.docchanneling.docdemo;

public class Appointments {

    public String customerName;
    public String doctorName;
    public String channelCenterName;
    public String dateOfAppointment;

    public Appointments (){

    }

    public Appointments(String customerName, String doctorName, String channelCenterName, String dateOfAppointment) {
        this.customerName = customerName;
        this.doctorName = doctorName;
        this.channelCenterName = channelCenterName;
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getChannelCenterName() {
        return channelCenterName;
    }

    public void setChannelCenterName(String channelCenterName) {
        this.channelCenterName = channelCenterName;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

}
