package com.info.leavemodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Holiday {
    /*private String success;
    private String status;
    private String pagecreatedate;
    private LinkedHashMap<String, String > resmitatiller;
*/
    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private String fixed;
    private String global;
    private String counties;
    private String launchYear;
    private String type;

}
