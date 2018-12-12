package com.demo.docchanneling.docdemo;

public class ConfirmedBookingInfo {

    private String channelCenterName;
    private String customerName;
    private String dateOfAppointment;
    private String doctorName;
    private String time;

    public ConfirmedBookingInfo() {
    }

    public ConfirmedBookingInfo(String channelCenterName, String customerName, String dateOfAppointment, String doctorName, String time) {
        this.channelCenterName = channelCenterName;
        this.customerName = customerName;
        this.dateOfAppointment = dateOfAppointment;
        this.doctorName = doctorName;
        this.time = time;
    }

    public String getChannelCenterName() {
        return channelCenterName;
    }

    public void setChannelCenterName(String channelCenterName) {
        this.channelCenterName = channelCenterName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
