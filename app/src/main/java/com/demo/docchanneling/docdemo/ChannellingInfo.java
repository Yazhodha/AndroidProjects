package com.demo.docchanneling.docdemo;


public class ChannellingInfo {

    public ChannellingInfo() {
    }

    public ChannellingInfo(String id, String docName, String channelCenterName, String date, String channelSpec) {
        this.id = id;
        this.docName = docName;
        this.channelCenterName = channelCenterName;
        this.date = date;
        this.channelSpec = channelSpec;
    }

    private String id;

    private String docName;

    private String channelCenterName;

    private String date;

    private String channelSpec;




    public String getChannelSpec() {
        return channelSpec;
    }

    public void setChannelSpec(String channelSpec) {
        this.channelSpec = channelSpec;
    }



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDocName ()
    {
        return docName;
    }

    public void setDocName (String docName)
    {
        this.docName = docName;
    }

    public String getChannelCenterName ()
    {
        return channelCenterName;
    }

    public void setChannelCenterName (String channelCenterName)
    {
        this.channelCenterName = channelCenterName;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", docName = "+docName+", channelCenterName = "+channelCenterName+", date = "+date+"]";
    }
}
